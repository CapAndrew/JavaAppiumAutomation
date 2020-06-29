import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageobject;

    protected void setUp() throws Exception{

        super.setUp();

        MainPageobject = new MainPageObject(driver);
    }

    private ArrayList<String> articleList = new ArrayList();

    @Ignore
    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Ignore
    @Test
    public void testCancelSearch() {
        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        MainPageobject.waitForElementAndSendKeys(
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
            MainPageobject.waitForElementPresent(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article + "']"),
                    "Cannot find article with name " + article,
                    5
            );
        }
        //Первый клик стирает то, что написано в поиске
        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot X to cancel search",
                5
        );
        //Второй клик закрывает поиск
        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot X to cancel search",
                5
        );

        MainPageobject.waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );
    }

    @Ignore
    @Test
    public void testCheckTextInArticleTitle() {
        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        MainPageobject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "JAVA",
                5
        );

        for (int i = 1; i <= 3; i++) {
            WebElement title_article = MainPageobject.waitForElementPresent(
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
        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        MainPageobject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "Appium",
                15
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("(//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium'])"),
                "Cannot find 'Appium' article in search",
                15
        );

        MainPageobject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        MainPageobject.swipeUpToFindElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "Cannot find end of the article",
                20
        );
    }

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageobject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                "Java",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Cannot find article",
                5
        );

        MainPageobject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                20
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );

        Thread.sleep(10000);
        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find button to add article to reading list",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        MainPageobject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String folder_name = "Learning programming";

        MainPageobject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Cannot put text into articles folder input",
                folder_name,
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press OK button",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot find X link",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My List",
                5
        );

        Thread.sleep(10000);
        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@text='" + folder_name + "']"),
                "Cannot find folder with name " + folder_name,
                5
        );

        MainPageobject.swipeElementToLeft(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot find saved arrticle"
        );

        MainPageobject.waitForElementNotPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot delete saved arrticle",
                5
        );
    }

    @Ignore
    @Test
    public void testAmountOfNotEmptySearch() {

        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin Park discography";
        MainPageobject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                search_line,
                5
        );

        String search_result_locatior = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        MainPageobject.waitForElementPresent(
                By.xpath(search_result_locatior),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_elements = MainPageobject.getAmountOfElements(
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

        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        MainPageobject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                search_line,
                5
        );

        String search_result_locatior = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        String empty_result_error = "//*[@text = 'No results found']";

        MainPageobject.waitForElementPresent(
                By.xpath(empty_result_error),
                "Cannot find empty result label by the request" + search_line,
                15
        );

        MainPageobject.assertElementNotPresent(
                By.xpath(search_result_locatior),
                "We've found some result by request " + search_line
        );
    }

    @Test
    public void testSaveTwoArticleToMyList() throws InterruptedException {
        MainPageobject.searchArticlesAndOpenOne("Java", "Object-oriented programming language");

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/title'][contains(@text , 'Add to reading list')]"),
                "Cannot find button to add article to reading list",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        MainPageobject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String folder_name = "Learning programming";

        MainPageobject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Cannot put text into articles folder input",
                folder_name,
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press OK button",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot find X link",
                5
        );

        MainPageobject.searchArticlesAndOpenOne("Java", "Island of Indonesia");

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/title'][contains(@text , 'Add to reading list')]"),
                "Cannot find button to add article to reading list",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text = '"+folder_name+"']"),
                "Cannot find existing folder",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot find X link",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My List",
                5
        );

        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@text='" + folder_name + "']"),
                "Cannot find folder with name " + folder_name,
                5
        );

        MainPageobject.swipeElementToLeft(
                By.xpath("//*[@text = 'island of Indonesia']"),
                "Cannot find saved arrticle"
        );

        MainPageobject.waitForElementPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot delete saved arrticle",
                5
        );
    }

    @Test
    public void testCheckArticleTitle() {

        MainPageobject.searchArticles("Java");

        MainPageobject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Cannot find article",
                5
        );

        MainPageobject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title"
        );
    }
}
