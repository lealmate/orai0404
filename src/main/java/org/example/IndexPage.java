package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IndexPage extends Pages{
    public IndexPage(WebDriver driver) {
        super(driver);
    }

    private final String URL = "https://automationexercise.com/products";

    public void navigate(){
        driver.navigate().to(URL);
    }

    private final By SEARCH_BOX = By.xpath("//input[@id = 'search_product']");

    @Step("first method")
    public void searchItem(String itemMenu){
        driver.findElement(SEARCH_BOX).sendKeys(itemMenu);
    }

    private final By SEARCH_BUTTON = By.xpath("//button[@id = 'submit_search']");

    @Step("second method")
    public void clickSearchButton(){
        driver.findElement(SEARCH_BUTTON).click();
    }

    private final By ITEM = By.xpath("//div[@class='features_items']/div[@class = 'col-sm-4']/div/div/div/p");

    @Step("return elements")
    public String[] listItem(){
        List<WebElement> items = driver.findElements(ITEM);

        String[] resultArray = new String[items.size()];
        int count = 0;
        for(WebElement item : items){
            resultArray[count++] = item.getText();
        }
        return resultArray;
    }
}
