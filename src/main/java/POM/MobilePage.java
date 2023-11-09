package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobilePage {
    WebDriver driver;
    By MobileLink = By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']");
    By AddIphoneToCartLink = By.xpath("//li[1]//div[1]//div[3]//button[1]");
    By DiscountInput = By.xpath("//input[@id='coupon_code']");

    By prevPriceLink = By.xpath("//strong//span[@class='price']");
    By ApplyButtonLink = By.xpath("//span[contains(text(),'Apply')]");

    By finalPriceLink = By.xpath("//strong//span[@class='price']");

    public double prevPrice = 0.0f;
    public double finalPrice = 0.0f;
    public MobilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void ClickMobileLink() {
        driver.findElement(MobileLink).click();
    }
    public void ClickAddIPhoneToCart() {
        driver.findElement(AddIphoneToCartLink).click();
    }

    public void EnterCouponCode() {
        var DiscountInputElement = driver.findElement(DiscountInput);
        DiscountInputElement.sendKeys("GURU50");
    }

    public void SavePreviousPrice() {
        prevPrice = Double.parseDouble(driver.findElement(prevPriceLink).getText().substring(1));
    }

    public void ClickApplyButton() {
        driver.findElement(ApplyButtonLink).click();
    }

    public void SaveFinalPrice() {
        finalPrice = Double.parseDouble(driver.findElement(finalPriceLink).getText().substring(1));
    }


}
