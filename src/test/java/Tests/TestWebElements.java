package Tests;

import java.awt.*;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestWebElements {
    private static WebDriver driver;

    public TestWebElements() {
        System.setProperty("webdriver.chrome.driver", "D:\\QA\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        //options
        options.addArguments("start-maximized");
        options.setHeadless(false);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
    }

    @Test //1
    public void Should_Open_Registration_Page() throws InterruptedException {

        //GIVEN
        driver.get("http://online-sh.herokuapp.com/login");
        Thread.sleep(600L);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));

        //WHEN
        By emailLocator = RelativeLocator.with(By.id("exampleInputEmail1")).above(By.id("exampleInputPassword1"));
        WebElement input1 = driver.findElement(emailLocator);
        input1.sendKeys("abc@abc.ua");
        Thread.sleep(600L);
        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1")).below(By.id("exampleInputEmail1"));
        WebElement input2 = driver.findElement(passwordLocator);
        input2.sendKeys("12345");
        Thread.sleep(600L);
        driver.findElement(By.className("btn-primary")).click();

        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("name")));
    }

    @Test //2
    public void Should_Log_In() throws InterruptedException {

        //GIVEN
        driver.get("http://online-sh.herokuapp.com/login");

        //WHEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        driver.findElement(By.className("btn-primary")).click();

        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div:nth-child(2) > h1")));

    }

    @Test //3
    public void Should_Find_Color_Of_CSS_Element() throws InterruptedException {

        //GIVEN
        driver.get("https://www.selenium.dev/documentation/");
        Thread.sleep(600L);

        //WHEN
        driver.findElement(By.xpath("//*[@id=\"m-documentationwebdriver\"]")).click();
        Thread.sleep(600L);
        driver.findElement(By.xpath("//*[@id=\"m-documentationwebdriverelements\"]/span")).click();
        Thread.sleep(600L);
        driver.findElement(By.xpath("//*[@id=\"m-documentationwebdriverelementsinformation\"]/span")).click();
        Thread.sleep(600L);

        //THEN
        String colorName = driver.findElement(By.cssSelector("#get-css-value")).getCssValue("color");
        System.out.println("color: "+ colorName);
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
}
}


