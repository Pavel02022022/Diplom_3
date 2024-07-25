package org.practicum.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    //Ссылка "Войти"
    private By loginLink = By.linkText("Войти");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public ForgotPasswordPage clickLogin(){
        driver.findElement(loginLink).click();
        return this;
    }
}
