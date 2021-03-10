package ai.certifai.Shopee;


import ai.certifai.Shopee.Page.ShopeeMainPage;
import ai.certifai.Shopee.Page.ShopeeSearchResultPage;
import ai.certifai.Utils.CSVHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ShopeeProductScraping {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            CSVHandler file = new CSVHandler("test.csv", false);
            file.addHeader("product name, min price, max price, amount sold, seller location");

            driver.get("https://shopee.com.my/");
            driver.manage().window().maximize();

            ShopeeMainPage mainPage = new ShopeeMainPage(driver);

            mainPage.selectLanguage();

            ShopeeSearchResultPage searchResultPage = mainPage.search("watch");

            while (searchResultPage.hasNext())
            {

                searchResultPage.scrollToEnd();

                List<String> data = searchResultPage.scrapData();

                file.append(data);

                searchResultPage = searchResultPage.nextPage();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
