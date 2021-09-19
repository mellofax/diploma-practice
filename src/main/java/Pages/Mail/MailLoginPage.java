package Pages.Mail;

import Account.Account;
import Static.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Static.Page.WaitSomeElementToVisibility;

public class MailLoginPage {
    WebDriver driver;
    @FindBy(name = "login")
    private WebElement login;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "button[data-testid='enter-password']")
    private WebElement FirstEnterButton;
    @FindBy(css = "button[data-testid='login-to-mail']")
    private  WebElement SecondEnterButton;

    public MailLoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean Login(Account account) {
        try {
            login.sendKeys(account.getLogin());
            FirstEnterButton.click();
            WaitSomeElementToVisibility(driver, password, 5);
            password.sendKeys(account.getPassword());
            SecondEnterButton.click();
            return true;
        }
        catch (Exception e)
        {
            Log.getLogger().error(e.getMessage());
            return false;
        }
    }
}
