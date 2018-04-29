package com.threepie.stocknewsticker;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import com.threepie.stocknewsticker.request.StockRequestBuilder;
import com.threepie.stocknewsticker.response.ApiStockInformationResponse;
import com.threepie.stocknewsticker.utils.Constants;
import com.threepie.stocknewsticker.external.IntradayGateway;

/**
 * Java wrapper for the NewsAPI, encapsulating all API endpoints and possible queries.
 */
public class StockApi {
    private String apiKey;
    private IntradayGateway intradayGateway;
    private Client restClient;

    public StockApi() {
        this.setApiKey();
        initializeEndpoints();
    }

    private void initializeEndpoints() {
        this.intradayGateway = new IntradayGateway();
    }

    /**
     * Sets the API key in the given StockRequestBuilder object, and also instantiates a new JAX-RS
     * client with which to issue HTTP requests, if one does not already exist. The JAX-RS client
     * is initialized here, and reused for all REST interactions, because instantiating a new
     * JAX-RS client is costly.
     * @param apiRequest Request to send to this Endpoint
     */
    private void initializeRequestAndClient(StockRequestBuilder apiRequest) {
        apiRequest.setApikey(this.apiKey);
        if (this.restClient == null) {
            this.restClient = ClientBuilder.newClient();
        }
    }

    public void setApiKey() {
        this.apiKey = Constants.STOCK_API_KEY;
    }

    /**
     * Consumes the top-headlines endpoint. To view documentation on the actual REST endpoint,
     * visit https://newsapi.org/docs/endpoints/top-headlines
     * @param apiRequest Request to send to this Endpoint
     * @return ApiStockInformationResponse object that is mapped from the response of this call to the
     * top-headlines REST endpoint.
     */
    public ApiStockInformationResponse sendStockRequest(StockRequestBuilder apiRequest) {
        initializeRequestAndClient(apiRequest);
        return this.intradayGateway.sendRequest(apiRequest, this.restClient);
    }
}
