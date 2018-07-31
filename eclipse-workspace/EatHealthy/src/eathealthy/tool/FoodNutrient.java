package eathealthy.tool;
import eathealthy.model.*;

public class FoodNutrient {
	Food food;
	double amount;
	double protein;
	double fat;
	double carbohydrate;
	double calorie;
	public FoodNutrient(Food food, double amount, double protein, double fat, double carbohydrate, double calorie) {
		super();
		this.food = food;
		this.amount = amount;
		this.protein = protein;
		this.fat = fat;
		this.carbohydrate = carbohydrate;
		this.calorie = calorie;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public double getCalorie() {
		return calorie;
	}
	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}
	

}
