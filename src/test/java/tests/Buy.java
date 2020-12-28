package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CommonPage;
import pages.ProductPage;
import pages.SearchPage;
import utils.ComponentUtils;
import utils.WaitUtils;


import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Buy extends BaseTest{
    List<WebElement> errors;
    String errorMessage;

    @Test(dataProvider = "addToCart")
    public void AddToCart(String object) {

        WaitUtils.waitUntilElementClickable(driver, 3, driver.findElement(By.linkText("MP3 Players")));
        driver.findElement(By.linkText("MP3 Players")).click();

        driver.findElement(By.linkText("Show All MP3 Players")).click();
        driver.findElement(By.id("list-view")).click();



        errors = driver.findElements(By.cssSelector(".alert-dismissible"));
        for (WebElement error : errors) {
            String visibility = error.getCssValue("visibility");
            if (visibility.equals("visible")) {
                errorMessage = error.getText();
                break;
            }
        }
        assertEquals(errorMessage, "Success: You have added "+ object +" to your shopping cart!");
    }

    @DataProvider(name = "addToCart")
    public static Object[][] addToCart() {
        return new Object[][]{
                {"iPod Nano"},
                {"iPod Touch"},
                {"iPod Shuffle"}
        };
    }


}
