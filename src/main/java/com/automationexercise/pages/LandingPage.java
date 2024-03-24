package com.automationexercise.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.automationexercise.Utilities.Utility.*;

public class LandingPage{


   private WebDriver driver;
    public LandingPage(WebDriver driver){
        this.driver=driver;

    }


    //locators

     private By usernameField = By.id("username");
     private By passwordField  = By.id("password");

     private By loginButton = By.xpath("//button[@type='submit']");

     private By errorMessage = By.cssSelector("div >p");


     private By skipButton = By.cssSelector("button[type='button']");

     public static final String ExpectedPageTitle = "Events | AWS";

     //Actions
     public String getErrorMessage()
     {

         return getText(driver,errorMessage);
     }





    public LandingPage LoginToApplication(String username, String password)
    {
        sendData(driver,usernameField,username);
        sendData(driver,passwordField,password);
        clicking(driver,loginButton);
        return new LandingPage(driver);
    }


    public EventsPage clickOnSkipButton(){

        clicking(driver,skipButton);
        return new EventsPage(driver);
    }



}
