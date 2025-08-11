
package testCases;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.xmlbeans.impl.repackage.Repackage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testbase.Baseclass;

public class tcAccount_reg_page extends Baseclass {

	
		
	
	
	@Test(groups = {"Regression", "master"})
	public void verify_account_registration()  {
	try {
		logger.info(" **** starting account reg page test case ****");
		HomePage hp = new HomePage(driver);
		logger.info("click on my ac ");
		hp.clickMyAccount();
		logger.info(" click on register ");
		hp.clickRegister();
		logger.info(" providing customerr details ");
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		regpage.enterFirstname(randomString().toLowerCase());
		regpage.enterLastname(randomString().toLowerCase());
		regpage.enterEmail(randomString()+"@agmail.com");
		regpage.enterTelephone(randomnbr());
		String pwd = alphanumeric();
		regpage.enterPassword(pwd);
		regpage.enterConfirmPassword(pwd);
		regpage.clickAgree();
		regpage.clickContinue();
		
		logger.info(" validating expected messages ");
		
		
		String confmsg = regpage.getConfirmationMessage();
		
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
	
	
	
	
	
	
	
	} catch(Exception e ) {
		
		logger.error(" test failed ");
		logger.debug("debug log");
		Assert.fail();
		logger.info("tc loggerpage finish ");
		
		
	}
	
	
	
	}
}
