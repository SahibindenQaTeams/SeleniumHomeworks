package com.coorporate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class UserRegisterCoopwithCss {
    protected WebDriver driver;
    String url = "https://secure.sahibinden.com/kayit/kurumsal/";
    protected WebDriverWait wait;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver" ,"/opt/chromedriver4");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    @Test
    public void userRegister() {
        driver.get(url);
        System.out.println("Sayfa redirect edildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
        WebElement cookieAcceoption = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookieAcceoption.click();
        System.out.println("Cookie pop up accept edildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#name")));
        WebElement usernameInput = driver.findElement(By.cssSelector("#name"));
        usernameInput.sendKeys("Hasan ");
        System.out.println("Username yazildi ve ilgili placeholder  " + usernameInput.getAttribute("placeholder"));

        WebElement surnameInput = driver.findElement(By.cssSelector("#surname"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#surname")));
        surnameInput.sendKeys("Test  ");
        System.out.println("Surname  yazildi  ve ilgili placeholder " +surnameInput.getAttribute("placeholder"));

        WebElement email = driver.findElement(By.cssSelector("#email"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email")));
        email.sendKeys("yildirha"+(Math.random()*100)+"@gmail.com");
        System.out.println("Mail  adresi input a girildi ve ilgili attrubute : " +email.getAttribute("placeholder") );

        WebElement password  = driver.findElement(By.cssSelector("#password"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#password")));
        password.sendKeys("123.abc.123");
        System.out.println("Password  yazildi ve ilgili attribute : " + password.getAttribute("placeholder"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#phone")));
        WebElement phoneInput = driver.findElement(By.cssSelector("input#phone"));
        phoneInput.sendKeys("2563133921");
        System.out.println("Phone eklendi ve ilgili attribute : " + phoneInput.getAttribute("placeholder"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#category")));
        Select realEstetaselect  = new Select(driver.findElement(By.cssSelector("#category")));
        realEstetaselect.selectByVisibleText("Vasıta");
        System.out.println("Kategori secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#city")));
        Select citySelect  = new Select(driver.findElement(By.cssSelector("#city")));
        citySelect.selectByVisibleText("Aydın");
        System.out.println("Kategori secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#town")));
        Select townSelect = new Select(driver.findElement(By.cssSelector("#town")));
        townSelect.selectByVisibleText("Kuşadası");
        System.out.println("Ilce secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#quarter")));
        Select quarter = new Select(driver.findElement(By.cssSelector("#quarter")));
        quarter.selectByVisibleText("Kadıkalesi Mh.");
        System.out.println("Mahalle secimi yapildi ");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('limitedCompany').click()");
        System.out.println("Limited company select edildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#taxOfficeCity")));
        Select taxOfficeCity = new Select(driver.findElement(By.cssSelector("#taxOfficeCity")));
        taxOfficeCity.selectByVisibleText("İstanbul");
        System.out.println("Vergi dairesi secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#taxOffice")));
        Select taxOffice = new Select(driver.findElement(By.cssSelector("#taxOffice")));
        taxOffice.selectByVisibleText("Adalar Vergi Dairesi");
        System.out.println("Vergi dairesi ofisi secimi yapildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#taxNumber")));
        WebElement taxNumber = driver.findElement(By.cssSelector("#taxNumber"));
        taxNumber.sendKeys("1600776991");
        System.out.println("Vergi numarasi girildi ");

        js.executeScript("document.querySelector('#endUserLicenceAgreement').click()");
        System.out.println("Agreement check e tiklandi ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#signUpButton"))).click();
        System.out.println("Kayit ol a tiklandi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#confirmSubmit"))).click();
        System.out.println("Email Onay  pop up onaylandi ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("email")));
        WebElement confirmEmailText = driver.findElement(By.className("email"));
        System.out.println("Ilgili email adresi == > " + confirmEmailText.getText());

    }

        @After
        public void tearDown(){
            System.out.println("Tarayici kapatiliyor ");
            driver.quit();
        }
}
