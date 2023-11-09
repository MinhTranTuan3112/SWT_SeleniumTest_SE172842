package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    By myAccountLink = By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']");
    By emailInputlocator = By.xpath("//input[@id='email']");
    By passwordInputlocator = By.xpath("//input[@id='pass']");
    By loginButton = By.xpath("//span[contains(text(),'Login')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }



    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public void clickMyAccountLink() {
        driver.findElement(myAccountLink).click();
    }

    public void enterEmail(String email) {
        WebElement element = driver.findElement(emailInputlocator);
        element.clear();
        element.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement element = driver.findElement(passwordInputlocator);
        element.clear();
        element.sendKeys(password);
    }

    public void clickloginButton() {
        driver.findElement(loginButton).click();
    }


}