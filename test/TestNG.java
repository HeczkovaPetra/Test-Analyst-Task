import def.GoogleSearch;
import def.TMobileForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestNG {
    public String tmob;
    public WebDriver driver;
    
    @BeforeTest
    public void test0() {
        tmob = "t-mobile";
        driver = new ChromeDriver();
    }
    
    @Test(priority = 0)
    public void test1() {
        String act, url;
        
        GoogleSearch t = new GoogleSearch(tmob);
        url = t.gsearch(driver);
        TMobileForm f = new TMobileForm(url, "Pokus1", "", "111333777", "x@x.x");
        
        f.setContent("Kolik? Co? Proc? Jak?");
        f.setPhonenum("777333111");
        
        act = f.fillForm(driver);
        assertEquals(act, "YES");
    }
    
    @Test(priority = 1)
    public void test2() {
        String act, url;
      
        GoogleSearch t = new GoogleSearch(tmob);
        url = t.gsearch(driver);
        TMobileForm f = new TMobileForm(url,"Pokus2","Kolik?","666999","abc12@123.commm");
        act = f.fillForm(driver);
        assertEquals(act, "YES");
    }
    
    @AfterTest
    public void testF() {
        driver.close();
   }
}