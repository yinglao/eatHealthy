package review.dal;
import review.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class CreditCardsDao {
	
	protected ConnectionManager connectionManager;
	private static CreditCardsDao instance = null;
	protected CreditCardsDao() { 
		connectionManager = new ConnectionManager();
	}
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	
	public CreditCards create(CreditCards creditCard) throws SQLException{
		String insertCreditCard = "INSERT INTO CreditCards(CardNumber, Expiration, UserName) VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);
			insertStmt.setLong(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setString(3, creditCard.getUser().getUserName());
			
			insertStmt.executeUpdate();
			return creditCard;
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
	public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException {
		System.out.println("reach here");
		String selectCreditCard = "SELECT * FROM CreditCards WHERE CardNumber = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet result = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setLong(1, cardNumber);
			result = selectStmt.executeQuery();
			if (result.next()) {
				Long resultCardNumber = result.getLong("CardNumber");
//				Date created =  new Date(results.getTimestamp("Created").getTime())
				Date expiration = new Date(result.getTimestamp("Expiration").getTime());
//				System.out.println(expiration);
				UsersDao usersDao = UsersDao.getInstance();
				String userName = result.getString("UserName");
				
				CreditCards creditCard = new CreditCards(resultCardNumber, expiration, usersDao.getUserByUserName(userName));
				return creditCard;
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
	public List<CreditCards> getCreditCardsByUserName(String userName) throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCard = "SELECT * FROM CreditCards WHERE UserName = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet result = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setString(1, userName);
			result = selectStmt.executeQuery();
			while (result.next()) {
				Long cardNumber = result.getLong("CardNumber");
				Date expiration = new Date(result.getTimestamp("Expiration").getTime());
				UsersDao usersDao = UsersDao.getInstance();
				String resultUserName = result.getString("UserName");
				CreditCards creditCard = new CreditCards(cardNumber, expiration, usersDao.getUserByUserName(resultUserName));
				creditCards.add(creditCard);
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
		return creditCards;
	}
	
	public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException{
		String updateCreditCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCreditCard);
			updateStmt.setTimestamp(1, new Timestamp(newExpiration.getTime()));
			updateStmt.setLong(2, creditCard.getCardNumber());
			updateStmt.executeUpdate();

			creditCard.setExpiration(newExpiration);			
			return creditCard;
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
	public CreditCards delete(CreditCards creditCard) throws SQLException{
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber = ?";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setLong(1, creditCard.getCardNumber());
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


}
