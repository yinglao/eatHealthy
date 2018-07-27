package eathealthy.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eathealthy.model.Food;
import eathealthy.model.Nutrient;
import eathealthy.model.Footnote;
import eathealthy.model.Footnote.FootnoteType;

public class FootnoteDao {
	protected ConnectionManager connectionManager;

	private static FootnoteDao instance = null;
	protected FootnoteDao() {
		connectionManager = new ConnectionManager();
	}
	public static FootnoteDao getInstance() {
		if(instance == null) {
			instance = new FootnoteDao();
		}
		return instance;
	}

	public Footnote create(Footnote footnote) throws SQLException {
		String insertFootnote =
			"INSERT INTO Footnote(FoodId, NutrientId, FootnoteType, FootnoteText) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Footnote has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertFootnote,
				Statement.RETURN_GENERATED_KEYS);
			// Note: for the sake of simplicity, just set Picture to null for now.
			insertStmt.setInt(1, footnote.getFood().getFoodId());
			insertStmt.setInt(2, footnote.getNutrient().getNutrientId());
			insertStmt.setString(3, footnote.getFootnoteType().name());
			insertStmt.setString(4, footnote.getText());

			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int footnoteId = -1;
			if(resultKey.next()) {
				footnoteId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			footnote.setFootnoteId(footnoteId);
			return footnote;
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
	 * Update the content of the Footnote instance.
	 * This runs a UPDATE statement.
	 */
	public Footnote updateText(Footnote footnote, String newText) throws SQLException {
		String updateFootnote = "UPDATE Footnote SET FootnoteText=? WHERE FootnoteId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFootnote);
			updateStmt.setString(1, newText);
			updateStmt.setInt(2, footnote.getFootnoteId());
			updateStmt.executeUpdate();

			// Update the footnote param before returning to the caller.
			footnote.setText(newText);
			return footnote;
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
	 * Delete the Footnote instance.
	 * This runs a DELETE statement.
	 */
	public Footnote delete(Footnote footnote) throws SQLException {
		// Note: BlogComments has a fk constraint on Footnote with the reference option
		// ON DELETE CASCADE. So this delete operation will delete all the referencing
		// BlogComments.
		String deleteFootnote = "DELETE FROM Footnote WHERE FootnoteId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFootnote);
			deleteStmt.setInt(1, footnote.getFootnoteId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Footnote instance.
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
	 * Get the Footnote record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Footnote instance.
	 * Note that we use FootnoteDao to retrieve the referenced Footnote instance.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the Footnote, Footnote tables and then build each object.
	 */
	public Footnote getFootnoteById(int footnoteId) throws SQLException {
		String selectFootnote =
			"SELECT * " +
			"FROM Footnote " +
			"WHERE FootnoteId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFootnote);
			selectStmt.setInt(1, footnoteId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultFootnoteId = results.getInt("FootnoteId");
				Footnote.FootnoteType footnoteType = FootnoteType.valueOf(results.getString("FootnoteType"));
				String text = results.getString("FootnoteText");
				int foodId = results.getInt("FoodId");
				int nutrientId = results.getInt("NutrientId");
				NutrientDao nutrientDao = NutrientDao.getInstance();
				FoodDao foodDao = FoodDao.getInstance();
				Nutrient nutrient = nutrientDao.getNutrientById(nutrientId);
				Food food = foodDao.getFoodById(foodId);
				
				Footnote footnote = new Footnote(resultFootnoteId, food, nutrient, footnoteType, text);
				return footnote;
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
	
	public Footnote getFootnoteByFoodAndNutrient(Food food, Nutrient nutrient) throws SQLException {
		String selectFootnote =
			"SELECT * " +
			"FROM Footnote " +
			"WHERE FoodId=? " + "AND " + "NutrientId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFootnote);
			selectStmt.setInt(1, food.getFoodId());
			selectStmt.setInt(2, nutrient.getNutrientId());
			results = selectStmt.executeQuery();
			if(results.next()) {
				int footnoteId = results.getInt("FootnoteId");
				Footnote.FootnoteType footnoteType = FootnoteType.valueOf(results.getString("FootnoteType"));
				String text = results.getString("FootnoteText");
				int foodId = results.getInt("FoodId");
				int nutrientId = results.getInt("NutrientId");
				NutrientDao nutrientDao = NutrientDao.getInstance();
				FoodDao foodDao = FoodDao.getInstance();
				Nutrient resultNutrient = nutrientDao.getNutrientById(nutrientId);
				Food resultFood = foodDao.getFoodById(foodId);
				
				Footnote footnote = new Footnote(footnoteId, resultFood, resultNutrient, footnoteType, text);
				return footnote;
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
	 * Get the all the Footnote for a user.
	 */
	public List<Footnote> getFootnoteByFood(Food food) throws SQLException {
		List<Footnote> footnoteList = new ArrayList<Footnote>();
		String selectFootnote =
			"SELECT * " +
			"FROM Footnote " +
			"WHERE FoodId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFootnote);
			selectStmt.setInt(1, food.getFoodId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int footnoteId = results.getInt("FootnoteId");
				Footnote.FootnoteType footnoteType = FootnoteType.valueOf(results.getString("FootnoteType"));
				String text = results.getString("FootnoteText");
				int foodId = results.getInt("FoodId");
				int nutrientId = results.getInt("NutrientId");
				NutrientDao nutrientDao = NutrientDao.getInstance();
				FoodDao foodDao = FoodDao.getInstance();
				Nutrient nutrient = nutrientDao.getNutrientById(nutrientId);
				Food resultFood = foodDao.getFoodById(foodId);
				
				Footnote footnote = new Footnote(footnoteId, resultFood, nutrient, footnoteType, text);
				footnoteList.add(footnote);
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
		return footnoteList;
	}		

			
}
