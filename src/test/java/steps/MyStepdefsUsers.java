package steps;

import Models.Post;
import Models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import util.Endpoints;
import util.TestNGListeners;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MyStepdefsUsers {

    ObjectMapper objectMapper = new ObjectMapper();
    User user,user1;
    Post post;
    static Response response,putresponse,delresponse;
    static User responseUser,putresponseUser;
    static Post responsePost;
    @Given("Creating a user")
    public void creatingAUser() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("createUser");
        user = new User(
                (String) testData.get("name"),
                "aks" + System.currentTimeMillis() + "@gmail.com",
                (String) testData.get("gender"),
                (String) testData.get("status"));

        response = given()
                .body(user)
                .when().post(Endpoints.USERENDPOINT)
                .then().body(matchesJsonSchemaInClasspath("user_schema.json")).statusCode(201).extract().response();
    }

    @Then("the user is created")
    public void theUserIsCreated() throws JsonProcessingException {
        responseUser = objectMapper.readValue(response.asString(), User.class);
        Assert.assertEquals(user.getEmail(), responseUser.getEmail());
        Assert.assertEquals(user.getName(), responseUser.getName());
        Assert.assertEquals(user.getGender(), responseUser.getGender());
        Assert.assertEquals(user.getStatus(), responseUser.getStatus());
    }

    @When("updating a user")
    public void updatingAUser() {
        JSONObject testdata = (JSONObject) TestNGListeners.data.get("putUser");
        user1 = new User(
                (String) testdata.get("name"),
                "pavani" + System.currentTimeMillis() + "@gmail.com",
                (String) testdata.get("gender"),
                (String) testdata.get("status"));

        putresponse = given()
                .body(user1)
                .when().put(Endpoints.USERENDPOINT + "/" + responseUser.getId())
                .then().body(matchesJsonSchemaInClasspath("user_schema.json")).statusCode(200).extract().response();
    }

    @Then("the user is updated")
    public void theUserIsUpdated() throws JsonProcessingException {
        putresponseUser = objectMapper.readValue(putresponse.asString(), User.class);
        Assert.assertEquals(user1.getEmail(), putresponseUser.getEmail());
        Assert.assertEquals(user1.getName(), putresponseUser.getName());
        Assert.assertEquals(user1.getGender(), putresponseUser.getGender());
        Assert.assertEquals(user1.getStatus(), putresponseUser.getStatus());
    }

    @When("deleting a user")
    public void deletingAUser() {
        delresponse = given()
                .when().delete(Endpoints.USERENDPOINT + "/" + responseUser.getId())
                .then().statusCode(204).extract().response();
    }

    @When("^updating the user with invalid details \"(.*)\" and \"(.*)\"$")
    public void updatingTheUserWithInvalidDetails(String arg0, String arg1) {
        System.out.println(arg0);
        System.out.println(arg1);
    }

    @Then("the user not be updated")
    public void theUserNotBeUpdated() {
    }
}
