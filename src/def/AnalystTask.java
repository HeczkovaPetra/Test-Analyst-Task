package def;

import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnalystTask {
    public String url;
    private String s;
    private String c;
    private String p;
    private String e;
    
    public AnalystTask(String baseUrl) {
        this.url = baseUrl;
    }
    
    public AnalystTask(String baseUrl, String s, String c, String p, String e) {
        this.url = baseUrl;
        this.s = s;
        this.c = c;
        this.p = p;
        this.e = e;
    }
    
    public void setS(String s) {this.s = s;}
    public String getS() {return this.s;}
    public void setC(String c) {this.c = c;}
    public String getC() {return this.c;}
    public void setP(String p) {this.p = p;}
    public String getp() {return this.p;}
    public void setE(String e) {this.e = e;}
    public String getE() {return this.e;}
    
    public String gsearch(WebDriver driver) {
        
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys(url);
        q.submit();
        
        // cekam nez najde vysledky
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        
        //prvni odkaz v seznamu - predpokladam ze homepage
        WebElement fe = driver.findElement(By.xpath("//h3[@class='r']/a"));
        String url2 = fe.getAttribute("href");
        driver.navigate().to(url2);
        
        WebElement fe2 = driver.findElement(By.linkText("Podpora"));
        //zkraceni 4
        Actions action = new Actions(driver); // odkryvam
        action.moveToElement(fe2).build().perform();
        
        // vyberu spravny odkaz
        List<WebElement> fe3 = driver.findElements(By.xpath("//li[@class='col-md-4']/a"));
        driver.navigate().to(fe3.get(3).getAttribute("href"));
        
        String res = driver.findElement(By.linkText("Kontaktní formulář")).getAttribute("href");
        return res;
    }
    
    public String fillForm(WebDriver driver, String url) {
        if(s == null || c == null || p == null || e == null) {
            System.err.println("Something is missing.");
            return "MISS";
        }
        driver.navigate().to(url);
        
        //jsem tam - vyplnuju form
        driver.findElement(By.name("subject")).sendKeys(s);
        driver.findElement(By.name("content")).sendKeys(c);
        driver.findElement(By.name("phoneNumber")).sendKeys(p);
        driver.findElement(By.name("email")).sendKeys(e);
        
        //checkbox
        WebElement fe4 = driver.findElement(By.xpath("//*[@type='checkbox']"));
        JavascriptExecutor Executor = ((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", fe4);
        
        //nahraju obrazek
        WebElement fe5 = driver.findElement(By.xpath("//input[@type='file']"));
        File file = new File("pic.jpg");
        System.out.println(file.getAbsolutePath());
        fe5.sendKeys(file.getAbsolutePath());
        
        // odeslani a kontrola
        driver.findElement(By.name("submit")).submit();
        try {
            driver.findElement(By.xpath("//div[@class='portlet-msg-success']"));
        } catch (NoSuchElementException e) {
            return "NO";
        }
        
        return "YES";
    }
}
