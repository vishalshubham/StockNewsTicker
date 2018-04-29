package com.threepie.stocknewsticker.response;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.threepie.stocknewsticker.datamodel.Article;

/**
 * Represents attributes that are common to the NewsAPI REST endpoints.
 */
public class ApiArticlesResponse extends ApiResponse {
    private Integer totalResults;
    private ArrayList<Article> articles;

    public ApiArticlesResponse() {
        super();
        this.setTotalResults(0);
    }

    void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    /**
     * @return Total number of news articles in the response.
     */
    public Integer totalResults() {
        return this.totalResults;
    }

    /**
     * @return ArrayList of news Articles.
     */
    public ArrayList<Article> articles() {
        return this.articles;
    }

    /**
     * @return JSONObject of news Articles.
     */
    public JSONObject getString() {
    		JSONObject obj = new JSONObject();
    		JSONArray arr = new JSONArray();

    		for(Article article : articles) {
    			arr.put(article.getString());
    		}

    		try {
			obj.put("total", totalResults);
			obj.put("data", arr);
			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}
    		return null;
    }
}
