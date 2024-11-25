

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Fitpeo {
    WebDriver driver;
    JavascriptExecutor js;

   @BeforeClass
    public void setUp() {
        // Set up ChromeDriver path
     //   System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void automateFitPeo() throws InterruptedException {
        driver.get("https://www.fitpeo.com/");
        driver.findElement(By.linkText("Revenue Calculator")).click();
        
        js = (JavascriptExecutor) driver;
        WebElement scroll = driver.findElement(By.xpath("//div[@class='MuiBox-root css-19zjbfs']"));
        Thread.sleep(5000);
        js.executeScript("arguments[0].scrollIntoView(true);", scroll);
       
        System.out.println("user is able to Scroll upto element");
        
        
        
        WebElement slider = driver.findElement(By.xpath("//input[@aria-valuenow='200']"));
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = 820;", slider); 
        Thread.sleep(5000);
        
        WebElement sliderTextField = driver.findElement(By.xpath("//input[@value='200']"));
        assert sliderTextField.getAttribute("value").equals("820") : "Slider value mismatch";

       Thread.sleep(5000);
        
        Actions cleardata = new Actions(driver);
        cleardata.click(driver.findElement(By.xpath("//input[@type='number']")))
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
   
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys("560");
    
        String sliderValue = slider.getAttribute("value");
        Assert.assertEquals(sliderValue, "560");

       
        driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[1]")).click();
        
        driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[2]")).click();
       
        driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[3]")).click();
       
        driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[8]")).click();
       

       
        WebElement reimbursementHeader = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body2 inter css-1xroguk'][4]")); // Update with actual ID
        String reimbursementValue = reimbursementHeader.getText();
        System.out.println("Reimbursement value: " + reimbursementValue);
        Assert.assertEquals(reimbursementValue, "$110700");


    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        Fitpeo test = new Fitpeo();
        test.setUp();
        try {
            test.automateFitPeo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            test.tearDown();
        }
    }
}