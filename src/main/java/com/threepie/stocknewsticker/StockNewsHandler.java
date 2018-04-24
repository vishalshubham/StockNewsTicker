package com.threepie.stocknewsticker;

import com.threepie.stocknewsticker.external.ApiCaller;
import com.threepie.stocknewsticker.utils.Constants;

public class StockNewsHandler {

	public String getStockInformation(
    		String symbol,
    		String from,
    		String to) {

		return 
			"{data:{" + getStockData(symbol, from, to) + "}," +
			"news:{" + getStockNews(symbol, from, to) + "}}";
	}

	public String getStockNews(
    		String symbol,
    		String from,
    		String to) {
		final String newsUri = 
				Constants.NEWS_BASE_URL + Constants.NEWS_API +
				"?apiKey=" + Constants.NEWS_API_KEY +
				"&q=" + symbol +
				"&from=" + from +
				"&to=" + to +
				"&language=en" +
				"&sortBy=popularity";
		return ApiCaller.callApi(newsUri);
	}

	public String getStockData(
    		String symbol,
    		String from,
    		String to) {
		final String stockUri = 
				Constants.STOCK_BASE_URL + Constants.STOCK_API +
				"?apikey=" + Constants.STOCK_API_KEY +
				"&symbol=" + symbol +
				"&function=TIME_SERIES_INTRADAY" +
				"&interval=5min" +
				"&outputsize=full";
		return ApiCaller.callApi(stockUri);
	}
}
