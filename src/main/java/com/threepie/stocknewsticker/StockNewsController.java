package com.threepie.stocknewsticker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StockNewsController {

	@RequestMapping("/getStockData")
    public String getStockData(@RequestParam("symbol") String symbol) {
		final String uri = 
				Constants.BASE_URL + Constants.API_EVERYTHING +
				"?apiKey=" + Constants.API_KEY +
				"&q=" + symbol +
				"&from=" + "2018-04-23" +
				"&to=" + "2018-04-24" +
				"&language=" + "en" +
				"&sortBy=relevancy";
		return StockDataGetter.callApi(uri);
	}
}
