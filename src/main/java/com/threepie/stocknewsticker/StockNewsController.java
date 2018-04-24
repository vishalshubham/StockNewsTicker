package com.threepie.stocknewsticker;

import org.springframework.web.bind.annotation.RestController;
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
    		@RequestParam("symbol") String symbol,
    		@RequestParam("from") String from,
    		@RequestParam("to") String to) {

		if (handler==null) handler = new StockNewsHandler();

		return handler.getStockData(symbol, from, to);
	}
}
