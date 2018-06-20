package com.example.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.spring.util.KYCUtilities;

import org.json.JSONObject;

@Service
public class MyWalletApiService {


	private static final Logger logger = LoggerFactory.getLogger(MyWalletApiService.class);

	public MyWalletApiService(){}

	@Autowired
	private RestTemplate restTemplate;

	public JSONObject getUsers(){

		String url="http://18.218.205.60:8080/users";

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Accept","application/json");

		HttpEntity <String> httpEntity = new HttpEntity <String> (httpHeaders);
		logger.info("Request Url for get Blocks from public blockchain  : "+url);
		logger.info("request param is : "+builder.build().toUriString());
		logger.info("Request Headers is : "+httpEntity.getHeaders());

		ResponseEntity<String> postResponse =null;

		try{
			postResponse = restTemplate.exchange(builder.build().toUriString(), HttpMethod.GET,httpEntity, String.class);
			logger.info("Response of get blocks : " + postResponse.getBody());

			JSONObject responseJson =  KYCUtilities.getJSONObject(postResponse.getBody());
			logger.info("HTTP Status response Status code : "+postResponse.getStatusCode());
			if(responseJson == null){
				logger.info("Incorrect response getting from public blockchain");
				return null;
			}
             System.out.println("+++++++++++++++ "+responseJson.getBoolean("error"));
			if(responseJson.getBoolean("error")){
				logger.info("get error response from public blockchain. message is "+responseJson.getString("error"));
				return null;
			}
			//JSONObject jsonObject = responseJson;
			return responseJson;
		}catch(Exception e){
			logger.info("Exception occur while get blocks from public blockchain : "+e);
			return null;
		}

	}

	public JSONObject getEmail(JSONObject jsonRequest){
	
		String url="http://18.218.205.60:8080/swagger-ui.html#!/user-controller/getByEmailUsingGET";
		
		UriComponentsBuilder uriComponents = UriComponentsBuilder.fromUriString(url);
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Accept","application/json");
		
		logger.info("APi Url is : "+url);
		
		HttpEntity <String> httpEntity = new HttpEntity <String> ( jsonRequest.toString(),httpHeaders );
		ResponseEntity<String> putResponse =null;
		logger.info("Request Url for get Blocks from public blockchain  : "+url);
		logger.info("request param is : "+uriComponents.build().toUriString());
		logger.info("Request headers is : "+httpEntity.getHeaders());
		logger.info("Request body is : "+httpEntity.getBody());
		logger.info("Request has body is : "+httpEntity.hasBody());
		
		try{
			putResponse = restTemplate.exchange(uriComponents.build().toUriString(), HttpMethod.GET,httpEntity, String.class);
			
			System.out.println("putResponse.getStatusCode() : "+putResponse.getStatusCode());
			System.out.println("putResponse.getBody() : "+putResponse.getBody());
			
			logger.info("Response of Put Request: role : " + putResponse.getBody());

			JSONObject responseJson = KYCUtilities.getJSONObject(putResponse.getBody());
			
			logger.info("HTTP Status response Status code : "+putResponse.getStatusCode());
			if(responseJson == null){
				logger.info("Incorrect response getting from public blockchain");
				return null;
			}
			if(responseJson.get("success") == null){
				logger.info("get error response from public blockchain. message is "+responseJson.getString("error"));
				return null;
			}
			
		     return responseJson;
		}
		   catch(Exception e){
			logger. info("Exception occur while post role on public blockchain : "+e);
			return null;
		}
	}
	
}
