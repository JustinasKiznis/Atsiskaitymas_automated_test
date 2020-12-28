package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    By quantityInput = By.cssSelector(".horizontal-quantity ");
    By addToCartButton = By.cssSelector(".btn-add-cart");
    By productTitle = By.cssSelector("div[class*='productTitle'] > h2");

    /**
     * Change quantity which shoud be added to basket
     *
     * @param qnt integer
     */
    public void changeQuantity(int qnt) {
        clearText(quantityInput);
        enterText(quantityInput, String.valueOf(qnt));
    }

    public void clickAddToCart() {
        click(addToCartButton);
    }

    public void changeQntAndAddToCart(int qnt) {
        changeQuantity(qnt);
        clickAddToCart();
    }

    public String getProductName() {
        return getText(productTitle);
    }
}
