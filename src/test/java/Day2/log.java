package Day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class log {

	@Test
	void logTypes()
	{
		given()
		
		.when()
		    .get("https://reqres.in/api/users?page=2&id=5")
		    
		.then()
		   .log().body();
//		   .log().cookies();
//		   .log().headers();
	}
}
