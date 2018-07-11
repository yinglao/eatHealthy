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



public class SitDownRestaurantDao extends RestaurantsDao{
	private static SitDownRestaurantDao instance = null;
	protected SitDownRestaurantDao() {
		super();
	}
	public static SitDownRestaurantDao getInstance() {
		if(instance == null) {
			instance = new SitDownRestaurantDao();
		}
		return instance;
	}
	public SitDownRestaurant create(SitDownRestaurant sitDownRestaurant) throws SQLException {
		// Insert into the superclass table first.
		Restaurants restaurant = create(new Restaurants(sitDownRestaurant.getName(),
				sitDownRestaurant.getDescription(),
				sitDownRestaurant.getMenu(), 
				sitDownRestaurant.getHours(),
				sitDownRestaurant.isActive(),
				sitDownRestaurant.getCuisine(),
				sitDownRestaurant.getStreet1(),
				sitDownRestaurant.getStreet2(),
				sitDownRestaurant.getCity(),
				sitDownRestaurant.getState(),
				sitDownRestaurant.getZip(),
				sitDownRestaurant.getCompany()));

		String insertSitDownRestaurant = "INSERT INTO SitDownRestaurant(RestaurantId,Capacity) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitDownRestaurant);
			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setInt(2, sitDownRestaurant.getCapacity());
			insertStmt.executeUpdate();
			return sitDownRestaurant;
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
	public SitDownRestaurant getSitDownRestaurantById(int sitDownRestaurantId) throws SQLException {
		// To build an SitDownRestaurant object, we need the Persons record, too.
		String selectSitDownRestaurant =
			"SELECT SitDownRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, Capacity " +
			"FROM SitDownRestaurant INNER JOIN Restaurants " +
			"ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
			"WHERE SitDownRestaurant.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
			selectStmt.setInt(1, sitDownRestaurantId);
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
				int capacity = results.getInt("Capacity");
				SitDownRestaurant sitDownRestaurant = new SitDownRestaurant(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company, capacity);
				return sitDownRestaurant;
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
	public List<SitDownRestaurant> getSitDownRestaurantsByCompanyName(String companyName)throws SQLException {
		List<SitDownRestaurant> sitDownRestaurants = new ArrayList<SitDownRestaurant>();
		String selectSitDownRestaurant =
		"SELECT SitDownRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, Capacity " +
		"FROM SitDownRestaurant INNER JOIN Restaurants " +
		"ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " + 
			"WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
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
				int capacity = results.getInt("Capacity");
				SitDownRestaurant sitDownRestaurant = new SitDownRestaurant(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company, capacity);
				sitDownRestaurants.add(sitDownRestaurant);
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
		return sitDownRestaurants;
	}
	public SitDownRestaurant delete(SitDownRestaurant sitDownRestaurant) throws SQLException {
		String deleteSitDownRestaurant = "DELETE FROM SitDownRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSitDownRestaurant);
			deleteStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from SitDownRestaurant first.
			super.delete(sitDownRestaurant);

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
