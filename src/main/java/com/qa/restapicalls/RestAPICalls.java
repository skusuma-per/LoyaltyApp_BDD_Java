package com.qa.restapicalls;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.qa.base.TestBase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;


public class RestAPICalls extends TestBase
{
	//GET Call
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
	
		CloseableHttpClient c= HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		for(Map.Entry<String,String> entry : headerMap.entrySet()) 
		{
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closablehttpresponse = c.execute(httpget);
		return closablehttpresponse;
				
	}
		
	//POST Call
	
	public CloseableHttpResponse post(String url, String en, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient c= HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		
		//pass the payload
		httppost.setEntity(new StringEntity(en));
		
		//headers
		for(Map.Entry<String,String> entry : headerMap.entrySet()) 
		{
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closablehttpresponse = c.execute(httppost);
		return closablehttpresponse;
				
	}
	
	
	// PUT Call
	
	public CloseableHttpResponse put(String url, String en, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient c= HttpClients.createDefault();
		HttpPut httpput = new HttpPut(url);
		
				
		httpput.setEntity(new StringEntity(en));
		
		//headers
		for(Map.Entry<String,String> entry : headerMap.entrySet()) 
		{
			httpput.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closablehttpresponse = c.execute(httpput);
		return closablehttpresponse;
				
	}
	
	
	


}
