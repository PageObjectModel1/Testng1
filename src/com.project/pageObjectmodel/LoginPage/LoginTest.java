package LoginPage;

import common.CommonConstant;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends LoginFuncationality
{
    @Test(description = "validate Login with invalid data")
    public void validateLogin1()
    {
        try
        {
            String title = validate_Login(getData("emailId"),getData("password")+1);
            System.out.println("title " + title);
            Assert.assertTrue(title.contains(getData("loginpageTitle")),"Some thing went wrong");
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw e;
        }
    }

    @Test(description = "validate Login with valid data ")
    public void validateLogin2()
    {
        try{
            String title = validate_Login(getData("emailId"),getData("password"));
            Assert.assertTrue(title.contains(getData("homepageTitle")),"failed to login");
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw e;
        }
    }
}