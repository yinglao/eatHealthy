package eathealthy.model;

public class Footnote {
	protected int FootnoteId;
	protected Food food;
	protected Nutrient nutrient;
	protected FootnoteType footnoteType;
	protected String text;
	
	public enum FootnoteType{
		D, N, M
	}

	public Footnote(int footnoteId, Food food, Nutrient nutrient, FootnoteType footnoteType, String text) {
		super();
		FootnoteId = footnoteId;
		this.food = food;
		this.nutrient = nutrient;
		this.footnoteType = footnoteType;
		this.text = text;
	}

	public Footnote(Food food, Nutrient nutrient, FootnoteType footnoteType, String text) {
		super();
		this.food = food;
		this.nutrient = nutrient;
		this.footnoteType = footnoteType;
		this.text = text;
	}

	public int getFootnoteId() {
		return FootnoteId;
	}

	public void setFootnoteId(int footnoteId) {
		FootnoteId = footnoteId;
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

	public FootnoteType getFootnoteType() {
		return footnoteType;
	}

	public void setFootnoteType(FootnoteType footnoteType) {
		this.footnoteType = footnoteType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

}
