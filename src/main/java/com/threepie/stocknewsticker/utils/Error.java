package com.threepie.stocknewsticker.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Error {
	public static String sendError(String status, String code, String message) {
		try {
			return new JSONObject()
					.put("status", status)
					.put("code", code)
					.put("message", message).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
