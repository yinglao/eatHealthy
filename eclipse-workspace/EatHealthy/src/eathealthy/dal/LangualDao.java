package eathealthy.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eathealthy.model.Food;
import eathealthy.model.LangualDescription;
import eathealthy.model.Langual;

public class LangualDao {
	protected ConnectionManager connectionManager;

	private static LangualDao instance = null;
	protected LangualDao() {
		connectionManager = new ConnectionManager();
	}
	public static LangualDao getInstance() {
		if(instance == null) {
			instance = new LangualDao();
		}
		return instance;
	}

	public Langual create(Langual langual) throws SQLException {
		String insertLangual =
			"INSERT INTO Langual(FoodId, Factor) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Langual has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertLangual,
				Statement.RETURN_GENERATED_KEYS);
			// Note: for the sake of simplicity, just set Picture to null for now.
			insertStmt.setInt(1, langual.getFood().getFoodId());
			insertStmt.setString(2, langual.getLangualDescription().getLangualFactor());

			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int langualId = -1;
			if(resultKey.next()) {
				langualId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			langual.setLangualId(langualId);
			return langual;
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
	 * Delete the Langual instance.
	 * This runs a DELETE statement.
	 */
	public Langual delete(Langual langual) throws SQLException {
		// Note: BlogComments has a fk constraint on Langual with the reference option
		// ON DELETE CASCADE. So this delete operation will delete all the referencing
		// BlogComments.
		String deleteLangual = "DELETE FROM Langual WHERE LangualId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLangual);
			deleteStmt.setInt(1, langual.getLangualId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Langual instance.
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
	 * Get the Langual record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Langual instance.
	 * Note that we use LangualDao to retrieve the referenced Langual instance.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the Langual, Langual tables and then build each object.
	 */
	public Langual getLangualById(int langualId) throws SQLException {
		String selectLangual =
			"SELECT * " +
			"FROM Langual " +
			"WHERE LangualId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLangual);
			selectStmt.setInt(1, langualId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultLangualId = results.getInt("LangualId");
				int foodId = results.getInt("FoodId");
				String langualFactor = results.getString("Factor");
				FoodDao foodDao = FoodDao.getInstance();
				Food food = foodDao.getFoodById(foodId);
				LangualDescriptionDao langualDescriptionDao = LangualDescriptionDao.getInstance();
				LangualDescription langualDescription = langualDescriptionDao.getLangualDescriptionByFactor(langualFactor);
				
				Langual langual = new Langual(resultLangualId, food, langualDescription);
				return langual;
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
	

	public List<Langual> getLangualByFood(Food food) throws SQLException {
		List<Langual> langualList = new ArrayList<Langual>();
		String selectLangual =
			"SELECT * " +
			"FROM Langual " +
			"WHERE FoodId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLangual);
			selectStmt.setInt(1, food.getFoodId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int langualId = results.getInt("LangualId");
				int foodId = results.getInt("FoodId");
				String langualFactor = results.getString("Factor");
				FoodDao foodDao = FoodDao.getInstance();
				Food resultFood = foodDao.getFoodById(foodId);
				LangualDescriptionDao langualDescriptionDao = LangualDescriptionDao.getInstance();
				LangualDescription langualDescription = langualDescriptionDao.getLangualDescriptionByFactor(langualFactor);
				
				Langual langual = new Langual(langualId, resultFood, langualDescription);
				langualList.add(langual);
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
		return langualList;
	}		

	
}
