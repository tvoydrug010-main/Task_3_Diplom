package org.example.pages.factory;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.example.pages.models.User;

import java.util.Locale;

public class UserFactory {
    private static final Faker faker = new Faker(Locale.forLanguageTag("ru"));

    @Step("Заполнили валидного пользователя")
    public static User createValidUser() {
        return new User(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                "TestPassword123!"

        );
    }
    @Step("Заполнили невалидного пользователя с коротким поле Пароль")
    public static User createUserWithShortPassword() {
        return new User(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                "12345"
        );
    }

}
