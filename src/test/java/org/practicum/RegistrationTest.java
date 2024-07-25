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
import org.practicum.pom.RegistrationPage;
import org.practicum.user.User;
import org.practicum.user.UserClient;

import static org.practicum.driver.WebDriverCreator.createWebDriver;
import static org.practicum.user.UserGenerator.randomUser;
import static org.practicum.user.UserGenerator.userWithShortPassword5;
import static org.utils.Utils.deleteUserAfterTestExecution;

public class RegistrationTest {
    private WebDriver driver;
    private static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";
    private static  final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    ValidatableResponse response;

    @Before
    public void setup() {
        driver = createWebDriver();
        RestAssured.baseURI = BASE_URL;
        driver.get(REGISTER_URL);

    }
    @After
    public void teardown() {
        driver.quit();
        deleteUserAfterTestExecution(response);
    }

    @DisplayName("Успешная регистрация")
    @Description("Успешная регистрация с проверкой авторизации")
    @Test
    public void registrationExpectCreated() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        User user = randomUser();
        UserClient userClient = new UserClient();

        registrationPage.fillNameAtRegistration(user.getName())
                .fillEmailAtRegistration(user.getEmail())
                .fillPasswordAtRegistration(user.getPassword())
                .clickRegistrationButton();

        // Проверяем, что пользователь создан и им можно авторизоваться
        response = userClient.login(user.getEmail(), user.getPassword());
        response.statusCode(200);
    }

    @DisplayName("Ошибка для некорректного пароля")
    @Description("Ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
    @Test
    public void registrationWithShortPasswordExpectErrorMessage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        User user = userWithShortPassword5();

        registrationPage.fillNameAtRegistration(user.getName())
                .fillEmailAtRegistration(user.getEmail())
                .fillPasswordAtRegistration(user.getPassword())
                .clickRegistrationButton();

        // Проверяем  ошибку для некорректного пароля.
        Assert.assertEquals("Некорректный пароль", registrationPage.getWrongPasswordText());
    }

}
