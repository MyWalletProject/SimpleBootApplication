package com.example.spring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;

public class KYCUtilities {

	
	private static final Logger logger= LoggerFactory.getLogger(KYCUtilities.class);

	
	public static JSONObject getJSONObject(String string) {
		try{
		return new JSONObject(string);
		}
		catch (Exception e) {
			logger.error("Exception occur in JSON Object ");		
			return null;
		}
	}
	
}
