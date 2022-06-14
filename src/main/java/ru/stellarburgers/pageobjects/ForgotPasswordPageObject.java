package ru.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPageObject {

    //локатор кнопки логина
    @FindBy(how = How.XPATH, using = "//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginButton;

    //клик по кнопке логина
    @Step("Клик по логина")
    public void loginButtonClick(){
        loginButton.click();
    }

}
