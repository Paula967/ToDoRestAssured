package com.qacart.todo.Apis;
import com.qacart.todo.Data.Route;
import com.qacart.todo.Models.User;
import com.qacart.todo.base.Specs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class userApi {
    public static Response Register(User user){
     return given()
             .spec(Specs.getRequestSpec())
             .body(user)
             .when()
             .post(Route.REGISTER_PATH)
             .then()
             .log().all()
             .extract().response();
    }
    public static Response Login(User user){
     return given()
             .spec(Specs.getRequestSpec())
             .body(user)
             .when()
             .post(Route.LOGIN_PATH)
             .then()
             .log().all()
             .extract().response();
    }
}
