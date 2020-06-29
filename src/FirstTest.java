import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import jdk.internal.instrumentation.InstrumentationMethod;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;
    private ArrayList<String> articleList = new ArrayList();

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\GIT\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Ignore
    @Test
    public void FirstTest() {

        waitForElementAndClick(
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

    @Ignore
    @Test
    public void TestCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "Java",
                5
        );

        articleList.add("Island of Indonesia");
        articleList.add("Programming language");
        articleList.add("Object-oriented programming language");

        for (String article : articleList
        ) {
            waitForElementPresent(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article + "']"),
                    "Cannot find article with name " + article,
                    5
            );
        }
        //Первый клик стирает то, что написано в поиске
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot X to cancel search",
                5
        );
        //Второй клик закрывает поиск
        waitForElementAndClick(
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

    @Ignore
    @Test
    public void CheckTextInArticleTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "JAVA",
                5
        );

        for (int i = 1; i <= 3; i++) {
            WebElement title_article = waitForElementPresent(
                    By.xpath("(//*[@resource-id = 'org.wikipedia:id/page_list_item_title'])[" + i + "]"),
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

    @Ignore
    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "Appium",
                15
        );

        waitForElementAndClick(
                By.xpath("(//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium'])"),
                "Cannot find 'Appium' article in search",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "Cannot find end of the article",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList() throws InterruptedException {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "Java",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Cannot find article",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                20
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );

        Thread.sleep(10000);
        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find button to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String folder_name = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Cannot put text into articles folder input",
                folder_name,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My List",
                5
        );

        Thread.sleep(10000);
        waitForElementAndClick(
                By.xpath("//*[@text='" + folder_name + "']"),
                "Cannot find folder with name " + folder_name,
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot find saved arrticle"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot delete saved arrticle",
                5
        );
    }

    @Ignore
    @Test
    public void testAmountOfNotEmptySearch() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin Park discography";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                search_line,
                5
        );

        String search_result_locatior = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locatior),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_elements = getAmountOfElements(
                By.xpath(search_result_locatior)
        );

        Assert.assertTrue(
                "We found too few elements",
                amount_of_search_elements > 0
        );

    }

    @Ignore
    @Test
    public void testAmountOfEmptySearch() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                search_line,
                5
        );

        String search_result_locatior = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        String empty_result_error = "//*[@text = 'No results found']";

        waitForElementPresent(
                By.xpath(empty_result_error),
                "Cannot find empty result label by the request" + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locatior),
                "We've found some result by request " + search_line
        );
    }

    @Test
    public void saveTwoArticleToMyList() throws InterruptedException {
        searchArticlesAndOpenOne("Java", "Object-oriented programming language");

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/title'][contains(@text , 'Add to reading list')]"),
                "Cannot find button to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String folder_name = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Cannot put text into articles folder input",
                folder_name,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot find X link",
                5
        );

        searchArticlesAndOpenOne("Java", "Island of Indonesia");

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/title'][contains(@text , 'Add to reading list')]"),
                "Cannot find button to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text = '"+folder_name+"']"),
                "Cannot find existing folder",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My List",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + folder_name + "']"),
                "Cannot find folder with name " + folder_name,
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text = 'island of Indonesia']"),
                "Cannot find saved arrticle"
        );

        waitForElementPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot delete saved arrticle",
                5
        );
    }

    @Test
    public void checkArticleTitle() {

        searchArticles("Java");

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Cannot find article",
                5
        );

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title"
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String error_message, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {

        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int lower_y = element.getLocation().getY();
        int upper_y = lower_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(500)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {

        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message) {

        int amount_of_elements = getAmountOfElements(by);

        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private void assertElementPresent(By by, String error_message){

        int amount_of_elements = getAmountOfElements(by);

        if (amount_of_elements != 1) {
            String default_message = "An element '" + by.toString() + "' not presented";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private void searchArticles(String search_line) {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                search_line,
                5
        );
    }

    private void searchArticlesAndOpenOne(String search_line, String article_name) {
        searchArticles(search_line);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '"+article_name+"']"),
                "Cannot find article " + article_name,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                25
        );

    }
}
