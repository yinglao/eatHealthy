package review.model;

import review.model.Restaurants.CuisineType;

public class TakeOutRestaurant extends Restaurants {
	protected int maxWaitTime;
	public TakeOutRestaurant(int restaurantId, String name, String description, String menu, String hours, boolean active, CuisineType cuisine, String street1, String street2, String city, String state, int zip, Companies company, int maxWaitTime) {
		super(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company);
		this.maxWaitTime = maxWaitTime;
	}
	public TakeOutRestaurant(String name, String description, String menu, String hours, boolean active, CuisineType cuisine, String street1, String street2, String city, String state, int zip, Companies company, int maxWaitTime) {
		super(name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company);
		this.maxWaitTime = maxWaitTime;
	}
	public TakeOutRestaurant(int restaurantId, int maxWaitTime) {
		super(restaurantId);
		this.maxWaitTime = maxWaitTime;
	}
	public int getMaxWaitTime() {
		return maxWaitTime;
	}
	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}
	
	

}
