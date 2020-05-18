import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("Method getLocalNumber returns number != 14",mainClass.getLocalNumber()==14);
    }

}
