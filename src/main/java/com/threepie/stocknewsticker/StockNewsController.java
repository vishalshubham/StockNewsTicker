package com.threepie.stocknewsticker;

import org.springframework.web.bind.annotation.RestController;

import com.threepie.stocknewsticker.request.RequestBuilder;
import com.threepie.stocknewsticker.response.ApiArticlesResponse;
import com.threepie.stocknewsticker.response.ApiSourcesResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StockNewsController {
	private StockNewsHandler handler = null;

	public StockNewsController() {
		handler = new StockNewsHandler();
	}

	@RequestMapping(value = "/stockdata", method = RequestMethod.GET, produces = "application/json")
    public String getStockData(
    		@RequestParam("symbol") String symbol) {

		if (handler==null) handler = new StockNewsHandler();

		return handler.getStockInformation(symbol);
	}
}
