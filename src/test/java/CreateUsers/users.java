package CreateUsers;

import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class users {

    //@Test(enabled = false)
    public void getUser() {
        RestAssured.given().baseUri("https://reqres.in/")
                .when().queryParam("/api/users/2").get()
                .then().statusCode(200).log().all();
    }

    @Test
    public void createUsers(){
        RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body("{ \"id\": 1,  \"username\": \"johns\",  \"firstName\": \"john\",  \"lastName\": \"dev\",  \"email\": \"john@gmail.com\",  \"password\": \"Test@123\",  \"phone\": \"123456789\",  \"userStatus\": 0 }")
                .when()
                .post("https://petstore.swagger.io/v2/user")
                .then().statusCode(200).log().all();

    }

    }
