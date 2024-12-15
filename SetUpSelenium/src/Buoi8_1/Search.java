package src.Buoi8_1;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Search {
    String URL_login = "https://hauifood.com/auth/login";
    String URL_dashBoard = "https://hauifood.com/";
    WebDriver driver;
    public void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/form[1]/div[4]/button[1]"));
        loginButton.click(); // Gọi click nếu cần thao tác đăng nhập
    }
    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL_login);
        login("dinhhlieu22@gmail.com","Lieut2003@@@");
        Thread.sleep(6000);
    }

    @Test
    public void searchWithCorrectName() throws InterruptedException {
        String keySearch = "Bánh mì siêu nhân";
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='banner-search']"));
        searchInput.sendKeys(keySearch);
        Thread.sleep(4000);
        //Kich enter
        WebElement timKiem = driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]"));
        timKiem.click();
        Thread.sleep(4000);
        //check list product
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement searchResultsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Danh sách sản phẩm')]")));
        List<WebElement> productList = driver.findElements(By.xpath("//body/div[@id='root']/div[@class='App']/div[@class='wrapper']/div[@class='content']/div[@class='Home_home__9Ke73']/div[@class='container gx-5']/div[@class='home__search-result-container Home_home__search-result-show__+VEO+']/div[@class='list']/div[1]"));
        Thread.sleep(2000);
        Assert.assertTrue(searchResultsElement.isDisplayed());
        Assert.assertEquals(searchResultsElement.getText(), "Danh sách sản phẩm");
        Assert.assertTrue(!productList.isEmpty());
        driver.quit();
    }
    @Test
    public void searchApproximatelyName() throws InterruptedException {
        String keySearch = "mì";
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='banner-search']"));
        searchInput.sendKeys(keySearch);
        Thread.sleep(4000);

        // Kích vào nút "Tìm kiếm"
        WebElement timKiem = driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]"));
        timKiem.click();
        Thread.sleep(4000);

        // Kiểm tra tiêu đề "Danh sách sản phẩm"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchResultsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Danh sách sản phẩm')]")));

        Assert.assertTrue(searchResultsElement.isDisplayed());
        Assert.assertEquals(searchResultsElement.getText(), "Danh sách sản phẩm");

        // Cuộn xuống để hiển thị toàn bộ danh sách sản phẩm
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Tìm danh sách sản phẩm
        List<WebElement> productList = driver.findElements(By.xpath("//body/div[@id='root']/div[@class='App']/div[@class='wrapper']/div[@class='content']/div[@class='Home_home__9Ke73']/div[@class='container gx-5']/div[@class='home__search-result-container Home_home__search-result-show__+VEO+']/div[@class='list']/div"));

        // Cuộn qua từng phần tử
        for (int i = 0; i < productList.size(); i++) {
            js.executeScript("arguments[0].scrollIntoView(true);", productList.get(i));
            Thread.sleep(1000); // Tạm dừng để kiểm tra
        }

        // Kiểm tra danh sách không rỗng
        Assert.assertTrue(!productList.isEmpty());

        // Đóng trình duyệt
        driver.quit();
    }


    @Test
    public void searchWithIncorrectName() throws InterruptedException {
        String keySearch = "Bánh mỳ";
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='banner-search']"));
        searchInput.sendKeys(keySearch);
        Thread.sleep(4000);
        //Kich enter
        WebElement timKiem = driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]"));
        timKiem.click();
        Thread.sleep(4000);
        WebElement noProcductElement = driver.findElement(By.xpath("//div[@class='NoResult_no-result__container__2sxtq']"));
        Assert.assertTrue(noProcductElement.isDisplayed());
        driver.quit();
    }

    @Test
    public void searchWithNoName() throws InterruptedException {
        String keySearch = "";
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='banner-search']"));
        searchInput.sendKeys(keySearch);
        Thread.sleep(4000);
        //Kich enter
        WebElement timKiem = driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]"));
        timKiem.click();
        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.or(ExpectedConditions.urlToBe(URL_dashBoard)));
        Assert.assertEquals(driver.getCurrentUrl(), URL_dashBoard);
        driver.quit();
    }


}
