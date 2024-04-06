package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*1. HashMap
2. Using Org.json
3. POJO (Plain Old Java Object)
4. External JSON file
*/

//1. HashMap
public class waysToCreatPostRequestBody {

	public int id;

//	@Test(priority=1)
	void postReqUsingHashMap()
	{
		HashMap data=new HashMap();
		data.put("Name", "John");
		data.put("Age", "23");
		
		String courseArr[]= {"Java","Python"};
		data.put("Courses",courseArr);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/people")
//		.jsonPath().getInt("id");
		
		.then()
		.statusCode(201)
		.body("Name", equalTo("John"))
		.body("Age", equalTo("23"))
		.body("Courses[0]", equalTo("Java"))
		.body("Courses[1]", equalTo("Python"))
		.header("Content-Type", "application/json")
		.log().all();
	}
	
//	@Test(priority=2)
	void testDelete()
	{
		given()
		.when()
		.delete("http://localhost:3000/people")
		
		.then()
		.statusCode(404)
		.log().all();
	}
	
	
	
	//2. Using org.json
//	@Test(priority=1)
	void usingOrgJson()
	{
		JSONObject data=new JSONObject();
		data.put("Name","Jack");
		data.put("Age", "44");
		
		String coursesArr[]= {"C","C++"};
		data.put("Courses",coursesArr);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/people")
		
		.then()
		.statusCode(201)
		.log().all();
	}
//	@Test(priority=2)
	void deleteUser()
	{
		given()
		
		.when()
		.delete("http://localhost:3000/people")
		
		.then()
		.statusCode(404)
		.log().all();
	}
	
	//3. Using POJO (Plain Old Java Object) class
//	@Test(priority=1)
	void postUsingPOJO()
	{
		POJO_class data= new POJO_class();
		data.setName("John");
		data.setAge("23");

		String courses[]= {"C","C++"};
		
		data.setCourses(courses);

		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/people")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("John"))
		.body("age", equalTo("23"))
		.log().all();
	}
	
//	@Test(priority=2)
	void deleteUserPOJO()
	{
		given()
		
		.when()
		.delete("http://localhost:3000/people")
		
		.then()
		.statusCode(404)
		.log().all();
		
	}
	
	
	//4. Post request body using External JSON file
	@Test(priority=1)
	void createUser() throws FileNotFoundException
	{
		File f=new File(".//data.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		
		given()
		     .contentType("application/json")
		     .body(data.toString())
		     
		.when()
		     .post("http://localhost:3000/people")
		    
		
		.then()
		     .statusCode(201)
		     .log().all();
	}
	
	@Test(priority=2)
	void deleteExtUser()
	{
		given()
		
		.when()
		     .delete("http://localhost:3000/people")
		.then()
		     .statusCode(404)
		     .log().all();
	}
}
