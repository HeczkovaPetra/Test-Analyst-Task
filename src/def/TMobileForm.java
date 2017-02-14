package def;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents T-Mobile Form.
 * Can send form filled with data in parameters.
 * 
 * @author Petra
 */
public class TMobileForm {
    
    private String url;
    private String subject;
    private String content;
    private String phonenum;
    private String email;
    
    /**
    * Constructor - creates and validates data for question.
    * 
    * @param url        url of the form
    * @param subject    subject of question
    * @param content    actual question
    * @param phonenum   phone number must be 6-12 numbers long
    * @param email      email in format x@x.x
    */
    public TMobileForm(String url, String subject, String content, String phonenum, String email) {
        if(url == null) {
            throw new IllegalArgumentException("Url can't be null.");
        }
        if(subject == null) {
            throw new IllegalArgumentException("Subject can't be null.");
        }
        if(content == null) {
            throw new IllegalArgumentException("Content can't be null.");
        }
        if(!valNum(phonenum)) {
            throw new IllegalArgumentException("Phone number must be 6-12 numbers long.");
        }
        if(!valEmail(email)) {
            throw new IllegalArgumentException("Email in incorrect format.");
        }
        this.url = url;
        this.subject = subject;
        this.content = content;
        this.phonenum = phonenum;
        this.email = email;
    }
    
    public void setUrl(String url) {this.url = url;}
    public String getUrl() {return this.url;}
    public void setSubject(String subject) {this.subject = subject;}
    public String getSubject() {return this.subject;}
    public void setContent(String content) {this.content = content;}
    public String getContent() {return this.content;}
    public void setPhonenum(String phonenum) {
        if(valNum(phonenum))
            this.phonenum = phonenum;
        else 
            throw new IllegalArgumentException("Phone number must be 6-12 numbers long.");
    }
    public String getPhonenum() {return this.phonenum;}
    public void setEmail(String email) {
        if(valEmail(email))
            this.email = email;
        else
            throw new IllegalArgumentException("Email in incorrect format.");
    }
    public String getEmail() {return this.email;}
    
    /**
     * Fills and sends form.
     * It also clicks on checkbox and loads picture. 
     * 
     * @param driver    webdriver
     * @return          zero if everything ok
     * @see             pic.jpg
     */
    public int fillForm(WebDriver driver) {
        driver.navigate().to(url);
        driver.findElement(By.name("subject")).sendKeys(subject);
        driver.findElement(By.name("content")).sendKeys(content);
        driver.findElement(By.name("phoneNumber")).sendKeys(phonenum);
        driver.findElement(By.name("email")).sendKeys(email);
        
        // checkbox
        WebElement fe4 = driver.findElement(By.xpath("//*[@type='checkbox']"));
        JavascriptExecutor Executor = ((JavascriptExecutor)driver);
        Executor.executeScript("arguments[0].click();", fe4);
        
        // loading picture
        WebElement fe5 = driver.findElement(By.xpath("//input[@type='file']"));
        File file = new File("pic.jpg");
        fe5.sendKeys(file.getAbsolutePath());
        
        // sending and checking
        driver.findElement(By.name("submit")).click();
        try {
            driver.findElement(By.xpath("//div[@class='portlet-msg-success']"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        }
        return 0;
    }
    
    /**
     * Checking if phone number is in correct format. 
     * It must be 6-12 numbers long.
     * 
     * @param phonenum  phone number
     * @return          true if number is valid
     */
    private Boolean valNum(String phonenum) {
        if(phonenum == null)
            return false;
        return phonenum.matches("^[0-9]{6,12}$");
    }
    
    /**
     * Checking if email address is in correct format.
     * It must be something@something.something
     * 
     * @param email email address
     * @return      true if email is valid
     */
    private Boolean valEmail(String email) {
        if(email == null)
            return false;
        return email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");
    }
}
