import def.GoogleSearch;
import def.TMobileForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Tests if classes GoogleSearch and TMobileForm work.
 * There are 2 test, basically the same thing.
 * 
 * @author Petra
 */
public class TestNG {
    public String tmob;
    public WebDriver driver;
    
    @BeforeTest
    public void test0() {
        tmob = "t-mobile";
        driver = new ChromeDriver();
    }
    
    @Test
    public void test1() {
        String url;
        
        GoogleSearch t = new GoogleSearch(tmob);
        url = t.gsearch(driver);
        TMobileForm f = new TMobileForm(url, "Predmet1", "", "111333777", "xcf@ssx.com");
        
        f.setContent("Kolik? Co? Proc? Jak?");
        f.setPhonenum("666555444");
        int i = f.fillForm(driver);
        assertEquals(i, 0, "Form was not filled correctly.");
    }
    
    @Test
    public void test2() {
        String url;
      
        GoogleSearch t = new GoogleSearch(tmob);
        url = t.gsearch(driver);
        TMobileForm f = new TMobileForm(url,"Predmet2","Kolik?","77778888","a45d@1sda.commm");
        int i = f.fillForm(driver);
        assertEquals(i, 0, "Form was not filled correctly.");
    }
    
    @AfterTest
    public void testF() {
        driver.close();
   }
}
