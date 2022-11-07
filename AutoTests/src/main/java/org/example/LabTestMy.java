package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LabTestMy {

    private WebDriver chromeDriver;

    private static final String baseUrl = "https://www.foxtrot.com.ua/";
    @BeforeClass(alwaysRun = true)
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        this.chromeDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public void preconditions(){
        chromeDriver.get(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        chromeDriver.quit();
    }
    @Test
    public void testClickOnForCompare(){
        WebElement forCompareButton = chromeDriver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div/div/div[3]/div[1]/i[2]"));
        Assert.assertNotNull(forCompareButton);
        forCompareButton.click();
        Assert.assertNotEquals(chromeDriver.getCurrentUrl(),baseUrl);
    }
    @Test
    public void testSearch(){
        chromeDriver.get(baseUrl);
        WebElement searchField = chromeDriver.findElement(By.tagName("input"));
        Assert.assertNotNull(searchField);
        String inputValue = "Smartphone Samsung";
        searchField.sendKeys(inputValue);
        Assert.assertNotEquals(chromeDriver.getCurrentUrl(),baseUrl);
        Assert.assertNotNull(searchField);
    }
    @Test
    public void testSlider() {
        WebElement nextButton = chromeDriver.findElement(By.className("icon-carousel-next"));
        WebElement nextButtonByCss = chromeDriver.findElement(By.cssSelector("span.icon-carousel-next"));
        Assert.assertEquals(nextButton, nextButtonByCss);
        nextButton.click();
    }
    @Test
    public void testNextButt(){
        WebElement nextButton = chromeDriver.findElement(By.className("icon-carousel-next"));
        WebElement prevButton = chromeDriver.findElement(By.className("icon-carousel-prev"));

        for (int i=0; i<10; i++){
            if (nextButton.getAttribute("class").contains("disabled")){
                nextButton.click();
                Assert.assertTrue(prevButton.getAttribute("class").contains("disabled"));
                Assert.assertFalse(nextButton.getAttribute("class").contains("disabled"));
            }
            else {
                prevButton.click();
            }
        }
    }

}
