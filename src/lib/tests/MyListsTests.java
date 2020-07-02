package lib.tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {

    private MainPageObject MainPageobject;

    protected void setUp() throws Exception {

        super.setUp();

        MainPageobject = new MainPageObject(driver);
    }

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        String folder_name = "Learning programming";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMyList(folder_name);
        ArticlePageObject.closeArticle();

        NavigationUI.clickMyList();

        MyListPageObject.openFolderByName(folder_name);
        MyListPageObject.swipeArticleToDelete(article_title);
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
                By.xpath("//android.widget.TextView[@text = '" + folder_name + "']"),
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

}
