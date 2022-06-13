import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stellarburgers.*;

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
        RegisterPageSelenide registerPageSelenide = open("https://stellarburgers.nomoreparties.site/register", RegisterPageSelenide.class);
        registerPageSelenide.registration(name, email, password);
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
    public void loginThroughMainPageTest(){
        MainPageSelenide mainPageSelenide = open("https://stellarburgers.nomoreparties.site/", MainPageSelenide.class);
        mainPageSelenide.signInButtonClick();
        LoginPageSelenide loginPageSelenide = page(LoginPageSelenide.class);
        loginPageSelenide.login(email, password);
        mainPageSelenide.personalAccountButtonClick();
        ProfilePageSelenide profilePageSelenide = page(ProfilePageSelenide.class);
        profilePageSelenide.getStoryOfOrdersButton().should(Condition.exist);
    }

    @Test
    public void loginThroughPersonallAccountButtonTest(){
        MainPageSelenide mainPageSelenide = open("https://stellarburgers.nomoreparties.site/", MainPageSelenide.class);
        mainPageSelenide.personalAccountButtonClick();
        LoginPageSelenide loginPageSelenide = page(LoginPageSelenide.class);
        loginPageSelenide.login(email, password);
        mainPageSelenide.personalAccountButtonClick();
        ProfilePageSelenide profilePageSelenide = page(ProfilePageSelenide.class);
        profilePageSelenide.getStoryOfOrdersButton().should(Condition.exist);
    }

    @Test
    public void  loginThroughRegistrationPageTest(){
        RegisterPageSelenide registerPageSelenide = open("https://stellarburgers.nomoreparties.site/register", RegisterPageSelenide.class);
        registerPageSelenide.loginButtonClick();
        LoginPageSelenide loginPageSelenide = page(LoginPageSelenide.class);
        loginPageSelenide.login(email, password);
        MainPageSelenide mainPageSelenide = page(MainPageSelenide.class);
        mainPageSelenide.personalAccountButtonClick();
        ProfilePageSelenide profilePageSelenide = page(ProfilePageSelenide.class);
        profilePageSelenide.getStoryOfOrdersButton().should(Condition.exist);
    }

    @Test
    public void loginThroughForgotPasswordPageTest(){
        ForgotPasswordPageSelenide forgotPasswordPageSelenide = open("https://stellarburgers.nomoreparties.site/forgot-password", ForgotPasswordPageSelenide.class);
        forgotPasswordPageSelenide.loginButtonClick();
        LoginPageSelenide loginPageSelenide = page(LoginPageSelenide.class);
        loginPageSelenide.login(email, password);
        MainPageSelenide mainPageSelenide = page(MainPageSelenide.class);
        mainPageSelenide.personalAccountButtonClick();
        ProfilePageSelenide profilePageSelenide = page(ProfilePageSelenide.class);
        profilePageSelenide.getStoryOfOrdersButton().should(Condition.exist);
    }
}
