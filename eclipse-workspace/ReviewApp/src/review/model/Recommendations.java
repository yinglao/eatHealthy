package review.model;

public class Recommendations {
	int recommendationId;
	protected Users user;
	protected Restaurants restaurant;
	public Recommendations(int recommendationId, Users user, Restaurants restaurant) {
		super();
		this.recommendationId = recommendationId;
		this.user = user;
		this.restaurant = restaurant;
	}
	
	public Recommendations(Users user, Restaurants restaurant) {
		super();
		this.user = user;
		this.restaurant = restaurant;
	}

	public Recommendations(int recommendationId) {
		super();
		this.recommendationId = recommendationId;
	}

	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
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
