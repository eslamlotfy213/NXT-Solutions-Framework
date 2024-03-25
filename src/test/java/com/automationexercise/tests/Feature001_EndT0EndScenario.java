package com.automationexercise.tests;

import com.automationexercise.pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testData.DataReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class Feature001_EndT0EndScenario extends  BaseTest{

    EventsPage  eventsPage;
    EventsTablePage eventsTablepage;
    DashboardPage dashboardPage;
    PackagesPage packagesPage;
    AttendeesPage attendeesPage;
    UserDetails userDetails;

    @Description("userStory_#001 check the user is able to login and create a  attendees to a trip")
    @Test(dataProvider = "getData",priority = 1)
    public void EndT0EndScenario(HashMap<String,String> input) throws IOException
    {

        landingPage.LoginToApplication(input.get("username"), input.get("password"));
        eventsPage =  landingPage.clickOnSkipButton();
        eventsPage.createNewEvent(input.get("eventName"),input.get("eventSlug"),input.get("eventType"),input.get("start_Date"),input.get("end_Date"),input.get("start_Time"),input.get("end_Time"),input.get("timeZone"));
        eventsTablepage =  eventsPage.clickCreateEvent();
        Assert.assertEquals(eventsPage.getSuccessMessage(),input.get("eventMessage"));


        dashboardPage= eventsTablepage.clickOnTableCheck(input.get("eventName"));
        Assert.assertEquals(dashboardPage.getDashboardHeader(),input.get("dashboardHeader"));



        dashboardPage.navigationToSideBar(input.get("MainMenuItem_Planning"),input.get("SubMenuItem_Packages"));
        packagesPage = new PackagesPage(driver);
        packagesPage.createNewPackage(input.get("pageTitle"),input.get("featureName"),input.get("userType"));
        Assert.assertEquals(packagesPage.getPackagedHeader(),input.get("packagesHeader"));



        dashboardPage.navigationToSideBar(input.get("MainMenuItem_Registration"),input.get("SecondSubMenuItem_Attendees"));
        attendeesPage = new AttendeesPage(driver);
        attendeesPage.createNewGroup(input.get("groupName"));
        Assert.assertEquals(attendeesPage.getSuccessMessage(),input.get("groupMessage"));
        attendeesPage.clickOnTableUsers(input.get("groupName"));


        userDetails= attendeesPage.clickOnAddUser();
        TripInfo tripInfo =  userDetails.fillUserDetails(input.get("firstname"),input.get("lastname"),input.get("title"),input.get("organization"),input.get("userTypes"));
        Assert.assertEquals(userDetails.getSuccessMessage(),"User created successfully");
        tripInfo.addUserTrip(input.get("packageName"));
        Assert.assertEquals( tripInfo.getSuccessMessage(),input.get("tripMessage"));


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








