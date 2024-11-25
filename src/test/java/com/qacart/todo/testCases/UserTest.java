package com.qacart.todo.testCases;
import com.qacart.todo.Apis.userApi;
import com.qacart.todo.Data.ErrorMessage;
import com.qacart.todo.Models.Error;
import com.qacart.todo.Models.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import com.qacart.todo.Steps.userStep;
@Feature("User Feature")
public class UserTest {

    @Story("Should Be Able To Register")
    @Test(description = "Should Be Able To Register")
    public void ShouldBeAbleToRegister(){
        User user=userStep.generateUser();
        Response response=userApi.Register(user);
        User returnedUser=response.body().as(User.class);
        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedUser.getFirstName(),equalTo(user.getFirstName()));
    }

    @Story("Should Not Able To Register With The Same Email")
    @Test(description = "Should Not Able To Register With The Same Email")
    public void ShouldNotAbleToRegisterWithTheSameEmail(){
        User user=userStep.getRegisteredUser();
        Response response= userApi.Register(user);
        Error returnedError=response.body().as(Error.class);
        assertThat(response.statusCode(),equalTo(400));
        assertThat(returnedError.getMessage(),equalTo(ErrorMessage.ErrorMesForRegisterSavedEmail));
    }

    @Story("should Be Able To Login")
    @Test(description = "should Be Able To Login")
    public void shouldBeAbleToLogin(){
        User user=userStep.getRegisteredUser();
        User LoginData=new User(user.getEmail(),user.getPassword());
        Response response= userApi.Login(LoginData);
        User returnedUser=response.body().as(User.class);
        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnedUser.getFirstName(),equalTo(user.getFirstName()));
        assertThat(returnedUser.getAccessToken(),not(equalTo(null)));
    }

    @Story("should Not Be Able To Login")
    @Test(description = "should Not Be Able To Login")
    public void shouldNotBeAbleToLogin(){
        User user=userStep.getRegisteredUser();
        User LoginData=new User(user.getEmail(),"lknuiyasf12354");
        Response response=userApi.Login(LoginData);
        Error returnedError=response.body().as(Error.class);
        assertThat(response.statusCode(),equalTo(401));
        assertThat(returnedError.getMessage(),equalTo(ErrorMessage.ErrorMesForWrongEmailOrPassword));
    }
}
