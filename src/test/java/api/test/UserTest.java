package api.test;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload = new User();
		
		// hashcode() generate the random number
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5, 10));
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	 
//Create user
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.debug("payload before create: {}", userPayload);
	logger.info("************** Creating user *****************");
	Response res=	UserEndPoints.CreateUser(userPayload);
	res.then().log().all();
	
	Assert.assertEquals(res.getStatusCode(), 200);
	logger.info("************** User is Created *****************");
	 logger.debug("detailed payload: {}");
	System.out.println("Now I have updated just for git practice1");
	System.out.println("Now I have updated just for git practice2");
 	}
	
	
//Get User	
	@Test(priority = 2)
	public void testGetUserName()
	{
		logger.info("************** Reading user *****************");
		Response res= UserEndPoints.ReadUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
		logger.info("************** User info is displayed *****************");
	}
	
//Update user
	@Test(priority = 3)
	public void testUpdateUser()
	{
		logger.info("************** Updating user *****************");
		// update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response res= UserEndPoints.UpdateUser(userPayload, this.userPayload.getUsername());
		res.then().log().body();
		Assert.assertEquals(res.statusCode(), 200);
		logger.info("************** User is Updated *****************");
		//checking after update
		Response resAfterUpdate= UserEndPoints.ReadUser(this.userPayload.getUsername());
		resAfterUpdate.then().log().all();
		Assert.assertEquals(resAfterUpdate.statusCode(), 200);
	}
	
//Delete user
	@Test(priority = 4)
	public void testDeleteUser()
	{
		logger.info("************** Delating the user *****************");
		Response res= UserEndPoints.DeleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************** User is deleted *****************");
	}
	

}
