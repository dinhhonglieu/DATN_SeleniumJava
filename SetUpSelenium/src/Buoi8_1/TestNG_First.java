package src.Buoi8_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_First {
    WebDriver driver = null;
//    public String baseURL = "https://demo.growcrm.io/login";
    public String baseURL = "http://localhost:3000/login";
    @Test
    public void testExample() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/Auto_Selenium/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.navigate().to(baseURL);
        Thread.sleep(2000);
/*        String expectedTitle ="ABC Inc";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);*/
        Thread.sleep(2000);
        driver.quit();
    }
}
