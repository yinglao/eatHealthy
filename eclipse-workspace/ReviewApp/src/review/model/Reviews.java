package review.model;
import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Date created;
	protected String content;
	protected double rating;
	protected Users user;
	protected Restaurants restaurant;
	public Reviews(int reviewId) {
		super();
		this.reviewId = reviewId;
	}
	public Reviews(int reviewId, Date created, String content, double rating, Users user, Restaurants restaurant) {
		super();
		this.reviewId = reviewId;
		this.created = created;
		this.content = content;
		this.rating = rating;
		this.user = user;
		this.restaurant = restaurant;
	}
	public Reviews(Date created, String content, double rating, Users user, Restaurants restaurant) {
		super();
		this.created = created;
		this.content = content;
		this.rating = rating;
		this.user = user;
		this.restaurant = restaurant;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Restaurants getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
