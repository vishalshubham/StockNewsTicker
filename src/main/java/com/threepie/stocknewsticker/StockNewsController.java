package com.threepie.stocknewsticker;

import org.springframework.web.bind.annotation.RestController;

import com.threepie.stocknewsticker.external.ApiCaller;
import com.threepie.stocknewsticker.utils.Constants;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StockNewsController {

	@RequestMapping("/getStockData")
    public String getStockData(
    		@RequestParam("symbol") String symbol,
    		@RequestParam("from") String from,
    		@RequestParam("to") String to) {

		final String uri = 
				Constants.BASE_URL + Constants.API_EVERYTHING +
				"?apiKey=" + Constants.API_KEY +
				"&q=" + symbol +
				"&from=" + "2018-04-23" +
				"&to=" + "2018-04-24" +
				"&language=" + "en" +
				"&sortBy=relevancy";
		return ApiCaller.callApi(uri);
	}
}
