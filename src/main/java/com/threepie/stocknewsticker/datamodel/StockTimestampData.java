package com.threepie.stocknewsticker.datamodel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Maps to an StockData object from the alphavantageAPI.
 */
public class StockTimestampData {
	private String timeStamp;
	private Double open;
	private Double close;
	private Double high;
	private Double low;
	private Double volume;

    public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

    public JSONObject getString() {
    		JSONObject obj = new JSONObject();
		try {
			obj.put("time", timeStamp);
			obj.put("open", open);
			obj.put("close", close);
			obj.put("high", high);
			obj.put("low", low);
			obj.put("volume", volume);
			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
    }
}
