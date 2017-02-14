package def;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TMobileForm {
    
    private String url;
    private String subject;
    private String content;
    private String phonenum;
    private String email;
    
    public TMobileForm(String baseUrl, String subject, String content, String phonenum, String email) {
        this.url = baseUrl;
        this.subject = subject;
        this.content = content;
        this.phonenum = phonenum;
        this.email = email;
    }
    
    public void setSubject(String subject) {this.subject = subject;}
    public String getSubject() {return this.subject;}
    public void setContent(String content) {this.content = content;}
    public String getContent() {return this.content;}
    public void setPhonenum(String phonenum) {this.phonenum = phonenum;}
    public String getPhonenum() {return this.phonenum;}
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return this.email;}
    
    public String fillForm(WebDriver driver, String url) {
        if(subject == null || content == null || phonenum == null || email == null) {
            return "MISS";
        }
        driver.navigate().to(url);
        
        //jsem tam - vyplnuju form
        driver.findElement(By.name("subject")).sendKeys(subject);
        driver.findElement(By.name("content")).sendKeys(content);
        driver.findElement(By.name("phoneNumber")).sendKeys(phonenum);
        driver.findElement(By.name("email")).sendKeys(email);
        
        //checkbox
        WebElement fe4 = driver.findElement(By.xpath("//*[@type='checkbox']"));
        JavascriptExecutor Executor = ((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", fe4);
        
        //nahraju obrazek
        WebElement fe5 = driver.findElement(By.xpath("//input[@type='file']"));
        File file = new File("pic.jpg");
        fe5.sendKeys(file.getAbsolutePath());
        
        // odeslani a kontrola
        driver.findElement(By.name("submit")).submit();
        try {
            driver.findElement(By.xpath("//div[@class='portlet-msg-success']"));
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
        
        return "YES";
    }
}
