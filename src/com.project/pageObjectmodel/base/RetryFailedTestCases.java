package base;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import suite.AbstractPageObject;

public class RetryFailedTestCases extends AbstractPageObject implements IRetryAnalyzer
{
    private boolean retry_status = false;
    private int RETRY_COUNTER = 1;
    private final int MAX_RETRY_COUNT = Integer.parseInt(CONFIG.getProperty("MAX_RETRY_COUNT"));


    public boolean retry(ITestResult result)
    {
        if (RETRY_COUNTER <= MAX_RETRY_COUNT)
        {
            System.out.println("Going to retry test case: " + result.getName() + ", " + (RETRY_COUNTER) + " out of " + MAX_RETRY_COUNT +" times with Test Status : "+getResultStatusName(result.getStatus()));
            RETRY_COUNTER++;
            retry_status = true;
        } else {
            System.out.println(result.getName()+" : Retry for this Test Case completed the MAX of Retry attempts with Test Status : "+getResultStatusName(result.getStatus()));
            result.getThrowable().printStackTrace();
            Assert.fail( result.getThrowable().getCause().toString());
        }
        return retry_status;
    }

    public String getResultStatusName(int status)
    {
        String resultName = null;
        if(status==1)
            resultName = "SUCCESS";
        if(status==2)
            resultName = "FAILURE";
        if(status==3)
            resultName = "SKIP";
        return resultName;
    }
}
