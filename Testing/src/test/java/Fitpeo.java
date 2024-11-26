import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Fitpeo {
    WebDriver driver;
    JavascriptExecutor js;

    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void automateFitPeo() throws InterruptedException {
    	// Navigate to the FitPeo Homepage:
        driver.get("https://www.fitpeo.com/");
        
        // Navigate to the Revenue Calculator Page:
        driver.findElement(By.linkText("Revenue Calculator")).click();

        // Scroll Down to the Slider section:
        js = (JavascriptExecutor) driver;
        WebElement scroll = driver.findElement(By.xpath("//div[@class='MuiBox-root css-19zjbfs']"));
        Thread.sleep(5000);
        js.executeScript("arguments[0].scrollIntoView(true);", scroll);

        System.out.println("User scrolled to the Revenue Calculator section.");
        
        // Adjust the Slider:

        Actions act=new Actions(driver);
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
        int targetValue = 820;
        js.executeScript("arguments[0].setAttribute('aria-valuenow', arguments[1]);", slider, targetValue);
        Thread.sleep(5000);
        js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", slider, targetValue);

        String updatedValue = slider.getAttribute("aria-valuenow");
        System.out.println("Updated slider value: " + updatedValue);

        // Update the Text Field:
        Actions clearData = new Actions(driver);
        clearData.click(driver.findElement(By.xpath("//input[@type='number']")))
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
        // Validate Slider Value:
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys("560");
        String sliderValue = slider.getAttribute("value");
        Assert.assertEquals(sliderValue, "560");

        // Select CPT Codes:
        driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[1]")).click();
        driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[2]")).click();
        driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[3]")).click();
        driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[8]")).click();

        // Validate Total Recurring Reimbursement:
        WebElement reimbursementHeader = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body2 inter css-1xroguk'][4]"));
        String reimbursementValue = reimbursementHeader.getText();
        System.out.println("Reimbursement value: " + reimbursementValue);
        Assert.assertEquals(reimbursementValue, "$110700");
    }

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