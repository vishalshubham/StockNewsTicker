package com.threepie.stocknewsticker;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import com.threepie.stocknewsticker.request.NewsRequestBuilder;
import com.threepie.stocknewsticker.response.ApiArticlesResponse;
import com.threepie.stocknewsticker.response.ApiSourcesResponse;
import com.threepie.stocknewsticker.utils.Constants;
import com.threepie.stocknewsticker.external.TopGateway;
import com.threepie.stocknewsticker.external.EverythingGateway;
import com.threepie.stocknewsticker.external.SourceGateway;

/**
 * Java wrapper for the NewsAPI, encapsulating all API endpoints and possible queries.
 */
public class NewsApi {
    private String apiKey;
    private TopGateway topGateway;
    private EverythingGateway everythingGateway;
    private SourceGateway sourceGateway;
    private Client restClient;

    public NewsApi() {
        this.setApiKey();
        initializeEndpoints();
    }

    private void initializeEndpoints() {
        this.topGateway = new TopGateway();
        this.everythingGateway = new EverythingGateway();
        this.sourceGateway = new SourceGateway();
    }

    /**
     * Sets the API key in the given NewsRequestBuilder object, and also instantiates a new JAX-RS
     * client with which to issue HTTP requests, if one does not already exist. The JAX-RS client
     * is initialized here, and reused for all REST interactions, because instantiating a new
     * JAX-RS client is costly.
     * @param apiRequest Request to send to this Endpoint
     */
    private void initializeRequestAndClient(NewsRequestBuilder apiRequest) {
        apiRequest.setApikey(this.apiKey);
        if (this.restClient == null) {
            this.restClient = ClientBuilder.newClient();
        }
    }

    public void setApiKey() {
        this.apiKey = Constants.NEWS_API_KEY;
    }

    /**
     * Consumes the top-headlines endpoint. To view documentation on the actual REST endpoint,
     * visit https://newsapi.org/docs/endpoints/top-headlines
     * @param apiRequest Request to send to this Endpoint
     * @return ApiArticlesResponse object that is mapped from the response of this call to the
     * top-headlines REST endpoint.
     */
    public ApiArticlesResponse sendTopRequest(NewsRequestBuilder apiRequest) {
        initializeRequestAndClient(apiRequest);
        return this.topGateway.sendRequest(apiRequest, this.restClient);
    }

    /**
     * Consumes the everything endpoint. To view documentation on the actual REST endpoint, visit
     * https://newsapi.org/docs/endpoints/everything
     * @param apiRequest Request to send to this Endpoint
     * @return ApiArticlesResponse object that is mapped from the response of this call to the
     * everything REST endpoint.
     */
    public ApiArticlesResponse sendEverythingRequest(NewsRequestBuilder apiRequest) {
        initializeRequestAndClient(apiRequest);
        return this.everythingGateway.sendRequest(apiRequest, this.restClient);
    }

    /**
     * Consumes the sources endpoint. To view documentation on the actual REST endpoint, visit
     * https://newsapi.org/docs/endpoints/sources
     * @param apiRequest Request to send to this Endpoint
     * @return ApiSourcesResponse object that is mapped from the response of this call to the
     * everything REST endpoint.
     */
    public ApiSourcesResponse sendSourcesRequest(NewsRequestBuilder apiRequest) {
        initializeRequestAndClient(apiRequest);
        return this.sourceGateway.sendRequest(apiRequest, this.restClient);
    }

}
