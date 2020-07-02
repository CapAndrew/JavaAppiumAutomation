import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageobject;

    protected void setUp() throws Exception {

        super.setUp();

        MainPageobject = new MainPageObject(driver);
    }







}
