package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage {
    WebDriver driver;

    By addressInputlocator = By.xpath("//input[@id='billing:street1']");
    By cityInputlocator = By.xpath("//input[@id='billing:city']");
    By countryInputlocator = By.xpath("//select[@id='billing:country_id']");
    By zipInputlocator = By.xpath("//input[@id='billing:postcode']");
    By telephoneInputlocator = By.xpath("//input[@id='billing:telephone']");
    By continuebuttonLink1 = By.xpath("//button[@onclick='billing.save()']//span//span[contains(text(),'Continue')]");
    By shippinginformationLink = By.xpath("//h2[normalize-space()='Shipping Information']");

    By continuebuttonLink2 = By.cssSelector("button[onclick='shipping.save()']");//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]
    By continuebuttonLink3 = By.xpath("//button[@onclick='shippingMethod.save()']");
    By continuebuttonLink4 = By.xpath("//button[@onclick='payment.save()']");

    By check_money_orderLink = By.xpath("//input[@id='p_method_checkmo']");
    By placeorderLink = By.xpath("//button[@title='Place Order']");
    By orderidLink = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]/a[1]");

    By selectNewAddress = By.xpath("//select[@id='billing-address-select']");

    By firstNameInputLocator = By.id("billing:firstname");
    By lastnameInputLocator = By.id("billing:lastname");
    By postcodeInputLocator = By.id("billing:postcode");
    By checkOutButton = By.xpath("//button[@title='Proceed to Checkout']");
    //Shipping
    By shippingFirstName = By.id("shipping:firstname");
    By shippingLastName = By.id("shipping:lastname");
    By shippingAddress = By.id("shipping:street1");
    By shippingCity = By.id("shipping:city");
    By shippingTelephone = By.id("shipping:telephone");


    By newAddress = By.xpath("//select[@id='billing-address-select']");

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public By selectNewAddress() {
        WebElement element = driver.findElement(selectNewAddress);
        return selectNewAddress;
    }

    public void enterFirstName(String firstName){
        WebElement firstNameElement = driver.findElement(firstNameInputLocator);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);

    }
    public void enterLastName(String lastName){
        WebElement lastNameElement = driver.findElement(lastnameInputLocator);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }
    public void enterPostcode(String postcode){
        WebElement postcodeElement = driver.findElement(postcodeInputLocator);
        postcodeElement.clear();
        postcodeElement.sendKeys(postcode);
    }

    //methods billing
    public void clickCheckOutButton(){
        driver.findElement(checkOutButton).click();
    }


    public void enterAddress(String address) {
        WebElement element = driver.findElement(addressInputlocator);
        element.clear();
        element.sendKeys(address);
    }

    public void enterCity(String city) {
        WebElement element = driver.findElement(cityInputlocator);
        element.clear();
        element.sendKeys(city);
    }

    //methods Shipping

    public void enterShippingFirstName(String firstname2){
        WebElement shippingFirstNameElement = driver.findElement(shippingFirstName);
        shippingFirstNameElement.clear();
        shippingFirstNameElement.sendKeys(firstname2);
    }

    public void enterShippingLastName(String lastname2){
        WebElement shippingLastNameElement = driver.findElement(shippingLastName);
        shippingLastNameElement.clear();
        shippingLastNameElement.sendKeys(lastname2);
    }
    public void enterShippingAddress(String address2){
        WebElement shippingAddressElement = driver.findElement(shippingAddress);
        shippingAddressElement.clear();
        shippingAddressElement.sendKeys(address2);
    }

    public void enterShippingCity(String city2){
        WebElement shippingCityElement = driver.findElement(shippingCity);
        shippingCityElement.clear();
        shippingCityElement.sendKeys(city2);
    }

    public void enterShippingTelephone(String telephone2){
        WebElement shippingTelephoneElement = driver.findElement(shippingTelephone);
        shippingTelephoneElement.clear();
        shippingTelephoneElement.sendKeys(telephone2);
    }
    public By chooseCountry() {
        WebElement country = driver.findElement(countryInputlocator);
        return countryInputlocator;
    }

    public void enterZIP(String zipid) {
        WebElement element = driver.findElement(zipInputlocator);
        element.clear();
        element.sendKeys(zipid);
    }

    public void enterTelephone(String telephone) {
        WebElement element = driver.findElement(telephoneInputlocator);
        element.clear();
        element.sendKeys(telephone);
    }

    public void clickContinuebutton() {
        driver.findElement(continuebuttonLink1).click();
    }

    public void clickshippinginformationLink() {
        driver.findElement(shippinginformationLink).click();
    }

    public void clickContinuebutton2() {
        var btn = driver.findElement(continuebuttonLink2);
        if (btn != null) {
//            btn.click();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", btn);
        }

//        driver.findElement(continuebuttonLink2).click();
    }

    public void clickContinuebutton3() {
        driver.findElement(continuebuttonLink3).click();
    }

    public void click_check_money_order() {
        driver.findElement(check_money_orderLink).click();
    }

    public void clickContinuebutton4() {
        driver.findElement(continuebuttonLink4).click();
    }

    public String getOrderId() {
        String OrderId = driver.findElement(orderidLink).getText();
        return OrderId;
    }

    public void clickplaceorderLink() {
        driver.findElement(placeorderLink).click();
    }

}