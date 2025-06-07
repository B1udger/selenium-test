package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
    private final WebDriver driver;
    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By addBolt     = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final By cartBadge   = By.cssSelector(".shopping_cart_badge");
    private final By sortSelect  = By.className("product_sort_container");
    private final By burgerMenu  = By.id("react-burger-menu-btn");
    private final By logoutLink  = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        driver.findElement(addBackpack).click();
    }

    public void addBoltToCart() {
        driver.findElement(addBolt).click();
    }

    public int getCartCount() {
        String text = driver.findElement(cartBadge).getText();
        return Integer.parseInt(text);
    }

    public void sortBy(String optionValue) {
        Select sel = new Select(driver.findElement(sortSelect));
        sel.selectByValue(optionValue);
    }

    public void logout() {
        driver.findElement(burgerMenu).click();
        driver.findElement(logoutLink).click();
    }
}
