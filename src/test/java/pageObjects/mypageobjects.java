package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class mypageobjects extends BasePage{

	WebDriver driver;
	public mypageobjects(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccountHeading;

	
	public boolean isMyacpageexist() {
		try {
		return myAccountHeading.isDisplayed();
		} 
		catch (Exception e) {
					 return false;
				}
				
				
				
	}
	
	

	
	
	
	
	
	
	
	
	

}
