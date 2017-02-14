import def.GoogleSearch;
import def.TMobileForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        TMobileForm f = new TMobileForm(url, "Pokus1", "", "111333777", "x@x.x");
        
        f.setContent("Kolik? Co? Proc? Jak?");
        f.setPhonenum("777333111");
        f.fillForm(driver);
    }
    
    @Test
    public void test2() {
        String url;
      
        GoogleSearch t = new GoogleSearch(tmob);
        url = t.gsearch(driver);
        TMobileForm f = new TMobileForm(url,"Pokus2","Kolik?","666999","abc12@123.commm");
        f.fillForm(driver);
    }
    
    @AfterTest
    public void testF() {
      //  driver.close();
   }
}