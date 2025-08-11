package testbase;

import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
// ... other imports
//import java.util.logging.LogManager;

import org.apache.commons.io.FileUtils;
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
import org.testng.annotations.Parameters; // Add this import

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Baseclass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"os", "browser"}) // Recommended: Add this annotation
    public void setup(String os, String browser) throws IOException { // Changed 'br' to 'browser'
         
         logger = LogManager.getLogger(this.getClass());

        // Read config.properties
        FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        String executionEnv = p.getProperty("execuationenv");
        		
   

        if (executionEnv.equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Set platform
            if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else if (os.equalsIgnoreCase("chrome")) {
                capabilities.setPlatform(Platform.ANDROID);
            }

            // Set browser
            if (browser.equalsIgnoreCase("chrome")) { // Use 'browser'
                capabilities.setBrowserName("chrome");
            } else if (browser.equalsIgnoreCase("edge")) { // Use 'browser'
                capabilities.setBrowserName("MicrosoftEdge");
            } else if (browser.equalsIgnoreCase("firefox")) { // Use 'browser'
                capabilities.setBrowserName("firefox");
            }

            try {
//                driver = new RemoteWebDriver(new URL("\"http://192.168.15.61:4444/wd/hub"), capabilities);
//                driver = new RemoteWebDriver(new URL("http://192.168.15.61:4444/wd/hub"), capabilities);
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else if (executionEnv.equalsIgnoreCase("local")) {
            // Local execution
            if (browser.equalsIgnoreCase("chrome")) { // Use 'browser'
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("edge")) { // Use 'browser'
                driver = new EdgeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) { // Use 'browser'
                driver = new FirefoxDriver();
            }
        }

        driver.manage().window().maximize();
        driver.get(p.getProperty("appURL2"));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomString() {
        return org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(5);
    }

    public String randomnbr() {
        return org.apache.commons.lang3.RandomStringUtils.randomNumeric(10);
    }

    public String alphanumeric() {
  
//    	\B 
        return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(8);
    }

    public String captureScreen(String tname) throws IOException {
        Date date = new Date();
        String timestamp = date.toString().replace(":", "-").replace(" ", "-");

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targetPath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timestamp + ".png";

        File target = new File(targetPath);
        FileUtils.copyFile(src, target);

        return targetPath;
    }
}