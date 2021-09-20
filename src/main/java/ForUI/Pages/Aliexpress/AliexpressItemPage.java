package ForUI.Pages.Aliexpress;

import ForUI.Static.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static ForUI.Static.Page.ScrollPageToSomeElement;
import static ForUI.Static.Page.WaitSomeElementToVisibility;

public class AliexpressItemPage{
    WebDriver driver;

    @FindBy(css = "button[ae_button_type='remove']")
    private WebElement DeleteItemButton;
    @FindBy(xpath = "//*[text()='OK']")
    private WebElement ConfirmDeleteButton;
    @FindBy(xpath = "//*[text()='Ваша Корзина пуста']")
    private WebElement BasketText;

    @FindBy(css = "a[ae_button_type = 'go_to_cart']")
    private WebElement ToBasketButton;
    @FindBy(xpath = "//*[text()='Добавить в корзину']")
    private WebElement AddToBasket;

    @FindBy(css = "button[class = 'ali-kit_Button__button__ngexmt ali-kit_Button__size-m__ngexmt contained ali-kit_Button__default__ngexmt Product_LikeButton__button__eiubz Product_Actions__button__1j0pn']")
    private WebElement AddToFavoriteButton;

    public AliexpressItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean AddToBasket() {
        SelectFeaturesOfItem();
        ScrollPageToSomeElement(driver, AddToBasket);
        AddToBasket.click();
        WaitSomeElementToVisibility(driver, ToBasketButton, 5);
        return ToBasketButton.isDisplayed();
    }

    public boolean DeleteItem() {
        ToBasketButton.click();
        DeleteItemButton.click();
        ConfirmDeleteButton.click();
        WaitSomeElementToVisibility(driver, BasketText, 5);
        return BasketText.isDisplayed();
    }

    public int AddToFavorite() {
        try
        {
            ScrollPageToSomeElement(driver, AddToFavoriteButton);
            int count = Integer.parseInt(AddToFavoriteButton.getText());
            return count;
        }
        catch (Exception e)
        {
            Log.getLogger().error(e.getMessage());
            return -1;
        }
    }

    public int GetCountOfFavoritesAfterAdd() {
        AddToFavoriteButton.click();
        return Integer.parseInt(AddToFavoriteButton.getText());
    }

    private void SelectFeaturesOfItem() {
        try {
            int size = driver.findElements(By.className("Product_SkuValuesBar__container__6ryfe")).size();
            List<WebElement> list = driver.findElements(By.className("Product_SkuValuesBar__container__6ryfe"));
            for (int i = 0; i < size; i++) {
                List<WebElement> spis = list.get(i).findElements(By.className("Product_SkuValueBaseItem__item__o90dx"));
                if(spis.size() > 1) {
                    list.get(i).findElement(By.className("Product_SkuValueBaseItem__item__o90dx")).click();
                }
            }
        }
        catch (Exception e)
        {
            Log.getLogger().error(e.getMessage());
        }
    }
}
