package application;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase{
    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[.=' Log in ']"));
    }
    public void fillLoginForm(String email, String password){
        type(By.id("email"),email);
        type(By.id("password"),password);
    }
    public void fillLoginForm(User user){
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }
    public void submitForm(){
        click(By.xpath("//*[@type='submit']"));
    }

    public void clickOkButton() {
        click(By.xpath("//button[.='Ok']"));
    }

    public boolean isLogged() {
        return wd.findElements(By.xpath("//a[.=' Log in ']")).size()>0;
    }

    public void logout() {
        click(By.xpath("//a[.=' Logout ']"));
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[.=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click();");
        //click(By.xpath("//label[@class='checkbox-label terms-label']"));
        //js.executeScript("document.querySelector('#terms-of-use').checked=true;");
    }

    public void login(User user) {
        click(By.xpath("//a[.=' Log in ']"));
        type(By.id("email"),user.getEmail());
        type(By.id("password"),user.getPassword());
        click(By.xpath("//*[@type='submit']"));
        click(By.xpath("//button[.='Ok']"));
        pause(2000);
    }
}
