package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.automationexercise.Utilities.Utility.clicking;

public class EventsTablePage {


    private WebDriver driver;

    public EventsTablePage(WebDriver driver) {
        this.driver = driver;

    }

    private By tableCheckbox(String eventName) {

        return By.xpath("//td[.='" + eventName + "']//parent::tr");
    }


    public DashboardPage clickOnTableCheck(String eventName)
    {
        clicking(driver,tableCheckbox(eventName));
        return new DashboardPage(driver);
    }


}