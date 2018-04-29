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
	private NewsApi newsApi = null;

	public StockNewsController() {
		handler = new StockNewsHandler();
		newsApi = new NewsApi();
	}

	@RequestMapping(value = "/stockdata", method = RequestMethod.GET, produces = "application/json")
    public String getStockData(
    		@RequestParam("symbol") String symbol) {

		if (handler==null) handler = new StockNewsHandler();

		return handler.getStockInformation(symbol);
	}

	@RequestMapping(value = "/sources", method = RequestMethod.GET, produces = "application/json")
    public String getSources() {

		if (handler==null) handler = new StockNewsHandler();

		RequestBuilder sourcesRequest = new RequestBuilder().setLanguage("en");

		ApiSourcesResponse apiSourcesResponse = newsApi.sendSourcesRequest(sourcesRequest);

		return apiSourcesResponse.getString().toString();
	}

	@RequestMapping(value = "/articles", method = RequestMethod.GET, produces = "application/json")
    public String getArticles(
    		@RequestParam("symbol") String symbol) {

		if (handler==null) handler = new StockNewsHandler();

		RequestBuilder sourcesRequest = new RequestBuilder()
				.setQ(symbol + " stock")
				.setLanguage("en");

		ApiArticlesResponse apiArticlesResponse = newsApi.sendEverythingRequest(sourcesRequest);

		return apiArticlesResponse.getString().toString();
	}
}
