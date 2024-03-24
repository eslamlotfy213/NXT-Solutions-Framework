package com.automationexercise.tests;

import com.automationexercise.Utilities.LogUtils;
import com.automationexercise.pages.LandingPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

     public  WebDriver driver;
     public LandingPage landingPage;
     public Properties pro;

     public WebDriver initializeDriver() {
        //BrowserName=chrome
         try {
             pro = new Properties();
             FileInputStream fis = new FileInputStream("src/test/java/resources/test-data/Global.properties");
             pro.load(fis);
             String browser =  System.getProperty("BrowserName") !=null ? System.getProperty("BrowserName") : pro.getProperty("BrowserName");
             if (browser.contains("chrome"))
             {
                 driver = new ChromeDriver();
             } else if (browser.equalsIgnoreCase("firefox")) {
                 driver = new FirefoxDriver();
             }else if (browser.equalsIgnoreCase("edge")) {
                 driver = new EdgeDriver();
             }else if (browser.equalsIgnoreCase("ie")){
                 driver = new InternetExplorerDriver();
             } else if (browser.contains("headless")) {
                 ChromeOptions options = new ChromeOptions();
                 options.addArguments("--headless");
                 driver = new ChromeDriver(options);
                 driver.manage().window().setSize(new Dimension(1440,900));
             }

             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
             driver.manage().window().maximize();

             return driver;
         } catch (IOException e) {

             e.printStackTrace();
             return null;
         }
     }




    @BeforeMethod(alwaysRun=true)
    public LandingPage launchApplication()
    {
        driver = initializeDriver();
        if (driver != null) {
            landingPage = new LandingPage(driver);
            driver.get(pro.getProperty("URL"));
            LogUtils.info("Open website with URL: "+ pro.getProperty("URL"));
            return landingPage;
        }
        // Handle the case when driver initialization fails
        return null;
    }




    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        if (driver != null) {
            driver.close();
        }
    }


    public String getScreenshot(String testCaseName, WebDriver driver){
        String screenshotPath = null;

        try {

            //take screenshot and save it in a file

            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            //copy the file to the required path

            File destinationFile = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");

            FileHandler.copy(sourceFile, destinationFile);

            String[] relativePath = destinationFile.toString().split("reports");

            screenshotPath = ".\\" + relativePath[1];

        } catch (IOException e) {

            System.out.println("Failure to take screenshot " + e);

        }

        return screenshotPath;

    }


}
