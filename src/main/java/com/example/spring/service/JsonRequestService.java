package com.example.spring.service;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.spring.req_domain.Req_Email;
import com.example.spring.req_domain.Req_Role;

@Service
public class JsonRequestService {

	private static final Logger logger = LoggerFactory.getLogger(JsonRequestService.class);
	
	public JSONObject getRequestJsonForRole(@Valid Req_Role req_roleData){

		JSONObject requestJson = new JSONObject();
		try{
			requestJson.put("roleId", req_roleData.getRoleId());
			requestJson.put("roleName", req_roleData.getRoleName());
			requestJson.put("roleDescription", req_roleData.getRoleDescription());
			requestJson.put("isActive", req_roleData.getIsActive());
			System.out.println("-----requestJson-----"+requestJson);
            logger.info("requestJson ::::: "+requestJson);
			return requestJson;
		}catch (Exception e) {
			logger.error("Exception occur while create put JSON request for role : ",e);
			return null;
		}
	}
	
	public JSONObject getRequestJsonForEmailId(String email){
		JSONObject requestJson = new JSONObject();
		try{
			requestJson.put("email", email);
			logger.info("requestJson ::::: "+requestJson);
			return requestJson;
		}catch (Exception e) {
			logger.error("Exception occur while get JSON request for EMAIL : ",e);
			return null;
		}
	}
}
