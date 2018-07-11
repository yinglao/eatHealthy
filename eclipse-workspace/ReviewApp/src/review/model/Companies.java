package review.model;

public class Companies {
	protected String CompanyName;
	protected String About;
	public Companies(String companyName, String about) {
		super();
		CompanyName = companyName;
		About = about;
	}
	public Companies(String companyName) {
		super();
		CompanyName = companyName;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getAbout() {
		return About;
	}
	public void setAbout(String about) {
		About = about;
	}
	

}
