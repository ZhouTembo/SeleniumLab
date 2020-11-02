package SeleniumTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {
    private WebDriver driver;

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver","C:/Users/Zhou/selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.wuxiaworld.com/");
    }

//    1
    @Test
    void test(){
        assertEquals("https://www.wuxiaworld.com/", driver.getCurrentUrl());
    }

//    2
    @Test
    void testSeries(){
        List<WebElement> anchors = driver.findElements(By.tagName("a"))  ;
        List<String> contents = new ArrayList<>();
        for(WebElement anchor: anchors){
            contents.add(anchor.getText());
        }
        assertThat(contents, hasItems("SERIES"));

    }

//    3
    @Test
    void testTotalInputAnchors(){
        List<WebElement> anchors = driver.findElements(By.tagName("input"));
        assertEquals(1, anchors.size());
    }

//    4
    @Test
    void testPrintH4Headers(){
        List<WebElement> headers = driver.findElements(By.tagName("P"));
        for (WebElement header :headers){
            System.out.println(header.getText());

        }



    }

//5
    @Test
    void testSeriesClick(){
        WebElement series = driver.findElement(By.cssSelector(".dropdown:nth-child(2) > a"));
        series.click();
        assertEquals("https://www.wuxiaworld.com/novels",driver.getCurrentUrl());
    }


//    6
    @Test
    void testRefresh(){
        driver.navigate().refresh();
        assertEquals("Home - WuxiaWorld", driver.getTitle());
    }

//    7
    @Test
    void testKoreanClick(){
        driver.navigate().to("https://www.wuxiaworld.com/novels");
        WebElement series = driver.findElement(By.cssSelector(".dropdown:nth-child(2) > a"));
        series.click();
        WebElement korean = driver.findElement(By.cssSelector("#react-tabs-6 > a"));
        korean.click();
    }

//    8
    @Test
    void testBookmarkPage(){
        WebElement bookmark = driver.findElement(By.xpath("//a[contains(text(),'Bookmarks')]"));
        bookmark.click();
        assertEquals("Log in - WuxiaWorld", driver.getTitle());

    }

    @Test
    void testEmailOnBookmarkPage(){
        driver.navigate().to("https://www.wuxiaworld.com/account/login?ReturnUrl=%2Fprofile%2Fbookmarks");
        WebElement email = driver.findElement(By.id("Email"));
        System.out.println(email.getText());

    }



    @AfterEach
    void tearDown(){
        driver.close();

    }

}
