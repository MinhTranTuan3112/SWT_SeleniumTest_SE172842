package POM;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AdminPage {
    WebDriver driver;
    private static final String imagesPath = "src\\main\\resources";

    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(imagesPath + fileName));
    }


    By UserNameLink = By.xpath("//input[@id='username']");
    By PasswordLink = By.xpath("//input[@id='login']");

    By LoginBtnLink = By.xpath("//input[@title='Login']");
    By OrderLink = By.xpath("//span[normalize-space()='Orders']");

    By SalesLink = By.xpath("//a[@class='active']");
    By OrderIDInputLink = By.xpath("//input[@id='sales_order_grid_filter_real_order_id']");

    By StartDateInputLink = By.cssSelector("body > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > table:nth-child(1) > thead:nth-child(2) > tr:nth-child(2) > th:nth-child(3) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)");
    By EndDateInputLink = By.cssSelector("body > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > table:nth-child(1) > thead:nth-child(2) > tr:nth-child(2) > th:nth-child(3) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)");

    By SearchButtonLink = By.xpath("(//span[contains(text(),'Search')]");
    public String Username = "user01";
    public String Password = "guru99com";

    public String OrderId = "100021248";
    public String StartDate = "10/01/2023";
    public String EndDate = "11/15/2023";

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void EnterUsername() {
        driver.findElement(UserNameLink).sendKeys(Username);
    }

    public void EnterPassword() {
        driver.findElement(PasswordLink).sendKeys(Password);
    }

    public void ClickLoginButton() {
        driver.findElement(LoginBtnLink).click();
    }

    public void HoverOnSalesLink() {
        var saleLinkElement = driver.findElement(SalesLink);
        Actions action = new Actions(driver);
        action.moveToElement(saleLinkElement).perform();
    }
    public void ClickOrderLink() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(OrderLink));
    }

    public void EnterOrderId() {
        driver.findElement(OrderIDInputLink).sendKeys(OrderId);
    }

    public void EnterStartDate() {
        driver.findElement(StartDateInputLink).sendKeys(StartDate);
    }

    public void EnterEndDate() {
        driver.findElement(EndDateInputLink).sendKeys(EndDate);
        driver.findElement(EndDateInputLink).sendKeys(Keys.ENTER);
    }

    public void ClickSearchButton() {
        driver.findElement(SearchButtonLink).click();
    }

    public void screenShot() {
        try {
            takeScreenshot(driver, "TC10.png");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
