package ru.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPageSelenide {

    //локатор кнопки логина
    @FindBy(how = How.XPATH, using = "//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginButton;

    //клик по кнопке логина
    public void loginButtonClick(){
        loginButton.click();
    }

}
