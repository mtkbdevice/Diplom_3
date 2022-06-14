import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.pageobjects.MainPageObject;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BurgerConstructorTest {
    MainPageObject mainPageObject = open("https://stellarburgers.nomoreparties.site/", MainPageObject.class);

    @Before
    public void maxSizeScreenConfiguration() {
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @Test
    @DisplayName("Переход в раздел булок в конструкторе")
    public void transferToBunsTest(){
        mainPageObject.selectСonstructorSection(2);
        mainPageObject.selectСonstructorSection(0);
        mainPageObject.getCratorBun().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Переход в раздел соусов в конструкторе")
    public void transferToSoucesTest(){
        mainPageObject.selectСonstructorSection(1);
        mainPageObject.getTraditionalGalacticSauce().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Переход в раздел начинок в конструкторе")
    public void transferToFillingsTest(){
        mainPageObject.selectСonstructorSection(2);
        mainPageObject.getLuminescentFillet().shouldBe(Condition.visible);
    }
}
