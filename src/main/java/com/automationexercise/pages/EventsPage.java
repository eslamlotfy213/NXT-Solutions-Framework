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

    private By StartFromScratch_Button = By.cssSelector("div > [role='button']:nth-child(1)");

    private By EventName_text = By.xpath("(//input[@class='Input_root__fi0ZK'])[1]");

    private By EventSlug_text = By.xpath("(//input[@class='Input_root__fi0ZK'])[2]");

    private By EventType_dropdownlist= By.xpath("//div[@class='flex items-center space-x-s20']//select[@class='Input_root__fi0ZK Input_selectInput__aHlvF']");

    private By Timezone_dropdownlist= By.xpath("//div[@class='flex flex-col w-full pt-8 sm:pt-0']//select[@class='Input_root__fi0ZK Input_selectInput__aHlvF']");



    private By StartDate = By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(7) > main:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)");


    private By End_Date = By.cssSelector("div[data-tip='true'] input[type='date']");

    private By Start_Time = By.xpath("(//input[@type='time'])[1]");

    private By End_Time= By.cssSelector("div[data-tip='true'] input[type='time']");

    private By clickCreateButton = By.xpath("//button[text()='Create']");


    private  By successMessage = By.xpath("//div[@role='status']");


    public EventsPage CreateNewEvent(String eventName, String eventSlug,String eventType,String timeZone)
    {
     clicking(driver,newEvent_Button);
     clicking(driver,StartFromScratch_Button);
     sendData(driver,EventName_text,eventName);
     sendData(driver,EventSlug_text,eventSlug+ getTimestamp());


     selectFromDropDown( driver,EventType_dropdownlist ,eventType);

     driver.findElement(StartDate).sendKeys("03232024");
     driver.findElement(End_Date).sendKeys("04232024");


     driver.findElement(Start_Time).sendKeys("1111PM");
     driver.findElement(End_Time).sendKeys("1211PM");


     selectFromDropDown( driver,Timezone_dropdownlist ,timeZone);


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
