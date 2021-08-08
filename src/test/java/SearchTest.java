import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTest extends TestBase{

    @Test(groups = {"web"})
    public void positiveTestSendKey(){
        //with concatenate string
        logger.info("Haifa\",\"08/10/2021\",\"08/30/2021\"");

        app.search().typeSearchCurrentMonth("Haifa","08/10/2021","08/30/2021");
        logger.info("message");
        app.userHelper().submitForm();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarAppeared());
    }
    @Test(groups = {"a","web"})
    public void negativeTestSendKey(){
        //with concatenate string
        app.search().typeSearchCurrentMonth("Haifa","06/29/2021","06/30/2021");

        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isDataInPath());
        Assert.assertTrue(app.search().buttonYallaInactive());
    }

    @Test()
    public void selectPeriodCurrentMouth(){
        app.search().fillSearchFormCurrentMonth("Haifa","08/10/2021","08/30/2021");
        app.userHelper().submitForm();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarAppeared());


    }

    @Test
    public void selectPeriodInFuture(){
        app.search().fillSearchFormInFuture("Haifa","08/10/2021","08/30/2021");
        app.userHelper().submitForm();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarAppeared());
    }
    @AfterMethod
    public void postConditions(){
        app.search().backToHome();
    }
}
