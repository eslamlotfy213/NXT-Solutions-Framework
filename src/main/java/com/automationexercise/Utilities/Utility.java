package com.automationexercise.Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


public class Utility {

    private static WebDriver driver;

    public Utility(WebDriver driver)
    {

        this.driver = driver;
    }


    //TODO: Clicking on element after checking clickability
    public static void clicking(WebDriver driver, By locator) {
        WaitsUtils.explicitlyWaitForClickability(driver, locator);
        findWebElement(driver, locator).click();
    }

    //TODO: Send data to element after checking visibility
    public static void sendData(WebDriver driver, By locator, String data) {
        WaitsUtils.explicitlyWaitForVisibility(driver, locator);
        findWebElement(driver, locator).sendKeys(data);
    }



    //TODO: get text from element after checking visibility
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return findWebElement(driver, locator).getText();

    }


    public static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd_h-m-ssa").format(new Date());
    }

    public static String getSimpleTimestamp() {
        return new SimpleDateFormat("h-m-ssa").format(new Date());
    }


    //TODO:  Scroll to specific element
    public static void scrollToElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) (driver)).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", findWebElement(driver, locator));
    }

    public static void scrollToElementAtTop(WebDriver driver, By locator) {
        ((JavascriptExecutor) (driver)).executeScript("arguments[0].scrollIntoView(true);", findWebElement(driver, locator));
    }

    public static void scrollToElementAtBottom(WebDriver driver, By locator) {
        ((JavascriptExecutor) (driver)).executeScript("arguments[0].scrollIntoView(false);", findWebElement(driver, locator));
    }

    //TODO:  Scroll to specific position
    public static void scrollToPosition(WebDriver driver, int x, int y) {
        ((JavascriptExecutor) (driver)).executeScript("window.scrollTo(" + x + ", " + y + ");");
    }

    // Method to zoom out using JavaScript
    public static void zoomOut(WebDriver driver, int zoomFactor) {
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '" + zoomFactor + "%'");
    }

    /**
     * Verify if the given web element is visible.
     *
     * @param by Represent a web element as the By object
     * @return true/false
     */
    public static boolean verifyElementVisible(By by) {
        try {
            WaitsUtils.explicitlyWaitForVisibility(driver, by);
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    //Convert Locator to Web Element
    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    /**
     * Find multiple elements with the locator By object
     *
     * @param by     is an element of type By
     * @param driver is an element of Web driver
     * @return Returns a List of WebElement objects
     */
    public static List<WebElement> findWebElements(WebDriver driver, By by) {
        return driver.findElements(by);
    }

    //TODO: Function for getting selected option from drop down
    public static WebElement getSelectedOptionFromDropDown(WebDriver driver, By locator) {
        return new Select(Utility.findWebElement(driver, locator)).getFirstSelectedOption();
    }

    //TODO: Function for selecting from drop down
    public static void selectFromDropDown(WebDriver driver, By locator, String option) {
        new Select(Utility.findWebElement(driver, locator)).selectByVisibleText(option);
    }


    public static int generateRandomNumber(int upperBound) { //0 >> upper-1  > 5
        return new Random().nextInt(upperBound) + 1;
    }

    public static Set<Integer> generateUniqueNumber(int numberNeeded, int totalNumbers) {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numberNeeded) {
            int randomNumber = generateRandomNumber(totalNumbers);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }



    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies)
            driver.manage().addCookie(cookie);
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

    /**
     * Maximize window
     */
    public static void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * Minimize window
     */
    public static void minimizeWindow() {
        driver.manage().window().minimize();
    }

    /**
     * Get the JavascriptExecutor object created
     *
     * @return JavascriptExecutor
     */
    public static JavascriptExecutor getJsExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    /**
     * Reload the current web page.
     */
    public static void reloadPage(WebDriver driver) {
        driver.navigate().refresh();
        LogUtils.info("Reloaded page " + driver.getCurrentUrl());
    }



    /**
     * Return page title
     *
     *
     */
    public static String pageTitle()
    {
      return  driver.getTitle();
    }




    /**
     * Return hover For Element
     *
     *
     */
    public static void hoverForElementText(WebDriver driver,By mainLocator , By subLocator)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(mainLocator)).click().build().perform();
        actions.moveToElement(driver.findElement(subLocator)).click().build().perform();;
    }


}
