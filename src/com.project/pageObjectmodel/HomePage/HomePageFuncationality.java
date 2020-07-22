package HomePage;

import LoginPage.LoginFuncationality;
import common.CommonConstant;
import suite.AbstractPageObject;

public class HomePageFuncationality extends AbstractPageObject
{

    LoginFuncationality login;

    public void composeEmail(String toEmailid, String subject, String bodytext, String username, String password)
    {
        try {
            if (!CommonConstant.current_page.get().equalsIgnoreCase("HomePage")) {
                login = new LoginFuncationality();
                login.validate_Login(username, password);
            }
            //todo
        }catch (Exception e )
        {

        }


    }

    public void checkSaveItem()
    {

    }



}
