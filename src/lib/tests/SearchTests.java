package lib.tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

public class SearchTests extends CoreTestCase {
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        articleList.add("Island of Indonesia");
        articleList.add("Programming language");
        articleList.add("Object-oriented programming language");

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        for (String article : articleList
        ) {
            SearchPageObject.waitForSearchResult(article);
        }
        //Первый клик стирает то, что написано в поиске
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        //Второй клик закрывает поиск
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }





    @Ignore
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_line = "Linkin Park discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_elements = SearchPageObject.getAmountOfFindArticles();

        assertTrue(
                "We found too few elements",
                amount_of_search_elements > 0
        );
    }

    @Ignore
    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_line = "zxcvasdfqwer";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
