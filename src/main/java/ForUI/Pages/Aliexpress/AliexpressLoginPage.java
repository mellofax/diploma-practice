package ForUI.Pages.Aliexpress;

import ForUI.Account.Account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ForUI.Static.Page.WaitSomeElementToVisibility;

public class AliexpressLoginPage {
    WebDriver driver;
    @FindBy(id = "fm-login-id")
    private WebElement EmailInput;
    @FindBy(id = "fm-login-password")
    private WebElement PasswordInput;

    @FindBy(className = "fm-button")
    private WebElement LoginButton;

    @FindBy(css = "button[class = 'ali-kit_Button__button__ngexmt ali-kit_Button__size-m__ngexmt contained ali-kit_Button__default__ngexmt CouponPopup_CouponPopup__closeButton__1y0kf']")
    private WebElement SuccessLogin;

    public AliexpressLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean Login(Account account)
    {
        EmailInput.sendKeys(account.getLogin());
        PasswordInput.sendKeys(account.getPassword());
        LoginButton.click();
        WaitSomeElementToVisibility(driver, SuccessLogin, 5);
        return  SuccessLogin.isDisplayed();
    }
}
