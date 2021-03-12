package ai.certifai.DemoScript;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 *Web Element locating
 *
 *
 */
public class TutorialScript2 {

    public static void main(String[] args) throws InterruptedException {
        //instantiate chrome driver
        WebDriver driver = new ChromeDriver();

        //go to google.com
        driver.get("https://www.google.com/");

        //maximize window
        driver.manage().window().maximize();

        By input = By.cssSelector("input[type='text']");

        driver.findElement(input).sendKeys("adidas" + Keys.RETURN);

        By imageBtn = By.xpath("//div[@class='MUFPAc']//child::a[contains(text(),'Images')]");

        //find the image button and click
        driver.findElement(imageBtn).click();

        Thread.sleep(3000);

        //exit
        driver.quit();
    }
}
