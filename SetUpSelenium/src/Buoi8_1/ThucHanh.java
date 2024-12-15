package src.Buoi8_1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ThucHanh {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;
        System.setProperty("webdriver.chorme.driver", "D:/Auto_Selenium/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.growcrm.io/login");
        Thread.sleep(2000);

    }
}
