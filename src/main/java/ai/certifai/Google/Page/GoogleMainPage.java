package ai.certifai.Google.Page;

import ai.certifai.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleMainPage extends BasePage {

    private By input = By.cssSelector("input[type='text']");

    private final String URL = "https://www.google.com/";
    public GoogleMainPage(WebDriver driver)
    {
        super(driver);
        driver.get(URL);
    }

    public GoogleSearchResultPage search(String query)
    {
        keyIn(input, query + Keys.RETURN);
        return new GoogleSearchResultPage(driver);
    }
}
