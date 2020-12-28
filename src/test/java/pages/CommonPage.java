package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.ComponentUtils;

import java.util.List;

public class CommonPage extends BasePage {

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    //-----------Locators----------------------
    By searchInput = By.name("search_query");
    By searchButton = By.cssSelector(".search-button");
    By miniCart = By.cssSelector(".mini-cart-desktop .dropdown-toggle");
    By miniCartDropDown = By.cssSelector(".dropdown-menu");
    By nameOfProductInMiniCart = By.cssSelector(".dropdown-cart-products p[class='product-title']");
    By goToCartButtonInMiniCart = By.cssSelector(".dropdown-cart-action");
    By megaMenu = By.cssSelector(".megamenu-submenu");
    By allCategoriesButton = By.cssSelector(".megamenu-container-link");
    By flyout = By.cssSelector(".autocomplete-suggestions");
    By flyoutProductTitle = By.cssSelector(".autocomplete-suggestions-product-title");

    //-----------WebElements-------------------
    public WebElement miniCart() {
        return driver.findElement(miniCart);
    }

    public WebElement miniCartDropDown() {
        return driver.findElement(miniCartDropDown);
    }

    public WebElement getMainCategory(String mainCategory) {
        return driver.findElement(By.partialLinkText(mainCategory));
    }

    public WebElement getAllCategoriesButton() {
        return driver.findElement(allCategoriesButton);
    }

    public WebElement getFlyout() {
        return driver.findElement(flyout);
    }

    public List<WebElement> getAllFlyoutProducts() {
        return driver.findElements(flyoutProductTitle);
    }

    //-----------Methods-----------------------

    /**
     * Enters text to search input field
     *
     * @param searchTerm String
     */
    public void enterTextToSearch(String searchTerm) {
        enterText(searchInput, searchTerm);
    }

    /**
     * Click on the serch button to initiate the search
     */
    public void startSearch() {
        click(searchButton);
    }

    public boolean allProductInFlyoutAreCorrect(String expectedName) {
        boolean allProductsCorrect = true;
        for (WebElement product : getAllFlyoutProducts()) {
            String productName = product.getText().toLowerCase();
            if (!productName.contains(expectedName)) {
                allProductsCorrect = false;
                System.out.println("Wrong product: " + productName);
            }
        }
        return allProductsCorrect;
    }

    public void openProductInFlyout(int numberOfProductToOpen) {
        getAllFlyoutProducts().get(numberOfProductToOpen - 1).click();
    }

    /**
     * Gets the name of the first product in Mini Cart
     *
     * @return String
     */
    public String getNameOfProductInMiniCart() {
        String nameWithQnt = getText(nameOfProductInMiniCart);
        return nameWithQnt.replaceAll("\\s[(][\\d][)]", "");
    }

    public void clickOpenBasketOnMiniCart() {
        click(goToCartButtonInMiniCart);
    }

    /**
     * Expands first level menu
     *
     * @param mainCategory String
     */
    public void expandMainMenu(String mainCategory) {
        if (!mainMenuIsVisible()) ComponentUtils.hoverOverElement(driver, getAllCategoriesButton());
        ComponentUtils.hoverOverElement(driver, getMainCategory(mainCategory));
    }

    /**
     * Check if the main menu is visible (first level)
     *
     * @return boolean
     */
    public boolean mainMenuIsVisible() {
        return driver.findElement(megaMenu).isDisplayed();
    }

    public void openSubCategory(String subCategory) {
        click(By.partialLinkText(subCategory));
    }

    /**
     * Returns Mega Menu locator
     *
     * @return By locator
     */
    public By getMegaMenu() {
        return megaMenu;
    }
}
