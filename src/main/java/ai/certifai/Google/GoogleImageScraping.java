package ai.certifai.Google;

import ai.certifai.Google.Page.GoogleMainPage;
import ai.certifai.Google.Page.GoogleSearchImagePage;
import ai.certifai.Google.Page.GoogleSearchResultPage;
import ai.certifai.Utils.ImageDownloader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;


/**
 * Script to scrape all image on google by searching keyword
 *
 */
public class GoogleImageScraping
{
    public static void main( String[] args )
    {
        WebDriver driver = new ChromeDriver();

        //create GoogleMainPage object
        GoogleMainPage google = new GoogleMainPage(driver);

        //search keyword in main page -> search result page
        GoogleSearchResultPage resultPage = google.search("adidas");

        //click the image button -> image search result page
        GoogleSearchImagePage imageResult = resultPage.toImage();

        //in the image search result page, scroll till the end
        imageResult.scrollToEnd();

        //get all image src list
        List<String> imgSrcList = imageResult.imageSourceList();

        //define savePath
        String savePath = new File("").getAbsolutePath() + "/output/";

        //save the links as image to path
        ImageDownloader.saveImages(imgSrcList, savePath);
    }
}
