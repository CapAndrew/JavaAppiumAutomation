package lib.tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        String folder_name = "Learning programming";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToNewMyList(folder_name);
        ArticlePageObject.closeArticle();

        NavigationUI.clickMyList();

        MyListsPageObject.openFolderByName(folder_name);
        MyListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticleToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        String name_of_folder = "Learning programming";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String first_article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToNewMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Island of Indonesia");

        String second_article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToExistsMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI.clickMyList();

        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeArticleToDelete(second_article_title);
        MyListsPageObject.waitForArticleToDisappearByTitle(second_article_title);
        MyListsPageObject.waitForArticleToAppearByTitle(first_article_title);
    }

}
