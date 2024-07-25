package org.practicum.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {


    private WebDriver driver;
    // Кнопка конструктор
    private By costructor = By.xpath(".//p[contains(text(), 'Конструктор')]");
    // Логотип Stellar Burgers
    private By logo = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    // Кнопка Выход
    private By exitButton = By.xpath(".//button[contains(text(), 'Выход')]");
    // Профиль
    private By profile = By.xpath(".//a[@href = '/account/profile']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on constructor")
    public PersonalAccountPage clickOnConstructor() {
        driver.findElement(costructor).click();
        return this;
    }
    @Step("Click on logo")
    public PersonalAccountPage clickOnlogo() {
        driver.findElement(logo).click();
        return this;
    }

    @Step("Click on exit button")
    public PersonalAccountPage clickOnExitButton(){
        waitForClicable(exitButton);
        driver.findElement(exitButton).click();
        return this;
    }

    @Step("Wait for clickable")
    public PersonalAccountPage waitForClicable(By element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    @Step("Profile form is displayed")
    public boolean accountFormIsDisplayed() {
        waitForClicable(profile);
        return driver.findElement(profile).isDisplayed();
    }
}


