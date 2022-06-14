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
import ru.stellarburgers.pageobjects.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static io.restassured.RestAssured.given;

public class LoginYandexBrowserTest {
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
    @DisplayName("Логин через главную страницу")
    public void loginThroughMainPageTest(){
        MainPageObject mainPageObject = open("https://stellarburgers.nomoreparties.site/", MainPageObject.class);
        mainPageObject.signInButtonClick();
        LoginPageObject loginPageObject = page(LoginPageObject.class);
        loginPageObject.login(email, password);
        mainPageObject.personalAccountButtonClick();
        ProfilePageObject profilePageObject = page(ProfilePageObject.class);
        profilePageObject.getStoryOfOrdersButton().should(Condition.exist);
    }

    @Test
    @DisplayName("Логин через кнопку личный кабинет")
    public void loginThroughPersonallAccountButtonTest(){
        MainPageObject mainPageObject = open("https://stellarburgers.nomoreparties.site/", MainPageObject.class);
        mainPageObject.personalAccountButtonClick();
        LoginPageObject loginPageObject = page(LoginPageObject.class);
        loginPageObject.login(email, password);
        mainPageObject.personalAccountButtonClick();
        ProfilePageObject profilePageObject = page(ProfilePageObject.class);
        profilePageObject.getStoryOfOrdersButton().should(Condition.exist);
    }

    @Test
    @DisplayName("Логин из окна регистрации")
    public void  loginThroughRegistrationPageTest(){
        RegisterPageObject registerPageObject = open("https://stellarburgers.nomoreparties.site/register", RegisterPageObject.class);
        registerPageObject.loginButtonClick();
        LoginPageObject loginPageObject = page(LoginPageObject.class);
        loginPageObject.login(email, password);
        MainPageObject mainPageObject = page(MainPageObject.class);
        mainPageObject.personalAccountButtonClick();
        ProfilePageObject profilePageObject = page(ProfilePageObject.class);
        profilePageObject.getStoryOfOrdersButton().should(Condition.exist);
    }

    @Test
    @DisplayName("Логин через окно восстановления пароля")
    public void loginThroughForgotPasswordPageTest(){
        ForgotPasswordPageObject forgotPasswordPageSelenide = open("https://stellarburgers.nomoreparties.site/forgot-password", ForgotPasswordPageObject.class);
        forgotPasswordPageSelenide.loginButtonClick();
        LoginPageObject loginPageObject = page(LoginPageObject.class);
        loginPageObject.login(email, password);
        MainPageObject mainPageObject = page(MainPageObject.class);
        mainPageObject.personalAccountButtonClick();
        ProfilePageObject profilePageObject = page(ProfilePageObject.class);
        profilePageObject.getStoryOfOrdersButton().should(Condition.exist);
    }
}
