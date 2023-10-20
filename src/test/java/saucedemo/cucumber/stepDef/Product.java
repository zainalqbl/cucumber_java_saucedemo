package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Product {
    WebDriver driver;

    String baseUrl = "https://www.saucedemo.com";

    @Given("I open saucedemo website")
    public void i_open_the_saucedemo_website() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String loginPage = driver.findElement(By.xpath("//div[contains(text(),'Swag Labs')]")).getText();
        Assert.assertEquals("Swag Labs", loginPage);
    }

    @When("I login success")
    public void i_login_success() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String iventoryPage = driver.findElement(By.xpath("//*[@class='title']")).getText();
        Assert.assertEquals("Products", iventoryPage);
    }

    @When("I add {string} to the cart")
    public void i_add_product_to_the_cart(String product) {
        String produk = product.toLowerCase().replace(" ", "-");
        driver.findElement(By.xpath("//*[@id='add-to-cart-" + produk + "']")).click();
        String count = driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a/span")).getText();
        Assert.assertEquals("1", count);
    }

    @When("I click the shopping cart button")
    public void i_click_the_shopping_card_button() {
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
    }

    @Then("I verify {string} in cart page")
    public void i_verify_product_in_cart_page(String product) {
        String title = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals("Your Cart", title);

        List<WebElement> inventoryItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
        int itemCount = inventoryItems.size();
        Assert.assertEquals(1, itemCount);

        String produk = driver.findElement(By.xpath("//*[contains(text(), '" + product + "')]")).getText();
        Assert.assertEquals(product, produk);

        driver.quit();
    }

    @When("I click the checkout button")
    public void i_click_the_checkout_button(){
        driver.findElement(By.xpath("//*[@id='checkout']")).click();
    }

    @When("I enter {string} {string} {string} address")
    public void i_enter_the_shipping_address(String first, String last, String zip) {
        driver.findElement(By.id("first-name")).sendKeys(first);
        driver.findElement(By.id("last-name")).sendKeys(last);
        driver.findElement(By.id("postal-code")).sendKeys(zip);
    }

    @When("I click the continue button")
    public void i_click_the_continue_button(){
        driver.findElement(By.xpath("//*[@id='continue']")).click();
    }

    @Then("I verify {string} checkout result")
    public void i_verify_checkout_result(String status) {
        if (status.equals("success")) {
            String checkoutPage = driver.findElement(By.xpath("//*[@class='title']")).getText();
            Assert.assertEquals("Checkout: Overview", checkoutPage);
        } else {
            WebElement errorElement = driver.findElement(By.cssSelector(".error-message-container.error"));
            Assert.assertEquals(errorElement.isDisplayed(), true);
        }

        driver.quit();
    }

    @When("I click the finish button")
    public void i_click_the_finish_button(){
        driver.findElement(By.xpath("//*[@id='finish']")).click();
    }

    @Then("I verify purchase result")
    public void i_verify_purchase_result() {
        String checkoutPage = driver.findElement(By.xpath("//*[@class='title']")).getText();
        Assert.assertEquals("Checkout: Complete!", checkoutPage);

        String message = driver.findElement(By.cssSelector("h2.complete-header")).getText();
        Assert.assertEquals("Thank you for your order!", message);

        driver.quit();
    }

    @Given("I open saucedemo inventory website")
    public void i_open_saucedemo_inventory_website() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    @Then("I verify result")
    public void i_verify_result() {
        String loginPage = driver.findElement(By.xpath("//div[contains(text(),'Swag Labs')]")).getText();
        Assert.assertEquals("Swag Labs", loginPage);

        driver.quit();
    }
}
