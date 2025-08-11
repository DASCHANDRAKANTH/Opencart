package testCases;





import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.mypageobjects;
import pageObjects.AccountRegistrationPage;


import testbase.Baseclass;





public class verifyMyacc extends Baseclass{
	
	  
	@Test(groups ="1")
public void testverifyMyaccount() {
	try {
	logger.info("test case 002 starting verify my ac ");
	
HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
 LoginPage lp = new LoginPage(driver);
 lp.setmail(p.getProperty("email"));
 lp.setpwd(p.getProperty("password"));
 lp.clickLogin();
 
    mypageobjects mp = new mypageobjects(driver);
   boolean txtpage =  mp.isMyacpageexist();
//	Assert.assertEquals(txtpage, true,"loginfailed");
  Assert.assertTrue(txtpage);
  
	
	} catch (Exception e) {
//		 TODO: handle exception
		logger.info("**** fail *****");
		Assert.fail();
	}
	
	
	
}

}
