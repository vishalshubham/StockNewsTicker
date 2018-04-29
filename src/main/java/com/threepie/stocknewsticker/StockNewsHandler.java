package com.threepie.stocknewsticker;

import org.json.JSONException;
import org.json.JSONObject;
import com.threepie.stocknewsticker.external.ApiCaller;
import com.threepie.stocknewsticker.utils.Constants;

public class StockNewsHandler {

	public String getStockInformation(
    		String symbol) {
		return getStockData(symbol).toString();
	}

	public JSONObject getStockData(
    		String symbol) {
		JSONObject result = new JSONObject();
		try {
			final String stockUri = 
					Constants.STOCK_BASE_URL + Constants.STOCK_API +
					"?apikey=" + Constants.STOCK_API_KEY +
					"&symbol=" + symbol +
					"&function=TIME_SERIES_INTRADAY" +
					"&interval=5min" +
					"&outputsize=full";
			JSONObject stockNode = new JSONObject(ApiCaller.callApi(stockUri));
			JSONObject metadata = (JSONObject) stockNode.get("Meta Data");
			String date = metadata.getString("3. Last Refreshed");
			String currentTime = date.replace(" ", "T");

			result.put("data", stockNode);
			result.put("news", getStockNews(symbol, currentTime));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getStockNews(
    		String symbol,
    		String time) {
		try {
			final String newsUri = 
					Constants.NEWS_BASE_URL + Constants.NEWS_API +
					"?apiKey=" + Constants.NEWS_API_KEY +
					"&q=" + symbol +
					"&from=" + time.substring(0, 10) +
					"&to=" + time.substring(0, 10) +
					"&language=en" +
					"&sortBy=popularity";
			JSONObject obj = new JSONObject(ApiCaller.callApi(newsUri));
			JSONObject result  = new JSONObject();
			result.put("data", obj.get("articles"));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
