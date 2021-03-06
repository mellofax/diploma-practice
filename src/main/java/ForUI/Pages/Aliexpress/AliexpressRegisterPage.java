package ForUI.Pages.Aliexpress;

import ForUI.Account.Account;
import ForUI.Static.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ForUI.Static.Page.WaitSomeElementToVisibility;

public class AliexpressRegisterPage {
    WebDriver driver;
    @FindBy(className = "fm-tabs-tab")
    private WebElement RegisterButton;
    @FindBy(className = "fm-button")
    private WebElement CreateAccountButton;

    @FindBy(css = "input[placeholder = 'Email address'")
    private WebElement EmailInput;
    @FindBy(css = "input[placeholder = 'Password'")
    private WebElement PasswordInput;

    @FindBy(css= "input[class = 'fm-verify-code fm-verify-code-4']")
    private WebElement Verification;
    @FindBy(css = "button[class = 'ali-kit_Button__button__ngexmt ali-kit_Button__size-m__ngexmt contained ali-kit_Button__default__ngexmt CouponPopup_CouponPopup__closeButton__1y0kf']")
    private WebElement SuccessCreate;

    @FindBy(css = "span[class='fm-error-tip']")
    private WebElement ErrorMessage;

    public AliexpressRegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean Register(Account account) {
        try {
            RegisterButton.click();
            EmailInput.sendKeys(account.getLogin());
            PasswordInput.sendKeys(account.getPassword());
            CreateAccountButton.click();
            return true;
        }catch (Exception e)
        {
            Log.getLogger().error(e.getMessage());
            return false;
        }

    }
    public String Register(String login, String password) {
        EmailInput.sendKeys(login);
        PasswordInput.sendKeys(password);
        WaitSomeElementToVisibility(driver, ErrorMessage, 2);
        return ErrorMessage.getText();
    }
    public boolean InsertVerificationPassword(String password) {
        try {
            Verification.sendKeys(password);
            return true;
        }catch (Exception e)
        {
            Log.getLogger().error(e.getMessage());
            return false;
        }
    }
    public boolean CompleteRegistration() {
        CreateAccountButton.click();
        WaitSomeElementToVisibility(driver, SuccessCreate, 10);
        return SuccessCreate.isDisplayed();
    }
}
