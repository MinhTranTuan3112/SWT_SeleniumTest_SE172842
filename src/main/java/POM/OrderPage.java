package POM;
import POM.RegisterPage;
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
public class OrderPage {
    WebDriver driver;
    By OrderLink = By.xpath("//a[normalize-space()='My Orders']");
    By ViewOrder = By.xpath("//a[normalize-space()='View Order']");
    By PrintOrder = By.cssSelector(".link-print");

    By GrandTotal = By.cssSelector("strong span[class='price']");
    By ReOrder = By.xpath("//a[normalize-space()='Reorder']");

    By ChangeQty = By.xpath("//input[@title='Qty']");
    By UpdateQty = By.xpath("//button[@title='Update']");
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickOrderLink(){
        driver.findElement(OrderLink).click();
    }

    public void clickViewOrder(){
        driver.findElement(ViewOrder).click();
    }

    public void clickPrintOrder(){
        driver.findElement(PrintOrder).click();
    }

    public void clickReOrder(){
        driver.findElement(ReOrder).click();
    }

    public String getGrandTotal(){
        return  driver.findElement(GrandTotal).getText();
    }

    public void ChangeQty(){
        WebElement element = driver.findElement(ChangeQty);
        element.clear();
        element.sendKeys("10");
    }

    public void UpdateQty(){
        driver.findElement(UpdateQty).click();
    }
}
