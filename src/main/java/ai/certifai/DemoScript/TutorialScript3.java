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

    public static void main(String[] args) throws InterruptedException {
        //instantiate chrome driver
        driver = new ChromeDriver();

        //instantiate explicit wait
        wait = new WebDriverWait(driver, 30);

        //instantiate actions
        actions = new Actions(driver);

        //instantiate javascript executor
        js = (JavascriptExecutor) driver;

        //go to google.com
        driver.get("https://www.google.com/");

        //maximize window
        driver.manage().window().maximize();

        //define input textbox
        By input = By.cssSelector("input[type='text']");

        //key in values into input textbox
        driver.findElement(input).sendKeys("skymind" + Keys.RETURN);

        //define image button in result page
        By imageBtn = By.xpath("//div[@class='MUFPAc']//child::a[contains(text(),'Images')]");

        //wait for image button to be clickable
        WebElement imageBtnElement =  wait.until(ExpectedConditions.elementToBeClickable(imageBtn));

        //click the image button
        imageBtnElement.click();

        //define result indicator
        By resultIndicator = By.xpath("//div[@class='DwpMZe ']");

        //scroll to end
        scrollToEnd(resultIndicator);

        //define list to store img source links
        List<String> imageSourceList = new ArrayList<>();

        //define result images
        By resultImages = By.xpath("//img[@class='rg_i Q4LuWd']");

        //define the enlarged image after click
        By imageSource = By.cssSelector("div.BIB1wf img.n3VNCb");

        //find list of target images
        List<WebElement> imgList = wait.until(d -> d.findElements(resultImages));

        //for demo purpose, only saves 10 images
        int counter = 10;

        for (WebElement img : imgList)
        {
            //wait until img to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(img));

            //click image
            actions.click(img).perform();

            //time to load image
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            //break condition
            if (counter-- == 0)
            {
                break;
            }

            //add url to list
            imageSourceList.add(wait.until(d -> d.findElement(imageSource)).getAttribute("src"));
        }

        //define save path
        String savePath = new File("").getAbsolutePath() + "/output/";

        //download images from links
        ImageDownloader.saveImages(imageSourceList,savePath);

        //exit
        driver.quit();
    }

    public static void scrollToEnd(By resultIndicator)
    {
        do {
            long curHeight = (Long) js.executeScript( "return window.pageYOffset + document.documentElement.clientHeight");
            long height = (Long) js.executeScript( "return document.body.scrollHeight");
            js.executeScript("window.scrollTo(0,"+ height + ")");

            if (loadStatus(resultIndicator) == 0 && curHeight == height){
                wait.until(ExpectedConditions.elementToBeClickable(resultIndicator)).click();
            }
        } while (loadStatus(resultIndicator) != 1);
    }

    public static int loadStatus(By resultIndicator)
    {
        String statusAttribute = "data-status";
        String status = wait.until(d -> d.findElement(resultIndicator)).getAttribute(statusAttribute);
        if (status.equals("3")) return 1;
        else if (status.equals("5")) return 0;
        else return -1;
    }
}
