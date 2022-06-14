package ru.stellarburgers.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$$;

public class MainPageObject {

    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    private SelenideElement signInButton;

    //локатор кнопки "Личный кабинет"
    @FindBy(how = How.XPATH, using = "//a[@class='AppHeader_header__link__3D_hX'][@href='/account']")
    private SelenideElement personalAccountButton;

    //локатор заголовков раздела конструктора
    @FindBy(how = How.XPATH, using = "//h2[@class='text text_type_main-medium mb-6 mt-10']")
    private SelenideElement constructorSectionHeader;

    //локатор традиционного галактического соуса
    @FindBy(how = How.XPATH, using = "//img[@src='https://code.s3.yandex.net/react/code/sauce-03.png']")
    private SelenideElement traditionalGalacticSauce;

    //локатор "Филе люминисентного"
    @FindBy(how = How.XPATH, using = "//img[@src='https://code.s3.yandex.net/react/code/meat-03.png']")
    private SelenideElement luminescentFillet;

    //локатор "Краторная булка"
    @FindBy(how = How.XPATH, using = "//img[@src='https://code.s3.yandex.net/react/code/bun-02.png']")
    private SelenideElement cratorBun;

    //клик на кнопку "Войти в аккаунт"
    @Step("Клик на кнопку войти в аккаунт")
    public void signInButtonClick(){
        signInButton.click();
    }

    //клик на кнопку "Личный кабинет"
    @Step("Клик на кнопку личный кабинет")
    public void personalAccountButtonClick(){
        personalAccountButton.click();
    }

    //метод выбора раздела конструктора бургера
    @Step("Выбор раздела конструктора бургера")
    public void selectСonstructorSection(int index){
        ElementsCollection coll = $$(By.xpath("//span[@class='text text_type_main-default']"));
        for (int i = 0; i < coll.size(); i++){
            if(coll.indexOf(i) == index){
                coll.get(i).click();
                break;
            }
        }
    }

    //метод получения текста заголовка раздела конструктора
    @Step("Получение текста заголовка раздела конструктора")
    public String constructorHeaderGetText(){
        return constructorSectionHeader.getText();
    }

    //получение элемента традиционного галактического соуса
    @Step("Получение элемента традиционного галактического соуса")
    public SelenideElement getTraditionalGalacticSauce(){
        return traditionalGalacticSauce;
    }

    //получение элемента люминисцентного филе
    @Step("Получение элемента люминисцентного филе")
    public SelenideElement getLuminescentFillet(){
        return luminescentFillet;
    }

    //получение элемента краторной булки
    @Step("Получение элемента краторной булки")
    public SelenideElement getCratorBun(){
        return cratorBun;
    }
}
