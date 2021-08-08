import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewcarTest extends TestBase{
    @BeforeMethod
    public void precondition(){
        if(app.userHelper().isLogged()){
            app.userHelper().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
        }
    }

    @Test
    public void createNewCar(){

        int i =(int) ((System.currentTimeMillis()/1000)%3600);

        System.out.println(i);

       Car car = Car.builder()
               .address("Tel Aviv, Israel")
               .make("BMW")
               .model("M5")
               .year("2020")
               .engine("2.0")
               .fuel("Petrol")
               .gear("MT")
               .wD("AWD")
               .doors("5")
               .seats("4")
               .clasS("C")
               .fuelConsumption("6.5")
               .carRegNumber("12-"+i)
               .price("65")
               .distanceIncluded("500")
               .typeFeature("type of")
               .about("very good car")
               .build();
        System.out.println("Car Num --->" +car.getCarRegNumber());
        System.out.println(car.getCarRegNumber());


       app.carHelper().openCarForm();
       app.carHelper().fillCarForm(car);
       app.carHelper().attachPhoto();
        app.carHelper().clickButtonSubmit();
        app.carHelper().pause(3000);
        Assert.assertTrue(app.carHelper().isCarAdded());

    }
    @AfterMethod
    public void postCondition(){
        app.carHelper().submitCar();
    }

}
