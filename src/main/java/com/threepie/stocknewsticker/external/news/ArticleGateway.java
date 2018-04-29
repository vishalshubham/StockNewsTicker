package com.threepie.stocknewsticker.external.news;

import com.threepie.stocknewsticker.datamodel.Article;
import com.threepie.stocknewsticker.datamodel.Source;
import com.threepie.stocknewsticker.request.NewsRequestBuilder;
import com.threepie.stocknewsticker.response.ApiArticlesResponse;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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
    		ApiArticlesResponse response = new ApiArticlesResponse();
    	    ArrayList<Article> articles = new ArrayList<Article>();
    		try {
    			JSONObject result = new JSONObject(responseBody);
			JSONArray array = result.getJSONArray("articles");
			for(int i=0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				Article article = new Article();
				Source source = new Source();
				
				source.setId(obj.getJSONObject("source").getString("id"));
				source.setName(obj.getJSONObject("source").getString("name"));
				article.setSource(source);
				article.setAuthor(obj.getString("author"));
				article.setTitle(obj.getString("title"));
				article.setDescription(obj.getString("description"));
				article.setUrl(obj.getString("url"));
				article.setUrlToImage(obj.getString("urlToImage"));
				article.setPublishedAt(obj.getString("publishedAt"));
				articles.add(article);
			}
			response.setTotalResults(result.getInt("totalResults"));
			response.setArticles(articles);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        return response;
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
        builder.header("Cache-Control", "no-cache, no-store, must-revalidate");
        builder.header("Pragma", "no-cache");
        builder.header("Expires", "0");
        Response response = builder.header("X-Api-Key", apiRequest.getApikey()).get();
        String responseBody = response.readEntity(String.class);

        ApiArticlesResponse responseObj = getDataFromResponseBody(responseBody);
        this.checkExceptions(response, responseObj);
        return responseObj;
    }
}
