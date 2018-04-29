package com.threepie.stocknewsticker.external.stock;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import com.threepie.stocknewsticker.request.StockRequestBuilder;

/**
 * Represents the NewsAPI REST endpoint that returns the top headlines that match a given query.
 * For more information, refer to the REST API docs:
 * https://newsapi.org/docs/endpoints/top-headlines
 */
public class IntradayGateway extends StockGateway {
    /**
     * Instantiates a new TopEndpoint object. Sets the rootURL for the corresponding
     * REST endpoint.
     */
    public IntradayGateway() {
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
        if (apiRequest.getApikey() != null) {
            target = target.queryParam("apikey", apiRequest.getApikey());
        }
        return target;
    }
}
