package lib.tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticleTests extends CoreTestCase {

    private MainPageObject MainPageobject;

    protected void setUp() throws Exception {

        super.setUp();

        MainPageobject = new MainPageObject(driver);
    }

    @Test
    public void testCheckTextInArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        for (int i = 1; i <= 3; i++) {
            WebElement title_article =SearchPageObject.waitForSearchResultByIndex(i);
            String title_article_text = title_article.getAttribute("text");
            assertEquals(
                    "Unexpected title",
                    true,
                    title_article_text.toLowerCase().contains("java")
            );
        }
    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
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
