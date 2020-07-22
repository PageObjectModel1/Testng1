package suite;

import base.BaseFuncationality;
import common.CommonConstant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

public class BrowserFactory extends BaseFuncationality
{

    @BeforeSuite(alwaysRun = true)
    @Parameters(value={"browser","baseurl"})
    public void doInitializeBrowser(String browser, String baseurl)
    {
        System.out.println("baseurl " + baseurl);
        try
        {
            initizeModules();
            if(baseurl.equalsIgnoreCase("prod"))
                baseurl=CONFIG.getProperty("prod");
            if(baseurl.equalsIgnoreCase("pre-prod"))
                baseurl=CONFIG.getProperty("pre-prod");

            if(browser.equalsIgnoreCase("chrome_incognito"))
            {
                System.setProperty("webdriver.chrome.driver",CommonConstant.chromeDriver+"//chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
            }
            else
            if(browser.equalsIgnoreCase("chrome_normal"))
            {
                System.setProperty("webdriver.chrome.driver",CommonConstant.chromeDriver+"//chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            }
            else
            if(browser.equalsIgnoreCase("firefox"))
            {
                System.setProperty("webdriver.gecko.driver",CommonConstant.geekoDriver+"//geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            else
            if(browser.equalsIgnoreCase("ie"))
            {

            }
            else
            if(browser.equalsIgnoreCase("android_Chrome"))
            {

            }
            driver.get(baseurl);
            Reporter.log("Browser Launched " + browser);
            CommonConstant.current_page.set("YahooLandingPage");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @AfterSuite
    public void closeBrowser()
    {
        if(driver!=null)
        {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
        Reporter.log("Browser closed");
    }


    @BeforeClass(alwaysRun = true)
    @Parameters(value={"browser","baseurl"})
    public void checkBrowserStatus(String browser, String baseurl)
    {
        if(!browserstatus())
        {
            doInitializeBrowser(browser,baseurl);
            Reporter.log("Browser re-launached ");
        }
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters(value={"browser","baseurl"})
    public void setup(String browser, String baseurl)
    {
        if(!browserstatus())
        {
            doInitializeBrowser(browser,baseurl);
            Reporter.log("Browser re-launached ");
        }
    }


    public boolean browserstatus()
    {
        boolean status=false;
        try
        {
            String title= driver.getTitle();
            if(driver!=null)
                status=true;
        }
        catch (Exception e)
        {
            System.out.println("exception in ");
        }
        return status;
    }


    @AfterClass(alwaysRun = true)
    public void checkBrowserStatus()
    {
        // Todo
    }
}
