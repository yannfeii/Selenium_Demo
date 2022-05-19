import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    public void login (){
        System.setProperty("webdriver.chrome.driver","D:\\SelfStudy\\chromedriver.exe");
        ChromeDriver chromerDriver= new ChromeDriver();
        chromerDriver.get("");
    }

}