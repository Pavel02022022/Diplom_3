package org.practicum.pom;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    //Кнопка Войти в аккаунт
    private By enterButton = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    //Кнопка Личный кабинет
    private  By personalAccountButton = By.xpath(".//p[contains(text(), 'Личный Кабинет')]");
    //Кнопка Конструктор
    private By constructor= By.xpath(".//p[text()='Конструктор']");
    // Логотип Stellar Burgers
    private By logo = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    // Панель выбора булок
    private By bunsSelector = By.xpath(".//span[text()='Булки']//parent::div");
    // Панель выбора соусов
    private By saucesSelector = By.xpath(".//span[text()='Соусы']//parent::div");
    // Панель выбора начинок
    private By fillingsSelector = By.xpath(".//span[text()='Начинки']//parent::div");
    // Активная вкладка в конструкторе
    private final By activeSelector = By.xpath(".//div[contains(@class, 'current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click enter button")
    public MainPage clickEnterButton(){
        waitForElement(enterButton);
        waitForClicable(enterButton);
        driver.findElement(enterButton).click();
        return this;
    }
    @Step("Wait for clicable")
    public MainPage waitForClicable(By element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    @Step("Constructor and logo is displayed")
    public boolean constructorAndLogoIsDisplayed(){
        return (driver.findElement(constructor).isDisplayed() && driver.findElement(logo).isDisplayed());

    }
    @Step("Wait for element")
    public MainPage waitForElement(By element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(element)));
        return this;
    }
    @Step("Click personal account button")
    public MainPage clickPersonalAccountButton() {
        waitForElement(personalAccountButton);
        driver.findElement(personalAccountButton).click();
        return this;
    }

    @Step("Buns selector click")
    public MainPage bunsSelectorClick() {
        driver.findElement(bunsSelector).click();
        return this;
    }

    @Step("Sauces selector click")
    public MainPage saucesSelectorClick() {
        driver.findElement(saucesSelector).click();
        return this;
    }
    @Step("Fillings selector click")
    public void fillingsSelectorClick() {
        driver.findElement(fillingsSelector).click();
    }
    @Step("Buns selected")
    public boolean isBunsSelected() {
        return driver.findElement(bunsSelector).getText()
                .equals(driver.findElement(activeSelector).getText());
    }
    @Step("Buns selected")
    public boolean isSaucesSelected() {
        return driver.findElement(saucesSelector).getText()
                .equals(driver.findElement(activeSelector).getText());
    }
    @Step("Buns selected")
    public boolean isFillingSelected() {
        return driver.findElement(fillingsSelector).getText()
                .equals(driver.findElement(activeSelector).getText());
    }
}
