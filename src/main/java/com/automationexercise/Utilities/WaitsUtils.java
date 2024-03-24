package com.automationexercise.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Function;

public class WaitsUtils {

    private static WebDriver driver;

    public WaitsUtils(WebDriver driver)
    {

        this.driver = driver;
    }


    public  static void fluentWaitForElement(By findBy){

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofSeconds(5L))
                .ignoring(NoSuchElementException.class);
        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (driver.findElement(findBy).isDisplayed())
                {
                    return driver.findElement(findBy);
                }else{
                    return  null;
                }
            }
        });
    }

    public static void implicitlyWait() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(DataUtils.getConfigValue("config", "WAIT_IMPLICIT"))));
    }

    //TODO: General Explicit Wait
    public static WebDriverWait generalWait(WebDriver driver, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    //TODO:  Explicit Wait For Clickability
    public static void explicitlyWaitForClickability(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(DataUtils.getConfigValue("config", "WAIT_EXPLICIT"))))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    //TODO:  Explicit Wait For Visibility
    public static void explicitlyWaitForVisibility(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(DataUtils.getConfigValue("config", "WAIT_EXPLICIT"))))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for the given element to present
     *
     * @param by an element of object type By
     * @return an existing WebElement object
     */
    public static WebElement waitForElementPresent(By by) {

        try {
            return generalWait(driver, Integer.parseInt(DataUtils.getConfigValue("config", "WAIT_EXPLICIT")))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
        }
        return null;
    }

    public static boolean waitForElementHasAttribute(By by, String attributeName) {
        try {
            return generalWait(driver, Integer.parseInt(DataUtils.getConfigValue("config", "WAIT_EXPLICIT")))
                    .until(ExpectedConditions.attributeToBeNotEmpty(Objects.requireNonNull(waitForElementPresent(by)), attributeName));
        } catch (Throwable error) {
            LogUtils.error("Timeout for element " + by.toString() + " to exist attribute: " + attributeName);
        }
        return false;
    }


}
