package api.endpoints;

import api.payload.Users;
import api.utilities.ReadPropertyFile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UsersEndPoints {


    public static Response creatUsers(Users userPayload) {
        return RestAssured.given()
                .baseUri(ReadPropertyFile.getPropertyValue("base.url"))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)
                .when().post(ReadPropertyFile.getPropertyValue("create.users"));
    }

    public static Response getUser(String username) {
        return RestAssured
                .given().baseUri(ReadPropertyFile.getPropertyValue("base.url"))
                .pathParams("username", username)
                .when().get(ReadPropertyFile.getPropertyValue("update.users"));
    }

    public static Response updateUser(String username) {
        return RestAssured.given().baseUri(ReadPropertyFile.getPropertyValue("base.url"))
                .pathParams("username",username)
                .when().put(ReadPropertyFile.getPropertyValue("update.users"));
    }

    public static Response deleteUser(String username){
        return RestAssured.given().baseUri(ReadPropertyFile.getPropertyValue("base.url"))
                .pathParams("username", username)
                .when().delete(ReadPropertyFile.getPropertyValue("delete.users"));
    }
}
