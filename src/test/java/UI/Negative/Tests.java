package UI.Negative;

import ForUI.Pages.Aliexpress.AliexpressHomePage;
import ForUI.Pages.Aliexpress.AliexpressLoginPage;
import ForUI.Pages.Aliexpress.AliexpressRegisterPage;
import ForUI.Static.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class Tests {
    WebDriver driver;
    AliexpressLoginPage aliexpressLoginPage;
    AliexpressRegisterPage aliexpressRegisterPage;

    @BeforeGroups(groups = "NegativeTests")
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://login.aliexpress.ru/?return_url=https%3A%2F%2Fbest.aliexpress.ru%2F%3Flan%3Den");
        Log.getLogger().info("=============================NEGATIVE=TESTS================================");
    }
    @Test(groups = "NegativeTests", priority = 1)
    @Description("Don't filling the fields (LOGIN PAGE)")
    public void DontFillingFieldsLoginPage() {
        aliexpressLoginPage = PageFactory.initElements(driver, AliexpressLoginPage.class);
        Assert.assertEquals(aliexpressLoginPage.Login("", ""), "Please enter your email address or member ID.");
        Log.getLogger().info("Loggin is failed!");
    }
    @Test(groups = "NegativeTests", priority = 2)
    @Description("Filling only login field (LOGIN PAGE)")
    public void FillingLoginOnlyLoginPage() {
        Assert.assertEquals(aliexpressLoginPage.Login("tms578gg@mail.ru", ""), "Please enter your password.");
        Log.getLogger().info("Loggin is failed!");
    }
    @Test(groups = "NegativeTests", priority = 3)
    @Description("Filling only password field (LOGIN PAGE)")
    public void FillingPasswordOnlyLoginPage() {
        Assert.assertEquals(aliexpressLoginPage.Login("", "gsdgsh245"), "Please enter your email address or member ID.");
        Log.getLogger().info("Loggin is failed!");
    }

    @Test(groups = "NegativeTests", priority = 4)
    @Description("Unregistered account (LOGIN PAGE)")
    public void FillingUnregisteredAccountLoginPage() {
        Assert.assertEquals(aliexpressLoginPage.Login("578gg@mail.ru", "gsdgsh245"), "Your account name or password is incorrect.");
        Log.getLogger().info("Loggin is failed!");
    }

    @Test(groups = "NegativeTests", priority = 5)
    @Description("Don't filling the fields (REGISTER PAGE)")
    public void DontFillingFieldsRegisterPage() {
        aliexpressRegisterPage = PageFactory.initElements(driver, AliexpressRegisterPage.class);
        driver.findElement(By.className("fm-tabs-tab")).click();
        Assert.assertEquals(aliexpressRegisterPage.Register("", ""), "Please enter a valid Email Address");
        Log.getLogger().info("Registration is failed!");
    }

    @Test(groups = "NegativeTests", priority = 6)
    @Description("Filling only login field (REGISTER PAGE)")
    public void FillingLoginOnlyRegisterPage() {
        Assert.assertEquals(aliexpressRegisterPage.Register("tms578gg@mail.ru", ""), "Please enter 6 - 20 characters (A-Z, a-z, 0-9 only)");
        Log.getLogger().info("Registration is failed!");
    }
    @Test(groups = "NegativeTests", priority = 7)
    @Description("Filling field (REGISTER PAGE)")
    public void FillingRegisteredAccountRegisterPage() {
        Assert.assertEquals(aliexpressRegisterPage.Register("", "gsdgsh245"), "Please enter 6 - 20 characters (A-Z, a-z, 0-9 only)");
        Log.getLogger().info("Registration is failed!");
    }

    @AfterGroups(groups = "NegativeTests")
    public void Close() {
        driver.close();
        Log.getLogger().info("===========================================================================");
    }
}
