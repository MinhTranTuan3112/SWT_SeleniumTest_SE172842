package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CartPage {
    WebDriver driver;
    By regionInputLocator = By.id("region");
    By postcodeInputLocator = By.id("postcode");

    By proceedLink = By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterRegionInput(String region) {
        WebElement regionElement = driver.findElement(regionInputLocator);
        regionElement.clear();
        regionElement.sendKeys(region);

    }

    public void enterPostcodeInput(String postcode) {
        WebElement postcodeElement = driver.findElement(postcodeInputLocator);
        postcodeElement.clear();
        postcodeElement.sendKeys(postcode);
    }

    public void clickproceedLink(){
        driver.findElement(proceedLink).click();
    }
}