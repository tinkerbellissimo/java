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
 * Created by tinkerbellissimo on 1/9/17.
 */
public class Homework9_2 {

        private WebDriver driver;
        private WebDriverWait wait;

        @Before
        public void start() {
            System.setProperty("webdriver.chrome.driver", "/Users/tinkerbellissimo/Downloads/chromedriver");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        }

        @Test
        public void Homework9_2() {
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click( );
            WebElement table = driver.findElement(By.className("dataTable"));
            List<WebElement> rows = table.findElements(By.className("row"));

            ArrayList<String> links = new ArrayList<>( );
            for(WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                WebElement name = cells.get(2);
                links.add(name.findElement(By.tagName("a")).getAttribute("href"));
            }

            for(String link : links) {
                driver.get(link);
                WebElement zonesTable = driver.findElement(By.id("table-zones"));
                List<WebElement> zoneRows = zonesTable.findElements(By.tagName("tr"));

                ArrayList<String> zones = new ArrayList<>();
                for (int i=1; i<zoneRows.size()-1; i++) {
                    WebElement zoneRow = zoneRows.get(i);
                    List<WebElement> zoneCells = zoneRow.findElements(By.tagName("td"));
                    zones.add(zoneCells.get(2).getText());
                    WebElement zoneValue=zoneCells.get(2).findElement(By.cssSelector("[selected=\"selected\"]"));
                    zones.add(zoneValue.getText());
                }
                comparison(zones);
            }
        }

        @After
        public void stop() {
            driver.quit( );
            driver = null;
        }

        public static boolean comparison(ArrayList<String> initialArray) {

            ArrayList<String> sortedArray = new ArrayList<>(initialArray);
            Collections.sort(sortedArray);

            for (int i=0; i<initialArray.size(); i++) {

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











