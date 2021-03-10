package ai.certifai.Google.Page;

import ai.certifai.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchImagePage extends BasePage {

    private final By resultIndicator = By.xpath("//div[@class='DwpMZe ']");
    private final By resultImages = By.xpath("//img[@class='rg_i Q4LuWd']");
    private final By imageSource = By.cssSelector("div.BIB1wf img.n3VNCb");

    public GoogleSearchImagePage(WebDriver driver)
    {
        super(driver);
    }

    public void scrollToEnd()
    {
        do {
            long curHeight = (Long) js.executeScript( "return window.pageYOffset + document.documentElement.clientHeight");
            long height = (Long) js.executeScript( "return document.body.scrollHeight");
            js.executeScript("window.scrollTo(0,"+ height + ")");

            if (loadStatus() == 0 && curHeight == height){
                click(resultIndicator);
            }
        } while (loadStatus() != 1);
    }

    // -1 => not-loaded
    // 0  => need to click to load
    // 1  => fully-loaded
    public int loadStatus()
    {
        String statusAttribute = "data-status";
        String status = getAttribute(resultIndicator, statusAttribute);
        if (status.equals("3")) return 1;
        else if (status.equals("5")) return 0;
        else return -1;
    }

    public List<String> imageSourceList(){

        List<String> outputList = new ArrayList<>();

        for (WebElement img : findElements(resultImages))
        {

            actions.moveToElement(img).click().perform();

            //time to load image
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            outputList.add(getAttribute(imageSource, "src"));
        }

        return outputList;
    }





}
