import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.model.SuccessfullUserLoginData;
import ru.stellarburgers.pageobjects.LoginPageObject;
import ru.stellarburgers.pageobjects.MainPageObject;
import ru.stellarburgers.pageobjects.ProfilePageObject;
import ru.stellarburgers.pageobjects.RegisterPageObject;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static io.restassured.RestAssured.given;

public class PersonalAccountYandexBrowserTest {
    private String email;
    private String password;
    private String name;


    @Before
    public void maxSizeScreenConfiguration(){
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\yandexdriver.exe");
        ChromeDriver driver = new ChromeDriver();
        setWebDriver(driver);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        email = RandomStringUtils.randomAlphabetic(8) + "@mail.ru";
        password = RandomStringUtils.randomAlphabetic(8);
        name = RandomStringUtils.randomAlphabetic(8);
        RegisterPageObject registerPageObject = open("https://stellarburgers.nomoreparties.site/register", RegisterPageObject.class);
        registerPageObject.registration(name, email, password);
    }

    @After
    public void deleteUser(){
        webdriver().driver().close();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"email\":\"" + email + "\","
                        + "\"password\":\"" + password + "\"}")
                .when()
                .post("/api/auth/login");

        String token = response.body().as(SuccessfullUserLoginData.class).getAccessToken();

        given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete("/api/auth/user/" + token);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void transitionToPersonallAccountTest(){
        MainPageObject mainPageObject = open("https://stellarburgers.nomoreparties.site/", MainPageObject.class);
        mainPageObject.signInButtonClick();
        LoginPageObject loginPageObject = page(LoginPageObject.class);
        loginPageObject.login(email, password);
        mainPageObject.personalAccountButtonClick();
        ProfilePageObject profilePageObject = page(ProfilePageObject.class);
        profilePageObject.getStoryOfOrdersButton().should(Condition.exist);
    }

    @Test
    @DisplayName("Переход к конструктору бургеров из личного кабинета")
    public void transitionToBurgerConstructorTest(){
        MainPageObject mainPageObject = open("https://stellarburgers.nomoreparties.site/", MainPageObject.class);
        mainPageObject.signInButtonClick();
        LoginPageObject loginPageObject = page(LoginPageObject.class);
        loginPageObject.login(email, password);
        mainPageObject.personalAccountButtonClick();
        ProfilePageObject profilePageObject = page(ProfilePageObject.class);
        profilePageObject.contrusctorButtonClick();
        mainPageObject.getCratorBun().should(Condition.exist);
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void exitFromPersonallAccountTest(){
        MainPageObject mainPageObject = open("https://stellarburgers.nomoreparties.site/", MainPageObject.class);
        mainPageObject.signInButtonClick();
        LoginPageObject loginPageObject = page(LoginPageObject.class);
        loginPageObject.login(email, password);
        mainPageObject.personalAccountButtonClick();
        ProfilePageObject profilePageObject = page(ProfilePageObject.class);
        profilePageObject.exitButtonClick();
        loginPageObject.getLoginBlock().should(Condition.exist);
    }
}
