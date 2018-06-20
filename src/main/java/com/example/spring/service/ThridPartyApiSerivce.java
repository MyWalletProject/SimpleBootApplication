package com.example.spring.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.req_domain.Req_Email;
import com.example.spring.req_domain.Req_Role;
import com.example.spring.util.KYCUtilities;
import com.example.spring.util.ResponseUtil;
import com.fasterxml.jackson.core.JsonParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ThridPartyApiSerivce {


	private static final Logger logger = LoggerFactory.getLogger(ThridPartyApiSerivce.class);


	@Autowired
	private MyWalletApiService myWalletApiService;
	
	@Autowired
	private APIHitService apiHitService;
	
	@Autowired
	private JsonRequestService jsonRequestService;
	
	public Map<String,Object> getUsers(){

		JSONObject response = myWalletApiService.getUsers();

		System.out.println("response iiiiiiiiii "+response.toString());
		Map<String,Object> result;
		if(response == null){
			result=new HashMap();
			result.put("error",true);

			return result;
		}

		try {
			System.out.println("new ObjectMapper().readValue(response.toString(), HashMap.class) ========== "+new ObjectMapper().readValue(response.toString(), HashMap.class));
			return new ObjectMapper().readValue(response.toString(), HashMap.class);
		} catch (JsonParseException e) {
			logger.info("Exception ",e);
			return null;	
		} catch (JsonMappingException e) {
			logger.info("Exception ",e);
			return null;	
		} catch (IOException e) {
			logger.info("Exception ",e);
			return null;	
		}
	}

	public Map<String,Object> createRole(Req_Role req_roleData){
		
		JSONObject jsonRequest= jsonRequestService.getRequestJsonForRole(req_roleData);
		JSONObject response = apiHitService.role(jsonRequest);

		Map<String,Object> result;
		if(response == null){
			result=new HashMap();
			result.put("error",true);

			return result;
		}

		try{
			if(!response.getBoolean("success")){
				logger.info(" get false response ");
				return null;
			}
		}catch (Exception e) {
			logger.info("Key error is not found in response : ",e);
		}
		HashMap<String,Object> results = null;
		response.remove("success");
		try {
			results = new ObjectMapper().readValue(response.toString(), HashMap.class);
		} catch (JsonParseException e) {
			logger.info("JsonParseException ",e);
			return null;
		} catch (JsonMappingException e) {
			logger.info("JsonMappingException ",e);
			return null;
		} catch (IOException e) {
			logger.info("Exception ",e);
			return null;
		}
		
		return results;

	}

	public Map<String,Object> getByEmail(String email){
		JSONObject jsonRequest= jsonRequestService.getRequestJsonForEmailId(email);
		JSONObject response = apiHitService.role(jsonRequest);

		Map<String,Object> result;
		if(response == null){
			result=new HashMap();
			result.put("error",true);

			return result;
		}

		try{
			if(!response.getBoolean("success")){
				logger.info(" get false response ");
				return null;
			}
		}catch (Exception e) {
			logger.info("Key error is not found in response : ",e);
		}
		HashMap<String,Object> results = null;
		response.remove("success");
		try {
			results = new ObjectMapper().readValue(response.toString(), HashMap.class);
		} catch (JsonParseException e) {
			logger.info("JsonParseException ",e);
			return null;
		} catch (JsonMappingException e) {
			logger.info("JsonMappingException ",e);
			return null;
		} catch (IOException e) {
			logger.info("Exception ",e);
			return null;
		}
		
		return results;
	}
	
	}
	

