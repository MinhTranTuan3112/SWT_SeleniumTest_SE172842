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

public class TestingUtils {
    //Fields
    private static final String imagesPath = "src\\main\\resources";

    //Constructor
    public TestingUtils() {

    }

    //Methods
    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(TestingUtils.imagesPath + fileName));
    }

    private boolean checkSorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void TC01() {
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");
        var MobileLink = driver.findElement(By.cssSelector("[href='http://live.techpanda.org/index.php/mobile.html']"));
        MobileLink.click();
        var SortDropdown = driver.findElement(By.cssSelector("[title='Sort By']"));
        Select SortSelect = new Select(SortDropdown);
        SortSelect.selectByVisibleText("Name");
        List<WebElement> productLinkLists = driver.findElements(By.cssSelector(".product-name a"));
        var nameList = new ArrayList<String>();
        for (var anchor : productLinkLists) {
            nameList.add(anchor.getText());
        }
        try {
            takeScreenshot(driver, "TC01.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        boolean isSorted = checkSorted(nameList);
        Assertions.assertTrue(isSorted);
        driver.quit();
    }

    private double getActualPrice(String text) {
        String result = text.substring(1);
        return Double.parseDouble(result);
    }

    @Test
    public void TC02() {
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");
        var MobileLink = driver.findElement(By.cssSelector("[href='http://live.techpanda.org/index.php/mobile.html']"));
        MobileLink.click();
        //<a href="http://live.techpanda.org/index.php/mobile/sony-xperia.html" title="Sony Xperia">Sony Xperia</a>
        var SonyXperiaLink = driver.findElement(By.cssSelector("[href='http://live.techpanda.org/index.php/mobile/sony-xperia.html']"));
        SonyXperiaLink.click();
        try {
            takeScreenshot(driver, "TC02.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        var phonePriceElement = driver.findElement(By.cssSelector("span .price"));
        double phonePrice = getActualPrice(phonePriceElement.getText());
        Assertions.assertEquals(phonePrice, 100);
        driver.quit();
    }

    @Test
    public void TC03() {
        int shoppingCartCnt = 0;
        StringBuffer verificationError = new StringBuffer();
        //init web driver session
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");
        try {
            //Step 2. Click on Mobile Menu
            driver.findElement(By.linkText("MOBILE")).click();

            Thread.sleep(2000);

            //Click Add To Cart
            driver.findElement(By.xpath(".//*//li[2]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();


            Thread.sleep(2000);

            //Change quantity element to 1000
            driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();
            driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("1000");
            driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();


            Thread.sleep(2000);

            //Step 5. Verify the error message
            String reqQty = driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[2]/p")).getText();
            String msgQty = ("* The maximum quantity allowed for purchase is 500.");
            try {
                Assert.assertEquals(reqQty, msgQty);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Click on empty cart link
            driver.findElement(By.xpath(".//*[@id='empty_cart_button']")).click();


            Thread.sleep(3000);

            // Verify cart is empty
            String noItemsStg = ("You have no items in your shopping cart.");
            String noItemsMsg = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div[2]/p[1]")).getText();
            System.out.println("You have no items msg = " + noItemsMsg);

            try {
                Assert.assertEquals(noItemsStg, noItemsMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Quit
        driver.quit();
    }

    @Test
    public void TC04() {
        int scc = 0;

        StringBuffer verificationError = new StringBuffer();
        //init web driver session
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            //Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //Click on Mobile menu
            driver.findElement(By.linkText("MOBILE")).click();

            Thread.sleep(2000);

            //Click to compare 2 phones
            driver.findElement(By.xpath(".//*//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
            String Mobile1 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();
            System.out.println("Mobile1 = " + Mobile1);
            Thread.sleep(2000);
            driver.findElement(By.xpath(".//*//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
            String Mobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();
            System.out.println("Mobile2 = " + Mobile2);

            Thread.sleep(2000);

            //Click Compare button
            driver.findElement(By.xpath("//button[@title='Compare']")).click();

            Thread.sleep(2000);

            //Switch to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            Thread.sleep(2000);

            //Verify the pop-up window and check that the products are reflected in it
            //
            //Heading "COMPARE PRODUCTS" with selected products in it.

            String strHead = ("COMPARE PRODUCTS");
            String compHead = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div[1]/h1")).getText();
            System.out.println("compHead = " + compHead);
            String popupMobile1 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();  // text captured is "IPHONE" in uppercase
            String popupMobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();  // text captured "SONY XPERIA" in uppercase
            System.out.println("popupMobile1 = " + popupMobile1);
            System.out.println("popupMobile2 = " + popupMobile2);
            //Close the Popup Windows
            driver.findElement(By.xpath("//button[@title='Close Window']")).click();

            //Switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }


            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit
        driver.quit();
    }

    @Test
    public void TC05() {
        int ShoppingCartCount = 1;
        String firstName = "tran";
        String lastName = "minh";
        String email_address = "minh@gmail.com";
        String password = "minhtran123";
        String confirmPassword = "minhtran123";

        StringBuffer verificationError = new StringBuffer();
        //init web driver session
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            //Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            RegisterPage registerPage = new RegisterPage(driver);

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

            //Step 3. Click Create an Account link and fill New User information excluding the registered Email ID.
            registerPage.clickCreateAccountLink();
            //debug
            Thread.sleep(2000);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            //fill info
            registerPage.enterFirstName(firstName);
            registerPage.enterLastName(lastName);
            registerPage.enterEmail(email_address);
            registerPage.enterPassword(password);
            registerPage.enterConfirmPassword(confirmPassword);

            //debug
            Thread.sleep(2000);

            //Step 4. Click Register
//            driver.findElement(By.xpath("//button[@title='Register']")).click();
            registerPage.clickRegisterButton();
            //debug
            Thread.sleep(2000);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            //debug
            Thread.sleep(2000);
            // 5. Verify Registration is done. Expected account registration done.
            String vWelcome = ("WELCOME, " + firstName.toUpperCase() + " " + lastName.toUpperCase() + "!");
            String tWelcome = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[1]/div/p")).getText();
            System.out.println("tWelcome = " + tWelcome);

            try {
                Assert.assertEquals(tWelcome, vWelcome);
            } catch (Exception e) {
                e.printStackTrace();
            }


            //Step 6. Go to TV menu
            driver.findElement(By.xpath(".//*[@id='nav']/ol/li[2]/a")).click();
            //debug
            Thread.sleep(2000);

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //Step 7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("//li/a[@class='link-wishlist']")).click();
            //debug
            Thread.sleep(2000);

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            // Step 8. Click SHARE WISHLIST
            driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
            //debug
            Thread.sleep(2000);

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }


            //Step 9. In next page enter Email and a message and click SHARE WISHLIST
            registerPage.enterEmail(email_address);
            driver.findElement(By.id("message")).clear();
            driver.findElement(By.id("message")).sendKeys("A really good smart TV ");

            driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
            //debug
            Thread.sleep(2000);

            // Step 10. Check wishlist is shared. Expected wishlist shared successfully.
            String vWishList = "Your Wishlist has been shared.";
            String wishList = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
            System.out.println("wishList = " + wishList);
            try {
                Assert.assertEquals(vWishList, wishList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //debug
            Thread.sleep(3000);

            //screen
            ShoppingCartCount = (ShoppingCartCount + 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit
        driver.quit();
    }

    @Test
    public void testTC06() {

        //login
        String email = "minh@gmail.com";
        String pass = "123456";
        //billing
        String region = "Scotland";
        String postcode = "2000";
        String firstName ="nguyen";
        String lastName= "trung";
        String address = "150 Roads";
        String city = "Roses City";
        String telephone= "0334363339";
        //shipping
        String firstname2 = "Trung";
        String lastname2 = "Nguyen";
        String address2 = "256 Blue";
        String city2 = "Victory City";
        String telephone2= "0123456789";


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
                System.out.println("sFlatRate = "+sFlatRate);
                System.out.println("flatRate = "+flatRate);
                Assert.assertEquals(sFlatRate, flatRate);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String sFlatRatePrice = "Fixed - $5.00";
            String flatRatePrice = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label")).getText();
            try {
                System.out.println("sFlatRatePrice = "+sFlatRatePrice);
                System.out.println("flatRatePrice = "+flatRatePrice);
                Assert.assertEquals(sFlatRatePrice, flatRatePrice);
            } catch (Exception e) {
                e.printStackTrace();
            };

            // Step 9. Select Shipping Cost (already selected as default), Update Total
            driver.findElement(By.id("s_method_flatrate_flatrate")).click();
            driver.findElement(By.xpath("//button[@title='Update Total']")).click();
            //debug
            Thread.sleep(2000);

            // Step 10. Verify shipping cost is added to total
            String vFlatRatePrice = "$5.00";
            String shippingCostIncluded = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();

            try {
                System.out.println("vFlatRatePrice = "+vFlatRatePrice);
                System.out.println("shippingCostIncluded = "+shippingCostIncluded);
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




        }catch (Exception e){
            e.printStackTrace();
        }

        // Quit
        driver.quit();
    }
}
