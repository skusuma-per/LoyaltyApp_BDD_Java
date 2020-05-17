package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClientAPI;
import com.qa.restapicalls.RestAPICalls;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import qa.com.data.users;

/*
 * 1. Call url 
 * 2. Prepare the data to update the balance once the points are redeemed based on the userName
 */

public class Put_RedeemPoints extends TestBase{
	
	public static HashMap map = new HashMap();
	users u;
	String userName;
	int bal;
	int pointsredeemed = 10000;
	TestBase tb;
	String serviceUrl;
	String apiUrl;
	String url;
	RestAPICalls rca;
	CloseableHttpResponse closablehttpresponse;
	public Connection con;
	
	@BeforeMethod
	public void setup() throws JsonGenerationException, JsonMappingException, IOException, SQLException{
	
		tb = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl=prop.getProperty("serviceURL");
		url= serviceUrl + apiUrl;
	
		
		Statement stmt = con.createStatement();
		String validuser="(select userName from UserTable where userName ='+browser.param.userName')";
		if(validuser!=null && validuser ==userName)
		{
			RestAssured.baseURI="https://endpoint.com/";
			RestAssured.basePath="/update+userId";
		}else 
			System.out.println("Invalid User");
		
			
		int trp = u.getTotalrewardPoints();
		int rp= u.getRewardprice();
		u.setPointsredeemed(pointsredeemed);
		int bal = u.getBalance();
		this.bal=bal;
		System.out.println("Reward Price= "+ rp);
		System.out.println("Available Total Points = "+ trp);
		if(trp>rp) //if total points is greater than reward price
		{
			System.out.println("Points are redeemed. Available Balance "+ bal);
		}else
		{
			System.out.println("Insufficient Reward Points");
		}
		
		
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
	

	
	
	@Test
	public void redeempoints()
	{
		
		given()
		.contentType("application/json")
		.body(u)
	.when()
		.put()
	.then()
		.statusCode(200)
		.and()
		.body("SuccessCode", equalTo("Balance updated Successfully"))
		.assertThat().body("PointsRedeemed", equalTo(u.getPointsRedeemed()))
		.assertThat().body("balance", equalTo(bal))
		.log().all();
		
	}
	
}
