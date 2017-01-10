import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tinkerbellissimo on 1/7/17.
 */
public class Homework9A {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "/Users/tinkerbellissimo/Downloads/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Homework9A() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click( );
        WebElement table = driver.findElement(By.className("dataTable"));
        List<WebElement> rows = table.findElements(By.className("row"));

        ArrayList<String> countries = new ArrayList<>();
        for(WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            WebElement name = cells.get(4);
            countries.add(name.findElement(By.tagName("a")).getText());
        }
        comparison(countries);
    }

    @After
    public void stop() {
        driver.quit( );
        driver = null;
    }

    public static boolean comparison(ArrayList<String> initialArray) {

        ArrayList<String> sortedArray = new ArrayList<>(initialArray);
        Collections.sort(sortedArray);

        for(int i=0; i<initialArray.size(); i++) {

            if(initialArray.get(i) == sortedArray.get(i)) {
                System.out.println("PASS");
                return true;
            } else {
                System.out.println("FAIL");
                return false;
            }
       }
        return false;
    }
}









