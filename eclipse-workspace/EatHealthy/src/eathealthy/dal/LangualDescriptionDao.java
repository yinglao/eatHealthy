package eathealthy.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eathealthy.model.LangualDescription;


public class LangualDescriptionDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static LangualDescriptionDao instance = null;
	protected LangualDescriptionDao() {
		connectionManager = new ConnectionManager();
	}
	public static LangualDescriptionDao getInstance() {
		if(instance == null) {
			instance = new LangualDescriptionDao();
		}
		return instance;
	}

	/**
	 * Save the LangualDescription instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public LangualDescription create(LangualDescription langualDescription) throws SQLException {
		String insertLangualDescription = "INSERT INTO LangualDescription(Factor, Description) VALUES(?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLangualDescription);
			insertStmt.setString(1, langualDescription.getLangualFactor());
			insertStmt.setString(2, langualDescription.getDescription());
			insertStmt.executeUpdate();
			return langualDescription;
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
	 * Update the LangualDescriptionDesc of the LangualDescription instance.
	 * This runs a UPDATE statement.
	 */
	public LangualDescription updateLangualDescription(LangualDescription langualDescription, String newDescription) throws SQLException {
		String updateLangualDescription = "UPDATE LangualDescription SET Description=? WHERE Factor=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLangualDescription);
			updateStmt.setString(1, newDescription);
			updateStmt.setString(2, langualDescription.getLangualFactor());
			updateStmt.executeUpdate();
			
			// Update the langualDescription param before returning to the caller.
			langualDescription.setDescription(newDescription);
			return langualDescription;
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
	 * Delete the LangualDescription instance.
	 * This runs a DELETE statement.
	 */
	public LangualDescription delete(LangualDescription langualDescription) throws SQLException {
		String deleteLangualDescription = "DELETE FROM LangualDescription WHERE Factor=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLangualDescription);
			deleteStmt.setString(1, langualDescription.getLangualFactor());
			deleteStmt.executeUpdate();
			// Return null so the caller can no longer operate on the LangualDescription instance.
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
	 * Get the LangualDescription record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single LangualDescription instance.
	 */
	public LangualDescription getLangualDescriptionByFactor(String langualFactor) throws SQLException {
		String selectLangualDescription = "SELECT * FROM LangualDescription WHERE Factor=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLangualDescription);
			selectStmt.setString(1, langualFactor);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultLangualFactor = results.getString("Factor");
				String description = results.getString("Description");
				LangualDescription langualDescription = new LangualDescription(resultLangualFactor, description);
				return langualDescription;
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
	 * Get the matching LangualDescription records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching LangualDescription.
	 */
	public List<LangualDescription> getLangualDescriptionsByKeywords(String keyword) throws SQLException {
		List<LangualDescription> langualDescriptionList = new ArrayList<LangualDescription>();
		String selectLangualDescription =
			"select * " + 
			"from LangualDescription " + 
			"where Description like ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLangualDescription);
			selectStmt.setString(1, "%" + keyword + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultLangualFactor = results.getString("Factor");
				String description = results.getString("Description");
				LangualDescription langualDescription = new LangualDescription(resultLangualFactor, description);
				langualDescriptionList.add(langualDescription);
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
		return langualDescriptionList;
	}

}
