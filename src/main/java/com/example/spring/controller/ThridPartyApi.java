package com.example.spring.controller;

import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.req_domain.Req_Email;
import com.example.spring.req_domain.Req_Role;
import com.example.spring.service.ThridPartyApiSerivce;
import com.example.spring.util.ResponseUtil;


@RestController
public class ThridPartyApi {

	
	private static final Logger log = LoggerFactory.getLogger(ThridPartyApi.class);

	@Autowired
	  private ThridPartyApiSerivce tpaService;
	
	public ThridPartyApi(){
		
	}
	
	
	@GetMapping(value ="/api/v1/testApi",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getMyWalletUsers(){
		
	Map<String,Object> map	= tpaService.getUsers();
	
	log.info("value return is : "+map);
	
		if(map==null){
			return ResponseUtil.errorResp("Map can not be null  ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseUtil.successResponse("Successfully get My Wallet Users ", map, HttpStatus.OK);
	}
	
	@PostMapping(path="/api/v1/role",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> roleBinding(@Valid @RequestBody Req_Role reqData,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			return ResponseUtil.errorResp(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
		}
		
		Map<String,Object> map	= tpaService.createRole(reqData);
		
		log.info("value return is : "+map);
		
			if(map==null){
				return ResponseUtil.errorResp("Map can not be null ", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			return ResponseUtil.successResponse("Successfully get My Wallet Users ", map, HttpStatus.CREATED);
		}
	
	
	@GetMapping(value="/api/v1/userEmail/{id}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getByEmail(@PathVariable String email){

		if( email == null || email.equals("")){
			return new ResponseEntity<Object>("Email id can not be Null or empty ",HttpStatus.BAD_REQUEST);
		}

		log.info("User email send by UI : "+email);

		Map<String,Object> map = tpaService.getByEmail(email);

		if(map == null){
			return ResponseUtil.errorResp("Map can not be null ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseUtil.successResponse("User Fetched Email Successfully", map,HttpStatus.OK);
	}

	
	
	
}
