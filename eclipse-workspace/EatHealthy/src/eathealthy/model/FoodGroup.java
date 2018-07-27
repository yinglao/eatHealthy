package eathealthy.model;

public class FoodGroup {
	protected int FoodGroupId;
	protected String FoodGroupDesc;
	public FoodGroup(int foodGroupId, String foodGroupDesc) {
		super();
		FoodGroupId = foodGroupId;
		FoodGroupDesc = foodGroupDesc;
		
	}
	
	
	public FoodGroup(String foodGroupDesc) {
		super();
		FoodGroupDesc = foodGroupDesc;
	}

	public int getFoodGroupId() {
		return FoodGroupId;
	}
	public void setFoodGroupId(int foodGroupId) {
		FoodGroupId = foodGroupId;
	}
	public String getFoodGroupDesc() {
		return FoodGroupDesc;
	}
	public void setFoodGroupDesc(String foodGroupDesc) {
		FoodGroupDesc = foodGroupDesc;
	} 
	

}
