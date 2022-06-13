package ru.stellarburgers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$$;

public class MainPageSelenide {

    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    private SelenideElement signInButton;

    //локатор кнопки "Личный кабинет"
    @FindBy(how = How.XPATH, using = "//a[@class='AppHeader_header__link__3D_hX'][@href='/account']")
    private SelenideElement personalAccountButton;

    //клик на кнопку "Войти в аккаунт"
    public void signInButtonClick(){
        signInButton.click();
    }

    //локатор заголовков раздела конструктора
    @FindBy(how = How.XPATH, using = "//h2[@class='text text_type_main-medium mb-6 mt-10']")
    public SelenideElement constructorSectionHeader;

    //локатор традиционного галактического соуса
    @FindBy(how = How.XPATH, using = "//img[@src='https://code.s3.yandex.net/react/code/sauce-03.png']")
    public SelenideElement traditionalGalacticSauce;

    //локатор "Филе люминисентного"
    @FindBy(how = How.XPATH, using = "//img[@src='https://code.s3.yandex.net/react/code/meat-03.png']")
    public SelenideElement luminescentFillet;

    //локатор "Краторная булка"
    @FindBy(how = How.XPATH, using = "//img[@src='https://code.s3.yandex.net/react/code/bun-02.png']")
    public SelenideElement cratorBun;

    //клик на кнопку "Личный кабинет"
    public void personalAccountButtonClick(){
        personalAccountButton.click();
    }

    //метод выбора раздела конструктора бургера
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
    public String constructorHeaderGetText(){
        return constructorSectionHeader.getText();
    }

    //получение элемента традиционного галактического соуса
    public SelenideElement getTraditionalGalacticSauce(){
        return traditionalGalacticSauce;
    }

    //получение элемента люминисцентного филе
    public SelenideElement getLuminescentFillet(){
        return luminescentFillet;
    }

    //получение элемента краторной булки
    public SelenideElement getCratorBun(){
        return cratorBun;
    }
}
