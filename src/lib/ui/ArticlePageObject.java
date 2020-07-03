package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text = 'View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc = 'More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text = 'Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']",
            MY_EXITING_LIST_TPL = "xpath://android.widget.TextView[@text = '{FOLDER_NAME}']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc = 'Navigate up']";


    private static String getExsistingListElement(String substring) {
        return MY_EXITING_LIST_TPL.replace("{FOLDER_NAME}", substring);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 20);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find footer on page",
                20
        );
    }

    public void addArticleToNewMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                "Cannot put text into articles folder input",
                name_of_folder,
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToExistsMyList(String name_of_folder) {
        waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to add article to reading list",
                5
        );

        String existing_list_xpath = getExsistingListElement(name_of_folder);
        waitForElementAndClick(
                existing_list_xpath,
                "Cannot find existing folder",
                5
        );

    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find X link",
                5
        );
    }

    public void assertExistsResultOfSearch(){
        this.assertElementPresent(TITLE, "We found more than one title element or didn't find anyone");
    }
}














