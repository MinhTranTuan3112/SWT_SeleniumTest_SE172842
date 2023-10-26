package org.minhtran;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Main {
    public static void main(String[] args)  {
        System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //TC01
        driver.get("http://live.techpanda.org/");
        var MobileLink = driver.findElement(By.cssSelector("[href='http://live.techpanda.org/index.php/mobile.html']"));
        MobileLink.click();
        var SortDropdown = driver.findElement(By.cssSelector("[title='Sort By']"));
        Select SortSelect = new Select(SortDropdown);
        SortSelect.selectByVisibleText("Name");

        //TC02


        driver.quit();
    }
}