package com.threepie.stocknewsticker.external;

import com.google.gson.Gson;
import com.threepie.stocknewsticker.request.NewsRequestBuilder;
import com.threepie.stocknewsticker.response.ApiArticlesResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Endpoint that returns ApiArticleResponse objects. Classes derived from this abstract class
 * all represent Endpoints that return Articles.
 */
public abstract class ArticleGateway extends NewsEndpoint {
	ArticleGateway() {
        super();
    }

    /**
     * Takes a raw JSON response string and produces an ApiArticlesResponse object from this data.
     * @param responseBody Raw JSON response from the NewsAPI REST endpoint
     * @return Representation of the data in the raw JSON response
     */
    ApiArticlesResponse getDataFromResponseBody(String responseBody) {
        ApiArticlesResponse responseObj = (new Gson()).fromJson(responseBody,
                ApiArticlesResponse.class);
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
    public ApiArticlesResponse sendRequest(NewsRequestBuilder apiRequest,
                                           Client restClient) {
        WebTarget target = buildTarget(apiRequest, restClient);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON);
        System.out.println("External News call: " + target.getUri());
        Response response = builder.header("X-Api-Key", apiRequest.getApikey()).get();
        String responseBody = response.readEntity(String.class);

        ApiArticlesResponse responseObj = getDataFromResponseBody(responseBody);
        this.checkExceptions(response, responseObj);
        return responseObj;
    }
}
