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
        TMobileForm f = new TMobileForm(url, null, null, null, null);
        
        f.setSubject("Pokus");
        f.setContent("Kolik? Co? Proc? Jak?");
        f.setPhonenum("111333777");
        f.setEmail("x@x.com");
        
        act = f.fillForm(driver, url);
        assertEquals("FAIL", act, "YES");
    }
    
    @Test(priority = 1)
    public void test2() {
        String act, url;
      
        GoogleSearch t = new GoogleSearch(tmob);
        url = t.gsearch(driver);
        TMobileForm f = new TMobileForm(url,"Pokus2","Kolik?","666999","x@x.com");
        act = f.fillForm(driver, url);
        assertEquals("FAIL", act, "YES");
    }
    
    @AfterTest
    public void testF() {
        driver.close();
   }
}