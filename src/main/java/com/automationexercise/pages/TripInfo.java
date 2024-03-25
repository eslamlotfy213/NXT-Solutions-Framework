package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.automationexercise.Utilities.Utility.*;
import static com.automationexercise.Utilities.Utility.scrollToElement;
import static com.automationexercise.Utilities.WaitsUtils.waitForElementPresent;

public class TripInfo {

    private WebDriver driver;

    public TripInfo(WebDriver driver) {

            this.driver = driver;

    }






    private final By tripInfoTab = By.xpath("//p[normalize-space()='Trip Info']");

    private final By successMessage = By.xpath("//div[@role='status']");

    private final By  addTripButton = By.xpath("//p[text()='Add Trip']");


    private final By  createButton = By.xpath("//button[@class='Button_root__0RbKd Button_primary__NGIIN Button_xl__w2v80']");

    private final By  dropDownListPackages = By.cssSelector("[id='entity']");





    public void addUserTrip(String packagesName)
    {
        reloadPage(driver);
        scrollToElement(driver,tripInfoTab);
        clicking(driver,tripInfoTab);
        scrollToElement(driver,addTripButton);
        clicking(driver,addTripButton);
        selectFromDropDown(driver,dropDownListPackages,packagesName);
        scrollToElement(driver,createButton);
        clicking(driver,createButton);

    }


    public String getSuccessMessage()
    {
        return   getText(driver, successMessage);
    }







}
