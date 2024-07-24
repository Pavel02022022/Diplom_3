package org.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.practicum.pom.MainPage;

import static org.practicum.driver.WebDriverCreator.createWebDriver;

public class BurgerConstructrorTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    @Before
    public void setup() {
        driver = createWebDriver();
        RestAssured.baseURI = BASE_URL;
        driver.get(BASE_URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переходы к разделу «Булки»")
    @Description("Переходы к разделу «Булки» на главной странице, проверка видимости булки")
    public void clickBunsSelector() {
        MainPage mainPage = new MainPage(driver);
        mainPage.bunsSelectorClick();
        Assert.assertNotNull("Раздел 'Булки' не виден", mainPage.fluorescentBunShouldBeVisible());
    }

    @Test
    @DisplayName("Переходы к разделу «Соусы»")
    @Description("Переходы к разделу «Соусы» на главной странице, проверка видимости соуса")
    public void clickSaucesSelector() {
        MainPage mainPage = new MainPage(driver);
        mainPage.saucesSelectorClick();
        Assert.assertNotNull("Раздел 'Соусы' не виден", mainPage.spicyxSauceShouldBeVisible());
    }

    @Test
    @DisplayName("Переходы к разделу «Начинки»")
    @Description("Переходы к разделу «Начинки» на главной странице, , проверка видимости начиник")
    public void clickFillingsSelector() {
        MainPage mainPage = new MainPage(driver);
        mainPage.fillingsSelectorClick();
        Assert.assertNotNull("Раздел 'Начинки' не виден", mainPage.protostomiaFillingShouldBeVisible());
    }

}
