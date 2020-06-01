import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;

public class FirstTest {

    private AppiumDriver driver;
    private ArrayList<String> articleList = new ArrayList();

    @Before
    public void setUp()throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\GIT\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void FirstTest(){

        waitForElementPresentAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input",
                5
        );
    }

    @Test
    public void TestCancelSearch(){
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        waitForElementPresentAndSendsKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "Java",
                5
        );

        articleList.add("Island of Indonesia");
        articleList.add("Programming language");
        articleList.add("Object-oriented programming language");

        for (String article: articleList
        ) {
            waitForElementPresent(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Island of Indonesia']"),
                    "Cannot find article with name "+article,
                    5
            );
        }
        //Первый клик стирает то, что написано в поиске
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot X to cancel search",
                5
        );
        //Второй клик закрывает поиск
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot X to cancel search",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );
    }

    @Test
    public void CheckTextInArticleTitle(){
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        waitForElementPresentAndSendsKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "JAVA",
                5
        );

        for (int i = 1; i <= 3; i++) {
            WebElement title_article = waitForElementPresent(
                    By.xpath("(//*[@resource-id = 'org.wikipedia:id/page_list_item_title'])["+i+"]"),
                    "Cannot find article list",
                    5
            );

            String title_article_text = title_article.getAttribute("text");

            Assert.assertEquals(
                    "Unexpected title",
                    true,
                    title_article_text.toLowerCase().contains("java")
            );
        }
}

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresentAndSendsKeys(By by, String error_message, String value, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
}
