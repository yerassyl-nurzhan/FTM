import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ICanWin {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://pastebin.com");

        WebElement pasteInput = driver.findElement(By.xpath("//*[@class='textarea -form js-paste-code']"));
        pasteInput.sendKeys("Hello from WebDriver");

        WebElement pasteExpiration = driver.findElement(By.xpath("//*[@id='select2-postform-expiration-container']"));
        pasteExpiration.click();

        WebElement pasteExpiration10Min = driver.findElement(By.xpath("(//*[@class='select2-results__option'])[2]"));
        pasteExpiration10Min.click();

        WebElement pasteName = driver.findElement(By.xpath("//*[@id='postform-name']"));
        pasteName.sendKeys("helloweb");

        Thread.sleep(2000);

        WebElement pasteCreate = driver.findElement(By.xpath("//*[@class='btn -big']"));
        pasteCreate.click();


    }
}
