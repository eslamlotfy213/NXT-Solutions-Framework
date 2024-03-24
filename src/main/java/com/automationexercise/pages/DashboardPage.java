package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.automationexercise.Utilities.Utility.*;

public class DashboardPage {


    private  WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;

    }
    private  By dashboardHeader = By.xpath("//h2[text()='Dashboard']");


    private By mainMenu_list(String mainMenu) {

        return By.xpath("//span[text()='" + mainMenu + "']");
    }


    private By subMenu_list(String subMenu) {

        return By.xpath("//p[text()='" + subMenu + "']");
    }




    public String getDashboardHeader()
    {

        return   getText(driver, dashboardHeader);
    }


    public void hoverForElement(String mainMenu_Item, String subMenu_Item){
        hoverForElementText(driver,mainMenu_list(mainMenu_Item),subMenu_list(subMenu_Item));

    }





}