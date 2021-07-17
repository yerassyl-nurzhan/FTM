import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BringItOnTestReserve {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
    }

    @Test (description = "My first test")
    public void checkCreatingCodeInPastebinService() {
        driver.get("https://pastebin.com");

        //new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXsCompleted());

        String newCode1 = "git config --global user.name  'New Sheriff in Town'";
        String newCode2 = "git reset $(git commit-tree HEAD^{tree} -m 'Legacy code')";
        String newCode3 = "git push origin master --force";

        WebElement pasteInput = driver.findElement(By.xpath("//*[@class='textarea -form js-paste-code']"));
        pasteInput.sendKeys(newCode1 + '\n' + newCode2 + '\n' + newCode3);

        WebElement syntaxHighlighting = driver.findElement(By.xpath("//span[@id='select2-postform-format-container']"));
        syntaxHighlighting.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//li[text()='Bash'][@class='select2-results__option']")));

        WebElement syntaxHighlightingBash = driver.findElement(By.xpath("//li[text()='Bash'][@class='select2-results__option']"));
        syntaxHighlightingBash.click();

        WebElement pasteExpiration = driver.findElement(By.xpath("//span[@id='select2-postform-expiration-container']"));
        pasteExpiration.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//li[text()='10 Minutes'][@class='select2-results__option']")));

        WebElement pasteExpiration10Min = driver.findElement(By.xpath("//li[text()='10 Minutes'][@class='select2-results__option']"));
        pasteExpiration10Min.click();

        WebElement pasteName = driver.findElement(By.xpath("//*[@id='postform-name']"));
        pasteName.sendKeys("how to gain dominance among developers");

        WebElement pasteCreate = driver.findElement(By.xpath("//*[@class='btn -big']"));
        pasteCreate.click();

        //new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXsCompleted());

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//h1")));

        String resultPageHeader = driver.findElement(By.xpath("//h1")).getText();
        String resultPageCode = driver.findElement(By.xpath("//*[@class='textarea']")).getText();
        String resultPageSyntax = driver.findElement(By.xpath("//div[@class='left']/a")).getText();

        Assert.assertTrue(resultPageHeader.contains("how to gain dominance among developers"), "Header is incorrect");
        Assert.assertTrue(resultPageCode.contains(newCode1 + '\n' + newCode2 + '\n' + newCode3), "Code is incorrect");
        Assert.assertTrue(resultPageSyntax.contains("Bash"), "Syntax is incorrect");
    }
        @AfterMethod (alwaysRun = true)
        public void browserClose(){
            driver.quit();
            driver=null;
        }
}
