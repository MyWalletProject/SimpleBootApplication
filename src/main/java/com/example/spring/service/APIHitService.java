package com.example.spring.service;

import org.json.JSONObject;
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

@Service
public class APIHitService {

	
	private static final Logger logger = LoggerFactory.getLogger(APIHitService.class);

	@Autowired
	private RestTemplate restTemplate;

	public JSONObject role(JSONObject jsonRequest){
		
	String url="http://18.218.205.60:8080/role";
	
	UriComponentsBuilder uriComponents = UriComponentsBuilder.fromUriString(url);
	
	HttpHeaders httpHeaders=new HttpHeaders();
	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	httpHeaders.set("Accept","application/json");
	
	logger.info("APi Url is : "+url);
	
	HttpEntity <String> httpEntity = new HttpEntity <String> (jsonRequest.toString(), httpHeaders);
	ResponseEntity<String> putResponse =null;
	logger.info("Request headers is : "+httpEntity.getHeaders());
	logger.info("Request body is : "+httpEntity.getBody());
	logger.info("Request has body is : "+httpEntity.hasBody());
	
	try{
		putResponse = restTemplate.exchange(uriComponents.build().toUriString(), HttpMethod.POST,httpEntity, String.class);
		
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
