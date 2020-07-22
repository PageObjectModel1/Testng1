package base;

import common.CommonConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import utils.ORReading;


import java.io.FileInputStream;
import java.util.*;

public class BaseFuncationality
{
    public static WebDriver driver=null;
    public static Properties CONFIG;
    public static ORReading commonOR, CommonData;

    public static WebDriverWait wait=null;
    public static SoftAssert soft = null;
    public static Actions act= null;

    static
    {
        CONFIG = new Properties();
        commonOR = new ORReading();
        CommonData= new ORReading();
        try
        {
            commonOR.loadFromXML(CommonConstant.commonUIMap);
            CONFIG.loadFromXML(new FileInputStream(CommonConstant.configfile));

        }catch (Exception e)
        {
            System.out.println("common UI map and Config data is not loadded as expected " +e );
        }
    }

    public void initizeModules()
    {
        try
        {
            String uimap=loadUIModule();
            String datamap=loaddataProvider();
            commonOR.loadFromXML(uimap);
            CommonData.loadFromXML(datamap);
        }
        catch (Exception e)
        {
            System.out.println("initize method  -----  " + e );
        }
    }


    public String loadUIModule()
    {
        String uimap=null;
        String module=getModule();
        System.out.println("Module name is " + module);
        uimap= CONFIG.getProperty(module + "ObjectUI");
        System.out.println("uimap name is " + uimap);
        return uimap;
    }


    public String loaddataProvider()
    {
        String datamap=null;
        String module=getModule();
        System.out.println("Module name is " + module);
        datamap= CONFIG.getProperty(module + "DataMap");
        System.out.println("data name is " + datamap);
        return datamap;
    }


    public String getModule()
    {
        String module=null;

        System.out.println("this.getClass().toString() " + this.getClass().toString());

        if(this.getClass().toString().contains("HomePage."))
        {
            System.out.println("Module name: HomePage" );
            Reporter.log("Module name: HomePage" );
            module = "Common";
        }
        else if (this.getClass().toString().contains("LoginPage."))
        {
            System.out.println("Module name: LoginPage" );
            Reporter.log("Module name: LoginPage" );
            module="Common";
        }
        else if (this.getClass().toString().contains("SignupPage."))
        {
            System.out.println("Module name: SignupPage" );
            Reporter.log("Module name: SignupPage" );
            module="Common";
        }return module;
    }

    public WebElement getcommonObject(String object)   //homefield
    {
        WebElement elt=null;
        String locators, value;
        try
        {
            locators=commonOR.getPropertyType(object);  // locator  xpath
            value=commonOR.getPropertyValue(object); // value //span[text()='Home']
            switch(locators)
            {
                case "id":
                    elt= driver.findElement(By.id(value));
                    break;
                case "xpath":
                    elt= driver.findElement(By.xpath(value));
                    break;
                case "name":
                    elt= driver.findElement(By.name(value));
                    break;
                case "css":
                    elt= driver.findElement(By.cssSelector(value));
                    break;
                case "tagname":
                    elt= driver.findElement(By.tagName(value));
                    break;
                case "class":
                    elt= driver.findElement(By.className(value));
                    break;
                case "linktext":
                    elt= driver.findElement(By.linkText(value));
                    break;
                default:
                    Assert.fail("Object didnt not found");
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println("Object didnt not found");
        }
        return elt;   // driver.findElement(By.xpath(//span[text()='Home']));
    }

    public String getData(String data)   //emailId1
    {
        String expecteddata=null;
        String locators, value=null;
        ORReading objpro=CommonData;
        try
        {
            locators=objpro.getPropertyType(data);  // data
            value=objpro.getPropertyValue(data);  //manjunath.quality1@gmail.com
            switch(locators)
            {
                case "data":
                    expecteddata= value;
                    break;
                default:
                    Assert.fail("data formate is not proper " + data);
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println("data didnt not found : " + data);
        }
        return expecteddata;    //  expecteddata= manjunath.quality1@gmail.com
    }
}
