package com.zb.project.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import com.alibaba.fastjson.JSONObject;
import com.zb.project.util.AppConstants;
import com.zb.project.util.ResponseCode;





public class BaseAction {
	 private static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	  	 
	
	   protected ResponseEntity<String> renderOK() {
	        return render(ResponseCode.OK.getCode(), AppConstants.NULL_OBJECT, AppConstants.NULL_OBJECT);
	    }

	
	   protected ResponseEntity<String> renderOK(Object data) {
	            return render(ResponseCode.OK.getCode(), AppConstants.NULL_OBJECT, data);
	    }

	    protected ResponseEntity<String> renderOK(Object data, long total) {
	        return render(ResponseCode.OK.getCode(), AppConstants.NULL_OBJECT, data, total);
	    }

	    protected ResponseEntity<String> render(ResponseCode code) {
	        return render(code.getCode(), code.getName(), AppConstants.NULL_OBJECT);
	    }

	    protected ResponseEntity<String> render(int code, String message) {
	        return render(code, message, AppConstants.NULL_OBJECT);
	    }
	    
	    
	    protected ResponseEntity<String> render(int code, String message, Object data) {
	    	JSONObject  codeObj = new JSONObject();
	    	codeObj.put(AppConstants.JSON_CODE,code);
	    	codeObj.put(AppConstants.JSON_MESSAGE,message);
	    	codeObj.put(AppConstants.JSON_DATA, data);	      
	        String content = codeObj.toString();
	        return new ResponseEntity<>(content, HttpStatus.OK);
	    }

	    protected ResponseEntity<String> render(int code, String message, Object data, long total) {
	    	JSONObject  codeObj = new JSONObject();
	    	codeObj.put(AppConstants.JSON_CODE,code);
	    	codeObj.put(AppConstants.JSON_MESSAGE,message);
	    	codeObj.put(AppConstants.JSON_DATA, data);
	    	codeObj.put(AppConstants.JSON_TOTAL, total);	     
	        String content = codeObj.toString();
	        return new ResponseEntity<>(content, HttpStatus.OK);
	    }	


	    

	
	
}
