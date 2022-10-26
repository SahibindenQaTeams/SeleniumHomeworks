package com.coorporate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class UserRegisterCoopwithID {
    protected WebDriver driver;
    String url = "https://secure.sahibinden.com/kayit/kurumsal/";
    protected WebDriverWait wait;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver" ,"/opt/chromedriver4");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1,TimeUnit.MINUTES);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    @Test
    public void userRegister(){
        driver.get(url);
        System.out.println("Ilgili url e redirect edildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement nameinput = driver.findElement(By.id("name"));
        nameinput.sendKeys("Hasan");
        System.out.println("Name yazildi ve ilgili placeholder = " +nameinput.getAttribute("placeholder") );

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("surname")));
        WebElement surnameinput = driver.findElement(By.id("surname"));
        surnameinput.sendKeys("Test");
        System.out.println("Surname yazildi ve ilgili placeholder =  " + surnameinput.getAttribute("placeholder"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("yildirha"+(Math.random()*100)+"@gmail.com");
        System.out.println("Mail  adresi input a girildi ve ilgili placeholder = " + email.getAttribute("placeholder"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123.abc.123");
        System.out.println("Password girildi ve ilgili placeholder = " + password.getAttribute("placeholder"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
        WebElement phoneInput = driver.findElement(By.id("phone"));
        phoneInput.sendKeys("2563133921");
        System.out.println("Phone eklendi ve ilgili placeholder = " +phoneInput.getAttribute("placeholder"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category")));
        Select realEstetaselect  = new Select(driver.findElement(By.id("category")));
        realEstetaselect.selectByVisibleText("Vasıta");
        System.out.println("Kategori secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();
        System.out.println("Cookie pop up accept edildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
        Select citySelect = new Select(driver.findElement(By.id("city")));
        citySelect.selectByVisibleText("Aydın");
        System.out.println("Sehir secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("town")));
        Select townSelect = new Select(driver.findElement(By.id("town")));
        townSelect.selectByVisibleText("Kuşadası");
        System.out.println("Ilce secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quarter")));
        Select quarter = new Select(driver.findElement(By.id("quarter")));
        quarter.selectByVisibleText("Kadıkalesi Mh.");
        System.out.println("Mahalle secimi yapildi ");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('limitedCompany').click()");
        System.out.println("Limited company select edildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taxOfficeCity")));
        Select taxOfficeCity = new Select(driver.findElement(By.id("taxOfficeCity")));
        taxOfficeCity.selectByVisibleText("İstanbul");
        System.out.println("Vergi dairesi secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taxOffice")));
        Select taxOffice = new Select(driver.findElement(By.id("taxOffice")));
        taxOffice.selectByVisibleText("Adalar Vergi Dairesi");
        System.out.println("Vergi dairesi ofisi secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taxNumber")));
        WebElement taxNumber = driver.findElement(By.id("taxNumber"));
        taxNumber.sendKeys("1600776991");
        System.out.println("Vergi numarasi girildi ");

        js.executeScript("document.querySelector('#endUserLicenceAgreement').click()");
        System.out.println("Agreement check e tiklandi ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpButton"))).click();
        System.out.println("Kayit ol a tiklandi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmSubmit")));;
        WebElement confirmEmailButton  = driver.findElement(By.id("confirmSubmit"));
        confirmEmailButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("colorbox")));
        WebElement confirmationPopup = driver.findElement(By.id("colorbox"));
        Assert.assertTrue(confirmationPopup.isDisplayed());
        System.out.println("Confirmation pop up goruntelendi ");
        confirmEmailButton.click();
        System.out.println("Email Onay  pop up Onaylandi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("email")));
        WebElement confirmEmailText = driver.findElement(By.className("email"));
        System.out.println("Ilgili email adresi == > " + confirmEmailText.getText());
    }

    @After
    public void tearDown(){
        System.out.println("Sistem kapatiliyor ");
        driver.quit();
    }
}


