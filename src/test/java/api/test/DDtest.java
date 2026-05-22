package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtest {
	 
	
	@Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostuser(String userID,String userName,String fName, String lName, String useremail, String pwd, String phone)
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(useremail);
		userPayload.setPhone(phone);
		userPayload.setPassword(pwd);
		
		Response res=	UserEndPoints.CreateUser(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println(res.jsonPath().getString("message"));
		res.then().log().all();
	}
	
	@Test(priority = 2,dataProvider = "userNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username)
	{
		Response res= UserEndPoints.DeleteUser(username);
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then().log().all();
	}
	

}
