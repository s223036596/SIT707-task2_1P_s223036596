package sit707_week2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This class demonstrates Selenium locator APIs to identify HTML elements.
 *
 * Details in Selenium documentation https://www.selenium.dev/documentation/webdriver/elements/locators/
 *
 * @author Ahsan Habib
 */
public class SeleniumOperations {

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void officeworks_registration_page(String url) {
        // Step 1: Locate chrome driver folder in the local drive.
        System.setProperty("webdriver.chrome.driver", "C:\\\\chromedriver-win64\\\\chromedriver.exe");

        // Step 2: Use above chrome driver to open up a chromium browser.
        System.out.println("Fire up chrome browser.");
        WebDriver driver = new ChromeDriver();
        System.out.println("Driver info: " + driver);
        sleep(2);

        // Load a webpage in chromium browser.
        driver.get(url);

        // Find and populate input fields
        WebElement firstNameElement = driver.findElement(By.id("firstname"));
        firstNameElement.sendKeys("John");

        WebElement lastNameElement = driver.findElement(By.id("lastname"));
        lastNameElement.sendKeys("Doe");

        WebElement emailElement = driver.findElement(By.id("email"));
        emailElement.sendKeys("john.doe@example.com");

        // Enter an invalid password to trigger an error
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys("short");

        WebElement confirmPasswordElement = driver.findElement(By.id("confirmPassword"));
        confirmPasswordElement.sendKeys("short");

        // Identify and click the "Create account" button
        WebElement createAccountButton = driver.findElement(By.xpath("//button[contains(text(), 'Create account')]"));
        createAccountButton.click();

        // Take screenshot using Selenium API
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("officeworks_registration_error.png"));
            System.out.println("Screenshot captured and saved as officeworks_registration_error.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sleep a while
        sleep(2);

        // Close the browser
        driver.quit();
    }
}