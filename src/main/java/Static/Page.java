package Static;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    private static WebDriverWait wait = null;

    public static void SwitchPage(WebDriver driver) {
        for(String tab : driver.getWindowHandles())
            driver.switchTo().window(tab);
        Log.getLogger().info("The page is switched");
    }
    public static void SwitchPage(WebDriver driver, String tab) {
        driver.switchTo().window(tab);
    }
    public static void OpenNewTab(WebDriver driver, String url) {
        JavascriptExecutor jscript = (JavascriptExecutor)driver;
        jscript.executeScript("window.open(\"" + url + "\");");
        Log.getLogger().info("A new tab is open");
    }
    public static void ScrollPageToSomeElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();" , element);
        Log.getLogger().info("Page is successfuly scrolled");
    }
    public static void WaitSomeElementToClick(WebDriver driver, WebElement element, int time) {
        wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public static void WaitSomeElementToVisibility(WebDriver driver, WebElement element, int time){
        wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void ClosePage(WebDriver driver){
        driver.close();
        Log.getLogger().info("The page is closed");
    }
}
