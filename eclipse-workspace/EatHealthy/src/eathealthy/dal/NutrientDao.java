package eathealthy.dal;
import eathealthy.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eathealthy.dal.ConnectionManager;


public class NutrientDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static NutrientDao instance = null;
	protected NutrientDao() {
		connectionManager = new ConnectionManager();
	}
	public static NutrientDao getInstance() {
		if(instance == null) {
			instance = new NutrientDao();
		}
		return instance;
	}

	/**
	 * Save the Nutrient instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Nutrient create(Nutrient nutrient) throws SQLException {
		String insertNutrient = "INSERT INTO Nutrient(NutrientId, Unit, TagName, Description) VALUES(?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNutrient);
			insertStmt.setInt(1, nutrient.getNutrientId());
			insertStmt.setString(2, nutrient.getUnits());
			insertStmt.setString(3, nutrient.getTagName());
			insertStmt.setString(4, nutrient.getDescription());
			insertStmt.executeUpdate();
			return nutrient;
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
	 * Update the Description of the Nutrient instance.
	 * This runs a UPDATE statement.
	 */
	public Nutrient updateDescription(Nutrient nutrient, String newDescription) throws SQLException {
		String updateDescription = "UPDATE Nutrient SET Description=? WHERE NutrientId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDescription);
			updateStmt.setString(1, newDescription);
			updateStmt.setInt(2, nutrient.getNutrientId());
			updateStmt.executeUpdate();
			
			// Update the nutrient param before returning to the caller.
			nutrient.setDescription(newDescription);
			return nutrient;
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
	 * Delete the Nutrient instance.
	 * This runs a DELETE statement.
	 */
	public Nutrient delete(Nutrient nutrient) throws SQLException {
		String deleteNutrient = "DELETE FROM Nutrient WHERE NutrientId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNutrient);
			deleteStmt.setInt(1, nutrient.getNutrientId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Nutrient instance.
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
	 * Get the Nutrient record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Nutrient instance.
	 */
	public Nutrient getNutrientById(int nutrientId) throws SQLException {
		String selectNutrient = "SELECT * FROM Nutrient WHERE NutrientId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrient);
			selectStmt.setInt(1, nutrientId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultNutrientId = results.getInt("NutrientId");
				String unit = results.getString("Unit");
				String tagName = results.getString("TagName");
				String description = results.getString("Description");
				Nutrient nutrient = new Nutrient(resultNutrientId, unit, tagName, description);
				return nutrient;
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
	 * Get the matching Nutrient records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Nutrient.
	 */
	public List<Nutrient> getNutrientsByKeywords(String keyword) throws SQLException {
		List<Nutrient> nutrients = new ArrayList<Nutrient>();
		String selectNutrient =
			"select * " + 
			"from Nutrient " + 
			"where Description like ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrient);
			selectStmt.setString(1, "%" + keyword + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultNutrientId = results.getInt("NutrientId");
				String unit = results.getString("Unit");
				String tagName = results.getString("TagName");
				String description = results.getString("Description");
				Nutrient nutrient = new Nutrient(resultNutrientId, unit, tagName, description);
				nutrients.add(nutrient);
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
		return nutrients;
	}

}