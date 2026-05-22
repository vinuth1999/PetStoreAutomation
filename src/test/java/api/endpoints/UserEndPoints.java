package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java
//Created for perform Create, Read, Update, Delete the user API

public class UserEndPoints {
	
	// Create user
	public static Response CreateUser(User payload)
	{
	 Response res=	given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
	 
	 return res;
	}
	
	// Read User
	public static Response ReadUser(String username)
	{
	 Response res=	given()
			 			.pathParam("username", username)
			 		.when()
			 			.get(Routes.get_url);
	 return res;
	 
	}
	
	// Update User
	public static Response UpdateUser(User payload, String username)
	{
	 Response res=	given()
			 			.contentType(ContentType.JSON)
			 			.accept(ContentType.JSON)
			 			.pathParam("username", username)
			 			.body(payload)
			 		.when()
			 			.put(Routes.update_url);
	 return res;
	 
	}
	
	// Delete User
		public static Response DeleteUser(String username)
		{
		 Response res=	given()
				 			.pathParam("username", username)
				 		.when()
				 			.delete(Routes.delete_url);
		 return res;
		 
		}

}
