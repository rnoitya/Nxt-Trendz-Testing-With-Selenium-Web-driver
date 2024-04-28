import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class NxtTrendzTest {
    public static void main(String args[]) {

        try {
            System.setProperty("webdriver.chrome.driver", "E:\\Rahul\\Selenium testing\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
            // Open the Chrome Browser
            WebDriver driver = new ChromeDriver();

            // Open the NxtTrendz login page
            driver.get("https://rahulnxttrendz.ccbp.tech/login");

            // Find and fill in the form fields
            WebElement usernameEl = driver.findElement(By.xpath("//input[@id='username']"));
            usernameEl.sendKeys("rahul");
            WebElement passwordEl = driver.findElement(By.xpath("//input[@id='password']"));
            passwordEl.sendKeys("rahul@2021");

            WebElement buttonEl = driver.findElement(By.xpath("//button[@type='submit']"));
            buttonEl.submit();

            // Define the expected URL of the home page
            String homePageUrl = "https://rahulnxttrendz.ccbp.tech/";

            // Wait for the expected URL to be loaded
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(homePageUrl));

            // Get the current URL after login
            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.equals(homePageUrl)) {
                System.out.println("Navigation to home page was successful!");
            }

            // Find and click on the "Shop Now" button
            WebElement shopNowButtonEl =
                    driver.findElement(By.xpath("//button[@class='shop-now-button']"));
            shopNowButtonEl.click();

            // Define the expected URL of the products page
            String productsPageUrl = "https://rahulnxttrendz.ccbp.tech/products";

            // Get the current URL after navigation to products page
            currentUrl = driver.getCurrentUrl();

            if (currentUrl.equals(productsPageUrl)) {
                System.out.println("Navigation to products page was successful!");
            }

            //Wait until elements are displayed
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='product-item']")));

            // Verify the display of products on the products page
            List<WebElement> products = driver.findElements(By.xpath("//li[@class='product-item']"));

            if (products.size() > 0) {
                System.out.println("Products are displayed successfully on the products page: " + products.size());

                // Select Product with text Minifigures
                WebElement productNameEle = driver.findElement(By.xpath("//h1[text()='Minifigures']"));
                String expectedProductTitle = productNameEle.getText();
                System.out.println(expectedProductTitle);

                productNameEle.click();

                String productDetailsUrl = "https://rahulnxttrendz.ccbp.tech/products/1002";
                wait.until(ExpectedConditions.urlToBe(productDetailsUrl));

                String currentUrl1 = driver.getCurrentUrl();
                if (currentUrl1.equals(productDetailsUrl)) {
                    System.out.println("Navigation to product details page is successful!");
                }

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-details-success-view']")));
                WebElement productTitleElement = driver.findElement(By.xpath("//h1[contains(text(),'Minifig')]"));
                String actualProductTitle = productTitleElement.getText();
                if (expectedProductTitle.equals(actualProductTitle)) {
                    System.out.println("Product Name is - " + actualProductTitle);
                } else {
                    System.out.println("Actual product name is not same as expected product name" + actualProductTitle);
                }


            } else {
                System.out.println("No products found on the products page.");
            }

            // Close the browser
            driver.quit();
        } catch (Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }
}