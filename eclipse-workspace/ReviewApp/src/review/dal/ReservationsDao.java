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


public class ReservationsDao {
	protected ConnectionManager connectionManager;
	private static ReservationsDao instance = null;
	protected ReservationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReservationsDao getInstance() {
		if (instance == null) {
			instance = new ReservationsDao();
		}
		return instance;
	}
	public Reservations create(Reservations reservation) throws SQLException {
		String insertReservation =
				"INSERT INTO Reservations(Start, End, Size, UserName, RestaurantId) " +
				"VALUES(?,?,?,?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertReservation,
					Statement.RETURN_GENERATED_KEYS);
				insertStmt.setTimestamp(1, new Timestamp(reservation.getStart().getTime()));
				insertStmt.setTimestamp(2, new Timestamp(reservation.getStart().getTime()));
				insertStmt.setInt(3, reservation.getSize());
				insertStmt.setString(4, reservation.getUser().getUserName());
				insertStmt.setInt(5, reservation.getSitDownRestaurant().getRestaurantId());
				System.out.println(reservation.getSitDownRestaurant().getCapacity());
				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int reservationId = -1;
				if(resultKey.next()) {
					reservationId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				reservation.setReservationId(reservationId);
				return reservation;
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

	public Reservations getReservationById(int reservationId) throws SQLException {
		String selectReservation =
				"SELECT * " +
				"FROM Reservations " +
				"WHERE ReservationId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectReservation);
				selectStmt.setInt(1, reservationId);
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				SitDownRestaurantDao sitDownRestaurantDao = SitDownRestaurantDao.getInstance();
				if(results.next()) {
					int resultReservationId = results.getInt("ReservationId");
					Date start = new Date(results.getTimestamp("Start").getTime());
					Date end = new Date(results.getTimestamp("End").getTime());
					int size = results.getInt("Size");
					String userName = results.getString("UserName");
					int restaurantId = results.getInt("RestaurantId");
					
					Users user = usersDao.getUserByUserName(userName);
					SitDownRestaurant sitDownrestaurant = sitDownRestaurantDao.getSitDownRestaurantById(restaurantId);
					Reservations reservation = new Reservations(resultReservationId, start, end, size, user, sitDownrestaurant);
					return reservation;
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

	public List<Reservations> getReservationsByUserName(String userName) throws SQLException {
		List<Reservations> reservations = new ArrayList<Reservations>();
		String selectReservations =
			"SELECT * " +
			"FROM Reservations " + 
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setString(1,userName);
			results = selectStmt.executeQuery();
			SitDownRestaurantDao sitDownRestaurantDao = SitDownRestaurantDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int reservationId = results.getInt("ReservationId");
				Date start = new Date(results.getTimestamp("Start").getTime());
				Date end = new Date(results.getTimestamp("End").getTime());
				int size = results.getInt("Size");
				String resultUserName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				SitDownRestaurant sitDownrestaurant = sitDownRestaurantDao.getSitDownRestaurantById(restaurantId);
				Reservations reservation = new Reservations(reservationId, start, end, size, user, sitDownrestaurant);
				reservations.add(reservation);
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
		return reservations;
	}

	public List<Reservations> getReservationsBySitDownRestaurantId(int restaurantId) throws SQLException {
		List<Reservations> reservations = new ArrayList<Reservations>();
		String selectReservations =
			"SELECT * " +
			"FROM Reservations " + 
			"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setInt(1,restaurantId);
			results = selectStmt.executeQuery();
			SitDownRestaurantDao sitDownRestaurantDao = SitDownRestaurantDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int reservationId = results.getInt("ReservationId");
				Date start = new Date(results.getTimestamp("Start").getTime());
				Date end = new Date(results.getTimestamp("End").getTime());
				int size = results.getInt("Size");
				String resultUserName = results.getString("UserName");
				int resultRestaurantId = results.getInt("RestaurantId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				SitDownRestaurant sitDownrestaurant = sitDownRestaurantDao.getSitDownRestaurantById(resultRestaurantId);
				Reservations reservation = new Reservations(reservationId, start, end, size, user, sitDownrestaurant);
				reservations.add(reservation);
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
		return reservations;
	}
	
	public Reservations delete(Reservations reservation) throws SQLException {
	String deleteReservation = "DELETE FROM Reservations WHERE ReservationId=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deleteReservation);
		deleteStmt.setInt(1, reservation.getReservationId());
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
