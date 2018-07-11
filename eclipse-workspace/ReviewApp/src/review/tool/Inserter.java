package review.tool;

import review.dal.*;
import review.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

public class Inserter {
	
	public static void main(String[] args) throws SQLException {
		// get Daos
		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		CompaniesDao companiesDao = CompaniesDao.getInstance();
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		SitDownRestaurantDao sitDownRestaurantDao = SitDownRestaurantDao.getInstance();
		TakeOutRestaurantDao takeOutRestaurantDao = TakeOutRestaurantDao.getInstance();
		FoodCartRestaurantDao foodCartRestaurantDao = FoodCartRestaurantDao.getInstance();
		
		//UsersDao tests
		Users user = new Users("Bruce","password","Bruce","C","bruce@mail.com","5555555");
		user = usersDao.create(user);
		user = usersDao.getUserByUserName("Bruce");
		System.out.println(user.getFirstName() + user.getLastName() + user.getPhone());
		usersDao.delete(user);
		user = new Users("Bruce","password","Bruce","C","bruce@mail.com","5555555");
		user = usersDao.create(user);
		
		//CreditCardsDao tests
		CreditCards creditCard = new CreditCards(3499432187650987L,new Date(),user);
		creditCard = creditCardsDao.create(creditCard);
		creditCard = creditCardsDao.getCreditCardByCardNumber(3499432187650987L);
		System.out.println(creditCard);
		System.out.println(creditCard.getCardNumber() + " " + creditCard.getExpiration() + " " + creditCard.getUser().getUserName());
		creditCardsDao.delete(creditCard);
		creditCard = new CreditCards(3499432187650987L,new Date(),user);
		creditCard = creditCardsDao.create(creditCard);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(2019, 1, 27, 12, 0, 0);
		Date date = cal.getTime(); // get back a Date object;
		creditCard = creditCardsDao.updateExpiration(creditCard, date);
		List<CreditCards> creditCards = creditCardsDao.getCreditCardsByUserName("Bruce");
		System.out.println(creditCards.get(0).getUser().getUserName());
		
//		CompaniesDao tests
		Companies company = new Companies("company1", "About company1");
		company = companiesDao.create(company);
		company = companiesDao.getCompanyByCompanyName("company1");
		System.out.println(company.getCompanyName() + " " + company.getAbout());
		companiesDao.delete(company);
		company = new Companies("company1", "About company1");
		company = companiesDao.create(company);
		
		
		
		
		//RestaurantsDao tests
//		public Restaurants create(Restaurants restaurant)
//		public Restaurants getRestaurantById(int restaurantId)
//		public List<Restaurants> getRestaurantsByCuisine(Restaurants.CuisineType cusine)
//		Note: Define the CuisineType enum in Restuarants.java (see BlogUsers.java for an example).
//		public List<Restaurants> getRestaurantsByCompanyName(String companyName) 
//		public Restaurants delete(Restaurants restaurant)

		Restaurants restaurant = new Restaurants("restaurant1","about restaurant","menu","hours",true,Restaurants.CuisineType.valueOf("AFRICAN"),"street1","street2","seattle","wa",98195,company);
		restaurant = restaurantsDao.create(restaurant);
		restaurant = restaurantsDao.getRestaurantById(1);
		System.out.println(restaurant.getName() + " " + restaurant.getRestaurantId());
		List<Restaurants>restaurants = restaurantsDao.getRestaurantsByCompanyName("company1");
		restaurant = restaurants.get(0);
		System.out.println(restaurants.size());
		restaurants = restaurantsDao.getRestaurantsByCuisine(Restaurants.CuisineType.valueOf("AFRICAN"));
		System.out.println(restaurants.size());
		restaurantsDao.delete(restaurant);
		
		//sitDownRestaurantDao tests
//		public SitDownRestaurants create(SitDownRestaurants sitDownRestaurant)
//		public SitDownRestaurants getSitDownRestaurantById(int sitDownRestaurantId)
//		public List<SitDownRestaurants> getSitDownRestaurantsByCompanyName(String companyName)
//		public SitDownRestaurants delete(SitDownRestaurants sitDownRestaurant)
		
		SitDownRestaurant sitDownRestaurant = new SitDownRestaurant("restaurant1","about restaurant","menu","hours",true,Restaurants.CuisineType.valueOf("AFRICAN"),"street1","street2","seattle","wa",98195,company, 100);
		sitDownRestaurant = sitDownRestaurantDao.create(sitDownRestaurant);
		sitDownRestaurant = sitDownRestaurantDao.getSitDownRestaurantById(2);
		System.out.println(sitDownRestaurant.getName() + " " + sitDownRestaurant.getCapacity() + " " + sitDownRestaurant.getCompany().getCompanyName());
		List<SitDownRestaurant> sitDownRestaurants = sitDownRestaurantDao.getSitDownRestaurantsByCompanyName("company1");
		System.out.println(sitDownRestaurants.size());
		sitDownRestaurantDao.delete(sitDownRestaurant);

		//takeOutRestaurantDao tests
//		public TakeOutRestaurants create(TakeOutRestaurants takeOutRestaurant)
//		public TakeOutRestaurants getTakeOutRestaurantById(int takeOutRestaurantId)
//		public List<TakeOutRestaurants> getTakeOutRestaurantsByCompanyName(String companyName)
//		public TakeOutRestaurants delete(TakeOutRestaurants takeOutRestaurant)
		
		TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant("restaurant1","about restaurant","menu","hours",true,Restaurants.CuisineType.valueOf("AFRICAN"),"street1","street2","seattle","wa",98195,company, 100);
		takeOutRestaurant = takeOutRestaurantDao.create(takeOutRestaurant);
		takeOutRestaurant = takeOutRestaurantDao.getTakeOutRestaurantById(3);
		System.out.println(takeOutRestaurant.getName() + " " + takeOutRestaurant.getMaxWaitTime() + " " + takeOutRestaurant.getCompany().getCompanyName());
		List<TakeOutRestaurant> takeOutRestaurants = takeOutRestaurantDao.getTakeOutRestaurantsByCompanyName("company1");
		System.out.println(takeOutRestaurants.size());
		takeOutRestaurantDao.delete(takeOutRestaurant);
		
		//foodCartRestaurantDao tests
//		public FoodCartRestaurants create(FoodCartRestaurants foodCartRestaurant)
//		public FoodCartRestaurants getFoodCartRestaurantById(int foodCartRestaurantId)
//		public List<FoodCartRestaurants> getFoodCartRestaurantsByCompanyName(String companyName)
//		public FoodCartRestaurants delete(FoodCartRestaurants foodCartRestaurant)
		
		FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant("restaurant1","about restaurant","menu","hours",true,Restaurants.CuisineType.valueOf("AFRICAN"),"street1","street2","seattle","wa",98195,company, true);
		foodCartRestaurant = foodCartRestaurantDao.create(foodCartRestaurant);
		foodCartRestaurant = foodCartRestaurantDao.getFoodCartRestaurantById(4);
		System.out.println(foodCartRestaurant.getName() + " " + foodCartRestaurant.isLicensed() + " " + foodCartRestaurant.getCompany().getCompanyName());
		List<FoodCartRestaurant> foodCartRestaurants = foodCartRestaurantDao.getFoodCartRestaurantsByCompanyName("company1");
		System.out.println(foodCartRestaurants.size());
		foodCartRestaurantDao.delete(foodCartRestaurant);

	}
	
	

}
