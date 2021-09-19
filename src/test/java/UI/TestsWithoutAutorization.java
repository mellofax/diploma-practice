package UI;

import Pages.Aliexpress.AliexpressItemPage;
import Static.Log;
import Pages.Aliexpress.AliexpressHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import static Static.Page.ClosePage;
import static Static.Page.SwitchPage;

public class TestsWithoutAutorization {
    WebDriver driver;
    AliexpressHomePage aliexpressHomePage;

    @BeforeGroups(groups = "TestsWithoutAuthorization")
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://aliexpress.ru/");
        Log.getLogger().info("====================WITHOUT=AUTORIZATION=TEST=STARTING=====================");
    }

    @Test(groups = "TestsWithoutAuthorization", priority = 1)
    @Description("close ad on ALIEXPRESS test")
    public void CloseAdTest() {
        aliexpressHomePage = PageFactory.initElements(driver, AliexpressHomePage.class);
        Assert.assertTrue(aliexpressHomePage.CloseAd());
        Log.getLogger().info("Ad is successfully closed!");
    }

    @Test(groups = "TestsWithoutAuthorization", priority = 2)
    @Description("add to basket some item on ALIEXPRESS test")
    public void AddItemToBasketTest() throws InterruptedException {
        Assert.assertTrue(aliexpressHomePage.AddToBasketSomeItem("Фото на стену"));
        Log.getLogger().info("Item was successfully added to basket!");
    }

    @Test(groups = "TestsWithoutAuthorization", priority = 3)
    @Description("deleting item from the basket")
    public void DeleteItemFromBasketTest() {
        Assert.assertTrue(aliexpressHomePage.DeleteItem());
        Log.getLogger().info("Item was successfully deleted from basket!");
        ClosePage(driver);
        SwitchPage(driver);
    }

    @Test(groups = "TestsWithoutAuthorization", priority = 4)
    @Description("add to favorite some item on ALIEXPRESS test")
    public void AddItemToFavoriteTest() {
        Assert.assertEquals(aliexpressHomePage.AddToFavoriteSomeItem("естетика"), aliexpressHomePage.GetCountOfFavoritesAfterAdd());
        Log.getLogger().info("Item was successfully added to favorite!");
        ClosePage(driver);
        SwitchPage(driver);
    }

    @Test(groups = "TestsWithoutAuthorization", priority = 5)
    @Description("change language on ALIEXPRESS test")
    public void ChangeLanguageTest(){
        Assert.assertEquals(aliexpressHomePage.ChangeLanguage(), "English");
        Log.getLogger().info("Language was changed successfully!");
    }

    @AfterGroups(groups = "TestsWithoutAuthorization")
    public void Close() {
        ClosePage(driver);
        Log.getLogger().info("===========================================================================");
    }
}
