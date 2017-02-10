import def.AnalystTask;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestNG {
    public String s;
    public WebDriver driver;
    
    @BeforeTest
    public void test0() {
        s = "t-mobile";
        driver = new ChromeDriver();
    }
    
    @Test(priority = 0)
    public void test1() {
        String str, url;
        AnalystTask t = new AnalystTask(s);
        url = t.gsearch(driver);
        
        t.setS("Pokus");
        t.setC("Kolik? Co? Proc? Jak?");
        t.setP("111333777");
        t.setE("x@x.com");
        
        str = t.fillForm(driver, url);
        assertEquals("YES", str);
    }
    
    @Test(priority = 1)
    public void test2() {
        String str, url;
        AnalystTask t = new AnalystTask(s,"Pokus2","Kolik?","666999","x@x.com");
        url = t.gsearch(driver);
        
        str = t.fillForm(driver, url);
        assertEquals("YES", str);
    }
    
    @AfterTest
    public void testF() {
        driver.close();
   }
}