package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DragNDropTest {
    WebDriver driver;
    String username;
    String password;
    String url;
    String testUri;
    @BeforeClass
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\Hilel\\chromedriverv2\\chromedriver.exe");
        driver = new ChromeDriver();
        url = "https://jira.hillel.it";
        username = "blablabla";
        password = "blablabla221";
        testUri = "/secure/RapidBoard.jspa?rapidView=888&projectKey=SU";
        driver.get(url);
    }
    @Test
    public void dragNDropTest() throws InterruptedException {
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);
        driver.get(url + testUri);
        Thread.sleep(3000);
        Actions action = new Actions(driver);
        action.dragAndDrop(driver.findElement(By.cssSelector("div[data-issue-id='78894']")), driver.findElement(By.cssSelector("li[data-column-id='4280']"))).build().perform();
        List<WebElement> elements = driver.findElements(By.className("li[data-column-id='4280']"));
        for (WebElement el: elements) {
            Assert.assertTrue(el.getText().toLowerCase().contains("SU-1"));
        }
    }
}
