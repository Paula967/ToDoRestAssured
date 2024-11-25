package com.qacart.todo.Apis;
import com.qacart.todo.Data.Route;
import com.qacart.todo.Models.Todo;
import com.qacart.todo.base.Specs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class todoApi {
    public static Response addTodo(Todo todo,String token){
        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .body(todo)
                .when()
                .post(Route.TODO_PATH)
                .then()
                .log().all()
                .extract().response();
    }
    public static Response getTodo(String token,String ID){
        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when()
                .get(Route.TODO_PATH+"/"+ID)
                .then()
                .log().all()
                .extract().response();
    }
    public static Response UpdateTodo(Todo todo,String token,String ID){
        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .body(todo)
                .when()
                .put(Route.TODO_PATH+"/"+ID)
                .then()
                .log().all()
                .extract().response();
    }
    public static Response DeleteTodo(String token,String ID){
        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when()
                .delete(Route.TODO_PATH+"/"+ID)
                .then()
                .log().all()
                .extract().response();
    }
}
