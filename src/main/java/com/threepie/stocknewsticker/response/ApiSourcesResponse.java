package com.threepie.stocknewsticker.response;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.threepie.stocknewsticker.datamodel.Source;

/**
 * Represents attributes that are common to the NewsAPI REST endpoints that respond with news
 * sources.
 */
public class ApiSourcesResponse extends ApiResponse {
    private ArrayList<Source> sources;

    public ApiSourcesResponse() {
        super();
    }

    void setSources(ArrayList<Source> sources) {
        this.sources = sources;
    }

    /**
     * @return ArrayList of news sources that NewsAPI allows, with each element of the ArrayList
     * representing a different news source.
     */
    public ArrayList<Source> sources() {
        return this.sources;
    }

	public JSONObject getString() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for(Source source : sources) {
			arr.put(source.getString());
		}
		try {
			obj.put("sources", arr);
			return obj;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

