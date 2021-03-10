package ai.certifai.DemoScript;

import ai.certifai.Utils.ImageDownloader;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *Web Element locating
 *
 *
 */
public class TutorialScript3 {
    private static WebDriver driver;
    private static Wait<WebDriver> wait;
    private static JavascriptExecutor js;
    private static Actions actions;

    private static final By resultIndicator = By.xpath("//div[@class='DwpMZe ']");
    private static final By resultImages = By.xpath("//img[@class='rg_i Q4LuWd']");
    private static final By imageSource = By.cssSelector("div.BIB1wf img.n3VNCb");

    public static void main(String[] args) throws InterruptedException {
        //instantiate chrome driver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        //go to google.com
        driver.get("https://www.google.com/");

        //maximize window
        driver.manage().window().maximize();

        By input = By.cssSelector("input[type='text']");

        driver.findElement(input).sendKeys("adidas" + Keys.RETURN);

        By imageBtn = By.xpath("//div[@class='MUFPAc']//child::a[contains(text(),'Images')]");

        //wait
        WebElement imageBtnElement =  wait.until(d -> d.findElement(imageBtn));
        imageBtnElement.click();

        //scroll to end
        scrollToEnd();

        List<String> imageSourceList = new ArrayList<>();

        for (WebElement img : driver.findElements(resultImages))
        {
            actions.moveToElement(img).click();

            //time to load image
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }


            imageSourceList.add(wait.until(d -> d.findElement(imageSource)).getAttribute("src"));
        }

        String savePath = new File("").getAbsolutePath() + "/output/";

        ImageDownloader.saveImages(imageSourceList,savePath);

        //exit
        driver.quit();
    }

    public static void scrollToEnd()
    {
        do {
            long curHeight = (Long) js.executeScript( "return window.pageYOffset + document.documentElement.clientHeight");
            long height = (Long) js.executeScript( "return document.body.scrollHeight");
            js.executeScript("window.scrollTo(0,"+ height + ")");

            if (loadStatus() == 0 && curHeight == height){
                wait.until(d -> d.findElement(resultIndicator)).click();
            }
        } while (loadStatus() != 1);
    }

    public static int loadStatus()
    {
        String statusAttribute = "data-status";
        String status = wait.until(d -> d.findElement(resultIndicator)).getAttribute(statusAttribute);
        if (status.equals("3")) return 1;
        else if (status.equals("5")) return 0;
        else return -1;
    }
}
