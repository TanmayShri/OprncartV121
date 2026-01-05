package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j

public class BaseClass {
	static public WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups={"sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		
		//Loading config.property file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());//pass the class here this is the statement which we have to use to load the log4j to xml file so this will automaticaly get the log4j xml file 
		//logger variable we have to use to generate the logs
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities(); 
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			switch(br.toLowerCase())
			{
			case "chrome" :driver=new ChromeDriver(); break;
			case "edge" :driver=new EdgeDriver(); break;
			default : System.out.println("invalid browser name.."); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wb/hub"),capabilities);
		
		}
		if(p.getProperty("executio_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" :driver=new ChromeDriver(); break;
			case "edge" :driver=new EdgeDriver(); break;
			default : System.out.println("invalid browser name.."); return;
			}
		}
		
		
		
		
		
		switch(br.toLowerCase())
		{
		
		case "chrome": driver=new ChromeDriver(); break;
//		case "edge": driver=new EdgeDriver(); break;
//		case "firefox": driver=new FirefoxDriver(); break;
//		default : System.out.print("Invalid browser name.."); return;
		}
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL2"));//reading url from properties file
		driver.manage().window().maximize();
	}
	@AfterClass(groups={"sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
	     return generatedString;
	}
	public String randomNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
	     return generatedNumber;
	}
	public String randomAlphaNumeric()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		String generatedNumber=RandomStringUtils.randomNumeric(10);
	     return (generatedString+"@"+generatedNumber);
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
	
}
