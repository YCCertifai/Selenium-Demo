package ai.certifai;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public WebElement find(By element){
        return wait.until(d -> d.findElement(element));
    }

    public void keyIn(By element, String word){
        find(element).sendKeys(word);
    }

    public void click(By element){
        //do not click instantly, may cause unexpected conditions
        try{
            Thread.sleep(500);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public String getText(By element){
        return find(element).getText();
    }

    public String getAttribute(By element, String attribute)
    {
        return find(element).getAttribute(attribute);
    }

    public List<WebElement> findElements(By element)
    {
        return wait.until(d -> d.findElements(element));
    }

    public String getTitle()
    {
        return driver.getTitle();
    }
}
