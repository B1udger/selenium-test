package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import pages.*;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SwagLabsTests {

    private WebDriver driver;
    private LoginPage login;
    private InventoryPage inv;
    private CartPage cart;
    private CheckoutPage co;

    @BeforeAll
    public void setUp() throws InterruptedException {
        EdgeOptions opts = new EdgeOptions().addArguments("--start-maximized");
        driver = new EdgeDriver(opts);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        login = new LoginPage(driver);
        inv   = new InventoryPage(driver);
        cart  = new CartPage(driver);
        co    = new CheckoutPage(driver);
        // малко време да видим браузъра
        Thread.sleep(3000);
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testLoginFailure() throws InterruptedException {
        login.open();
        login.login("wrong_user", "wrong_pass");
        Thread.sleep(3000);
        Assertions.assertTrue(
                login.getError().contains("Username and password do not match"),
                "Очаквахме съобщение за грешка при логин"
        );
    }

    @Test
    public void testLoginSuccessAndLogout() throws InterruptedException {
        login.open();
        login.login("standard_user", "secret_sauce");
        Thread.sleep(3000);
        inv.logout();
        Thread.sleep(3000);
        // проверяваме, че се връща login page: има полето user-name
        Assertions.assertTrue(
                driver.findElement(By.id("user-name")).isDisplayed(),
                "След logout очакваме полето за username"
        );
    }

    @Test
    public void testAddItemsAndCartCount() throws InterruptedException {
        login.open();
        login.login("standard_user", "secret_sauce");
        Thread.sleep(3000);
        inv.addBackpackToCart();
        Thread.sleep(1000);
        inv.addBoltToCart();
        Thread.sleep(3000);
        Assertions.assertEquals(2, inv.getCartCount(), "Трябва да има 2 артикула в количката");
    }

    @Test
    public void testSortLowToHigh() throws InterruptedException {
        login.open();
        login.login("standard_user", "secret_sauce");
        Thread.sleep(3000);
        inv.sortBy("lohi");
        Thread.sleep(3000);
        // тук просто проверяваме, че dropdown се е сменил
        Assertions.assertDoesNotThrow(
                () -> inv.sortBy("lohi"),
                "Сортиране Low→High трябва да работи без грешка"
        );
    }

    @Test
    public void testCheckoutFlow() throws InterruptedException {
        login.open();
        login.login("standard_user", "secret_sauce");
        Thread.sleep(3000);

        inv.addBackpackToCart();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        Thread.sleep(3000);

        List<String> items = cart.getItemNames();
        Assertions.assertTrue(
                items.contains("Sauce Labs Backpack"),
                "В количката трябва да има Sauce Labs Backpack"
        );
        cart.checkout();
        Thread.sleep(3000);

        co.enterInfo("Ivan","Ivanov","1000");
        Thread.sleep(1000);
        co.continueCheckout();
        Thread.sleep(1000);
        co.finishCheckout();
        Thread.sleep(3000);

        // съобщението с удивителен знак, такова е на сайта
        Assertions.assertEquals(
                "Thank you for your order!",
                co.getCompleteMessage(),
                "Очаквахме точното текстово съобщение за успешна поръчка"
        );
    }
}
