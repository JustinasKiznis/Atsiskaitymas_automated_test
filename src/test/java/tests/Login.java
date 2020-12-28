package tests;

//import Tidy.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.WaitUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Login extends BaseTest {
    List<WebElement> errors;
    String errorMessage;

    @Test
    public void testLogin() {

        WaitUtils.waitUntilElementClickable(driver, 3, driver.findElement(By.xpath("//a[@title='My Account']")));
        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();


        errors = driver.findElements(By.xpath("//h2[text()='New Customer']"));
        for (WebElement error : errors) {
            String visibility = error.getCssValue("visibility");
            errorMessage = "Nematomas New Customer";
            if (visibility.equals("visible")) {
                errorMessage = "Matomas New Customer";
                break;
            }
        }
        assertEquals(errorMessage, "Matomas New Customer");

        driver.findElement(By.name("email")).sendKeys("text@gmail.com");
        driver.findElement(By.name("password")).sendKeys("TEST");
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();


        errors = driver.findElements(By.cssSelector(".alert-dismissible"));
        for (WebElement error : errors) {
            String visibility = error.getCssValue("visibility");
            if (visibility.equals("visible")) {
                errorMessage = error.getText();
                break;
            }
        }
        assertEquals(errorMessage, "Warning: No match for E-Mail Address and/or Password.");
    }
}