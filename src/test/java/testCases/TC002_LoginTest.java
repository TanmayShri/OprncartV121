package testCases;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"sanity","Master"})
	public void verify_login()
	{
		
		
		logger.info("***Starting TC_002****");
		try {
		//home page
	    HomePage hp=new HomePage(driver);
	    hp.clickMyaccount();
	    hp.clickLogin();
	    //login page
	    LoginPage lp=new LoginPage(driver);
	    lp.setEmail(p.getProperty("email"));
	    lp.setPassword(p.getProperty("password"));
	    lp.clickLogin();
	    //myaccount
	    MyAccountPage macc=new MyAccountPage(driver);
	    boolean targetpage=macc.isMyAcountPageExists();
	    Assert.assertEquals(targetpage, true);
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***finished TC_002****");
	}

}
