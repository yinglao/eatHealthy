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



public class FoodCartRestaurantDao extends RestaurantsDao{
	private static FoodCartRestaurantDao instance = null;
	protected FoodCartRestaurantDao() {
		super();
	}
	public static FoodCartRestaurantDao getInstance() {
		if(instance == null) {
			instance = new FoodCartRestaurantDao();
		}
		return instance;
	}
	public FoodCartRestaurant create(FoodCartRestaurant foodCartRestaurant) throws SQLException {
		// Insert into the superclass table first.
		Restaurants restaurant = create(new Restaurants(foodCartRestaurant.getName(),
				foodCartRestaurant.getDescription(),
				foodCartRestaurant.getMenu(), 
				foodCartRestaurant.getHours(),
				foodCartRestaurant.isActive(),
				foodCartRestaurant.getCuisine(),
				foodCartRestaurant.getStreet1(),
				foodCartRestaurant.getStreet2(),
				foodCartRestaurant.getCity(),
				foodCartRestaurant.getState(),
				foodCartRestaurant.getZip(),
				foodCartRestaurant.getCompany()));

		String insertFoodCartRestaurant = "INSERT INTO FoodCartRestaurant(RestaurantId,Licensed) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodCartRestaurant);
			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setBoolean(2, foodCartRestaurant.isLicensed());
			insertStmt.executeUpdate();
			return foodCartRestaurant;
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
	public FoodCartRestaurant getFoodCartRestaurantById(int foodCartRestaurantId) throws SQLException {
		// To build an FoodCartRestaurant object, we need the Persons record, too.
		String selectFoodCartRestaurant =
			"SELECT FoodCartRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, Licensed " +
			"FROM FoodCartRestaurant INNER JOIN Restaurants " +
			"ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId " +
			"WHERE FoodCartRestaurant.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurant);
			selectStmt.setInt(1, foodCartRestaurantId);
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
				boolean licensed = results.getBoolean("Licensed");
				FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company, licensed);
				return foodCartRestaurant;
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
	public List<FoodCartRestaurant> getFoodCartRestaurantsByCompanyName(String companyName)throws SQLException {
		List<FoodCartRestaurant> foodCartRestaurants = new ArrayList<FoodCartRestaurant>();
		String selectFoodCartRestaurant =
		"SELECT FoodCartRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, Licensed " +
		"FROM FoodCartRestaurant INNER JOIN Restaurants " +
		"ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId " + 
			"WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurant);
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
				boolean licensed = results.getBoolean("Licensed");
				FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company, licensed);
				foodCartRestaurants.add(foodCartRestaurant);
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
		return foodCartRestaurants;
	}
	public FoodCartRestaurant delete(FoodCartRestaurant foodCartRestaurant) throws SQLException {
		String deleteFoodCartRestaurant = "DELETE FROM FoodCartRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFoodCartRestaurant);
			deleteStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from FoodCartRestaurant first.
			super.delete(foodCartRestaurant);

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
