package review.dal;

import review.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;



import java.util.ArrayList;

public class RestaurantsDao {
	protected ConnectionManager connectionManager;

	private static RestaurantsDao instance = null;
	protected RestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RestaurantsDao getInstance() {
		if(instance == null) {
			instance = new RestaurantsDao();
		}
		return instance;
	}
	
	public Restaurants create(Restaurants restaurant) throws SQLException {
		String insertRestaurant =
				"INSERT INTO Restaurants(Name, Description, Menu, Hours,Active,CuisineType, Street1, Street2, City, State, Zip, CompanyName) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				// Restaurants has an auto-generated key. So we want to retrieve that key.
				insertStmt = connection.prepareStatement(insertRestaurant,
					Statement.RETURN_GENERATED_KEYS);
				insertStmt.setString(1, restaurant.getName());
				// Note: for the sake of simplicity, just set Picture to null for now.
				insertStmt.setString(2, restaurant.getDescription());
				insertStmt.setString(3, restaurant.getMenu());
				insertStmt.setString(4, restaurant.getHours());
				insertStmt.setBoolean(5, restaurant.isActive());
				insertStmt.setString(6, restaurant.getCuisine().name());
				insertStmt.setString(7, restaurant.getStreet1());
				insertStmt.setString(8, restaurant.getStreet2());
				insertStmt.setString(9, restaurant.getCity());
				insertStmt.setString(10, restaurant.getState());
				insertStmt.setInt(11, restaurant.getZip());
				insertStmt.setString(12, restaurant.getCompany().getCompanyName());
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				// For more details, see:
				// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
				resultKey = insertStmt.getGeneratedKeys();
				int restaurantId = -1;
				if(resultKey.next()) {
					restaurantId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				restaurant.setRestaurantId(restaurantId);
				return restaurant;
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
	public Restaurants getRestaurantById(int restaurantId) throws SQLException {
		String selectRestaurant =
				"SELECT * " +
				"FROM Restaurants " +
				"WHERE RestaurantId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRestaurant);
				selectStmt.setInt(1, restaurantId);
				results = selectStmt.executeQuery();
				if(results.next()) {
					int resultRestaurantId = results.getInt("RestaurantId");
					String name = results.getString("Name");
					String description = results.getString("Description");
					String menu = results.getString("Menu");
					String hours = results.getString("Hours");
					boolean active =  results.getBoolean("Active");
					Restaurants.CuisineType cuisine = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
					String street1 = results.getString("Street1");
					String street2 = results.getString("Street2");
					String city = results.getString("City");
					String state = results.getString("State");
					int zip = results.getInt("Zip");
					CompaniesDao companyDao = CompaniesDao.getInstance();
					String companyName = results.getString("CompanyName");
					Companies company = companyDao.getCompanyByCompanyName(companyName);
					
					Restaurants restaurant = new Restaurants(resultRestaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company);
					return restaurant;
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
	public List<Restaurants> getRestaurantsByCuisine(Restaurants.CuisineType cusine) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		String selectRestaurants =
			"SELECT * " +
			"FROM Restaurants " +
			"WHERE CuisineType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, cusine.name());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("restaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active =  results.getBoolean("Active");
				Restaurants.CuisineType cuisine = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				CompaniesDao companyDao = CompaniesDao.getInstance();
				String companyName = results.getString("CompanyName");
				Companies company = companyDao.getCompanyByCompanyName(companyName);
				
				Restaurants restaurant = new Restaurants(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company);
				restaurants.add(restaurant);
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
		return restaurants;
	}
//	Note: Define the CuisineType enum in Restuarants.java (see BlogUsers.java for an example).
	public List<Restaurants> getRestaurantsByCompanyName(String companyName) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		String selectRestaurants =
			"SELECT * " +
			"FROM Restaurants " +
			"WHERE companyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("restaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active =  results.getBoolean("Active");
				Restaurants.CuisineType cuisine = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				CompaniesDao companyDao = CompaniesDao.getInstance();
				String resultCompanyName = results.getString("CompanyName");
				Companies company = companyDao.getCompanyByCompanyName(resultCompanyName);
				
				Restaurants restaurant = new Restaurants(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company);
				restaurants.add(restaurant);
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
		return restaurants;
	} 
	public Restaurants delete(Restaurants restaurant) throws SQLException {
		// Note: BlogComments has a fk constraint on Restaurants with the reference option
		// ON DELETE CASCADE. So this delete operation will delete all the referencing
		// BlogComments.
		String deleteRestaurant = "DELETE FROM Restaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, restaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Restaurants instance.
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
