package eathealthy.model;

public class LangualDescription {
	protected String LangualFactor;
	protected String Description;
	public LangualDescription(String langualFactor, String description) {
		super();
		LangualFactor = langualFactor;
		Description = description;
	}
	public String getLangualFactor() {
		return LangualFactor;
	}
	public void setLangualFactor(String langualFactor) {
		LangualFactor = langualFactor;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	

}
