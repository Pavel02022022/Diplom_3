package org.utils;


import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.practicum.user.UserClient;

public class Utils {

    @Step("Get token at user registraton")
    public static String getTokenAtUserCreation(ValidatableResponse response){
        return response.extract().path("accessToken");
    }
    @Step("Delete user after test execution")
    public static void deleteUserAfterTestExecution(ValidatableResponse response){
        UserClient userClient = new UserClient();
        if (response!=null&&response.extract().statusCode()==200) {
            userClient.delete(getTokenAtUserCreation(response));
            System.out.println("Удаляем пользователя " +  response.extract().path("user.name") + " после выполнения теста.");
        }
    }
}
