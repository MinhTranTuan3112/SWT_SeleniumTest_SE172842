import POM.AdminPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import POM.LoginPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC10 {


    @Test
    public static void test() {
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        var email_address = "minh@gmail.com";
        var password = "123456";
        try {
            driver.get("http://live.techpanda.org/index.php/backendlogin");
            var adminPage = new AdminPage(driver);
            adminPage.EnterUsername();
            adminPage.EnterPassword();
            adminPage.ClickLoginButton();
            adminPage.ClickOrderLink();
            adminPage.EnterOrderId();
            adminPage.EnterStartDate();
            adminPage.EnterEndDate();
//            adminPage.ClickSearchButton();
            Thread.sleep(2000);
            adminPage.screenShot();
            Thread.sleep(1000);
            driver.quit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            driver.quit();
        }
    }
}
