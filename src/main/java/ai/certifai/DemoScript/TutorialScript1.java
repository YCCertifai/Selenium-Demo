package ai.certifai.DemoScript;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *Browse Google Page
 *
 *
 */
public class TutorialScript1 {

    public static void main(String[] args) throws InterruptedException {
        //instantiate chrome driver
        WebDriver driver = new ChromeDriver();

        //go to google.com
        driver.get("https://www.google.com/");

        //maximize window
        driver.manage().window().maximize();

        Thread.sleep(2000);

        //exit
        driver.quit();
    }
}
