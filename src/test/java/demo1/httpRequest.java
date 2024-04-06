package demo1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;


public class httpRequest {

	int id;
	@Test(priority=1)
	void listUser()
	{
		given()
		.when()
		   .get("https://reqres.in/api/users?page=2")
		.then()
		    .statusCode(200)
		    .body("page", equalTo(2))
		    .log().all();
	}
	
	@Test(priority=2)
	void createUser()
	{
		HashMap<String, String> data=new HashMap<String, String>();
				data.put("Name","Prasad");
				data.put("Job","QA");
				
		id=given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
	}
	
	@Test(priority=3, dependsOnMethods="createUser")
	void updateUser()
	{
		HashMap<String, String> data=new HashMap<String, String>();
		data.put("Name", "John");
		data.put("Job", "Engineer");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204)
		.log().all();
	}
}
