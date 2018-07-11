package review.dal;
import review.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class TakeOutRestaurantDao extends RestaurantsDao{
	private static TakeOutRestaurantDao instance = null;
	protected TakeOutRestaurantDao() {
		super();
	}
	public static TakeOutRestaurantDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantDao();
		}
		return instance;
	}
	public TakeOutRestaurant create(TakeOutRestaurant takeOutRestaurant) throws SQLException {
		// Insert into the superclass table first.
		Restaurants restaurant = create(new Restaurants(takeOutRestaurant.getName(),
				takeOutRestaurant.getDescription(),
				takeOutRestaurant.getMenu(), 
				takeOutRestaurant.getHours(),
				takeOutRestaurant.isActive(),
				takeOutRestaurant.getCuisine(),
				takeOutRestaurant.getStreet1(),
				takeOutRestaurant.getStreet2(),
				takeOutRestaurant.getCity(),
				takeOutRestaurant.getState(),
				takeOutRestaurant.getZip(),
				takeOutRestaurant.getCompany()));

		String insertTakeOutRestaurant = "INSERT INTO TakeOutRestaurant(RestaurantId,MaxWaitTime) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTakeOutRestaurant);
			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());
			insertStmt.executeUpdate();
			return takeOutRestaurant;
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
	public TakeOutRestaurant getTakeOutRestaurantById(int takeOutRestaurantId) throws SQLException {
		// To build an TakeOutRestaurant object, we need the Persons record, too.
		String selectTakeOutRestaurant =
			"SELECT TakeOutRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, MaxWaitTime " +
			"FROM TakeOutRestaurant INNER JOIN Restaurants " +
			"ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " +
			"WHERE TakeOutRestaurant.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurant);
			selectStmt.setInt(1, takeOutRestaurantId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisine = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				CompaniesDao companiesDao = CompaniesDao.getInstance();
				Companies company = companiesDao.getCompanyByCompanyName(results.getString("CompanyName"));
				int maxWaitTime = results.getInt("MaxWaitTime");
				TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company, maxWaitTime);
				return takeOutRestaurant;
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
	public List<TakeOutRestaurant> getTakeOutRestaurantsByCompanyName(String companyName)throws SQLException {
		List<TakeOutRestaurant> takeOutRestaurants = new ArrayList<TakeOutRestaurant>();
		String selectTakeOutRestaurant =
		"SELECT TakeOutRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, MaxWaitTime " +
		"FROM TakeOutRestaurant INNER JOIN Restaurants " +
		"ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " + 
			"WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurant);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisine = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				CompaniesDao companiesDao = CompaniesDao.getInstance();
				Companies company = companiesDao.getCompanyByCompanyName(results.getString("CompanyName"));
				int maxWaitTime = results.getInt("MaxWaitTime");
				TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company, maxWaitTime);
				takeOutRestaurants.add(takeOutRestaurant);
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
		return takeOutRestaurants;
	}
	public TakeOutRestaurant delete(TakeOutRestaurant takeOutRestaurant) throws SQLException {
		String deleteTakeOutRestaurant = "DELETE FROM TakeOutRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTakeOutRestaurant);
			deleteStmt.setInt(1, takeOutRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from TakeOutRestaurant first.
			super.delete(takeOutRestaurant);

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


}
