package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	
	//@Test(groups={"Regression","Master"})
	@Test
	public void verify_acount_registration() throws InterruptedException
	{
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		logger.debug("This is a debug log message");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickRegister();
		logger.info("Clicked on Registration page..");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer details..");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		
		String password=randomAlphaNumeric();
		regpage.setTelephone(randomNumber());
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		
		Thread.sleep(5000);
		regpage.clickContinue();
	//
		logger.info("Validing expected message..");
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test dailed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		finally {
		logger.info("**** Finish TC001_AccountRegistrationTest ****");
		}
	}

	
	
	

}
