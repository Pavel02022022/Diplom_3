package org.practicum.user;


import com.github.javafaker.Faker;


public class UserGenerator {
    public static Faker faker = new Faker();

    public static User randomUser() {
        return new User()
                .withEmail(faker.internet().emailAddress())
                .withPassword(faker.internet().password())
                .withName(faker.name().firstName());
    }

    public static User userWithShortPassword5() {
        return new User()
                .withEmail(faker.internet().emailAddress())
                .withPassword(faker.regexify("[A-Za-z0-9!@#$%^&*(){}_+]{5}"))
                .withName(faker.name().firstName());
    }

}
