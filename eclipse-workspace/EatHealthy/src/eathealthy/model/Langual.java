package eathealthy.model;

public class Langual {
	protected int langualId;
	protected Food food;
	protected LangualDescription langualDescription;
	public Langual(Food food, LangualDescription langualDescription) {
		super();
		this.food = food;
		this.langualDescription = langualDescription;
	}
	public Langual(int langualId, Food food, LangualDescription langualDescription) {
		super();
		this.langualId = langualId;
		this.food = food;
		this.langualDescription = langualDescription;
	}
	public int getLangualId() {
		return langualId;
	}
	public void setLangualId(int langualId) {
		this.langualId = langualId;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public LangualDescription getLangualDescription() {
		return langualDescription;
	}
	public void setLangualDescription(LangualDescription langualDescription) {
		this.langualDescription = langualDescription;
	}
	
	

}
