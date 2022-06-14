package ru.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPageObject {

    //локатор поля ввода имени
    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private SelenideElement namePlaceHolder;

    //локатор поля ввода электронной почты
    @FindBy(how = How.XPATH, using = "//input[@value='']")
    private SelenideElement emailPlaceHolder;

    //локатор поля ввода пароля
    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private SelenideElement passwordPlaceHolder;

    //локатор кнопки регистрации
    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement registrationButton;

    //локатор кнопки логина
    @FindBy(how = How.XPATH, using = "//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginButton;

    //заполнение поля имени
    @Step("Заполнение поля имени")
    public void setName(String name){
        namePlaceHolder.setValue(name);
    }

    //заполнение поля электронной почты
    @Step("Заполнение поля электронной почты")
    public void setEmail(String email){
        emailPlaceHolder.setValue(email);
    }

    //заполнение поля пароля
    @Step("Заполнение поля пароля")
    public void setPassword(String password){
        passwordPlaceHolder.setValue(password);
    }

    //заполнение всех полей для регистрации
    @Step("Заполнение всех полей для регистрации")
    public void registration(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        registrationButtonClick();
    }

    //клик по кнопке регистрации
    @Step("Клик по кнопке регистрации")
    public void registrationButtonClick(){
        registrationButton.click();
    }

    //клик по кнопке логина
    @Step("Клик по кнопке логина")
    public void loginButtonClick(){
        loginButton.click();
    }

    //получение поля ввода имени
    @Step("Получение поля ввода имени")
    public SelenideElement getNamePlaceHolder(){
        return namePlaceHolder;
    }

}
