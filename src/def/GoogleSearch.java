package def;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearch {
    private String url;
    
    public GoogleSearch(String baseUrl) {
        this.url = baseUrl;
    }
    
    public String gsearch(WebDriver driver) {
        
        driver.get("http://www.google.com/");
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
}