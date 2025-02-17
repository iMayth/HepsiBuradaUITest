package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public abstract  class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    String actualUrlResult;
    static org.apache.log4j.Logger Logger= LogManager.getLogger(BrowserUtils.class);

    public void openMainURL() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");

        Driver.get().get(ConfigReader.getInstance().getUrl());
        Logger.info("URL is opened");

    }

    public String getPageTitle(){
        return Driver.get().getTitle();
    }

    public String getText(By locator) {
        waitUntilElementVisible(locator);
        String text=Driver.get().findElement(locator).getText();
        Logger.info("Successfully got the text of the element");
        return text;
    }

    public static WebElement waitUntilElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        Logger.info("Waiting for the element to be visible");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static WebElement waitUntilElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        Logger.info("Waiting for the element to be visible");
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilElementClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        Logger.info("Waiting for the element to be clickable");
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void click(By locator) {
        waitUntilElementVisible(locator);
        Driver.get().findElement(locator).click();
        Logger.info("Successfully clicked the element");
    }

    public int randomNumberGenerator(int min, int max){
        int randomNumber = (int) (Math.random() * (max - min + 1) + min);
        Logger.info("Random number has been generated");
        return randomNumber;
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
            wait.until(expectation);
            Logger.info("Page is successfully loaded");
        } catch (Throwable error) {
            Logger.error(error);
            error.printStackTrace();
        }
    }
    public void sendKeys(String keyword, By actual) {
        Driver.get().findElement(actual).sendKeys(keyword);
    }

    public void resultSelector(String keyword, By actual, String expected) {
        List<WebElement> results=Driver.get().findElements(actual);
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getText().equals("https://www."+keyword+".com")){
                actualUrlResult=results.get(i).getText();
                Assert.assertEquals(expected,actualUrlResult);
                Logger.info("Expected Url result matched with actual Url result");
                results.get(i).click();
            }
            break;
        }
    }
}
