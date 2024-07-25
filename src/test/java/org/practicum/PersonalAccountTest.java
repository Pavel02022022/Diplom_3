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
import org.practicum.pom.LoginPage;
import org.practicum.pom.MainPage;
import org.practicum.pom.PersonalAccountPage;
import org.practicum.user.User;
import org.practicum.user.UserClient;

import static org.practicum.driver.WebDriverCreator.createWebDriver;
import static org.practicum.user.UserGenerator.randomUser;
import static org.utils.Utils.deleteUserAfterTestExecution;

public class PersonalAccountTest {
    private WebDriver driver;
    UserClient userClient;
    User user;

    private static  final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    ValidatableResponse response;

    @Before
    public void setup() {
        driver = createWebDriver();
        RestAssured.baseURI = BASE_URL;
        userClient = new UserClient();
        user = randomUser();
        driver.get(BASE_URL);
    }
    @After
    public void teardown() {
        driver.quit();
        deleteUserAfterTestExecution(response);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» без авторизации")
    @Description("Переход по клику на «Личный кабинет» незарегистрировнным пользователем")
    public void clickOnPersonalAccountWithoutAuth()  {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        response = userClient.create(user);

        mainPage.clickPersonalAccountButton();
        Assert.assertTrue("Переход по клику на «Личный кабинет» не выполнен", loginPage.loginFormIsDisplayed());
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» с авторизацией")
    @Description("Переход по клику на «Личный кабинет» зарегистрированным пользователем")
    public void clickOnPersonalAccountWithAuthExpect() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        response = userClient.create(user);
        mainPage.clickEnterButton();

        loginPage.fillEmailAtLogin(user.getEmail())
                .fillPasswordAtlogin(user.getPassword())
                .clickEnterButton();

        mainPage.clickPersonalAccountButton();
        Assert.assertTrue("Переход по клику на «Личный кабинет» не выполнен", personalAccountPage.accountFormIsDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета по клику на «Конструктор»")
    @Description("Переход из личного кабинета по клику на «Конструктор»")
    public void clickOnConstructorExpectMainPage() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        response = userClient.create(user);
        mainPage.clickEnterButton();

        loginPage.fillEmailAtLogin(user.getEmail())
                .fillPasswordAtlogin(user.getPassword())
                .clickEnterButton();

        mainPage.clickPersonalAccountButton();
        personalAccountPage.clickOnConstructor();

        Assert.assertTrue("Переход по клику на «Конструктор» не выполнен", mainPage.constructorAndLogoIsDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета по клику на логотип")
    @Description("Переход из личного кабинета по клику на логотип Stellar Burgers")
    public void clickOnLogoExpectMainPage() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        response = userClient.create(user);
        mainPage.clickEnterButton();

        loginPage.fillEmailAtLogin(user.getEmail())
                .fillPasswordAtlogin(user.getPassword())
                .clickEnterButton();

        mainPage.clickPersonalAccountButton();
        personalAccountPage.clickOnlogo();

        Assert.assertTrue("Переход по клику на на логотип Stellar Burgers не выполнен", mainPage.constructorAndLogoIsDisplayed());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Description("Выход из личного кабинета при клике по кнопке «Выйти»")
    public void clickOnExitButtonExpectExit() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        response = userClient.create(user);
        mainPage.clickEnterButton();

        loginPage.fillEmailAtLogin(user.getEmail())
                .fillPasswordAtlogin(user.getPassword())
                .clickEnterButton();

        mainPage.clickPersonalAccountButton();
        personalAccountPage.clickOnExitButton();
        Assert.assertTrue("Выход не выполнен, local storage не пуст", loginPage.isLocalStorageEmpty());
    }

}
