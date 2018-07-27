package eathealthy.model;

public class NutrientData {
	protected int NutrientDataId;
	protected double NutrientValue;
	protected Food food;
	protected Nutrient nutrient;
	public NutrientData(int nutrientDataId, double nutrientValue, Food food, Nutrient nutrient) {
		super();
		NutrientDataId = nutrientDataId;
		NutrientValue = nutrientValue;
		this.food = food;
		this.nutrient = nutrient;
	}
	
	public NutrientData(double nutrientValue, Food food, Nutrient nutrient) {
		super();
		NutrientValue = nutrientValue;
		this.food = food;
		this.nutrient = nutrient;
	}

	public int getNutrientDataId() {
		return NutrientDataId;
	}
	public void setNutrientDataId(int nutrientDataId) {
		NutrientDataId = nutrientDataId;
	}
	public double getNutrientValue() {
		return NutrientValue;
	}
	public void setNutrientValue(double nutrientValue) {
		NutrientValue = nutrientValue;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public Nutrient getNutrient() {
		return nutrient;
	}
	public void setNutrient(Nutrient nutrient) {
		this.nutrient = nutrient;
	}
	
	

}
