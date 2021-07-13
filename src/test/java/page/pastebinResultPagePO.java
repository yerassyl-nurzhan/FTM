package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pastebinResultPagePO {

    public final String splitRegex = "\\s";
    public WebDriver driver;
    public String pageTerm;

    public String defaultLocator = "";
    public String containsPart = " and contains(., '%s')";

    @FindBy(xpath = "//h1")
    public String resultPageHeader;

    @FindBy(xpath = "//*[@class='textarea']")
    public String resultPageCode;

    @FindBy(xpath = "//div[@class='left']/a")
    public String resultPageSyntax;

    public pastebinResultPagePO(WebDriver driver, String pageTerm) {
        this.pageTerm = pageTerm;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String checkResultPageHeader(){
        System.out.println("");
        return resultPageHeader;
    }

//    String resultPageHeader = driver.findElement(By.xpath("//h1")).getText();
//    String resultPageCode = driver.findElement(By.xpath("//*[@class='textarea']")).getText();
//    String resultPageSyntax = driver.findElement(By.xpath("//div[@class='left']/a")).getText();


    private String buildLocatorForPage() {
        String partWithPageTerm = "";
        String[] terms = pageTerm.split(splitRegex);
        for (String term : terms) {
            partWithPageTerm += String.format(containsPart, term);
        }
        String locatorForPage = String.format(defaultLocator, partWithPageTerm);
        System.out.println("DEBUG: Final locator with page terms: " + locatorForPage);
        return  locatorForPage;
    }
}
