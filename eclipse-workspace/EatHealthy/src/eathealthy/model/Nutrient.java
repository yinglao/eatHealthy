package eathealthy.model;

public class Nutrient {
	protected int NutrientId;
	protected String Units;
	protected String TagName;
	protected String Description;
	public Nutrient(int nutrientId, String units, String tagName, String description) {
		super();
		NutrientId = nutrientId;
		Units = units;
		TagName = tagName;
		Description = description;
	}
	public int getNutrientId() {
		return NutrientId;
	}
	public void setNutrientId(int nutrientId) {
		NutrientId = nutrientId;
	}
	public String getUnits() {
		return Units;
	}
	public void setUnits(String units) {
		Units = units;
	}
	public String getTagName() {
		return TagName;
	}
	public void setTagName(String tagName) {
		TagName = tagName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	

    
}
