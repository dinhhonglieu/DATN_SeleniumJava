package src.Buoi8_1;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartManagement {
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
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL_login);
    }
    @Test
    public void viewCartHasNoProduct() throws InterruptedException {
        login("dinhhlieu22@gmail.com","Lieut2003@@@");
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement iconCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Header_header__actions-group__uAY5S Header_header__actions-cart__DNsxJ']")));
        iconCart.click();
        WebElement emptyCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Giỏ hàng trống')]")));
        Assert.assertTrue(emptyCartMessage.isDisplayed());
        Assert.assertEquals(emptyCartMessage.getText().trim(), "Giỏ hàng trống");
        driver.quit();
    }
    @Test
    public void addNewProductToCart() throws InterruptedException {
        login("dinhhlieu22@gmail.com","Lieut2003@@@");
        String keySearch = "Bánh Mì Siêu Nhân";
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='banner-search']"));
        searchInput.sendKeys(keySearch);
        Thread.sleep(4000);
        //Kich enter
        WebElement timKiem = driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]"));
        timKiem.click();

        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='ProductCard_product__add-cart-btn__Ze7Gs']"));
        buttonAdd.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement buttonAddToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Button_wrapper__GqKsN QuantityDrawer_quantity-drawer__add-btn__a7VEh Button_primary__9MLUH']")));
        buttonAddToCart.click();
        Thread.sleep(3000);
        WebElement messagaeElement = buttonAddToCart.findElement(By.xpath("//div[contains(text(),'Thêm sản phẩm vào giỏ hàng thành công')]"));
        String message = messagaeElement.getText();
        Assert.assertEquals(message, "Thêm sản phẩm vào giỏ hàng thành công");
        Thread.sleep(7000);
        WebElement buttonCart = driver.findElement(By.xpath("//button[@class='Button_wrapper__GqKsN Button_outline__monpX Button_action__n+ONF Button_haveProducts__TqT1C']"));
        buttonCart.click();
        Thread.sleep(3000);
        //sản phẩm trong giỏ hàng
        WebElement cartItems = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart__products-list")));
        List<WebElement> productNames = cartItems.findElements(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]"));

        boolean productFound = false;
        for(WebElement productName : productNames){
            if (productName.getText().equalsIgnoreCase(keySearch)){
                productFound = true;
                break;
            }

        }
        Assert.assertFalse(productFound, "Sản phẩm "+ keySearch + " không có trong giỏ hàng!");
        driver.quit();
    }

    @Test
    public void removeAllProduct() throws InterruptedException {
        login("dinhhlieu22@gmail.com","Lieut2003@@@");
        Thread.sleep(7000);
        // Tìm và nhấp vào biểu tượng giỏ hàng
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='Button_wrapper__GqKsN Button_outline__monpX Button_action__n+ONF Button_haveProducts__TqT1C']")));
        cartIcon.click();

        List<WebElement> productsIncart = driver.findElements(By.className("CartItem_product__Tp4ck"));
        if (productsIncart.isEmpty()){
            System.out.println("Giỏ hàng trống!");
        }else {
            for (WebElement product: productsIncart){
                WebElement removeButton = product.findElement(By.xpath("//button[normalize-space()='Xóa']"));
                removeButton.click();
                wait.until(ExpectedConditions.stalenessOf(product));
            }
            System.out.println("Xóa thành công!");
        }
        driver.quit();
    }
}
