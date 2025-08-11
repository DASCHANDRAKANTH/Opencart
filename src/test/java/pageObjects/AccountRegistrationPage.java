package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage  extends BasePage{
	
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver ) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkAgree;
	
	// Locate Continue button
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	// Locate Confirmation Message 
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;  

	
	
	public void enterFirstname(String firstname) {
	    txtFirstname.clear();
	    txtFirstname.sendKeys(firstname);
	}

	public void enterLastname(String lastname) {
	    txtLastname.clear();
	    txtLastname.sendKeys(lastname);
	}

	public void enterEmail(String email) {
	    txtEmail.clear();
	    txtEmail.sendKeys(email);
	}

	public void enterTelephone(String telephone) {
	    txtTelephone.clear();
	    txtTelephone.sendKeys(telephone);
	}

	public void enterPassword(String password) {
	    txtPassword.clear();
	    txtPassword.sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
	    txtConfirmPassword.clear();
	    txtConfirmPassword.sendKeys(confirmPassword);
	}

	public void clickAgree() {
	    if (!chkAgree.isSelected()) {
	        chkAgree.click();
	    }
	}

	public void clickContinue() {
	    btnContinue.click();
	    
//	    or  
		/*
		 * // s-2 btnContinue.submit();
		 * 
		 * // s-3 Actions act = new Actions(driver);
		 * act.moveToElement(btnContinue).click().build().perform();
		 * 
		 * //s-4
		 * 
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguements[0].click();", btnContinue);
		 * 
		 * // s-5 btnContinue.sendKeys(Keys.RETURN);
		 * 
		 * // s-6 WebDriverWait mywait = new WebDriverWait(driver,
		 * Duration.ofSeconds(10));
		 * mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		 * 
		 */  
	    
	}

	public String getConfirmationMessage() {
	   try {
		   
		   return msgConfirmation.getText();
		   
	} catch (Exception e) {
		// TODO: handle exception
		return (e.getMessage());
	}
	}

	
	
	
	
	
}
