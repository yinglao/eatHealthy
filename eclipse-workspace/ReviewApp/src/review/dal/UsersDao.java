package review.dal;

import review.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UsersDao {
	protected ConnectionManager connectionManager;
	private static UsersDao instance = null;
	protected UsersDao() { 
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	
	
	public Users create(Users user) throws SQLException{
		String insertUser = "INSERT INTO Users(UserName, Password, FirstName, LastName, Email, Phone) VALUES(?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			insertStmt.setString(5, user.getEmail());
			insertStmt.setString(6, user.getPhone());
			insertStmt.executeUpdate();
			return user;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Users delete(Users user) throws SQLException{
		String deleteUser = "DELETE FROM Users WHERE UserName = ?";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
			deleteStmt.executeUpdate();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	public Users getUserByUserName(String userName) throws SQLException {
		String selectUser = "SELECT * FROM Users WHERE UserName = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet result = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			result = selectStmt.executeQuery();
			if (result.next()) {
				String resultUserName = result.getString("UserName");
				String password = result.getString("Password");
				String firstName = result.getString("FirstName");
				String lastName = result.getString("LastName");
				String email = result.getString("Email");
				String phone = result.getString("Phone");
				Users user = new Users(resultUserName, password, firstName, lastName, email, phone);
				return user;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
		}
		return null;
	}

}
