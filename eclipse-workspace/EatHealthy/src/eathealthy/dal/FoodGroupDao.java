package eathealthy.dal;
import eathealthy.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eathealthy.dal.ConnectionManager;


public class FoodGroupDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static FoodGroupDao instance = null;
	protected FoodGroupDao() {
		connectionManager = new ConnectionManager();
	}
	public static FoodGroupDao getInstance() {
		if(instance == null) {
			instance = new FoodGroupDao();
		}
		return instance;
	}

	/**
	 * Save the FoodGroup instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public FoodGroup create(FoodGroup foodGroup) throws SQLException {
		String insertFoodGroup = "INSERT INTO FoodGroup(FoodGroupId, FoodGroupDesc) VALUES(?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodGroup);
			insertStmt.setInt(1, foodGroup.getFoodGroupId());
			insertStmt.setString(2, foodGroup.getFoodGroupDesc());
			insertStmt.executeUpdate();
			return foodGroup;
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
	 * Update the FoodGroupDesc of the FoodGroup instance.
	 * This runs a UPDATE statement.
	 */
	public FoodGroup updateFoodGroupDesc(FoodGroup foodGroup, String newFoodGroupDesc) throws SQLException {
		String updateFoodGroupDesc = "UPDATE FoodGroup SET FoodGroupDesc=? WHERE FoodGroupId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFoodGroupDesc);
			updateStmt.setString(1, newFoodGroupDesc);
			updateStmt.setInt(2, foodGroup.getFoodGroupId());
			updateStmt.executeUpdate();
			
			// Update the foodGroup param before returning to the caller.
			foodGroup.setFoodGroupDesc(newFoodGroupDesc);
			return foodGroup;
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
	 * Delete the FoodGroup instance.
	 * This runs a DELETE statement.
	 */
	public FoodGroup delete(FoodGroup foodGroup) throws SQLException {
		String deleteFoodGroup = "DELETE FROM FoodGroup WHERE FoodGroupId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFoodGroup);
			deleteStmt.setInt(1, foodGroup.getFoodGroupId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the FoodGroup instance.
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
	 * Get the FoodGroup record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single FoodGroup instance.
	 */
	public FoodGroup getFoodGroupById(int foodGroupId) throws SQLException {
		String selectFoodGroup = "SELECT * FROM FoodGroup WHERE FoodGroupId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodGroup);
			selectStmt.setInt(1, foodGroupId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultFoodGroupId = results.getInt("FoodGroupId");
				String foodGroupDesc = results.getString("FoodGroupDesc");
				FoodGroup foodGroup = new FoodGroup(resultFoodGroupId, foodGroupDesc);
				return foodGroup;
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
	 * Get the matching FoodGroup records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching FoodGroup.
	 */
	public List<FoodGroup> getFoodGroupsByKeywords(String keyword) throws SQLException {
		List<FoodGroup> foodGroups = new ArrayList<FoodGroup>();
		String selectFoodGroup =
			"select * " + 
			"from FoodGroup " + 
			"where FoodGroupDesc like ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodGroup);
			selectStmt.setString(1, "%" + keyword + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				int foodGroupId = results.getInt("FoodGroupId");
				String foodGroupDesc = results.getString("FoodGroupDesc");
				FoodGroup foodGroup = new FoodGroup(foodGroupId, foodGroupDesc);
				foodGroups.add(foodGroup);
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
		return foodGroups;
	}

}
