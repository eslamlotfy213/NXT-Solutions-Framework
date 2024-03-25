package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.automationexercise.Utilities.Utility.*;

public class UserDetails {

    private WebDriver driver;

    public UserDetails(WebDriver driver) {

            this.driver = driver;

    }




    private By firstNameText = By.id("firstName");

    private By lastNameText = By.id("lastName");

    private By titleText = By.id("title");


    private By organizationText = By.id("organization");

    private By PhoneNumberText = By.xpath("//input[@placeholder='Phone Number']");

    private By emailText = By.id("email");


    private By createButton = By.xpath("//button[contains(.,'Create')]");
    private By cancelButton = By.xpath("//button[contains(.,'Cancel')]");

    private By UserTypeDropDown= By.id("userType");


    private  By successMessage = By.xpath("//div[@role='status']");




    public TripInfo fillUserDetails(String firstname,String lastname, String title, String organization, String userType)
    {

        sendData(driver, firstNameText, firstname + generateRandomChars ("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 5));
        sendData(driver, lastNameText, lastname+ generateRandomChars ("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 5));
        sendData(driver, titleText, title);
        sendData(driver, organizationText, organization);
        sendData(driver, PhoneNumberText, String.valueOf(generateRandomNumber(1111111111)));
        sendData(driver, emailText, "example" + generateRandomChars ("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 5)+ "@gmail.com");
        selectFromDropDown( driver,UserTypeDropDown ,userType);
        scrollToElement(driver, createButton);
        clicking(driver, createButton);

        return new TripInfo(driver);

    }


    public String getSuccessMessage()
    {
        return   getText(driver, successMessage);
    }







}
