package UI.Positive;

import ForUI.Account.Account;
import ForUI.DateBase.DBConnection;
import ForUI.Pages.Aliexpress.AliexpressRegisterPage;
import ForUI.Static.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class TestRegisterAccount {

    WebDriver driver;
    Account account;
    AliexpressRegisterPage aliexpressRegisterPage;

    @BeforeGroups(groups = "Registration")
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://login.aliexpress.ru/?return_url=https%3A%2F%2Fbest.aliexpress.ru%2F%3Flan%3Den");
        Log.getLogger().info("========================Registration=TEST=STARTING=======================");
        DBConnection connection = new DBConnection();
        account = connection.GetEmailAccount();
    }

    @Test(groups = "Registration", priority = 1)
    @Description("Filling the fields of the registration")
    public void FillingTheRegistrationFields() {
        aliexpressRegisterPage = PageFactory.initElements(driver, AliexpressRegisterPage.class);
        Assert.assertTrue(aliexpressRegisterPage.Register(account));
        Log.getLogger().info("The fields have been filled in successfully!");
    }
}
