package com.tdc.utilities;//package com.tdcIP.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class BrowserUtils {

    private static WebDriver driver = Driver.get();

    public static void sleep(int second){
        second *=1000;
        try {
            Thread.sleep(second);
        }catch (InterruptedException e ) {

        }
    }


    public static String getURL(){
        return Driver.get().getCurrentUrl();
    }

    //wait for an element to be clickable (with web element)
    public static void waitClickability(WebElement element, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //wait for an element to be clickable (with By locator)
    public static void waitClickability(By locator, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //wait for clickability of an element then click
    public static void clickWithWait(WebElement element, int timeOut){
        waitClickability(element,timeOut);
        element.click();
    }

    public static void waitUntilNumberOfWindows(int number){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(number));
    }

    public static void sendKeysWithWait(WebElement field, String text, int timeOut){
        waitForVisibility(field,timeOut);
        field.sendKeys(text);
    }


    //wait till URL contains a specific text
    public static void waitForURLContains(String urlPart, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.   until(ExpectedConditions.urlContains(urlPart));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //wait for visibility of a web element
    public static void waitForVisibility(WebElement element, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //wait for visibility of a web element
    public static void waitForVisibility(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //static wait - Thread.sleep()
    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //static wait - Thread.sleep()
    public static void wait(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //wait till a new window gets opened
    public static void waitForNewWindow() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 4);
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //switch to another window by passing index number
    public static void switchToWindow(int index) {
        try {
            waitForNewWindow();
            Set<String> windowHandles = driver.getWindowHandles();
            ArrayList<String> allTabs = new ArrayList<>(windowHandles);
            driver.switchTo().window(allTabs.get(index));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //wait for a web element till has a specific text
    public static void waitForText(WebElement element, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 6);
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //switch to iframe
    public static void switchToFrame(WebElement frame) {
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
//		driver.switchTo().frame(frame);
    }

    //click on a web element using JSexecutor
    public static void clickWithJSExe(WebElement element) {
        waitClickability(element, 3);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    //attempt to click on a web element a couple of times
    public static void clickManyTimes(WebElement element) {
        waitClickability(element, 3);
        for (int i = 0; i < 3; i++) {
            try {
                element.click();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
    }

    //open a new tab using JSexecutor
    public static void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open();");
    }

    //scroll into an element
    public static void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //scroll into an element
    public static void scrollToElement(By by) {
        try {
            WebElement element = driver.findElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
