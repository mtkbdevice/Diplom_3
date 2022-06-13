import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stellarburgers.MainPageSelenide;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BurgerConstructorYandexBrowserTest {

    MainPageSelenide mainPageSelenide = page(MainPageSelenide.class);

    @Before
    public void maxSizeScreenConfiguration() {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\yandexdriver.exe");
        ChromeDriver driver = new ChromeDriver();
        setWebDriver(driver);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        MainPageSelenide mainPageSelenide = open("https://stellarburgers.nomoreparties.site/", MainPageSelenide.class);
    }

    @After
    public void yandexBrowserClose(){
        webdriver().driver().close();
    }

    @Test
    public void transferToBunsTest(){
        mainPageSelenide.selectСonstructorSection(2);
        mainPageSelenide.selectСonstructorSection(0);
        mainPageSelenide.getCratorBun().shouldBe(Condition.visible);
    }

    @Test
    public void transferToSoucesTest(){
        mainPageSelenide.selectСonstructorSection(1);
        mainPageSelenide.getTraditionalGalacticSauce().shouldBe(Condition.visible);
    }

    @Test
    public void transferToFillingsTest(){
        mainPageSelenide.selectСonstructorSection(2);
        mainPageSelenide.getLuminescentFillet().shouldBe(Condition.visible);
    }
}
