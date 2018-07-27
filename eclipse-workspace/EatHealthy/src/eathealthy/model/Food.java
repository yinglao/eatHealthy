package eathealthy.model;

public class Food {
	protected int FoodId;
	protected String Description;
	protected double NitrogenFactor;
	protected double ProteinFactor;
	protected double FatFactor;
	protected double CarbohydrateFactor;
	protected FoodGroup foodGroup;
	
	
	public Food(int foodId, String description, double nitrogenFactor, double proteinFactor, double fatFactor,
			double carbohydrateFactor, FoodGroup foodGroup) {
		super();
		FoodId = foodId;
		Description = description;
		NitrogenFactor = nitrogenFactor;
		ProteinFactor = proteinFactor;
		FatFactor = fatFactor;
		CarbohydrateFactor = carbohydrateFactor;
		this.foodGroup = foodGroup;
	}
	public int getFoodId() {
		return FoodId;
	}
	public void setFoodId(int foodId) {
		FoodId = foodId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public double getNitrogenFactor() {
		return NitrogenFactor;
	}
	public void setNitrogenFactor(double nitrogenFactor) {
		NitrogenFactor = nitrogenFactor;
	}
	public double getProteinFactor() {
		return ProteinFactor;
	}
	public void setProteinFactor(double proteinFactor) {
		ProteinFactor = proteinFactor;
	}
	public double getFatFactor() {
		return FatFactor;
	}
	public void setFatFactor(double fatFactor) {
		FatFactor = fatFactor;
	}
	public double getCarbohydrateFactor() {
		return CarbohydrateFactor;
	}
	public void setCarbohydrateFactor(double carbohydrateFactor) {
		CarbohydrateFactor = carbohydrateFactor;
	}
	public FoodGroup getFoodGroup() {
		return foodGroup;
	}
	public void setFoodGroup(FoodGroup foodGroup) {
		this.foodGroup = foodGroup;
	}
	
	
	

}
