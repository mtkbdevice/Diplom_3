package ru.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePageObject {

    //локатор кнопки получения истории заказов
    @FindBy(how = How.XPATH, using = "//a[@class='Account_link__2ETsJ text text_type_main-medium text_color_inactive']")
    private SelenideElement storyOfOrdersButton;

    //локатор кнопки "Конструктор бургеров"
    @FindBy(how = How.XPATH, using = "//a[@class='AppHeader_header__link__3D_hX'][@href='/']")
    private SelenideElement construcotrButton;

    //локатор кнопки "Выход"
    @FindBy(how = How.XPATH, using = "//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']")
    private SelenideElement exitButton;

    //получение кнопки истории заказов
    @Step("Получение кнопки истории заказов")
    public SelenideElement getStoryOfOrdersButton(){
        return storyOfOrdersButton;
    }

    //клик по кнопке конструктора бургеров
    @Step("Клик по кнопке конструктора бургеров")
    public void contrusctorButtonClick(){
        construcotrButton.click();
    }

    //клик по кнопке выхода из аккаунта
    @Step("Клик по кнопке выхода из аккаунта")
    public void exitButtonClick(){
        exitButton.click();
    }

}
