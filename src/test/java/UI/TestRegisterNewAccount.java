package UI;

import ForUI.Account.Account;
import ForUI.DateBase.DBConnection;
import ForUI.Static.Log;
import ForUI.Pages.Aliexpress.AliexpressRegisterPage;
import ForUI.Pages.Mail.MailHomePage;
import ForUI.Pages.Mail.MailLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import static ForUI.Static.Page.*;

public class TestRegisterNewAccount {

    WebDriver driver;
    Account account;
    String VerificationPassword;
    AliexpressRegisterPage aliexpressRegisterPage;
    MailHomePage mailHomePage;

    @BeforeGroups(groups = "Register")
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://login.aliexpress.ru/?return_url=https%3A%2F%2Fbest.aliexpress.ru%2F%3Flan%3Den");
        Log.getLogger().info("========================Registration=TEST=STARTING=======================");
        DBConnection connection = new DBConnection();
        account = connection.GetEmailAccount();
    }

    @Test(groups = "Register", priority = 1)
    @Description("Filling the fields of the registration")
    public void FillingTheRegistrationFields() {
        aliexpressRegisterPage = PageFactory.initElements(driver, AliexpressRegisterPage.class);
        Assert.assertTrue(aliexpressRegisterPage.InsertInfo(account));
        Log.getLogger().info("The fields have been filled in successfully!");
    }

    @Test(groups = "Register", priority = 2)
    @Description("Login on mail")
    public void LoginOnMail() {
        OpenNewTab(driver, "https://mail.ru");
        SwitchPage(driver);
        MailLoginPage mailLoginPage = PageFactory.initElements(driver, MailLoginPage.class);
        Assert.assertTrue(mailLoginPage.Login(account));
        Log.getLogger().info("Login to the mail is successful");
    }

    @Test(groups = "Register", priority = 3)
    @Description("Find Message")
    public void FindMessage() {
        mailHomePage = PageFactory.initElements(driver, MailHomePage.class);
        Assert.assertTrue(mailHomePage.FindMessage());
        Log.getLogger().info("Message is successfully find!");
    }

    @Test(groups = "Register", priority = 4)
    @Description("Get verification password")
    public void GetVerPassword() {
        VerificationPassword = mailHomePage.GetVerificationPassword();
        Assert.assertNotNull(VerificationPassword);
        Log.getLogger().info("The password was received!");
    }

    @Test(groups = "Register", priority = 5)
    @Description("Insert verification password")
    public void InsertVerificationPassword() {
        ClosePage(driver);
        SwitchPage(driver);
        Assert.assertTrue(aliexpressRegisterPage.InsertVerificationPassword(VerificationPassword));
        Log.getLogger().info("Verification password insert successfully!");
    }

    @Test(groups = "Register", priority = 6)
    @Description("Complete create Account on aliexpress")
    public void CompleteCreateAccount() {
        Assert.assertTrue(aliexpressRegisterPage.CompleteRegistration());
    }

    @AfterGroups(groups = "Register")
    public void Close() {
        ClosePage(driver);
        Log.getLogger().info("=========================================================================");
    }
}
