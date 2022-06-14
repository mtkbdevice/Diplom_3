import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.model.SuccessfullUserLoginData;
import ru.stellarburgers.pageobjects.LoginPageObject;
import ru.stellarburgers.pageobjects.RegisterPageObject;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static io.restassured.RestAssured.given;

public class RegistrationTest {

    private String email;
    private String password;
    private String name;

    RegisterPageObject registerPageObject = open("https://stellarburgers.nomoreparties.site/register", RegisterPageObject.class);

    @Before
    public void maxSizeScreenConfiguration(){
        WebDriverRunner.getWebDriver().manage().window().maximize();
        email = RandomStringUtils.randomAlphabetic(8) + "@mail.ru";
        password = RandomStringUtils.randomAlphabetic(8);
        name = RandomStringUtils.randomAlphabetic(8);
    }

    @After
    public void deleteUser(){
        if(password.length() > 5){
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
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfullRegistrationTest(){
        registerPageObject.registration(name, email, password);
        LoginPageObject loginPageObject = page(LoginPageObject.class);
        loginPageObject.getLoginBlock().should(Condition.exist);
    }

    @Test
    @DisplayName("Регистрация с не правильной длинной пароля")
    public void registrationWrongLenghtPasswordTest(){
        password = RandomStringUtils.randomAlphabetic(5);
        registerPageObject.registration(name, email, RandomStringUtils.randomAlphabetic(5));
        registerPageObject.getNamePlaceHolder().should(Condition.exist);
    }
}
