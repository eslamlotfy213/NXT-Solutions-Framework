package com.automationexercise.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.automationexercise.Utilities.Utility.*;
import static com.automationexercise.Utilities.Utility.selectFromDropDown;


public class EventsPage {


   private WebDriver driver;
    public EventsPage(WebDriver driver){
        this.driver=driver;

    }


    private By newEvent_Button = By.xpath("//p[text()='New Event']");

    private By startFromScratch_Button = By.cssSelector("div > [role='button']:nth-child(1)");

    private By eventName_text = By.xpath("(//input[@class='Input_root__fi0ZK'])[1]");

    private By eventSlug_text = By.xpath("(//input[@class='Input_root__fi0ZK'])[2]");

    private By eventType_dropdownlist= By.xpath("//div[@class='flex items-center space-x-s20']//select[@class='Input_root__fi0ZK Input_selectInput__aHlvF']");

    private By timezone_dropdownlist= By.xpath("//div[@class='flex flex-col w-full pt-8 sm:pt-0']//select[@class='Input_root__fi0ZK Input_selectInput__aHlvF']");

    private By start_Date = By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(7) > main:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)");


    private By end_Date = By.cssSelector("div[data-tip='true'] input[type='date']");

    private By start_Time = By.xpath("(//input[@type='time'])[1]");

    private By end_Time= By.cssSelector("div[data-tip='true'] input[type='time']");

    private By clickCreateButton = By.xpath("//button[text()='Create']");


    private  By successMessage = By.xpath("//div[@role='status']");


    public EventsPage createNewEvent(String eventName, String eventSlug, String eventType,String startDate,String endDate,String startTime,String endTime,String timeZone)
    {
     clicking(driver,newEvent_Button);
     clicking(driver,startFromScratch_Button);
     sendData(driver,eventName_text,eventName);
     sendData(driver,eventSlug_text,eventSlug + getTimestamp());

     selectFromDropDown( driver,eventType_dropdownlist ,eventType);

     sendData(driver,start_Date,startDate);
     sendData(driver,end_Date,endDate);

     sendData(driver,start_Time,startTime);
     sendData(driver,end_Time,endTime);

     selectFromDropDown( driver,timezone_dropdownlist ,timeZone);
     return new EventsPage(driver);
    }


     public EventsTablePage clickCreateEvent()
     {
        scrollToElement(driver, clickCreateButton);
        clicking(driver, clickCreateButton);
        return  new EventsTablePage(driver);
     }


    public String getSuccessMessage()
    {
     return   getText(driver, successMessage);
    }


}
