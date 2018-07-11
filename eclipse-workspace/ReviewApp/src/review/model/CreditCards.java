package review.model;

import java.util.Date;

public class CreditCards {
	protected long cardNumber;
	protected Date expiration;
	protected Users user;
	public CreditCards(long cardNumber, Date expiration, Users user) {
		super();
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.user = user;
	}
	public CreditCards(long cardNumber) {
		super();
		this.cardNumber = cardNumber;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	

	
	

}
