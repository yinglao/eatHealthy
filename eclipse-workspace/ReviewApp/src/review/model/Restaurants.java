package review.model;

public class Restaurants {
	protected int restaurantId;
	protected String name;
	protected String description;
	protected String menu;
	protected String hours;
	protected boolean active;
	protected CuisineType cuisine;
	protected String street1;
	protected String street2;
	protected String city;
	protected String state;
	protected int zip;
	protected Companies company;
	
	public enum CuisineType{
		AFRICAN, AMERICAN, ASIAN, EUROPEAN, HISPANIC
	}

	public Restaurants(int restaurantId) {
		super();
		this.restaurantId = restaurantId;
	}
	

	public Restaurants(String name, String description, String menu, String hours, boolean active, CuisineType cuisine,
			String street1, String street2, String city, String state, int zip, Companies company) {
		super();
		this.name = name;
		this.description = description;
		this.menu = menu;
		this.hours = hours;
		this.active = active;
		this.cuisine = cuisine;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.company = company;
	}


	public Restaurants(int restaurantId, String name, String description, String menu, String hours, boolean active,
			CuisineType cuisine, String street1, String street2, String city, String state, int zip,
			Companies company) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.menu = menu;
		this.hours = hours;
		this.active = active;
		this.cuisine = cuisine;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.company = company;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public CuisineType getCuisine() {
		return cuisine;
	}

	public void setCuisine(CuisineType cuisine) {
		this.cuisine = cuisine;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public Companies getCompany() {
		return company;
	}

	public void setCompany(Companies company) {
		this.company = company;
	}

	
	

}
