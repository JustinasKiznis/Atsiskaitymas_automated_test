package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ComponentUtils;

import java.util.List;

import static org.testng.Assert.fail;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    //-----------Locators----------------------
    By productList = By.xpath("//div[contains(@class, 'product-list-product')] | //a[contains(@class, 'subcategory-grid-card')]");
    By inStockImage = By.cssSelector(".product-list-product-stock-status img");
    By addToCartButton = By.cssSelector(".btn-add-cart");
    By productTitle = By.cssSelector(".product-title");

    //-----------WebElements-------------------
    public List<WebElement> getAllProducts() {
        return driver.findElements(productList);
    }

    //-----------Methods-----------------------

    public By getProductList() {
        return productList;
    }

    /**
     * Adds the first product to the cart which is in Stock
     */
    public void addFirstProductInStock() {
        boolean allProductsOutOfStock = true;
        WebElement productInStock = getFirstProductInStock();
        if (productInStock != null) {
            allProductsOutOfStock = false;
            productInStock.findElement(addToCartButton).click();
        }
        if (allProductsOutOfStock) fail("All product were out of scope");
    }

    /**
     * Returns the name of the first product that is in stock
     *
     * @return name of the product
     */
    public String getNameOfFirstProductInStock() {
        return getFirstProductInStock().findElement(productTitle).getText();
    }

    /**
     * Returns the WebElement of the first product in Stock
     *
     * @return WebElement
     */
    public WebElement getFirstProductInStock() {
        for (WebElement product : getAllProducts()) {
            ComponentUtils.scrollToElement(driver, product);
            if (productIsInStock(product)) {
                return product;
            } else {
                String productName = product.findElement(productTitle).getText();
                System.out.println("Produkto '" + productName + "', nebuvo sandėlyje");
            }
        }
        return null;
    }

    /**
     * Check if element is in stock
     *
     * @param product WebElement
     * @return boolean
     */
    public boolean productIsInStock(WebElement product) {
        return product.findElement(inStockImage).getAttribute("alt").equals("in stock");
    }
}
