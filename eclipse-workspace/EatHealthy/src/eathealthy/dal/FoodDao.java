package eathealthy.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eathealthy.model.FoodGroup;
import eathealthy.model.Food;

public class FoodDao {
	protected ConnectionManager connectionManager;

	private static FoodDao instance = null;
	protected FoodDao() {
		connectionManager = new ConnectionManager();
	}
	public static FoodDao getInstance() {
		if(instance == null) {
			instance = new FoodDao();
		}
		return instance;
	}

	public Food create(Food food) throws SQLException {
		String insertFood =
			"INSERT INTO Food(FoodId, Description, N_Factor, Pro_Factor, Fat_Factor, CHO_Factor) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			// Food has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertFood,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, food.getFoodId());
			// Note: for the sake of simplicity, just set Picture to null for now.
			insertStmt.setString(2, food.getDescription());
			insertStmt.setDouble(3, food.getNitrogenFactor());
			insertStmt.setDouble(4, food.getProteinFactor());
			insertStmt.setDouble(5, food.getFatFactor());
			insertStmt.setDouble(6, food.getCarbohydrateFactor());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			return food;
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
			
		}
	}

	/**
	 * Update the content of the Food instance.
	 * This runs a UPDATE statement.
	 */
	public Food updateDescription(Food food, String newDescription) throws SQLException {
		String updateFood = "UPDATE Food SET Description=? WHERE FoodId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFood);
			updateStmt.setString(1, newDescription);
			updateStmt.setInt(2, food.getFoodId());
			updateStmt.executeUpdate();

			// Update the food param before returning to the caller.
			food.setDescription(newDescription);
			return food;
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
	 * Delete the Food instance.
	 * This runs a DELETE statement.
	 */
	public Food delete(Food food) throws SQLException {
		// Note: BlogComments has a fk constraint on Food with the reference option
		// ON DELETE CASCADE. So this delete operation will delete all the referencing
		// BlogComments.
		String deleteFood = "DELETE FROM Food WHERE FoodId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFood);
			deleteStmt.setInt(1, food.getFoodId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Food instance.
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
	 * Get the Food record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Food instance.
	 * Note that we use FoodDao to retrieve the referenced Food instance.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the Food, Food tables and then build each object.
	 */
	public Food getFoodById(int foodId) throws SQLException {
		String selectFood =
			"SELECT * " +
			"FROM Food " +
			"WHERE FoodId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFood);
			selectStmt.setInt(1, foodId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultFoodId = results.getInt("FoodId");
				String description = results.getString("Description");
				double nitrogenFactor = results.getDouble("N_Factor");
				double proteinFactor = results.getDouble("Pro_Factor");
				double fatFactor = results.getDouble("Fat_Factor");
				double carbohydrateFactor = results.getDouble("CHO_Factor");
				int foodGroupId = results.getInt("FoodGroupId");
				FoodGroupDao foodGroupDao = FoodGroupDao.getInstance();
				FoodGroup foodGroup = foodGroupDao.getFoodGroupById(foodGroupId); 
				
				Food food = new Food(resultFoodId, description, nitrogenFactor, proteinFactor, fatFactor, carbohydrateFactor, foodGroup);
				return food;
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
	 * Get the all the Food for a user.
	 */
	public List<Food> getFoodByFoodGroup(FoodGroup foodGroup) throws SQLException {
		List<Food> foodList = new ArrayList<Food>();
		String selectFood =
			"SELECT * " +
			"FROM Food " +
			"WHERE FoodGroupId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFood);
			selectStmt.setInt(1, foodGroup.getFoodGroupId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultFoodId = results.getInt("FoodId");
				String description = results.getString("Description");
				double nitrogenFactor = results.getDouble("N_Factor");
				double proteinFactor = results.getDouble("Pro_Factor");
				double fatFactor = results.getDouble("Fat_Factor");
				double carbohydrateFactor = results.getDouble("CHO_Factor");
				int foodGroupId = results.getInt("FoodGroupId");
				FoodGroupDao foodGroupDao = FoodGroupDao.getInstance();
				FoodGroup resultFoodGroup = foodGroupDao.getFoodGroupById(foodGroupId); 
				Food food = new Food(resultFoodId, description, nitrogenFactor, proteinFactor, fatFactor, carbohydrateFactor, resultFoodGroup);
				foodList.add(food);
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
		return foodList;
	}	

}
