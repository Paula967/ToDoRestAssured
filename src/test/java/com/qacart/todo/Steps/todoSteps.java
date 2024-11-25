package com.qacart.todo.Steps;

import com.github.javafaker.Faker;
import com.qacart.todo.Apis.todoApi;
import com.qacart.todo.Models.Todo;
import io.restassured.response.Response;

public class todoSteps {
    public static Todo generateTodo(){
        Faker faker=new Faker();
        String item=faker.book().title();
        Boolean isCompleted=false;
        return new Todo(item,isCompleted);
    }
    public static String getTodoId(Todo todo,String token){
        Response response= todoApi.addTodo(todo,token);
        return response.body().path("_id");
    }
    public static Response updateTodo(Todo todo,String token,String ID){
        todo.setItem("UpdatedTodo");
        Todo updatedtodo=new Todo(todo.getItem(),todo.getIsCompleted());
        return todoApi.UpdateTodo(updatedtodo,token,ID);
    }
}
