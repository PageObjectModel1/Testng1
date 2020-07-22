package LoginPage;

import common.CommonConstant;
import suite.AbstractPageObject;

public class LoginFuncationality extends AbstractPageObject
{
    public String validate_Login(String username, String password)
    {
        try {
            navigateToLogin();
            implicitWait();
            getcommonObject("emailID_Field").clear();
            getcommonObject("emailID_Field").sendKeys(username);
            clickwithJavascriptExe(getcommonObject("Nextbuttn1"));
            waitforElementClick(getcommonObject("password_Field"));
            getcommonObject("password_Field").sendKeys(password);
            pause();
            clickwithJavascriptExe(getcommonObject("Nextbuttn2"));
            pause();

            try {
                if (getcommonObject("headerAfterlogin").isDisplayed())
                {
                    naviagatToHomepage();
                    CommonConstant.current_page.set("HomePage");
                }
            } catch (Exception e)
            {
                System.out.println("excepetion came");
            }
            CommonConstant.current_page.set("LoginPasswordPage");
        } catch (Exception e)
        {
            System.out.println(e);
            throw e;
        }return getpageTitle();
    }


    public void navigateToLogin()
    {
        try
        {
            if(CommonConstant.current_page.get().equalsIgnoreCase("YahooLandingPage"))
            {
                getcommonObject("headersignlink").click();
                waitForURL(getData("loginpageurl"));
            }
            else {
                navigateToURL(CONFIG.getProperty("prod"));
                getcommonObject("headersignlink").click();
                waitForURL(getData("loginpageurl"));
            }

            CommonConstant.current_page.set("LoginPage");
        }catch (Exception e)
        {
            System.out.println(e);
            throw e;
        }
    }

    public void naviagatToHomepage()
    {
        try {
            getcommonObject("headermailbutton").click();
            waitforTitle(getData("homepageTitle"));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
