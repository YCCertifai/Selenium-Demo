package ai.certifai.Shopee.Page;

import ai.certifai.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShopeeSearchResultPage extends BasePage {

    private final By PRODUCT_CONTAINER = By.cssSelector("div[class='row shopee-search-item-result__items']");
    private final By PRODUCT = By.cssSelector("div[class='col-xs-2-4 shopee-search-item-result__item']");
    private final By PRODUCT_NAME = By.cssSelector("div[class='_1NoI8_ A6gE1J _1co5xN']");
    private final By PRODUCT_PRICE = By.cssSelector("span[class='_1xk7ak']");
    private final By SOLD_PRICE = By.cssSelector("div[class='_245-SC']");
    private final By SELLER_LOC = By.cssSelector("div[class='_41f1_p']");
    private final By NEXT_PAGE_BUTTON = By.cssSelector("button[class='shopee-button-outline shopee-mini-page-controller__next-btn']");

    public ShopeeSearchResultPage(WebDriver driver)
    {
        super(driver);
    }

    public void scrollToEnd()
    {
        long curHeight;
        long height;
        find(PRODUCT_CONTAINER);
        do {
            curHeight = (Long) js.executeScript( "return window.pageYOffset + document.documentElement.clientHeight");
            height = (Long) js.executeScript( "return document.body.scrollHeight");
            js.executeScript("window.scrollBy(0,"+ 100 + ")");

        } while (curHeight < height);
    }

    public List<String> scrapData()
    {
        ArrayList<String> outputList = new ArrayList<>();

        for(WebElement product : findElements(PRODUCT))
        {
            String name = product.findElement(PRODUCT_NAME).getText().replace(",", " ");
            List<WebElement> priceRange = product.findElements(PRODUCT_PRICE);
            String minPrice = priceRange.get(0).getText().replace(",", "");
            String maxPrice = priceRange.get(priceRange.size() - 1).getText().replace(",", "");
            String sold = product.findElement(SOLD_PRICE).getText();
            String location = product.findElement(SELLER_LOC).getText();

            outputList.add(String.join(", ", name, minPrice, maxPrice, sold, location));
        }
        return outputList;
    }

    public boolean hasNext()
    {
        try
        {
            find(NEXT_PAGE_BUTTON);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public ShopeeSearchResultPage nextPage()
    {
        click(NEXT_PAGE_BUTTON);
        return new ShopeeSearchResultPage(driver);
    }
}
