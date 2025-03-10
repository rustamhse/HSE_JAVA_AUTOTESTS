package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element) {
        waitForElementToBeVisible(element).click();
    }

    public void enterText(WebElement element, String text) {
        waitForElementToBeVisible(element).sendKeys(text);
    }
}
