package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.restapicalls.RestAPICalls;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import qa.com.data.users;

public class Post_EnrollUser extends TestBase{
	
	public static HashMap map = new HashMap();
	TestBase tb;
	RestAPICalls rca;
	users u;
	String serviceUrl;
	String apiUrl;
	String url;
	CloseableHttpResponse closablehttpresponse;
	
	/*
	 * 1. Call url 
	 * 2. Prepare the data to create a record using post call
	 */
	@BeforeMethod
	public void enrollUserSetUp() throws JsonGenerationException, JsonMappingException, IOException{

		tb = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl=prop.getProperty("serviceURL");
		url= serviceUrl + apiUrl;
		RestAssured.baseURI=url;
				
		rca = new RestAPICalls();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("content-type", "application/json");
		
		users u = new users();
		u.setFirstName("Corona");
		u.setLastName("Virus");
		u.setEmailid("c@email.com");
		u.setPhoneNumber("1231231234");
		u.setUserName("cc");
		
		//Convert javaobject to Json File
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("C:/Users/Jeremy Brua/eclipse-workspace/LoyaltyApp_BDD/src/main/java/com/qa/data/users.json"),u);
		
		//Convert json to jsonString
		String userJsonString = mapper.writeValueAsString(u);
		System.out.println(userJsonString);
		closablehttpresponse = rca.post(url, userJsonString, headerMap);
		
		String responsString = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsString);
		System.out.println("Response Json from API call ---> "+responseJson);
		
		//json to javaobj
		users uresobj = mapper.readValue(responsString, users.class);
		System.out.println("Users Result object"+uresobj);
		
		}
	
	/*
	 * 1. Assertions 
	 * 
	 */
	@Test
	public void enrolluser()
	{
		given()
			.params("userName", u.getUserName())
			.contentType("application/json")
			.body(u)
		.when()
			.post()
		.then()
			.statusCode(tb.response_code_201)
			.and()
			.body("message", equalTo("User created Successfully"))
			.log().all();
			
	}

}
