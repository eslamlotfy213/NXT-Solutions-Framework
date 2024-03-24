package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.automationexercise.Utilities.Utility.*;

public class PackagesPage {


    private WebDriver driver;

    public PackagesPage(WebDriver driver) {
        this.driver = driver;
    }



    By NewPackageButton = By.xpath("//button[contains(.,'New Package')]");
    By saveButton = By.xpath("//button[contains(.,'Save')]");

    By cancelButton = By.xpath("//button[contains(.,'Cancel')]");

    By packageTitle_Text = By.cssSelector("#package_title");

    By packagePriority_Text = By.cssSelector("#package_priority");


    By feature_name_text = By.cssSelector("#feature_name");


    private  By packagedHeader = By.xpath("//h2[text()='Packages']");

    private  By packageTypeDropDown = By.id("package_type");

    private  By successMessage = By.xpath("//div[@role='status']");



    public void CreateNewPackage(String title ,String featureName,String userType)
    {

        clicking(driver,NewPackageButton);
        sendData(driver, packageTitle_Text,title);
        sendData(driver,packagePriority_Text,String.valueOf(generateRandomNumber(100)));
        sendData(driver,feature_name_text,featureName);
        selectFromDropDown( driver,packageTypeDropDown ,userType);
        scrollToElement(driver,saveButton);
        clicking(driver,saveButton);

    }



    public String getPackagedHeader()
    {
        return   getText(driver, packagedHeader);
    }
}
