package eathealthy.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eathealthy.model.Weight;
import eathealthy.model.Food;

public class WeightDao {
	protected ConnectionManager connectionManager;

	private static WeightDao instance = null;
	protected WeightDao() {
		connectionManager = new ConnectionManager();
	}
	public static WeightDao getInstance() {
		if(instance == null) {
			instance = new WeightDao();
		}
		return instance;
	}

	public Weight create(Weight weight) throws SQLException {
		String insertWeight =
			"INSERT INTO Weight(FoodId, Amount, Unit, WeightInGram) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Weight has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertWeight,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, weight.getFood().getFoodId());
			// Note: for the sake of simplicity, just set Picture to null for now.
			insertStmt.setDouble(2, weight.getAmount());
			insertStmt.setString(3, weight.getUnit());
			insertStmt.setDouble(4, weight.getWeightInGram());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int weightId = -1;
			if(resultKey.next()) {
				weightId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			weight.setWeightId(weightId);
			
			return weight;
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
	 * Update the content of the Weight instance.
	 * This runs a UPDATE statement.
	 */
	public Weight updateWeightInGram(Weight weight, double newWeightInGram) throws SQLException {
		String updateWeight = "UPDATE Weight SET WeightInGram=? WHERE WeightId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateWeight);
			updateStmt.setDouble(1, newWeightInGram);
			updateStmt.setInt(2, weight.getWeightId());
			updateStmt.executeUpdate();

			// Update the weight param before returning to the caller.
			weight.setWeightInGram(newWeightInGram);
			return weight;
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
	 * Delete the Weight instance.
	 * This runs a DELETE statement.
	 */
	public Weight delete(Weight weight) throws SQLException {

		String deleteWeight = "DELETE FROM Weight WHERE WeightId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWeight);
			deleteStmt.setInt(1, weight.getWeightId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Weight instance.
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
	 * Get the Weight record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Weight instance.
	 * Note that we use WeightDao to retrieve the referenced Weight instance.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the Weight, Weight tables and then build each object.
	 */
	public Weight getWeightById(int weightId) throws SQLException {
		String selectWeight =
			"SELECT * " +
			"FROM Weight " +
			"WHERE WeightId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeight);
			selectStmt.setInt(1, weightId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultWeightId = results.getInt("WeightId");
				double amount = results.getDouble("Amount");
				String unit = results.getString("Unit");
				double weightInGram = results.getDouble("WeightInGram");
				int foodId = results.getInt("FoodId");
				FoodDao foodDao = FoodDao.getInstance();
				Food food = foodDao.getFoodById(foodId); 
				
				Weight weight = new Weight(resultWeightId, amount, unit, weightInGram, food);
				return weight;
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
	 * Get the all the Weight for a user.
	 */
	public List<Weight> getWeightByFood(Food food) throws SQLException {
		List<Weight> weightList = new ArrayList<Weight>();
		String selectWeight =
			"SELECT * " +
			"FROM Weight " +
			"WHERE FoodId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeight);
			selectStmt.setInt(1, food.getFoodId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int weightId = results.getInt("WeightId");
				double amount = results.getDouble("Amount");
				String unit = results.getString("Unit");
				double weightInGram = results.getDouble("WeightInGram");
				int foodId = results.getInt("FoodId");
				FoodDao foodDao = FoodDao.getInstance();
				Food resultFood = foodDao.getFoodById(foodId); 
				Weight weight = new Weight(weightId, amount, unit, weightInGram, resultFood);
				weightList.add(weight);
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
		return weightList;
	}	
	
}
