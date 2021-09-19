package Pages.Aliexpress;

import Static.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static Static.Page.*;

public class AliexpressHomePage extends AliexpressItemPage {
    WebDriver driver;
    @FindBy(css = "button[class = 'ali-kit_Button__button__ngexmt ali-kit_Button__size-m__ngexmt contained ali-kit_Button__default__ngexmt CouponPopup_CouponPopup__closeButton__1y0kf']")
    private WebElement AdCloseButton;
    @FindBy(css = "a[class = 'next-dialog-close']")
    private WebElement AdCloseButton1;

    @FindBy(css = "input[class = 'Header_SearchInput__input__85mzj'")
    private WebElement FindInput;
    @FindBy(css = "input[id='search-key']")
    private WebElement FindInput1;


    @FindBy(css = "a[class = 'switcher-info notranslate']")
    private WebElement SettingsButton;
    @FindBy(css = "div[class = 'switcher-currency-c language-selector']")
    private WebElement LanguageButton;
    @FindBy(xpath = "//*[text()='English']")
    private WebElement SelectLanguageButton;
    @FindBy(xpath = "//*[text()='Сохранить']")
    private WebElement ConfirmChanges;
    @FindBy(css = "span[class = 'language_txt'")
    private WebElement LanguageText;

    public AliexpressHomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean AddToBasketSomeItem(String item) throws InterruptedException {
        FindInput.sendKeys(item + "\n");
        OpenNewTab(driver, SomeItem().getAttribute("href"));
        SwitchPage(driver);
        return super.AddToBasket();
    }

    public int AddToFavoriteSomeItem(String item) {
        try {
            AdCloseButton1.click();
            FindInput1.sendKeys(" " + item + "\n");
            OpenNewTab(driver, SomeItem().getAttribute("href"));
            SwitchPage(driver);
            return super.AddToFavorite();
        }catch (Exception e)
        {
            Log.getLogger().error(e.getMessage());
            return -1;
        }
    }

    public boolean CloseAd() {
        AdCloseButton.click();
        return FindInput.isEnabled();
    }

    public String ChangeLanguage(){
        try {
            List<WebElement> list = driver.findElements(By.cssSelector("a[class = 'next-dialog-close']"));
            if (list.size() > 0)
                AdCloseButton1.click();
            ScrollPageToSomeElement(driver, SettingsButton);
            SettingsButton.click();
            LanguageButton.click();
            SelectLanguageButton.click();
            ConfirmChanges.click();
            Thread.sleep(6000);
            return LanguageText.getText();
        }
        catch (Exception e)
        {
            Log.getLogger().error(e.getMessage());
            return "error";
        }
    }


    private WebElement SomeItem() {
        List<WebElement> list = driver.findElements(By.cssSelector("a[class = 'item-title'"));
        int item = (int) ((Math.random() * (list.size() - 1)) + 1);
        return list.get(item);
    }
}
