package com.threepie.stocknewsticker.request;

/**
 * RequestBuilder is a Builder class that constructs a request based on given request parameters.
 * This class is sent to Endpoint objects, which take the RequestBuilder and construct a
 * query-string out of the given RequestBuilder in order to issue the appropriate GET request to
 * NewsAPI's REST endpoints.
 */
public interface RequestBuilder {
    public String apiKey = null;
}