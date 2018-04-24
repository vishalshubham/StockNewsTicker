package com.threepie.stocknewsticker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StockNewsController {

	@RequestMapping("/hell")
    public String index() {
		
		final String uri = "https://newsapi.org/v2/sources?apiKey=bebc2e5b6fe64fdd94aadbb64fd02bfa";

	    RestTemplate restTemplate = new RestTemplate();
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	     
	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	     
	    return result.getBody();
    }

	@RequestMapping("/getStockData")
    public String getStockData(@RequestParam("symbol") String symbol) {
		return "Hehehehehe " + symbol;
	}
}
