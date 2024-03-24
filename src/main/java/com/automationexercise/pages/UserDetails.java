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





    public void fillUserDetails(String firstname,String lastname, String title, String organization,int phoneNumber, String userType)
    {

        sendData(driver, firstNameText, firstname+ getTimestamp());
        sendData(driver, lastNameText, lastname+ getTimestamp());
        sendData(driver, titleText, title);
        sendData(driver, organizationText, organization);
        sendData(driver, PhoneNumberText, String.valueOf(phoneNumber));
        sendData(driver, emailText, "example" + getSimpleTimestamp() + "" + "@gmail.com");
        selectFromDropDown( driver,UserTypeDropDown ,userType);

        scrollToElement(driver, createButton);
        clicking(driver, createButton);

    }


    public String getSuccessMessage()
    {
        return   getText(driver, successMessage);
    }







}
