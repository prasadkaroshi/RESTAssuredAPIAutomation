package Day2;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class cookieHandle {

	@Test
	void cookiesHandle()
	{
		Response res=given()
		
		.when()
		    .post("https://www.google.com/");
		
//		 String cookie_value=res.getCookie("AEC");
//		 System.out.println(cookie_value);
		
		Map<String, String>cookies_value=res.getCookies();
		
		for(String k:cookies_value.keySet())
		{
			String cookie_value=res.getCookie(k);
			System.out.println(k+" "+cookie_value);
		}
		
//		.then()
		
	}
}
