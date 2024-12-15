package src.Buoi8_1;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Purchase {
    String URL_login = "https://hauifood.com/auth/login";
    String URL_dashBoard = "https://hauifood.com/";

    WebDriver driver;

    private WebElement loginButton; // Biến instance để lưu nút Đăng nhập

    public void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        loginButton = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/form[1]/div[4]/button[1]"));
        loginButton.click(); // Gọi click nếu cần thao tác đăng nhập
    }
    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL_login);
    }

    //tinh tien trong gio hang
    public double checkOutPrice() throws InterruptedException {
        Thread.sleep(8000);
        List<WebElement> productsIncart = driver.findElements(By.className("CartItem_product__Tp4ck"));
        double calculatedTotal = 0.0;
        for (WebElement row: productsIncart){
            String quantityText = driver.findElement(By.xpath("//body/div[@id='root']/div[@class='App']/div[@class='wrapper']/div[@class='Header_wrapper__dljRz']/div[@class='container gx-5']/div[@class='Cart_cart__weGic Cart_cart--show__Ach68']/div[@class='Cart_cart__container__dsWzz']/div[@class='Cart_cart__scroll__az6uQ']/div[@class='Cart_cart__content__aiN2O']/div[2]/div[2]/div[1]/div[1]/button[1]")).getText();
            int quantity = Integer.parseInt(quantityText);
            String priceText = driver.findElement(By.xpath("//div[@class='Cart_cart__container__dsWzz']//div[2]//div[2]//div[1]//div[3]//div[1]//span[1]")).getText();
            double price = Double.parseDouble(priceText);
            calculatedTotal += quantity * price;

        }
        return calculatedTotal;
    }

    //dia chi theo toa
    public void inputAddressBuild(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buildingDroplist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='CheckOut_address__building__53Lq+']")));
        buildingDroplist.click();
        //doi danh sach xuat hien
        WebElement buildingList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CheckOut_address__building-list__Vy9-o CheckOut_address__building--show__L9cpU']")));
        //Lay danh sach cac dia chi
        List<WebElement> builds = buildingList.findElements(By.className("CheckOut_address__building-item__rOkhu"));
        Assert.assertFalse(builds.isEmpty(),"Danh sách tòa nhà trống!");
        //chọn tòa nhà đầu tiên
        WebElement selectedBuild = builds.get(0);
        selectedBuild.click();
        //Kiem tra xem dia chi dau tien da dwoc lay chua
        WebElement selectedBuildText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Tòa A1']")));
        Assert.assertTrue(selectedBuildText.isDisplayed(),"Không được để trống!");

    }
    //dia chi theo tang
    public void inputAddressFloor(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buildingDroplist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='CheckOut_address__floor__D1DUG']")));
        buildingDroplist.click();
        //doi danh sach xuat hien
        WebElement buildingList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CheckOut_address__floor-list__HMyLl CheckOut_address__floor--show__I+Hny']")));
        //Lay danh sach cac dia chi
        List<WebElement> builds = buildingList.findElements(By.className("CheckOut_address__floor-item__aFatc"));
        Assert.assertFalse(builds.isEmpty(),"Danh sách tòa nhà trống!");
        //chọn tòa nhà đầu tiên
        WebElement selectedBuild = builds.get(0);
        selectedBuild.click();
        //Kiem tra xem dia chi dau tien da dwoc lay chua
        WebElement selectedBuildText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Tầng 1')]")));
        Assert.assertTrue(selectedBuildText.isDisplayed(),"Không được để trống!");

    }
    //dia chi theo phong
    public void inputAddressRoom(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buildingDroplist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='CheckOut_address__classroom__GmObF']")));
        buildingDroplist.click();
        //doi danh sach xuat hien
        WebElement buildingList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='CheckOut_address__classroom-list__ljGRf CheckOut_address__classroom--show__k2LA0']")));
        //Lay danh sach cac dia chi
        List<WebElement> builds = buildingList.findElements(By.className("CheckOut_address__classroom-item__K8Sos"));
        Assert.assertFalse(builds.isEmpty(),"Danh sách tòa nhà trống!");
        //chọn tòa nhà đầu tiên
        WebElement selectedBuild = builds.get(0);
        selectedBuild.click();
        //Kiem tra xem dia chi dau tien da dwoc lay chua
        WebElement selectedBuildText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Phòng 101']")));
        Assert.assertTrue(selectedBuildText.isDisplayed(),"Không được để trống!");

    }
    @Test
    public void paymentCard() throws InterruptedException {
        login("dinhhlieu22@gmail.com", "Lieut2003@@@");
        Thread.sleep(6000);
        //view cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iconCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Header_header__actions-group__uAY5S Header_header__actions-cart__DNsxJ']")));
        iconCart.click();
        //button thanh toan
        WebElement buttonPay = driver.findElement(By.xpath("//a[@href='/checkout']//button[@class='Button_wrapper__GqKsN Button_primary__9MLUH Button_checkout__hD-ta']"));
        buttonPay.click();
        //check order price (de sau)
        inputAddressBuild();
        inputAddressFloor();
        Thread.sleep(3000);

        inputAddressRoom();

        //payment by cash
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content']//label[1]")));

        List<WebElement> paymentMethods = driver.findElements(By.xpath("//div[@class='content']//label[1]"));
        WebElement paymentOption = paymentMethods.get(0);
        if (!paymentOption.isSelected()){
            paymentOption.click();
        }

        WebElement buttonPaymen = driver.findElement(By.xpath("//button[@class='Button_wrapper__GqKsN Button_primary__9MLUH Button_order__t1Yc1']"));
        buttonPaymen.click();
        driver.quit();
    }
}
