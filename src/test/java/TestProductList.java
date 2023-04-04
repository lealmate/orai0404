import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.example.IndexPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class TestProductList {

    WebDriver driver;

    @BeforeEach
    public void init(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testListItem(){
        IndexPage indexPage = new IndexPage(driver);
        indexPage.navigate();
        indexPage.searchItem("dress");
        indexPage.clickSearchButton();
        Allure.addAttachment("Any text", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String[] expected = {"Sleeveless Dress", "Stylish Dress", "Sleeves Top and Short - Blue & Pink", "Sleeveless Unicorn Patch Gown - Pink",
        "Cotton Mull Embroidered Dress", "Blue Cotton Indie Mickey Dress", "Long Maxi Tulle Fancy Dress Up Outfits -Pink", "Sleeveless Unicorn Print Fit & Flare Net Dress - Multi",
        "Rose Pink Embroidered Maxi Dress"};

        Assertions.assertArrayEquals(expected, indexPage.listItem());
    }
}
