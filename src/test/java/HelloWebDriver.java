import com.sun.source.util.SourcePositions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HelloWebDriver {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.epam.com/");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//*[@class='header-search__button header__icon']")));

        WebElement searchButton = driver.findElement(By.xpath("//*[@class='header-search__button header__icon']"));
        searchButton.click();

        WebElement searchInput = driver.findElement(By.xpath("//*[@id='new_form_search']"));
        searchInput.sendKeys("Software testing");

        WebElement findButton = driver.findElement(By.xpath("//*[@class='header-search__submit']"));
        findButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//*[@class='search-results__title-link']")));

        List<WebElement> searchResult = driver.findElements(By.xpath("//*[@class='search-results__title-link']"));
        System.out.println("Search results are: " + searchResult.size());

        driver.quit();

    }
}
