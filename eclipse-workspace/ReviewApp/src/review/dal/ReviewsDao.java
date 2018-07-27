package review.dal;
import review.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class ReviewsDao {
	protected ConnectionManager connectionManager;
	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if (instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
				"INSERT INTO Reviews(Created, Content, Rating, UserName, RestaurantId) " +
				"VALUES(?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertReview,
					Statement.RETURN_GENERATED_KEYS);
				insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
				insertStmt.setString(2, review.getContent());
				insertStmt.setDouble(3, review.getRating());
				insertStmt.setString(4, review.getUser().getUserName());
				insertStmt.setInt(5, review.getRestaurant().getRestaurantId());
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int reviewId = -1;
				if(resultKey.next()) {
					reviewId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				review.setReviewId(reviewId);
				return review;
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

	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview =
				"SELECT * " +
				"FROM Reviews " +
				"WHERE ReviewId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectReview);
				selectStmt.setInt(1, reviewId);
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
				if(results.next()) {
					int resultReviewId = results.getInt("ReviewId");
					String content = results.getString("Content");
					Date created = new Date(results.getTimestamp("Created").getTime());
					Double rating = results.getDouble("Rating");
					String userName = results.getString("UserName");
					int restaurantId = results.getInt("RestaurantId");
					
					Users user = usersDao.getUserByUserName(userName);
					Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
					Reviews review = new Reviews(resultReviewId, created, content, rating, user, restaurant);
					return review;
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

	public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT * " +
			"FROM Reviews " + 
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1,userName);
			results = selectStmt.executeQuery();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Double rating = results.getDouble("Rating");
				String resultUserName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
				Reviews review = new Reviews(reviewId, created, content, rating, user, restaurant);
				reviews.add(review);
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
		return reviews;
	}

	public List<Reviews> getReviewsByRestaurantId(int restaurantId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT * " +
			"FROM Reviews " + 
			"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1,restaurantId);
			results = selectStmt.executeQuery();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Double rating = results.getDouble("Rating");
				String resultUserName = results.getString("UserName");
				int resultRestaurantId = results.getInt("RestaurantId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Restaurants restaurant = restaurantsDao.getRestaurantById(resultRestaurantId);
				Reviews review = new Reviews(reviewId, created, content, rating, user, restaurant);
				reviews.add(review);
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
		return reviews;
	}
	
	public Reviews delete(Reviews review) throws SQLException {
	String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deleteReview);
		deleteStmt.setInt(1, review.getReviewId());
		deleteStmt.executeUpdate();

		// Return null so the caller can no longer operate on the Persons instance.
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
