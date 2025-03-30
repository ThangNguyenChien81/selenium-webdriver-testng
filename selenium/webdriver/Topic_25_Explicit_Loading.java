package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_25_Explicit_Loading {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // Điều kiện wait
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hellow World!");
    }


    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hellow World!");
    }

    @Test
    public void TC_03_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSeconds(6);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hellow World!");    }

    @Test
    public void TC_04_() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // Visible (dành cho 1 element sau khi xuất hiện)
        WebElement helloWorldText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(helloWorldText.getText(), "Hello World!");

        // Visible (dành cho 1 element sắp biến mất)
        boolean loadingIconStatus = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertTrue(loadingIconStatus);

        // Text
        boolean helloTextStatus =  explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
        Assert.assertTrue(helloTextStatus);


        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
