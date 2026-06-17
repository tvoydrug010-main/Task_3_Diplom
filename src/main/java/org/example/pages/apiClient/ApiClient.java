package org.example.pages.apiClient;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class ApiClient {
    protected static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";
    protected static final String BASE_PATH = "/api";

    protected String accessToken;
    protected String refreshToken;

    public ApiClient() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
    }

    static RequestSpecification getAuthRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public void saveTokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Step("Удаляем пользователя через запрос DELETE")
    public void deleteUser() {
        if (SaveData.TOKEN != null && !SaveData.TOKEN.isEmpty()) {
            given(getAuthRequestSpec())
                    .header("Authorization", SaveData.TOKEN)
                    .log().all()
                    .delete("/auth/user")
                    .then()
                    .statusCode(202);
        }
    }
}
