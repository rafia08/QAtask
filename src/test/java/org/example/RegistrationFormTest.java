package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegistrationFormTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("WebDriver.gecko.driver", "\"C:\\Users\\syeda\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe\\");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("./form.html");
    }

    @Test
    public void testValidRegistration() {
        driver.findElement(By.name("fullname")).sendKeys("Rafia Wajahat");
        driver.findElement(By.name("email")).sendKeys("rafia.wajahat1996@gmail.com");
        driver.findElement(By.name("password")).sendKeys("abc123");
        driver.findElement(By.name("confirmPassword")).sendKeys("abc123");
        driver.findElement(By.name("dob")).sendKeys("1995-01-01");

        Select genderSelect = new Select(driver.findElement(By.name("gender")));
        genderSelect.selectByVisibleText("Female");

        WebElement newsletterYes = driver.findElement(By.xpath("//input[@name='newsletter'][@value='Yes']"));
        if (!newsletterYes.isSelected()) {
            newsletterYes.click();
        }

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(),'Registration successful')]"));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed");
    }

    @Test
    public void testInvalidEmailFormat() {
        driver.findElement(By.name("fullname")).sendKeys("Rafia");
        driver.findElement(By.name("email")).sendKeys("rafia.wajahat@");
        driver.findElement(By.name("password")).sendKeys("abc123");
        driver.findElement(By.name("confirmPassword")).sendKeys("abc123");
        driver.findElement(By.name("dob")).sendKeys("000000");

        Select genderSelect = new Select(driver.findElement(By.name("gender")));
        genderSelect.selectByVisibleText("Female");

        WebElement newsletterYes = driver.findElement(By.xpath("//input[@name='newsletter'][@value='Yes']"));
        if (!newsletterYes.isSelected()) {
            newsletterYes.click();
        }

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'Please enter a valid email address')]"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
    }

    @Test
    public void testPasswordMismatch() {
        driver.findElement(By.name("fullname")).sendKeys("John Doe");
        driver.findElement(By.name("email")).sendKeys("johndoe@example.com");
        driver.findElement(By.name("password")).sendKeys("abc123");
        driver.findElement(By.name("confirmPassword")).sendKeys("123");
        driver.findElement(By.name("dob")).sendKeys("1995-01-01");

        Select genderSelect = new Select(driver.findElement(By.name("gender")));
        genderSelect.selectByVisibleText("Female");

        WebElement newsletterYes = driver.findElement(By.xpath("//input[@name='newsletter'][@value='Yes']"));
        if (!newsletterYes.isSelected()) {
            newsletterYes.click();
        }

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'Passwords do not match')]"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
