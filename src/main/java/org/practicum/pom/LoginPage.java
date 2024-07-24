package org.practicum.pom;



import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
    private WebDriver driver;

    // Поле enail
    private By emailAtLogin = By.xpath(".//input[@name='name']");
    // Поле Пароль
    private By passwordAtLogin = By.xpath(".//input[@name='Пароль']");
    // Кнопка Войти
    private By enterButton = By.xpath(".//button[contains(text(), 'Войти')]");

    // Форма для ввода логина и пароля
    private By loginForm = By.className("Auth_login__3hAey");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Fill email at login")
    public LoginPage fillEmailAtLogin(String text) throws InterruptedException {
        Thread.sleep(150);
        driver.findElement(emailAtLogin).sendKeys(text);
        return this;
    }
    @Step("Fill password at login")
    public LoginPage fillPasswordAtlogin(String text) {
        driver.findElement(passwordAtLogin).sendKeys(text);
        return this;
    }

    @Step("Click enter button")
    public LoginPage clickEnterButton() {
        waitForClicable(enterButton);
        driver.findElement(enterButton).click();
        return this;
    }

    @Step("Wait for clickable element")
    public LoginPage waitForClicable(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    // Ожидаем, что accessToken появится, если вход выполнен
    @Step("Get token from local storage")
    public String getTokenFromLocalStorage() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String accessToken = null;

        for (int i=0; i<3; i++){
            accessToken = (String) js.executeScript("return window.localStorage.getItem('accessToken');");
            if (accessToken==null){
                Thread.sleep(250);
            }
        }
        return accessToken;
    }

    // Ожидаем, что Local storage пуст, если выход выполнен
    @Step("Local storage must be empty after exit ")
    public boolean isLocalStorageEmpty() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long storageSize = null;

        for (int i=0; i<3; i++){
            storageSize = (Long) js.executeScript("return window.localStorage.length;");;
            if (storageSize>0){
                Thread.sleep(250);
            }
        }
        return storageSize == 0;
    }
    @Step("Login form is Displayed")
    public boolean loginFormIsDisplayed() {
        return driver.findElement(loginForm).isDisplayed();
    }

}
