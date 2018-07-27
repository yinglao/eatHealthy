package eathealthy.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import eathealthy.model.Nutrient;
import eathealthy.model.Food;
import eathealthy.model.NutrientData;


public class NutrientDataDao {
	
	protected ConnectionManager connectionManager;

	private static NutrientDataDao instance = null;
	protected NutrientDataDao() {
		connectionManager = new ConnectionManager();
	}
	public static NutrientDataDao getInstance() {
		if(instance == null) {
			instance = new NutrientDataDao();
		}
		return instance;
	}

	public NutrientData create(NutrientData nutrientData) throws SQLException {
		String insertNutrientData =
			"INSERT INTO NutrientData(FoodId, NutrientId, NutrientValue) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// NutrientData has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertNutrientData,
				Statement.RETURN_GENERATED_KEYS);
			// Note: for the sake of simplicity, just set Picture to null for now.
			insertStmt.setInt(1, nutrientData.getFood().getFoodId());
			insertStmt.setInt(2, nutrientData.getNutrient().getNutrientId());
			insertStmt.setDouble(3, nutrientData.getNutrientValue());

			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int nutrientDataId = -1;
			if(resultKey.next()) {
				nutrientDataId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			nutrientData.setNutrientDataId(nutrientDataId);
			return nutrientData;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Update the content of the NutrientData instance.
	 * This runs a UPDATE statement.
	 */
	public NutrientData updateValue(NutrientData nutrientData, double newValue) throws SQLException {
		String updateNutrientData = "UPDATE NutrientData SET nutrientValue=? WHERE NutrientDataId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateNutrientData);
			updateStmt.setDouble(1, newValue);
			updateStmt.setInt(2, nutrientData.getNutrientDataId());
			updateStmt.executeUpdate();

			// Update the nutrientData param before returning to the caller.
			nutrientData.setNutrientValue(newValue);
			return nutrientData;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the NutrientData instance.
	 * This runs a DELETE statement.
	 */
	public NutrientData delete(NutrientData nutrientData) throws SQLException {
		// Note: BlogComments has a fk constraint on NutrientData with the reference option
		// ON DELETE CASCADE. So this delete operation will delete all the referencing
		// BlogComments.
		String deleteNutrientData = "DELETE FROM NutrientData WHERE NutrientDataId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNutrientData);
			deleteStmt.setInt(1, nutrientData.getNutrientDataId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the NutrientData instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	/**
	 * Get the NutrientData record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single NutrientData instance.
	 * Note that we use NutrientDataDao to retrieve the referenced NutrientData instance.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the NutrientData, NutrientData tables and then build each object.
	 */
	public NutrientData getNutrientDataById(int nutrientDataId) throws SQLException {
		String selectNutrientData =
			"SELECT * " +
			"FROM NutrientData " +
			"WHERE NutrientDataId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrientData);
			selectStmt.setInt(1, nutrientDataId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultNutrientDataId = results.getInt("NutrientDataId");
				double nutrientValue = results.getDouble("NutrientValue");
				int foodId = results.getInt("FoodId");
				int nutrientId = results.getInt("NutrientId");
				NutrientDao nutrientDao = NutrientDao.getInstance();
				FoodDao foodDao = FoodDao.getInstance();
				Nutrient nutrient = nutrientDao.getNutrientById(nutrientId);
				Food food = foodDao.getFoodById(foodId);
				
				NutrientData nutrientData = new NutrientData(resultNutrientDataId, nutrientValue, food, nutrient);
				return nutrientData;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public NutrientData getNutrientDataByFoodAndNutrient(Food food, Nutrient nutrient) throws SQLException {
		String selectNutrientData =
			"SELECT * " +
			"FROM NutrientData " +
			"WHERE FoodId=? " + "AND " + "NutrientId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrientData);
			selectStmt.setInt(1, food.getFoodId());
			selectStmt.setInt(2, nutrient.getNutrientId());
			results = selectStmt.executeQuery();
			if(results.next()) {
				int nutrientDataId = results.getInt("NutrientDataId");
				double nutrientValue = results.getDouble("NutrientValue");
				int resultFoodId = results.getInt("FoodId");
				int resultNutrientId = results.getInt("NutrientId");
				NutrientDao nutrientDao = NutrientDao.getInstance();
				FoodDao foodDao = FoodDao.getInstance();
				Nutrient resultNutrient = nutrientDao.getNutrientById(resultNutrientId);
				Food resultFood = foodDao.getFoodById(resultFoodId);
				
				NutrientData nutrientData = new NutrientData(nutrientDataId, nutrientValue, resultFood, resultNutrient);
				return nutrientData;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Get the all the NutrientData for a user.
	 */
	public List<NutrientData> getNutrientDataByFood(Food food) throws SQLException {
		List<NutrientData> nutrientDataList = new ArrayList<NutrientData>();
		String selectNutrientData =
			"SELECT * " +
			"FROM NutrientData " +
			"WHERE FoodId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrientData);
			selectStmt.setInt(1, food.getFoodId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int nutrientDataId = results.getInt("NutrientDataId");
				double nutrientValue = results.getDouble("NutrientValue");
				int resultFoodId = results.getInt("FoodId");
				int nutrientId = results.getInt("NutrientId");
				NutrientDao nutrientDao = NutrientDao.getInstance();
				FoodDao foodDao = FoodDao.getInstance();
				Nutrient nutrient = nutrientDao.getNutrientById(nutrientId);
				Food resultFood = foodDao.getFoodById(resultFoodId);
				
				NutrientData nutrientData = new NutrientData(nutrientDataId, nutrientValue, resultFood, nutrient);
				nutrientDataList.add(nutrientData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return nutrientDataList;
	}		

	//This function takes a long time to run due to the large amount of food.
	public List<NutrientData> getNutrientDataByNutrient(Nutrient nutrient) throws SQLException {
		List<NutrientData> nutrientDataList = new ArrayList<NutrientData>();
		String selectNutrientData =
			"SELECT * " +
			"FROM NutrientData " +
			"WHERE NutrientId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrientData);
			selectStmt.setInt(1, nutrient.getNutrientId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int nutrientDataId = results.getInt("NutrientDataId");
				double nutrientValue = results.getDouble("NutrientValue");
				int foodId = results.getInt("FoodId");
				int resultNutrientId = results.getInt("NutrientId");
				NutrientDao nutrientDao = NutrientDao.getInstance();
				FoodDao foodDao = FoodDao.getInstance();
				Nutrient resultNutrient = nutrientDao.getNutrientById(resultNutrientId);
				Food food = foodDao.getFoodById(foodId);
				
				NutrientData nutrientData = new NutrientData(nutrientDataId, nutrientValue, food, resultNutrient);
				nutrientDataList.add(nutrientData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return nutrientDataList;
	}		
}
