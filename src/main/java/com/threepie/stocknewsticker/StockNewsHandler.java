package com.threepie.stocknewsticker;

import org.json.JSONObject;

import com.threepie.stocknewsticker.external.ApiCaller;
import com.threepie.stocknewsticker.utils.Constants;

public class StockNewsHandler {

	public String getStockData(
    		String symbol,
    		String from,
    		String to) {

		JSONObject jsonObject = new JSONObject();

		final String newsUri = 
				Constants.BASE_URL + Constants.API_EVERYTHING +
				"?apiKey=" + Constants.API_KEY +
				"&q=" + symbol +
				"&from=" + from +
				"&to=" + to +
				"&language=en" +
				"&sortBy=relevancy";
		return ApiCaller.callApi(newsUri);
	}
}
