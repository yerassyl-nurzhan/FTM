package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.CustomConditions;

public class pastebinHomePagePO extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    @FindBy(xpath = "//*[@class='textarea -form js-paste-code']")
    private  WebElement pasteInput;

    @FindBy(xpath = "//span[@id='select2-postform-format-container']")
    private  WebElement syntaxHighlighting;

    @FindBy(xpath = "//li[text()='Bash'][@class='select2-results__option']")
    private  WebElement syntaxHighlightingBash;

    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private  WebElement pasteExpiration;

    @FindBy(xpath = "//li[text()='10 Minutes'][@class='select2-results__option']")
    private  WebElement pasteExpiration10Min;

    @FindBy(xpath = "//*[@id='postform-name']")
    private  WebElement pasteName;

    @FindBy(xpath = "//*[@class='btn -big']")
    private  WebElement pasteCreate;

//    public pastebinHomePagePO(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }

//    public pastebinHomePagePO(WebDriver driver){super(driver);}

    public AbstractPage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(CustomConditions.jQueryAJAXsCompleted());
        return this;
    }

    public pastebinResultPagePO pasteForTerms (String term){
        pasteInput.sendKeys(term);
        syntaxHighlighting.click();
        syntaxHighlightingBash.click();
        pasteExpiration.click();
        pasteExpiration10Min.click();
        pasteName.sendKeys(term);
        pasteCreate.click();
        return new pastebinResultPagePO(driver, term);
    }

}
