package org.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.practicum.pom.ForgotPasswordPage;
import org.practicum.pom.LoginPage;
import org.practicum.pom.MainPage;
import org.practicum.pom.RegistrationPage;
import org.practicum.user.User;
import org.practicum.user.UserClient;

import static org.practicum.driver.WebDriverCreator.createWebDriver;
import static org.practicum.user.UserGenerator.randomUser;
import static org.utils.Utils.deleteUserAfterTestExecution;

public class LoginTest {
    private WebDriver driver;
    UserClient userClient;
    User user;
    private static  final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static  final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/register";
    private static  final String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    ValidatableResponse response;

    @Before
    public void setup() {
        driver = createWebDriver();
        RestAssured.baseURI = BASE_URL;
        userClient = new UserClient();
        user = randomUser();
    }

    @After
    public void teardown() {
        driver.quit();
        deleteUserAfterTestExecution(response);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт»")
    @Description("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void enterAtMainPage() throws InterruptedException {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        response = userClient.create(user);

        mainPage.clickEnterButton();
        loginPage.fillEmailAtLogin(user.getEmail())
                 .fillPasswordAtlogin(user.getPassword())
                 .clickEnterButton();

        // Если вход выполнен, то будет получен accessToken
        String accessToken = loginPage.getTokenFromLocalStorage();
        Assert.assertNotNull("Вход не выполнен, accessToken не обнаружен", accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Вход через кнопку «Личный кабинет» на главной странице")
    public void enterAtPersonalAccount() throws InterruptedException {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        response = userClient.create(user);

        mainPage.clickPersonalAccountButton();
        loginPage.fillEmailAtLogin(user.getEmail())
                .fillPasswordAtlogin(user.getPassword())
                .clickEnterButton();

        // Если вход выполнен, то будет получен accessToken
        String accessToken = loginPage.getTokenFromLocalStorage();
        Assert.assertNotNull("Вход не выполнен, accessToken не обнаружен", accessToken);
    }

    @Test
    @DisplayName("Bход через ссылку в форме регистрации")
    @Description("Bход через ссылку «Войти» в форме регистрации")
    public void enterAtRegistrationForm() throws InterruptedException {
        driver.get(REGISTRATION_URL);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        response = userClient.create(user);

        registrationPage.clickLogin();
        loginPage.fillEmailAtLogin(user.getEmail())
                .fillPasswordAtlogin(user.getPassword())
                .clickEnterButton();

        // Если вход выполнен, то будет получен accessToken
        String accessToken = loginPage.getTokenFromLocalStorage();
        Assert.assertNotNull("Вход не выполнен, accessToken не обнаружен", accessToken);
    }

    @Test
    //@DisplayName("Логин пользователя c неверным паролем")
    @DisplayName("Bход через ссылку в форме восстановления пароля")
    @Description("Bход через ссылку «Восстановить пароль» в форме восстановления пароля")
    public void enterAtForgotPasswordForm() throws InterruptedException {
        driver.get(FORGOT_PASSWORD_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        response = userClient.create(user);

        forgotPasswordPage.clickLogin();
        loginPage.fillEmailAtLogin(user.getEmail())
                .fillPasswordAtlogin(user.getPassword())
                .clickEnterButton();

        // Если вход выполнен, то будет получен accessToken
        String accessToken = loginPage.getTokenFromLocalStorage();
        Assert.assertNotNull("Вход не выполнен, accessToken не обнаружен", accessToken);
    }

}
