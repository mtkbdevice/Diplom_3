package ru.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageSelenide {

    //локатор поля ввода email
    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private SelenideElement emailHolder;

    //локатор поля ввода пароля
    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private SelenideElement passwordHolder;

    //локатор кнопки входа в аккаунт
    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement loginButton;

    //локатор с блоком полей для логина
    @FindBy(how = How.XPATH, using = "//div[@class='Auth_login__3hAey']")
    private SelenideElement loginBlock;

    //заполнение поля email
    public void setEmail(String email){
        emailHolder.setValue(email);
    }

    //заполнение поля password
    public void setPassword(String password){
        passwordHolder.setValue(password);
    }

    //клик по кнопке логина
    public void loginButtonClick(){
        loginButton.click();
    }

    //метод логина
    public void login(String email, String password){
        setEmail(email);
        setPassword(password);
        loginButtonClick();
    }

    //метод получение  кнопки логина
    public SelenideElement getLoginButton(){
        return loginButton;
    }

    //метод получения блока с полями для логина
    public SelenideElement getLoginBlock(){
        return loginBlock;
    }
}
