package org.example.pages.apiClient;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static java.lang.Math.log;
import static org.hamcrest.Matchers.*;

public class CreateUser extends ApiClient{
    private String email;
    private String password;
    private String name;

    public CreateUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Step("Успешный запрос регистрации пользователя")
    public void sendCreateUserSuccess(){
        Response response =  given(getAuthRequestSpec())
                .body(this)
                .post("/auth/register")
                .then()
                .body("success", equalTo(true))
                .body("user.email", containsString("@"))
                .body("user.name", notNullValue())
                .body("accessToken", startsWith("Bearer "))
                .body("refreshToken", notNullValue())
                .statusCode(200)
                .log().all()
                .extract().response();
        SaveData.TOKEN = response.jsonPath().getString("accessToken");
    }

}
