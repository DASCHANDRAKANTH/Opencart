package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	//locaters mail pwd click 
	//methods 
	
	@FindBy(xpath = ("//input[@id='input-email']"))
	WebElement email;
	
	@FindBy(id = "input-password")
	WebElement passwordInput;
	
    @FindBy(xpath = "//input[@type='submit' and @value='Login']")
    WebElement loginButton;

    
    public void setmail(String mail) {
    	email.sendKeys(mail);
		
	}
    
    public void setpwd(String pwd) {
    	passwordInput.sendKeys(pwd);
		
	}
    
    public void clickLogin() {
        loginButton.click();
    }

	

}
