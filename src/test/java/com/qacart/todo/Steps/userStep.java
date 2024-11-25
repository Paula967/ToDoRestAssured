package com.qacart.todo.Steps;

import com.github.javafaker.Faker;
import com.qacart.todo.Apis.userApi;
import com.qacart.todo.Models.User;
import io.restassured.response.Response;

public class userStep {
    public static User generateUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new User(firstName,lastName,email,password);
    }
    public static User getRegisteredUser(){
        User user=generateUser();
        userApi.Register(user);
        return user;
    }
    public static String generateUserToken(){
        User user=generateUser();
        Response response =userApi.Register(user);
        return response.body().path("access_token");
    }

}
