package UI.Positive;

import ForUI.Account.Account;
import ForUI.DateBase.DBConnection;
import ForUI.Pages.Aliexpress.AliexpressHomePage;
import ForUI.Pages.Aliexpress.AliexpressLoginPage;
import ForUI.Static.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import static ForUI.Static.Page.ClosePage;
import static ForUI.Static.Page.SwitchPage;

public class TestsWithAutorization {
    WebDriver driver;
    Account account;
    AliexpressHomePage aliexpressHomePage;

    @BeforeGroups(groups = "TestsWithAuthorization")
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://login.aliexpress.ru/?return_url=https%3A%2F%2Fbest.aliexpress.ru%2F%3Flan%3Den");
        Log.getLogger().info("======================WITH=AUTORIZATION=TEST=STARTING=====================");
        DBConnection connection = new DBConnection();
        account = connection.GetAliexpressAccount();
    }

    @Test(groups = "TestsWithAuthorization", priority = 1)
    @Description("Login on aliexpress")
    public void LoginOnAliexpress() {
        AliexpressLoginPage aliexpressLoginPage = PageFactory.initElements(driver, AliexpressLoginPage.class);
        Assert.assertTrue(aliexpressLoginPage.Login(account));
        Log.getLogger().info("Authorization is successful!");
    }

    @Test(groups = "TestsWithAuthorization", priority = 2)
    @Description("close ad on ALIEXPRESS test")
    public void CloseAdTest() {
        aliexpressHomePage = PageFactory.initElements(driver, AliexpressHomePage.class);
        Assert.assertTrue(aliexpressHomePage.CloseAd());
        Log.getLogger().info("Ad is successfully closed!");
    }

    @Test(groups = "TestsWithAuthorization", priority = 3)
    @Description("add to basket some item on ALIEXPRESS test")
    public void AddItemToBasketTest() throws InterruptedException {
        Assert.assertTrue(aliexpressHomePage.AddToBasketSomeItem("Фото на стену"));
        Log.getLogger().info("Item was successfully added to basket!");
    }

    @Test(groups = "TestsWithAuthorization", priority = 4)
    @Description("deleting item from the basket")
    public void DeleteItemFromBasketTest() {
        Assert.assertTrue(aliexpressHomePage.DeleteItem());
        Log.getLogger().info("Item was successfully deleted from basket!");
        ClosePage(driver);
        SwitchPage(driver);
    }

    @Test(groups = "TestsWithAuthorization", priority = 5)
    @Description("add to favorite some item on ALIEXPRESS test")
    public void AddItemToFavoriteTest() {
        Assert.assertEquals(aliexpressHomePage.AddToFavoriteSomeItem("естетика"), aliexpressHomePage.GetCountOfFavoritesAfterAdd() + 1);
        Log.getLogger().info("Item was successfully added to favorite!");
        ClosePage(driver);
        SwitchPage(driver);
    }

    @Test(groups = "TestsWithAuthorization", priority = 6)
    @Description("change language on ALIEXPRESS test")
    public void ChangeLanguageTest(){
        Assert.assertEquals(aliexpressHomePage.ChangeLanguage(), "English");
        Log.getLogger().info("Language was changed successfully!");
    }

    @AfterGroups(groups = "TestsWithAuthorization")
    public void Close() {
        ClosePage(driver);
        Log.getLogger().info("===========================================================================");
    }
}
