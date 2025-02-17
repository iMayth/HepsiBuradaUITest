package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.Driver;
import java.util.*;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[normalize-space()='Bilgisayar/Tablet']")
    public WebElement bilgisayartablet;

    @FindBy(xpath = "//span[@class='heroContent-wX4RJzHisIfuS5Xol0to']")
    public WebElement marka;

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement kabul;
    @FindBy(xpath = "//button[@data-test-id='addToCart']")
    public WebElement sepeteEkle;
    @FindBy(xpath = "//*[text()=' Ürün sepetinizde']")
    public WebElement onay;
    @FindBy(xpath = "(//button[normalize-space()='Sepete git'])")
    public WebElement sepeteGit;
    @FindBy(xpath = "(//div[@class='product_price_uXU6Q'])")
    public WebElement sepetFiyat;
    @FindBy(xpath = "//*[@data-test-id='default-price']")
    public WebElement detayFiyat;

    String productPrice;
    String cartPrice;

    public void acceptCookies() {
        kabul.click();
        waitForPageToLoad(5);
    }

    public void goToMain(String name) {

        Actions actions = new Actions(Driver.get());
        waitUntilElementVisible(By.xpath("//span[contains(text(),'Elektronik')]"));
        WebElement electronic = Driver.get().findElement(By.xpath("//span[contains(text(),'"+name+"')]"));
        actions.moveToElement(electronic);
        electronic.click();

    }

    public void goToSub(String name) {

        Actions actions = new Actions(Driver.get());
        actions.moveToElement(bilgisayartablet).perform();
        waitUntilElementVisible(By.linkText("" + name + ""));
        Driver.get().findElement(By.linkText("" + name + "")).click();

    }

    public void filterBrand(String name) {

        waitUntilElementVisible(marka);
        WebElement brand = Driver.get().findElement(By.xpath("//a[@title='"+name+" Tablet']"));
        brand.click();
        waitForPageToLoad(5);

    }

    public void filterScreenSize(String size) {

        WebElement sizeOption = Driver.get().findElement(By.xpath("//span[normalize-space()='" + size + "']"));
        JavascriptExecutor js = (JavascriptExecutor) Driver.get();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", sizeOption);

        waitForPageToLoad(5);
        waitUntilElementVisible(sizeOption);
        waitUntilElementClickable(sizeOption);
        sizeOption.click();

        Driver.get().navigate().refresh();
    }

    public static int convertPriceToInt(String priceText) {

        priceText = priceText.replace(".", "").replace(",", "").replace(" TL", "").trim();
        return Integer.parseInt(priceText);
    }

    public void highestPrice() {

        List<WebElement> priceElements = Driver.get().findElements(By.xpath("//div[@data-test-id='price-current-price']"));
        WebElement highestPriceElement = null;

        int maxPrice = 0;
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            int price = convertPriceToInt(priceText);
            if (price > maxPrice) {
                maxPrice = price;
                highestPriceElement = priceElement;
            }
        }
        if (highestPriceElement != null) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.get();
            js.executeScript("arguments[0].scrollIntoView(true);", highestPriceElement);
            highestPriceElement.click();
        } else {
            System.out.println("Fiyat bulunamadı");
        }
        waitFor(5);
    }

    public void addToChart() {

        String originalWindow = Driver.get().getWindowHandle();
        Set<String> allWindows = Driver.get().getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                Driver.get().switchTo().window(window);
                break;
            }
        }

        waitUntilElementVisible(sepeteEkle);
        sepeteEkle.click();

    }

    public void getDetayFiyat() {

        productPrice =  detayFiyat.getText().split(" ")[0];

    }

    public void confirmAddToCart(){

        waitUntilElementVisible(onay);
        onay.getText();
        Assert.assertTrue("Sepete eklendi",onay.isDisplayed());

    }

    public void goToCart(){

        waitUntilElementVisible(sepeteGit);
        sepeteGit.click();

    }

    public void getSepetFiyat(){

        waitUntilElementVisible(sepetFiyat);
        cartPrice = sepetFiyat.getText().split(" ")[0];

    }

    public void comparePrices(){

        Assert.assertEquals(cartPrice,productPrice);

    }
}