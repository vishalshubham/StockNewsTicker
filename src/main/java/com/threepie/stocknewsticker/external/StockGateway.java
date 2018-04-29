package com.threepie.stocknewsticker.external;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.threepie.stocknewsticker.request.StockRequestBuilder;
import com.threepie.stocknewsticker.response.ApiStockInformationResponse;

/**
 * Represents the NewsAPI REST endpoint that returns the top headlines that match a given query.
 * For more information, refer to the REST API docs:
 * https://newsapi.org/docs/endpoints/top-headlines
 */
public class StockGateway extends StockEndpoint {
    /**
     * Instantiates a new TopEndpoint object. Sets the rootURL for the corresponding
     * REST endpoint.
     */
    public StockGateway() {
        super();
        this.setRootURL("https://www.alphavantage.co/query?");
    }

    /**
     * Constructs a WebTarget that encapsulates the specified queries and REST endpoint. Checks
     * for the presence of attributes in the apiRequest object that the top-headlines REST endpoint
     * accepts, and constructs a WebTarget with these attributes attached as query-string params.
     * @param apiRequest Request to construct query parameters out of.
     * @param restClient Existing JAX-RS Client used to execute HTTP request. Takes this as a
     *                   parameter as opposed to creating a new Client object because Clients
     *                   are expensive to instantiate.
     * @return WebTarget that contains the parameters from the apiRequest object encoded as a query
     * string for issuing as the actual GET request to the top-headlines REST endpoint.
     */
    WebTarget buildTarget(StockRequestBuilder apiRequest, Client restClient) {
        WebTarget target = restClient.target(this.getRootURL());
        if (apiRequest.getFunction() != null) {
            target = target.queryParam("function", apiRequest.getFunction());
        }
        if (apiRequest.getSymbol() != null) {
            target = target.queryParam("symbol", apiRequest.getSymbol());
        }
        if (apiRequest.getInterval() != null) {
            target = target.queryParam("interval", apiRequest.getInterval());
        }
        if (apiRequest.getOutputsize() != null) {
            target = target.queryParam("outputsize", apiRequest.getOutputsize());
        }
        if (apiRequest.getDatatype() != null) {
            target = target.queryParam("datatype", apiRequest.getDatatype());
        }
        return target;
    }

    /**
     * Takes a raw JSON response string and produces an ApiArticlesResponse object from this data.
     * @param responseBody Raw JSON response from the NewsAPI REST endpoint
     * @return Representation of the data in the raw JSON response
     */
    ApiStockInformationResponse getDataFromResponseBody(String responseBody) {
    		ApiStockInformationResponse responseObj = (new Gson()).fromJson(responseBody,
    				ApiStockInformationResponse.class);
        responseObj.setRawJSON(responseBody);
        return responseObj;
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

        Response response = builder.header("X-Api-Key", apiRequest.getApikey()).get();
        String responseBody = response.readEntity(String.class);

        ApiStockInformationResponse responseObj = getDataFromResponseBody(responseBody);
        this.checkExceptions(response, responseObj);
        return responseObj;
    }
}
