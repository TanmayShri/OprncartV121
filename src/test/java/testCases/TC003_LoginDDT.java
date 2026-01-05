package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")//dataProviders is in different class and different package so need to provide one more parameter i.e dataProviderClass=DataProviders.class (in which class this data provider is present inside data providerclass)
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		
		logger.info("**Starting TC_003_LoginDDT***");
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAcountPageExists();
		
		/*
		 Data is valid- login success - test pass -logout
		                login failed - test fail
		 Data is invalid- login success - test fail -logout
		                  login failed - test fail 
		  
		  */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				Assert.assertTrue(true);
				macc.clickLogout();
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
			
		}
		logger.info("**Finished TC_003_LoginDDT***");                                                                   
			
	}
}
