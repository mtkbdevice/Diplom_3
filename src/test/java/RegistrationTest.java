import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.LoginPageSelenide;
import ru.stellarburgers.RegisterPageSelenide;
import ru.stellarburgers.SuccessfullUserLoginData;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static io.restassured.RestAssured.given;

public class RegistrationTest {

    private String email;
    private String password;
    private String name;

    RegisterPageSelenide registerPageSelenide = open("https://stellarburgers.nomoreparties.site/register", RegisterPageSelenide.class);

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
    public void successfullRegistrationTest(){
        registerPageSelenide.registration(name, email, password);
        LoginPageSelenide loginPageSelenide = page(LoginPageSelenide.class);
        loginPageSelenide.getLoginBlock().should(Condition.exist);
    }

    @Test
    public void registrationWrongLenghtPasswordTest(){
        password = RandomStringUtils.randomAlphabetic(5);
        registerPageSelenide.registration(name, email, RandomStringUtils.randomAlphabetic(5));
        registerPageSelenide.getNamePlaceHolder().should(Condition.exist);
    }
}
