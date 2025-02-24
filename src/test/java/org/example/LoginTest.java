package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

public class LoginTest {

    @Test
    public void checkPositiveLogin() {

        /*
        1. Открыть страницу https://www.saucedemo.com/
        2. Ввести в поле username значение standard_user
        3. Ввести в поле password значение secret_sauce
        4. Нажать кнопку Login
        5. Проверить, что мы переходим на страницу https://www.saucedemo.com/inventory.html с успешной авторизацией
         */

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        Boolean titleIsVisible = driver.findElement(By.cssSelector("[data-test=title]")).isDisplayed();

        Assert.assertTrue(titleIsVisible);

        driver.quit();
    }

    @Test
    public void checkNegativeLoginWithEmptyLogin() {

        /*
        1. Открыть страницу https://www.saucedemo.com/
        2. Оставить поле username пустым
        3. Ввести в поле password значение secret_sauce
        4. Нажать кнопку Login
        5. Проверить, что отображается ошибка Epic sadface: Username is required
        */

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();

        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");

        driver.quit();
    }

    @Test
    public void checkNegativeLoginWithWrongLogin() {

        /*
        1. Открыть страницу https://www.saucedemo.com/
        2. Ввести в поле username значение none_user
        3. Ввести в поле password значение secret_sauce
        4. Нажать кнопку Login
        5. Проверить, что отображается ошибка Epic sadface: Username and password do not match any user in this service
        */

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("none_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();

        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");

        driver.quit();
    }

    @Test
    public void checkNegativeLoginWithEmptyPassword() {

        /*
        1. Открыть страницу https://www.saucedemo.com/
        2. Ввести в поле username значение standard_user
        3. Оставить поле password пустым
        4. Нажать кнопку Login
        5. Проверить, что отображается ошибка Epic sadface: Password is required
        */

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();

        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");

        driver.quit();
    }

    @Test
    public void checkNegativeLoginWithWrongPassword() {

        /*
        1. Открыть страницу https://www.saucedemo.com/
        2. Ввести в поле username значение standard_user
        3. Ввести в поле password значение wrong_password
        4. Нажать кнопку Login
        5. Проверить, что отображается ошибка Epic sadface: Username and password do not match any user in this service
        */

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("wrong_password");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();

        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");

        driver.quit();
    }
}