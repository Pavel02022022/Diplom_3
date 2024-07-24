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
    private By сonstructor= By.xpath(".//p[text()='Конструктор']");
    // Логотип Stellar Burgers
    private By logo = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    // Панель выбора булок
    private By bunsSelector = By.xpath(".//span[text()='Булки']//parent::div");
    // Первая булка
    private By fluorescentBun = By.xpath(".//p[contains(text(), 'Флюоресцентная булка R2-D3')]");
    // Первый соус
    private By spicyxSauce = By.xpath(".//p[contains(text(), 'Соус Spicy-X')]");
    // Первая начинка
    private By protostomiaFilling = By.xpath(".//p[contains(text(), 'Мясо бессмертных моллюсков Protostomia')]");
    // Панель выбора соусов
    private By saucesSelector = By.xpath(".//span[text()='Соусы']//parent::div");
    // Панель выбора начинок
    private By fillingsSelector = By.xpath(".//span[text()='Начинки']//parent::div");
    // Блокитрующий элемент
    private By block = By.cssSelector("div[style='display: flex;']");
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
        return (driver.findElement(сonstructor).isDisplayed() && driver.findElement(logo).isDisplayed());

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
        driver.findElement(block).click();
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
        driver.findElement(block).click();
        driver.findElement(fillingsSelector).click();
    }
    @Step("Fluorescent bun should be visible")
    public boolean fluorescentBunShouldBeVisible(){
        waitForElement(fluorescentBun);
        return driver.findElement(fluorescentBun).isDisplayed();
    }
    @Step("Spicy-X  Sauseshould be visible")
    public boolean spicyxSauceShouldBeVisible(){
        waitForElement(spicyxSauce);
        return driver.findElement(spicyxSauce).isDisplayed();
    }
    @Step("Protostomia filling should be visible")
    public boolean protostomiaFillingShouldBeVisible(){
        waitForElement(personalAccountButton);
        return driver.findElement(protostomiaFilling).isEnabled();
    }







}
