package ai.certifai.Google.Page;

import ai.certifai.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultPage extends BasePage {

    private final By IMAGE_BUTTON = By.xpath("//div[@class='MUFPAc']//child::a[contains(text(),'Images')]");

    public GoogleSearchResultPage(WebDriver driver)
    {
        super(driver);
    }

    public GoogleSearchImagePage toImage()
    {
        click(IMAGE_BUTTON);
        return new GoogleSearchImagePage(driver);
    }
}
