package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "login2")
    private WebElement logInButton;

    @FindBy(id = "loginusername")
    private WebElement usernameInputField;

    @FindBy(id = "loginpassword")
    private WebElement passwordInputField;

    @FindBy(css = "#logInModal > div > div > div.modal-footer > button.btn.btn-primary")
    private WebElement proceedLogInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Клик по кнопке "Log in"
    public void clickLoginButton() {
        click(logInButton);
    }

    // Ввод логина (передаём username в параметре)
    public void enterUsername(String username) {
        enterText(usernameInputField, username);
    }

    // Ввод пароля (передаём password в параметре)
    public void enterPassword(String password) {
        enterText(passwordInputField, password);
    }

    // Клик по кнопке "Log in" после ввода данных
    public void submitLogin() {
        click(proceedLogInButton);
    }
}
