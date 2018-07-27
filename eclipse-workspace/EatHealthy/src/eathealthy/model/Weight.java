package eathealthy.model;

public class Weight {
	protected int weightId;
	protected double amount;
	protected String unit;
	protected double weightInGram;
	protected Food food;
	public Weight(int weightId, double amount, String unit, double weightInGram, Food food) {
		super();
		this.weightId = weightId;
		this.amount = amount;
		this.unit = unit;
		this.weightInGram = weightInGram;
		this.food = food;
	}
	public Weight(double amount, String unit, double weightInGram, Food food) {
		super();
		this.amount = amount;
		this.unit = unit;
		this.weightInGram = weightInGram;
		this.food = food;
	}
	public int getWeightId() {
		return weightId;
	}
	public void setWeightId(int weightId) {
		this.weightId = weightId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getWeightInGram() {
		return weightInGram;
	}
	public void setWeightInGram(double weightInGram) {
		this.weightInGram = weightInGram;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	
}
