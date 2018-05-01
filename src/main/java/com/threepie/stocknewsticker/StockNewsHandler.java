package com.threepie.stocknewsticker;

import org.json.JSONException;
import org.json.JSONObject;

import com.threepie.stocknewsticker.request.NewsRequestBuilder;
import com.threepie.stocknewsticker.request.StockRequestBuilder;
import com.threepie.stocknewsticker.response.ApiArticlesResponse;
import com.threepie.stocknewsticker.response.ApiSourcesResponse;
import com.threepie.stocknewsticker.response.ApiStockInformationResponse;
import com.threepie.stocknewsticker.utils.Constants;
import com.threepie.stocknewsticker.utils.Error;
import com.threepie.stocknewsticker.utils.ValidStocks;

public class StockNewsHandler {
	NewsApi newsApi;
	StockApi stockApi;
	ValidStocks validStocks;

	public StockNewsHandler () {
		newsApi = new NewsApi();
		stockApi = new StockApi();
		validStocks = new ValidStocks();
	}

	public String getStockInformation(String symbol) {
		if (!validStocks.isValid(symbol)) {
			return Error.sendError(
					"error",
					"inValidStock",
					"Currently, We do not support this stock. Please check the list of valid stocks");
		}
		JSONObject result = new JSONObject();
		try {
			result.put("news", getArticles(symbol));
			result.put("stock", getStocks(symbol));
			return result.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getSources() {

		NewsRequestBuilder sourcesRequest = new NewsRequestBuilder()
				.setLanguage("en");

		ApiSourcesResponse apiSourcesResponse = newsApi.sendSourcesRequest(sourcesRequest);

		return apiSourcesResponse.getString();
	}

	public JSONObject getArticles(String symbol) {

		NewsRequestBuilder sourcesRequest = new NewsRequestBuilder()
				.setQ(symbol + " stock")
				.setLanguage("en")
			    .setPage(100);

		sourcesRequest.setApikey(Constants.NEWS_API_KEY);

		ApiArticlesResponse apiArticlesResponse = newsApi.sendEverythingRequest(sourcesRequest);

		return apiArticlesResponse.getString();
	}

	public JSONObject getStocks(String symbol) {

		StockRequestBuilder stockRequest = new StockRequestBuilder()
				.setSymbol(symbol)
				.setFunction("TIME_SERIES_INTRADAY")
				.setInterval("5min")
				.setOutputsize("compact");

		stockRequest.setApikey(Constants.STOCK_API_KEY);

		ApiStockInformationResponse apiStockInformationResponse = stockApi.sendStockRequest(stockRequest);

		return apiStockInformationResponse.getString();
	}
}
