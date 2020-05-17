package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/* 
1. Declare status codes
2. Read the properties file 
*/

public class TestBase {
	
	public Properties prop;

	public static int response_code_200=200;
	public static int response_code_201=201;
	public static int response_code_404=404;
	public static int response_code_500=500;
			
	public TestBase() {
	
	try{
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/qa/config/config.properties");
		prop.load(ip);
		} catch(FileNotFoundException e)
	{
			e.printStackTrace(); 
	} catch(IOException e)
	{
		e.printStackTrace();
	}
	
}
	
	
}
