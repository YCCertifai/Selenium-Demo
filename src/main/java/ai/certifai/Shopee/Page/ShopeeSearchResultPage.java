package ai.certifai.Shopee.Page;

import ai.certifai.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShopeeSearchResultPage extends BasePage {

    private By productContainer = By.cssSelector("div[class='row shopee-search-item-result__items']");
    private By product = By.cssSelector("div[class='col-xs-2-4 shopee-search-item-result__item']");
    private By productName = By.cssSelector("div[class='_1NoI8_ A6gE1J _1co5xN']");
    private By productPrice = By.cssSelector("span[class='_1xk7ak']");
    private By soldAmount = By.cssSelector("div[class='_245-SC']");
    private By sellerLoc = By.cssSelector("div[class='_41f1_p']");
    private By nextPageButton = By.cssSelector("button[class='shopee-button-outline shopee-mini-page-controller__next-btn']");

    private By currentPage = By.cssSelector("span[class='shopee-mini-page-controller__current']");

    public ShopeeSearchResultPage(WebDriver driver)
    {
        super(driver);
    }

    public void scrollToEnd()
    {
        long curHeight;
        long height;
        find(productContainer);
        do {
            curHeight = (Long) js.executeScript( "return window.pageYOffset + document.documentElement.clientHeight");
            height = (Long) js.executeScript( "return document.body.scrollHeight");
            js.executeScript("window.scrollBy(0,"+ 100 + ")");

        } while (curHeight < height);
    }

    public List<String> scrapData()
    {
        ArrayList<String> outputList = new ArrayList<>();
        WebElement container = find(productContainer);

        for(WebElement product : findElements(product))
        {
            String name = product.findElement(productName).getText().replace(",", " ");
            List<WebElement> priceRange = product.findElements(productPrice);
            String minPrice = priceRange.get(0).getText().replace(",", "");
            String maxPrice = priceRange.get(priceRange.size() - 1).getText().replace(",", "");
            String sold = product.findElement(soldAmount).getText();
            String location = product.findElement(sellerLoc).getText();

            outputList.add(String.join(", ", name, minPrice, maxPrice, sold, location));
        }
        return outputList;
    }

    public boolean hasNext()
    {
        try
        {
            find(nextPageButton);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public ShopeeSearchResultPage nextPage()
    {
        click(nextPageButton);
        return new ShopeeSearchResultPage(driver);
    }
}
