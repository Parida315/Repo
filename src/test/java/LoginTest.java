import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @BeforeAll
    public static void setUp(){
        TestBase.browserName=UtilityClass.readConfigProperties("config-qa.properties","browsername");
        TestBase.browserSetup(UtilityClass.readConfigProperties("config-qa.properties","url"));
    }
    @Test
    public void loginTest(){
        String userName=UtilityClass.readConfigProperties("config-qa.properties","username");
        String password=UtilityClass.readConfigProperties("config-qa.properties","password");
        LoginUserObject userObject=new LoginUserObject(userName,password);
        LoginPage.logIn(userObject);
        Assertions.assertTrue(DashboardPage.verifyLogin());
    }
    @AfterAll
    public static void tearDow(){
       DashboardPage.logOut();
        TestBase.closeBrowser();
    }
}
