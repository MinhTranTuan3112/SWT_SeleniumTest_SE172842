import org.junit.jupiter.api.Test;
import POM.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC08 {
    @Test
    public static void test() {
        String email_address = "minh@gmail.com";
        String password = "123456";
        String country = "Viet Nam";
        String provice = "TP.HCM";
        String zipid = "1234";
        String address = "TanHoaDong";
        String city = "TP.HCM";
        String telephone = "1234567890";
        String newAdress = "SuVanHanh";
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://live.techpanda.org/");
            LoginPage loginPage = new LoginPage(driver);


            loginPage.clickMyAccountLink();


            loginPage.enterEmail(email_address);

            loginPage.enterPassword(password);

            loginPage.clickloginButton();


            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            OrderPage orderPage = new OrderPage(driver);
            orderPage.clickReOrder();


            String GrandBefore = orderPage.getGrandTotal();
            System.out.println("Price before changing quantity: " + GrandBefore);

            orderPage.ChangeQty();

            orderPage.UpdateQty();

            String GrandAfter = orderPage.getGrandTotal();
            System.out.println("Price after updating quantity: " + GrandAfter);


            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            CartPage cartPage = new CartPage(driver);
            cartPage.clickproceedLink();
            Thread.sleep(2000);

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            CheckOutPage checkOutPage = new CheckOutPage(driver);
            Select optionChoose = new Select(driver.findElement(checkOutPage.selectNewAddress()));
            optionChoose.selectByVisibleText("New Address");
            checkOutPage.enterAddress(address);

            checkOutPage.enterCity(city);

            checkOutPage.enterZIP(zipid);

            Select chooseCountry = new Select(driver.findElement(checkOutPage.chooseCountry()));
            chooseCountry.selectByIndex(1);

            checkOutPage.enterTelephone(telephone);

            checkOutPage.clickContinuebutton();

            checkOutPage.clickshippinginformationLink();

            checkOutPage.clickContinuebutton2();

            checkOutPage.clickContinuebutton3();

            checkOutPage.click_check_money_order();

            checkOutPage.clickContinuebutton4();

            checkOutPage.clickplaceorderLink();



            String OrderId = checkOutPage.getOrderId();
            System.out.println("Created order ID: " + OrderId);

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(1000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
             driver.quit();
        }
    }
}
