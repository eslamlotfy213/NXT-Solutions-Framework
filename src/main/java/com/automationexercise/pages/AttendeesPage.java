package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.automationexercise.Utilities.Utility.*;
import static com.automationexercise.Utilities.Utility.scrollToElement;

public class AttendeesPage {


    private WebDriver driver;

    public AttendeesPage(WebDriver driver) {
        this.driver = driver;
    }



    private By createGroupButton = By.xpath("//button[contains(.,'Create Group')]");
    private By saveButton = By.xpath("//button[contains(.,'Save')]");
    private By cancelButton = By.xpath("//button[contains(.,'Cancel')]");

    private  By allGroupsHeader = By.xpath("//h2[text()='All Groups']");

    private  By groupNameText = By.xpath("//input[@name='groupName']");

    private  By emailText = By.xpath("//input[@id='CC']");


    private By AddUserButton = By.xpath("//span[contains(.,'Add User')]");


    private  By successMessage = By.xpath("//div[@role='status']");

    private By tableUsers(String groupName) {

        return By.xpath("//p[text()='" + groupName + "']");
    }




    public void createNewGroup(String groupName)
    {
        clicking(driver, createGroupButton);
        sendData(driver, groupNameText, groupName);
        sendData(driver, emailText, "example" + getSimpleTimestamp() + "" + "@gmail.com");
        clicking(driver, saveButton);

    }



    public String getGroupsHeader()
    {

        return   getText(driver, allGroupsHeader);
    }



    public AttendeesPage clickOnTableUsers(String groupName)
    {

        clicking(driver,tableUsers(groupName));
        return new AttendeesPage(driver);
    }



    public UserDetails clickOnAddUser()
    {
        clicking(driver,AddUserButton);
        return new UserDetails(driver);
    }


    public String getSuccessMessage()
    {
        return   getText(driver, successMessage);
    }
}