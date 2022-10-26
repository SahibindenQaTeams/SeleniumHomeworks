package com.indivudual;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class UserRegisterwithCss {
    protected WebDriver driver;
    protected String url = "https://www.sahibinden.com/";

    protected WebDriverWait wait;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/opt/chromedriver4");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        System.out.println("Ilgili siteye yonlendirildi ");
    }
    @Test
    public void userRegister(){
        wait = new WebDriverWait(driver,30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('body > div.header-container > div > ul > li.register-text > a').click()");
        System.out.println("Create account'a tiklandi ve Sayfa redirect edildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
        WebElement cookieAcceoption = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookieAcceoption.click();
        System.out.println("Cookie pop up accept edildi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#name")));
        WebElement usernameInput = driver.findElement(By.cssSelector("#name"));
        usernameInput.sendKeys("Hasan ");
        System.out.println("Username yazildi ");

        WebElement surnameInput  = driver.findElement(By.cssSelector("#surname"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#surname")));
        surnameInput.sendKeys("Test  ");
        System.out.println("Surname  yazildi ");

        WebElement email = driver.findElement(By.cssSelector("#email"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email")));
        email.sendKeys("yildirha"+(Math.random()*100)+"@gmail.com");
        System.out.println("Mail  adresi input a girildi ");

        WebElement password  = driver.findElement(By.cssSelector("#password"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#password")));
        password.sendKeys("123.abc.123");
        System.out.println("Password  yazildi ");

        js.executeScript("document.querySelector('#endUserLicenceAgreement').click()");
        System.out.println("Agreement check e tiklandi ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#signUpButton.btn.btn-new.btn-strong"))).click();
        System.out.println("Sign up a tiklandi ");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#confirmSubmit"))).click();;
        System.out.println("Email Onay  pop up Onaylandi ");

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
