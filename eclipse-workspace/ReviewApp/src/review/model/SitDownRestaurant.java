package review.model;

import review.model.Restaurants.CuisineType;

public class SitDownRestaurant extends Restaurants {
	protected int capacity;
	public SitDownRestaurant(int restaurantId, String name, String description, String menu, String hours, boolean active, CuisineType cuisine, String street1, String street2, String city, String state, int zip, Companies company, int capacity) {
		super(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company);
		this.capacity = capacity;
	}
	public SitDownRestaurant(int restaurantId, int capacity) {
		super(restaurantId);
		this.capacity = capacity;
	}
	public SitDownRestaurant(String name, String description, String menu, String hours, boolean active, CuisineType cuisine, String street1, String street2, String city, String state, int zip, Companies company, int capacity) {
		super(name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, company);
		this.capacity = capacity;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	

}
