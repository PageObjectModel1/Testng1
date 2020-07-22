package common;

public class CommonConstant
{
    public final static String configfile=System.getProperty("user.dir")+"//config//Config.xml";
    public final static String geekoDriver=System.getProperty("user.dir")+"//browserDrivers";
    public final static String chromeDriver=System.getProperty("user.dir")+"//browserDrivers";
    public final static String sikuliInputs=System.getProperty("user.dir")+"//sikuliImages";



// change done
    public final static String commonUIMap=System.getProperty("user.dir")+"//objectRepository//"+"CommonObjectUI.xml";
    public final static String commonDataMap=System.getProperty("user.dir")+"//dataProvider//"+"CommonDataMap.xml";


    public final static ThreadLocal<String> current_page=new ThreadLocal<>();

}
