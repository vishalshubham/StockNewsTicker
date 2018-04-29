package com.threepie.stocknewsticker.external.stock;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.threepie.stocknewsticker.datamodel.Article;
import com.threepie.stocknewsticker.datamodel.Source;
import com.threepie.stocknewsticker.datamodel.StockTimestampData;
import com.threepie.stocknewsticker.request.StockRequestBuilder;
import com.threepie.stocknewsticker.response.ApiArticlesResponse;
import com.threepie.stocknewsticker.response.ApiStockInformationResponse;

/**
 * Represents the NewsAPI REST endpoint that returns the top headlines that match a given query.
 * For more information, refer to the REST API docs:
 * https://newsapi.org/docs/endpoints/top-headlines
 */
public abstract class StockGateway extends StockEndpoint {
    StockGateway() {
        super();
    }

    /**
     * Takes a raw JSON response string and produces an ApiArticlesResponse object from this data.
     * @param responseBody Raw JSON response from the NewsAPI REST endpoint
     * @return Representation of the data in the raw JSON response
     */
    ApiStockInformationResponse getDataFromResponseBody(String responseBody) {
    		ApiStockInformationResponse response = new ApiStockInformationResponse();
	    ArrayList<StockTimestampData> timestamps = new ArrayList<StockTimestampData>();
		try {
			JSONObject result = new JSONObject(responseBody);
			JSONObject timeseries = result.getJSONObject("Time Series (" + result.getJSONObject("Meta Data").getString("4. Interval") + ")");
			Iterator<?> keys = timeseries.keys();
			while( keys.hasNext() ) {
			    String key = (String)keys.next();
			    StockTimestampData timestampData = new StockTimestampData();
			    if (timeseries.get(key) instanceof JSONObject) {
			    		timestampData.setTimeStamp(key);
			    		timestampData.setOpen(timeseries.getJSONObject(key).getDouble("1. open"));
			    		timestampData.setClose(timeseries.getJSONObject(key).getDouble("4. close"));
			    		timestampData.setHigh(timeseries.getJSONObject(key).getDouble("2. high"));
			    		timestampData.setLow(timeseries.getJSONObject(key).getDouble("3. low"));
			    		timestampData.setVolume(timeseries.getJSONObject(key).getDouble("5. volume"));
			    }
			    timestamps.add(timestampData);
			}
			response.setInformation(result.getJSONObject("Meta Data").getString("1. Information"));
			response.setSymbol(result.getJSONObject("Meta Data").getString("2. Symbol"));
			response.setLastRefreshed(result.getJSONObject("Meta Data").getString("3. Last Refreshed"));
			response.setInterval(result.getJSONObject("Meta Data").getString("4. Interval"));
			response.setOutputSize(result.getJSONObject("Meta Data").getString("5. Output Size"));
			response.setTimezone(result.getJSONObject("Meta Data").getString("6. Time Zone"));
			response.setTimestamps(timestamps);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
    
    		/*ApiStockInformationResponse responseObj = (new Gson()).fromJson(responseBody,
    				ApiStockInformationResponse.class);
        responseObj.setRawJSON(responseBody);
        return responseObj;*/
    }

    /**
     * Sends an API request to the NewsAPI to this Endpoint, given a request with optional and
     * required request parameters.
     * @param apiRequest Request to send to this Endpoint
     * @param restClient Existing JAX-RS Client used to execute HTTP request. Takes this as a
     *                   parameter as opposed to creating a new Client object because Clients
     *                   are expensive to instantiate.
     * @return ApiArticlesResponse object that encapsulates the response from the REST endpoint.
     */
    public ApiStockInformationResponse sendRequest(StockRequestBuilder apiRequest,
                                           Client restClient) {
        WebTarget target = buildTarget(apiRequest, restClient);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON);
        System.out.println("External Stock call: " + target.getUri());
        builder.header("Cache-Control", "no-cache, no-store, must-revalidate");
        builder.header("Pragma", "no-cache");
        builder.header("Expires", "0");
        Response response = builder.header("X-Api-Key", apiRequest.getApikey()).get();
        String responseBody = response.readEntity(String.class);

        ApiStockInformationResponse responseObj = getDataFromResponseBody(responseBody);
        this.checkExceptions(response, responseObj);
        return responseObj;
    }
}
