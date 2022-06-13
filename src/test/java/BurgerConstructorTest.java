import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stellarburgers.MainPageSelenide;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BurgerConstructorTest {
    MainPageSelenide mainPageSelenide = open("https://stellarburgers.nomoreparties.site/", MainPageSelenide.class);

    @Before
    public void maxSizeScreenConfiguration() {
        WebDriverRunner.getWebDriver().manage().window().maximize();
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
