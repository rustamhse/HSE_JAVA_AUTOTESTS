package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void checkPositiveLogin(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/#"); // Открываем сайт

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        loginPage.enterUsername("Tamik2002");
        loginPage.enterPassword("TamikPassword");
        loginPage.submitLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

        assertTrue(welcomeMessage.getText().contains("Tamik2002"), "Имя пользователя не отображается, авторизация не прошла успешно!");

        driver.quit();
    }

    @Test
    public void checkNegativeLoginUserDoesNotExist(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/#"); // Открываем сайт

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        loginPage.enterUsername("NonExistingUser" + Math.random());
        loginPage.enterPassword("AnyPassword");
        loginPage.submitLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent()); // Ждем появления Alert

        // Переключаюсь на окно с алертом
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals("User does not exist.", alertText, "Текст ошибки не совпадает!");

        alert.accept();
        driver.quit();
    }

    @Test
    public void checkNegativeLoginWrongPassword(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/#"); // Открываем сайт

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        loginPage.enterUsername("Tamik2002");
        loginPage.enterPassword("TamikWrongPassword");
        loginPage.submitLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent()); // Ждем появления Alert

        // Переключаюсь на окно с алертом
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals("Wrong password.", alertText, "Текст ошибки не совпадает!");

        alert.accept();
        driver.quit();
    }
}