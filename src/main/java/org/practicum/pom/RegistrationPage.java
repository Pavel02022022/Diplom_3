package org.practicum.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;
    // Поле Имя
    private By nameAtRegistration = By.xpath(".//label[text()='Имя']/parent::div//input");
    // Поле email
    private By emailAtRegistration = By.xpath(".//label[text()='Email']/parent::div//input");
    // Поле пароль
    private By passwordAtRegistration = By.xpath("//input[@type='password']");
    // Кнопка Зарегистрироваться
    private By registrationButton = By.xpath(".//button[contains(text(), 'Зарегистрироваться')]");
    // Ошибка при вводе пароля
    private By inputError = By.className("input__error");
    // Ссылка Войти
    private By loginLink = By.linkText("Войти");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill name at registration")
    public RegistrationPage fillNameAtRegistration(String text){
        driver.findElement(nameAtRegistration).sendKeys(text);
        return this;
    }

    @Step("Fill email at registration")
    public RegistrationPage fillEmailAtRegistration(String text){
        driver.findElement(emailAtRegistration).sendKeys(text);
        return this;
    }

    @Step("Fill password at registration")
    public RegistrationPage fillPasswordAtRegistration(String text){
        driver.findElement(passwordAtRegistration).sendKeys(text);
        return this;
    }

    @Step("Click registration button")
    public RegistrationPage clickRegistrationButton(){
        driver.findElement(registrationButton).click();
        return  this;
    }

    // Когда введен некорректный пароль при регистрации
    @Step("Get wrong password text")
    public String getWrongPasswordText(){
        return driver.findElement(inputError).getText();
    }

    @Step("Click login")
    public RegistrationPage clickLogin(){
        driver.findElement(loginLink).click();
        return this;
    }

}
