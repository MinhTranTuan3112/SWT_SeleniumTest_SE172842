import POM.RegisterPage;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

import POM.*;
import org.openqa.selenium.support.ui.Select;

public class TC09 {
    @Test
    public static void test() {
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        var email_address = "minh@gmail.com";
        var password = "123456";
        try {
            driver.get("http://live.techpanda.org/");
            var mobilePage = new MobilePage(driver);
            mobilePage.ClickMobileLink();
            mobilePage.ClickAddIPhoneToCart();
            mobilePage.SavePreviousPrice();
            mobilePage.EnterCouponCode();
            mobilePage.ClickApplyButton();
            mobilePage.SaveFinalPrice();
            Assert.assertNotEquals(mobilePage.prevPrice, mobilePage.finalPrice, 0.1);
            driver.quit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            driver.quit();
        }
    }
}
