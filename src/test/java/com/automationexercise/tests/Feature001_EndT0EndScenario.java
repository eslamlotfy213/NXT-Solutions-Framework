package com.automationexercise.tests;

import com.automationexercise.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testData.DataReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class Feature001_EndT0EndScenario extends  BaseTest{



    @Test(dataProvider = "getData",priority = 1)
    public void EndT0EndScenario(HashMap<String,String> input) throws IOException
    {

        landingPage.LoginToApplication(input.get("username"), input.get("password"));
        EventsPage  eventsPage =  landingPage.clickOnSkipButton();
        eventsPage.CreateNewEvent(input.get("eventName"),input.get("eventSlug"),input.get("eventType"),input.get("timeZone"));
        EventsTablePage eventsTablepage =  eventsPage.clickCreateEvent();
        Assert.assertEquals(eventsPage.getSuccessMessage(),input.get("eventMessage"));


        DashboardPage dashboardPage= eventsTablepage.clickOnTableCheck(input.get("eventName"));
        Assert.assertEquals(dashboardPage.getDashboardHeader(),input.get("dashboardHeader"));


        //package page
        dashboardPage.hoverForElement(input.get("MainMenuItem_Planning"),input.get("SubMenuItem_Packages"));
        PackagesPage packagesPage = new PackagesPage(driver);
        packagesPage.CreateNewPackage(input.get("pageTitle"),input.get("featureName"),input.get("userType"));
        Assert.assertEquals(packagesPage.getPackagedHeader(),input.get("packagesHeader"));


        //Registration
        dashboardPage.hoverForElement(input.get("MainMenuItem_Registration"),input.get("SecondSubMenuItem_Attendees"));
        AttendeesPage attendeesPage = new AttendeesPage(driver);
        attendeesPage.createNewGroup(input.get("groupName"));
        Assert.assertEquals(attendeesPage.getSuccessMessage(),input.get("groupMessage"));
        attendeesPage.clickOnTableUsers(input.get("groupName"));



        //user details
        UserDetails userDetails= attendeesPage.clickOnAddUser();
        userDetails.fillUserDetails(input.get("firstname"),input.get("lastname"),input.get("title"),input.get("organization"),9999999,input.get("userTypes"));
        Assert.assertEquals(userDetails.getSuccessMessage(),"User created successfully");

    }

















    @DataProvider
    public Object[][] getData() throws IOException
    {
        DataReader dataReader = new DataReader();
        List<HashMap<String, String>> data=  dataReader.getJsonDataToMap();;
        return new Object[][]
                {{data.get(0)}};
    }


}








