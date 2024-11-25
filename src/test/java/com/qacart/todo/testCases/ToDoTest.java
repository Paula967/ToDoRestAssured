package com.qacart.todo.testCases;
import com.qacart.todo.Data.ErrorMessage;
import com.qacart.todo.Models.Error;
import com.qacart.todo.Models.Todo;
import com.qacart.todo.Steps.todoSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import com.qacart.todo.Apis.todoApi;
import com.qacart.todo.Steps.userStep;
@Feature("Todo Feature")
public class ToDoTest {

    @Story("Should Be Able To Add Todo")
    @Test(description = "Should Be Able To Add Todo")
    public void ShouldBeAbleToDo(){
        String token=userStep.generateUserToken();
        Todo todo= todoSteps.generateTodo();
        Response response =todoApi.addTodo(todo,token);
                Todo returnedTodo=response.body().as(Todo.class);
                assertThat(response.statusCode(),equalTo(201));
                assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
                assertThat(returnedTodo.getIsCompleted(),equalTo(todo.getIsCompleted()));
   }

    @Story("Should Not Be Able To Add Todo")
    @Test(description = "Should Not Be Able To Add Todo")
    public void ShouldNotBeAbleToDo(){
        String token=userStep.generateUserToken();
        Todo todo=new Todo("Learn RestAssured");
        Response response=todoApi.addTodo(todo,token);
                 Error returnedMessage=response.body().as(Error.class);
                 assertThat(response.statusCode(),equalTo(400));
                 assertThat(returnedMessage.getMessage(),equalTo(ErrorMessage.ErrorMesForNoIsCompletedValue));
    }

    @Story("Search For Todo By Id")
    @Test(description = "Search For Todo By Id")
    public void ShouldBeAbleGetToDoByID(){
        Todo todo=todoSteps.generateTodo();
        String token=userStep.generateUserToken();
        String taskID=todoSteps.getTodoId(todo,token);
        Response response= todoApi.getTodo(token,taskID);
                 assertThat(response.statusCode(),equalTo(200));
                 Todo returnedTodo=response.body().as(Todo.class);
                 assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
                 assertThat(returnedTodo.getIsCompleted(),equalTo(false));
    }

    @Story("Update a Specific Todo by Id")
    @Test(description = "Update a Specific Todo by Id")
    public void ShouldBeAbleToUpdateByID(){
        Todo todo=todoSteps.generateTodo();
        String token=userStep.generateUserToken();
        String taskID=todoSteps.getTodoId(todo,token);
        Response response=todoSteps.updateTodo(todo,token,taskID);
                 Todo returnedTodo=response.body().as(Todo.class);
                 assertThat(response.statusCode(),equalTo(200));
                 assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
                 assertThat(returnedTodo.getIsCompleted(),equalTo(todo.getIsCompleted()));
    }

    @Story("Delete a Specific Todo By Id")
    @Test(description = "Delete a Specific Todo By Id")
    public void ShouldBeAbleToDeleteByID(){
        String token=userStep.generateUserToken();
        Todo todo=todoSteps.generateTodo();
        String ID=todoSteps.getTodoId(todo,token);
        Response response=todoApi.DeleteTodo(token,ID);
                 assertThat(response.statusCode(),equalTo(200));
                 Todo returnedTodo=response.body().as(Todo.class);
                 assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
                 assertThat(returnedTodo.getIsCompleted(),equalTo(false));
                 assertThat(returnedTodo.getId(),equalTo(ID));
    }
}
