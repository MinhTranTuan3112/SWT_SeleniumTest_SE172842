import POM.CartPage;
import POM.CheckOutPage;
import POM.LoginPage;
import POM.RegisterPage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC06 {
    @Test
    public static void test() {
        try {
            //login
            String email = "minh@gmail.com";
            String pass = "123456";
            //billing
            String region = "Scotland";
            String postcode = "2000";
            String firstName = "nguyen";
            String lastName = "trung";
            String address = "150 Roads";
            String city = "Roses City";
            String telephone = "0334363339";
            //shipping
            String firstname2 = "Trung";
            String lastname2 = "Nguyen";
            String address2 = "256 Blue";
            String city2 = "Victory City";
            String telephone2 = "0123456789";


            StringBuffer verificationError = new StringBuffer();
            //init web driver session
            System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            try {
                //Step 1. Go to http://live.techpanda.org/
                driver.get("http://live.techpanda.org/");
                CheckOutPage checkOutPage = new CheckOutPage(driver);
                RegisterPage registerPage = new RegisterPage(driver);
                CartPage cartPage = new CartPage(driver);
                LoginPage loginPage = new LoginPage(driver);
                //Step 2. Click on my account link
                registerPage.clickMyAccountLink();
                //debug
                Thread.sleep(2000);
                // switching to new window
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                //debug
                Thread.sleep(2000);

                //Step 3. Login in application using previously created credential
                loginPage.enterEmail(email);
                loginPage.enterPassword(pass);
                loginPage.clickLoginButton();

                //debug
                Thread.sleep(2000);
                // switching to new window
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }

                //Step 4. Click on MY WISHLIST link
                driver.findElement(By.linkText("MY WISHLIST")).click();

                Thread.sleep(3000);

                // switching to new window
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }

                // 5.  In next page, Click ADD TO CART link
                driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();

                Thread.sleep(3000);

                // switching to new window
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }


                //Step 6.  Enter general shipping country, state/province and zip for the shipping cost estimate
                WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='country']"));
                Select selectOption = new Select(dropdownElement);
                selectOption.selectByVisibleText("United Kingdom");
                cartPage.enterRegionInput(region);
                cartPage.enterPostcodeInput(postcode);


                //debug
                Thread.sleep(2000);

                // switching to new window
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }

                // Step 7. Click Estimate
                driver.findElement(By.xpath(".//*[@id='shipping-zip-form']/div/button")).click();
                //debug
                Thread.sleep(2000);

                //Step 8. Verify Shipping cost generated
                String sFlatRate = "Flat Rate";
                String flatRate = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt")).getText();
                try {
                    System.out.println("sFlatRate = " + sFlatRate);
                    System.out.println("flatRate = " + flatRate);
                    Assert.assertEquals(sFlatRate, flatRate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String sFlatRatePrice = "Fixed - $5.00";
                String flatRatePrice = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label")).getText();
                try {
                    System.out.println("sFlatRatePrice = " + sFlatRatePrice);
                    System.out.println("flatRatePrice = " + flatRatePrice);
                    Assert.assertEquals(sFlatRatePrice, flatRatePrice);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ;

                // Step 9. Select Shipping Cost (already selected as default), Update Total
                driver.findElement(By.id("s_method_flatrate_flatrate")).click();
                driver.findElement(By.xpath("//button[@title='Update Total']")).click();
                //debug
                Thread.sleep(2000);

                // Step 10. Verify shipping cost is added to total
                String vFlatRatePrice = "$5.00";
                String shippingCostIncluded = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();

                try {
                    System.out.println("vFlatRatePrice = " + vFlatRatePrice);
                    System.out.println("shippingCostIncluded = " + shippingCostIncluded);
                    Assert.assertEquals(vFlatRatePrice, shippingCostIncluded);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 11. Click PROCEED TO CHECKOUT
                checkOutPage.clickCheckOutButton();
                //debug
                Thread.sleep(2000);
                // switching to new window
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                Thread.sleep(2000);

                //Step 12a. Enter Billing Information, and click Continue


                checkOutPage.enterFirstName(firstName);
                checkOutPage.enterLastName(lastName);
                checkOutPage.enterAddress(address);
                checkOutPage.enterCity(city);

                //select country

                WebElement dropdownElementNew = driver.findElement(By.xpath("//select[@id='billing:country_id']"));
                Select selectOptionNew = new Select(dropdownElementNew);
                selectOptionNew.selectByVisibleText("United Kingdom");
                checkOutPage.enterPostcode(postcode);
                checkOutPage.enterTelephone(telephone);

                // check"Ship to different address"
                driver.findElement(By.xpath("//label[@for='billing:use_for_shipping_no']")).click();

                driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();

                Thread.sleep(2000);

                // switching to new window
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                Thread.sleep(2000);


                //Step 12b. Enter Shipping Information, and click Continue
                checkOutPage.enterShippingFirstName(firstname2);
                checkOutPage.enterShippingLastName(lastname2);
                checkOutPage.enterShippingAddress(address2);
                checkOutPage.enterShippingCity(city2);
                checkOutPage.enterShippingTelephone(telephone2);
                driver.findElement(By.xpath(".//*[@id='shipping-buttons-container']/button")).click();

                // switching to new window
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                Thread.sleep(2000);

                //Step 13. In Shipping Method, Click Continue

                driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click();

                Thread.sleep(2000);

                // Step 14. In Payment Information select 'Check/Money Order' radio button. Click Continue
                driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();

                Thread.sleep(3000);

                driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click();

                Thread.sleep(3000);

                // Step 15. Click 'PLACE ORDER' button
                driver.findElement(By.xpath(".//*[@id='review-buttons-container']/button")).click();

                Thread.sleep(3000);

                // 16. Verify Oder is generated. Note the order number
                String orderNum = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();
                System.out.println("*** Your order number for your record = " + orderNum);


            } catch (Exception e) {
                e.printStackTrace();
            }

            // Quit
            driver.quit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
