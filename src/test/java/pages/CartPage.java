package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private final By itemNames = By.cssSelector(".inventory_item_name");
    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public java.util.List<String> getItemNames() {
        var elems = driver.findElements(itemNames);
        return elems.stream().map(e -> e.getText()).toList();
    }

    public void checkout() {
        driver.findElement(checkoutBtn).click();
    }
}
