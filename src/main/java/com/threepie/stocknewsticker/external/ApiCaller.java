package com.threepie.stocknewsticker.external;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

public class ApiCaller {

	public static JSONObject callApi(String uri) {
		System.out.println("External call:" + uri);
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setExpires(60000);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<JSONObject> result = restTemplate.exchange(uri, HttpMethod.GET, entity, JSONObject.class);
	    return result.getBody();
	}
}
