package ai.certifai.Shopee.Page;

import ai.certifai.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ShopeeMainPage extends BasePage {
    private String title;
    private By input = By.cssSelector("input[class='shopee-searchbar-input__input']");
    private By languagePopUp = By.cssSelector("div[class='language-selection']");
    private By englishLanguage = By.xpath("//*[text()='English']");

    public ShopeeMainPage(WebDriver driver)
    {
        super(driver);
        this.title = driver.getTitle();
    }

    public void selectLanguage()
    {
        if (driver.findElement(languagePopUp).isDisplayed()){
            driver.findElement(englishLanguage).click();
        }
    }

    public ShopeeSearchResultPage search(String query)
    {
        find(input).clear();
        keyIn(input, query + Keys.RETURN);

        return new ShopeeSearchResultPage(driver);
    }
}
