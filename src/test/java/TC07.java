import POM.LoginPage;
import POM.OrderPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC07 {
    @Test
    public static void test() {
        String email_address = "minh@gmail.com";
        String password = "123456";
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://live.techpanda.org/");
            LoginPage loginPage = new LoginPage(driver);
            Thread.sleep(1000);

            loginPage.clickMyAccountLink();
            Thread.sleep(2000);

            loginPage.enterEmail(email_address);
            Thread.sleep(1000);
            loginPage.enterPassword(password);
            Thread.sleep(1000);
            loginPage.clickloginButton();
            Thread.sleep(1000);

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            OrderPage orderPage = new OrderPage(driver);
            Thread.sleep(1000);
            orderPage.clickOrderLink();
            Thread.sleep(1000);
            orderPage.clickViewOrder();
            Thread.sleep(1000);
            orderPage.clickPrintOrder();
            Thread.sleep(1000);
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
