package com.threepie.stocknewsticker.response;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.threepie.stocknewsticker.datamodel.StockTimestampData;

public class ApiStockInformationResponse extends ApiResponse {
	private String symbol;
	private String lastRefreshed;
	private String interval;
	private String outputSize;
	private String timezone;
	private String information;
    private ArrayList<StockTimestampData> timestamps;

    public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLastRefreshed() {
		return lastRefreshed;
	}

	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getOutputSize() {
		return outputSize;
	}

	public void setOutputSize(String outputSize) {
		this.outputSize = outputSize;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public ArrayList<StockTimestampData> getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(ArrayList<StockTimestampData> timestamps) {
		this.timestamps = timestamps;
	}

	/**
     * @return JSONObject of news Articles.
     */
    public JSONObject getString() {
    		JSONObject obj = new JSONObject();
    		JSONArray arr = new JSONArray();

    		for(StockTimestampData timestamp : timestamps) {
    			arr.put(timestamp.getString());
    		}

    		try {
    			obj.put("symbol", symbol);
    			obj.put("lastRefreshed", lastRefreshed);
    			obj.put("interval", interval);
    			obj.put("outputSize", outputSize);
    			obj.put("timezone", timezone);
    			obj.put("information", information);
    			obj.put("data", arr);
			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}
    		return null;
    }
}
