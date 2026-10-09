package api.tests;

import api.endpoints.UsersEndPoints;
import api.payload.Users;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class UserTests {

    Users users;

    @BeforeClass
    public void setup() {
       users = new Users();
       users.setId(1);
       users.setFirstName("John");
       users.setLastname("Sena");
       users.setPhone("123456789");
       users.setUsername("johnsena");
       users.setEmail("John.sena@gmail.com");
       users.setPassword("Test@123");
       users.setUserStatus(0);
    }

    @Test (priority = 0)
    public void createTests() {
        Response response = UsersEndPoints.creatUsers(users);
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 1)
    public void getUsers() {
       Response response = UsersEndPoints.getUser(this.users.getUsername());

       Assert.assertEquals(response.getStatusCode(), 200);
       Assert.assertEquals(response.getHeader("content-type"), "application/json");

       String namedCookie = response.getCookie("");
       Map<String, String> cookies = response.getCookies();

       Assert.assertEquals(response.jsonPath().getString("username"), this.users.getUsername());
       Assert.assertEquals(response.jsonPath().getString("firstName"), this.users.getFirstName());
       Assert.assertEquals(response.jsonPath().getString("email"), this.users.getEmail());
       Assert.assertEquals(response.jsonPath().getInt("id"), this.users.getId());

       String email = response.jsonPath().get("email").toString();
    }

    @Test(priority = 2)
    public void updateUser(){
        Response response = UsersEndPoints.updateUser(this.users.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void deleteUser(){
        Response response = UsersEndPoints.deleteUser(this.users.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void getBooks() {
        Response response = RestAssured.given().header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExMjI2LCJlbWFpbCI6InN0dWRlbnQyMUBleGFtcGxlLmNvbSIsImlhdCI6MTc5MTM1NDIzNCwiZXhwIjoxNzkxOTU5MDM0fQ.ATgJEDEhb6-AwH4WInNVR7yHYhrYaPKtiek4o_fUX9g")
                .when().get("https://api.eventhub.rahulshettyacademy.com/api/bookings?page=1&limit=10");

        System.out.println(response.getStatusCode());

        //read response from three different ways

        //JSONObject first, need to pass response object asString();
        JSONObject jsonObject = new JSONObject(response.asString());

        JSONArray dataObject = jsonObject.getJSONArray("data");

        for (int i=0; i< dataObject.length(); i++){
           JSONObject js = dataObject.getJSONObject(i);
           System.out.println(js.getString("customerName"));
           JSONObject event = js.getJSONObject("event");
           System.out.println(event.getString("title"));
        }

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SchemaFiles/Books.json"));

        //then().body(path of attribute)
        response.then().body("data[0].customerName", Matchers.equalTo("Riya Sharma"));

        //jsonpath()
        String name = response.jsonPath().getString("data[0].customerName");
        System.out.println(name);



    }
}
