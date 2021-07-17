package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.pastebinResultPagePO;

public class BringItOnTest {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test (description = "My first test")
    public void checkCreatingCodeInPastebinService() {

        String newCode1 = "git config --global user.name  'New Sheriff in Town'";
        String newCode2 = "git reset $(git commit-tree HEAD^{tree} -m 'Legacy code')";
        String newCode3 = "git push origin master --force";

        String expectedResult = new pastebinResultPagePO(driver)
                .openPage()
                .pasteInput(newCode1 + '\n' + newCode2 + '\n' + newCode3)
                .syntaxHighlighting()
                .syntaxHighlightingBash()
                .pasteExpiration()
                .pasteExpiration10Min()
                .pasteName("how to gain dominance among developers")
                .pasteCreate()
                .checkResultPageHeader()
                .checkResultPageCode()
                .checkResultPageSyntax();


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
