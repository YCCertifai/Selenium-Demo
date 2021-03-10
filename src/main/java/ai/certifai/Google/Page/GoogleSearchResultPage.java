package ai.certifai.Google.Page;

import ai.certifai.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultPage extends BasePage {

    private By imageButton = By.xpath("//div[@class='MUFPAc']//child::a[contains(text(),'Images')]");

    public GoogleSearchResultPage(WebDriver driver)
    {
        super(driver);
    }

    public GoogleSearchImagePage toImage()
    {
        click(imageButton);
        return new GoogleSearchImagePage(driver);
    }
}
