package com.threepie.stocknewsticker;

import java.text.DateFormat;

import org.json.JSONException;
import org.json.JSONObject;
import com.threepie.stocknewsticker.external.ApiCaller;
import com.threepie.stocknewsticker.utils.Constants;

public class StockNewsHandler {

	public String getStockInformation(
    		String symbol,
    		String from,
    		String to) {
		JSONObject json = new JSONObject();
		try {
			//json.put("news", getStockNews(symbol, from, to));
			json.put("data", getStockData(symbol, from, to));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json.toString();
	}

	public JSONObject getStockData(
    		String symbol,
    		String from,
    		String to) {
		try {
			final String stockUri = 
					Constants.STOCK_BASE_URL + Constants.STOCK_API +
					"?apikey=" + Constants.STOCK_API_KEY +
					"&symbol=" + symbol +
					"&function=TIME_SERIES_INTRADAY" +
					"&interval=15min" +
					"&outputsize=full" +
					"&time=" + DateFormat.getInstance().toString();
			JSONObject stockNode = new JSONObject(ApiCaller.callApi(stockUri));
			JSONObject metadata = (JSONObject) stockNode.get("Meta Data");
			String date = metadata.getString("3. Last Refreshed");
			System.out.println("Date :" + date);
			return stockNode;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getStockNews(
    		String symbol,
    		String from,
    		String to) {
		try {
			final String newsUri = 
					Constants.NEWS_BASE_URL + Constants.NEWS_API +
					"?apiKey=" + Constants.NEWS_API_KEY +
					"&q=" + symbol +
					"&from=" + from +
					"&to=" + to +
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
