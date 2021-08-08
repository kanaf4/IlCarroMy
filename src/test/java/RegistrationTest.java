import application.MyDataProvider;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void precondition(){
        if(!app.userHelper().isLogged()){
            app.userHelper().logout();
        }
    }

    @Test(groups = {"web"})
    public void registrationTest(){
        int i =(int) ((System.currentTimeMillis()/1000)%3600);
        User user = new User()
                .withName("Mona")
                .withLastName("Dow")
                .withEmail("mona"+i+"@gmail.com")
                .withPassword("Mm123"+i+"$");
        logger.info("Registration with --> Name: "
                +user.getName() + "-->LastName: " +user.getLastName()+
                " --> Email:" + user.getEmail() + "-->Password -->" +user.getPassword());

app.userHelper().openRegistrationForm();
app.userHelper().fillRegistrationForm(user);
app.userHelper().checkPolicy();
app.userHelper().submitForm();
app.userHelper().pause(7000);
        String regS=app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));
        Assert.assertTrue(regS.contains("success"));
        logger.info("Test passed");



    }

    @Test(groups = {"web"},dataProvider = "registCSV",dataProviderClass = MyDataProvider.class)
    public void registrationTestCSV(User user) {


        app.userHelper().openRegistrationForm();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().checkPolicy();
        app.userHelper().submitForm();
        app.userHelper().pause(7000);
        String regS = app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));
        Assert.assertTrue(regS.contains("success"));
        logger.info("Test passed");

    }



    @Test(dataProvider ="validREGDataClassDP" ,dataProviderClass = MyDataProvider.class)
    public void registrationTestFrmDPClass(String name, String lastName,String email, String password){

        User user = new User()
                .withName(name)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password);

        logger.info("Registration with --> Name: "
                +user.getName() + "-->LastName: " +user.getLastName()+
                " --> Email:" + user.getEmail() + "-->Password -->" +user.getPassword());

        app.userHelper().openRegistrationForm();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().checkPolicy();
        app.userHelper().submitForm();
        app.userHelper().pause(7000);
        String regS=app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));
        Assert.assertTrue(regS.contains("success"));
        logger.info("Test passed");



    }


    @AfterMethod

    public void postCondition(){
        app.userHelper().clickOkButton();
    }
}
