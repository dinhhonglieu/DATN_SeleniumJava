package src.Buoi8_1.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Assert;
import org.testng.annotations.Test;
import static java.lang.Thread.sleep;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DangKy {
    String URL_login = "https://demo.nopcommerce.com/login?returnUrl=%2F";
    String URL_dashBoard = "https://demo.nopcommerce.com/";
    String loginWrongMess = "Tài khoản hoặc mật khẩu không chính xác";
    String loginBlankMess = "This field can not be empty";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    WebDriver driver;

    public void Login(){
        driver.get("https://saleserpdemo.bdtask-demo.com/v10_demo/login");
/*        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);*/
        driver.findElement(By.xpath("//td[normalize-space()='admin@gmail.com']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();


    }
    @Test(priority = 1, enabled = true)
    public void loginByUser(){
        Login();
//        Login("ngluong3768@gmail.com","123456");
/*        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(URL_dashBoard));
        Assert.assertEquals(driver.getCurrentUrl(), URL_dashBoard);*/
    }

}
