package suite;

import com.google.common.collect.Multimaps;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AbstractPageObject extends BrowserFactory
{
    public String getpageTitle()
    {
        return driver.getTitle();
    }

    public String getpageUrl()
    {
        return driver.getCurrentUrl();
    }

    public void waitforElementClick(WebElement ele)
    {
        wait= new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void waitforElementVisible(WebElement ele)
    {
        wait= new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitforElementinVisible(WebElement ele)
    {
        wait= new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void reloadpage()
    {
        driver.navigate().refresh();
    }

    public void keyboardInput(WebElement ele, String data)
    {
       act= new Actions(driver);
       act.sendKeys(ele,data, Keys.ENTER).perform();
    }

    public void swithtoFrame(WebElement ele)
    {
        driver.switchTo().frame(ele);
    }

    public void outfromFrame()
    {
        driver.switchTo().defaultContent();
    }

    public void waitforUrl(String url)
    {
        wait= new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.urlContains(url));
    }

    public void swithtowindow(String windowname)
    {
        driver.switchTo().window(windowname);
    }

    public void pagescroll()
    {
        act= new Actions(driver);
        act.sendKeys(Keys.PAGE_UP).perform();
    }

    public void pagescrollToElement(WebElement ele)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",ele);
    }

    public void clickwithJavascriptExe(WebElement ele)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",ele);
    }

    public void implicitWait()
    {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void pause()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void waitforTitle(String title)
    {
        wait= new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.titleContains(title));
    }


    public void mousehover(WebElement ele)
    {
        act= new Actions(driver);
        act.moveToElement(ele).perform();
    }

    public void navigateToURL(String url)
    {
        driver.navigate().to(url);
    }


    public void waitForURL(String url)
    {
        wait= new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.urlContains(url));
    }
}
