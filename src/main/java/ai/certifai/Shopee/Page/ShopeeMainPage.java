package ai.certifai.Shopee.Page;

import ai.certifai.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ShopeeMainPage extends BasePage {
    private final String title;
    private final By INPUT = By.cssSelector("INPUT[class='shopee-searchbar-input__input']");
    private final By LANGUAGE_POP_UP = By.cssSelector("div[class='language-selection']");
    private final By ENGLISH_LANGUAGE_SELECTOR = By.xpath("//*[text()='English']");

    public ShopeeMainPage(WebDriver driver)
    {
        super(driver);
        this.title = driver.getTitle();
    }

    public void selectLanguage()
    {
        if (driver.findElement(LANGUAGE_POP_UP).isDisplayed()){
            driver.findElement(ENGLISH_LANGUAGE_SELECTOR).click();
        }
    }

    public ShopeeSearchResultPage search(String query)
    {
        find(INPUT).clear();
        keyIn(INPUT, query + Keys.RETURN);

        return new ShopeeSearchResultPage(driver);
    }
}
