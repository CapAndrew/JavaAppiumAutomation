import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("Method getLocalNumber returns number != 14",mainClass.getLocalNumber()==14);
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Method getClassNumber returns number < 45", mainClass.getClassNumber() > 45);
    }

}
