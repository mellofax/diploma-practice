package ForUI.Pages.Mail;

import ForUI.Static.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ForUI.Static.Page.WaitSomeElementToVisibility;

public class MailHomePage {
    WebDriver driver;
    @FindBy(css = "div[class='dataset__items']>a")
    private WebElement VerificationMessage;
    @FindBy(css = "div[class='wrap_mr_css_attr']>div[class='code_mr_css_attr']")
    private WebElement VerificationPassword;

    public MailHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean FindMessage() {
        try {
            WaitSomeElementToVisibility(driver, VerificationMessage, 25);
            VerificationMessage.click();
            return true;
        }
        catch (Exception e)
        {
            Log.getLogger().error("The message was not received");
            return false;
        }
    }
    public String GetVerificationPassword() {
        WaitSomeElementToVisibility(driver,VerificationPassword ,25 );
        String password = VerificationPassword.getText();
        return password;
    }
}
