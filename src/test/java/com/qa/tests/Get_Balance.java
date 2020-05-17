package com.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import io.restassured.RestAssured;
import qa.com.data.users;

 public class Get_Balance extends TestBase{
	
	TestBase tb;
	users u;
	String serviceUrl;
	String apiUrl;
	String url;
	public Connection con;
	String dbURL;
	String username;
	String password;
	Statement stmt;
	ResultSet rs;
	String validuser;
	String userName=u.getUserName();
	CloseableHttpResponse closablehttpresponse;
		
	
	/*
	 ********** Set Up ************
	 * 1. Get URL
	 * 2. Make DB connection.
	 * 3. Check if the userName is available in the DB to retrieve the balance
	 * 
	 */
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException, SQLException, ClassNotFoundException
	{
		tb = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl=prop.getProperty("serviceURL");
		url= serviceUrl + apiUrl;
				
		con = DriverManager.getConnection(dbURL, username, password);
		Class.forName("com.sql.jdbc.Driver");
		
		System.out.println("Enter the userName to get the balance");
		try(Scanner s=new Scanner(System.in)){
			String i=s.nextLine();
			System.out.println("userName:"+i);
		}
		
		
		
		Statement stmt = con.createStatement();
		String validuser="(select userName from UserTable where userName =i)";
		this.validuser=validuser;
		stmt.executeQuery(validuser);
		if(validuser!=null && validuser ==userName)
		{
			RestAssured.baseURI=url;
			RestAssured.basePath="/userName";
		}else 
			System.out.println("Invalid User");
		
	}
	
	/*
	 ********** Assertions *************
	 * Verify the statuscode, response, and body
	 * 
	 */
	@Test(priority=1)
	public void getBalance()
	{
	  given()
	  .when()
	  	.get()
	  .then()
	  	.statusCode(tb.response_code_200)
	  	.statusLine("200 OK")
	  	.and()
	  	.assertThat().body("userName", equalTo(u.getUserName()))
	  	.assertThat().body("balance", equalTo(u.getBalance()))
	  	.log().all();
  }
	
	@Test(priority=2)
	public void rewardBalanceDBCheck() throws SQLException
	{
		
		String s="select userName,balance from user where userName='validuser'";
		Statement stmt = con.createStatement();
		rs=stmt.executeQuery(s);
		
		while(rs.next()){
		String userName = rs.getString("userName");
		int balance = rs.getInt("balance");
		System.out.println("userName "+userName);
		System.out.println("balance "+balance);
	
		}
			
  }
	

}
