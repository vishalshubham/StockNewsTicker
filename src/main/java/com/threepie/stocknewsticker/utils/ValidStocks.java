package com.threepie.stocknewsticker.utils;

import java.util.HashMap;

public class ValidStocks {

    private HashMap<String, String> validStocks = new HashMap<>();

    public ValidStocks() {
    		System.out.println("Initialized");
    		initializeStocks();
    }
    
    public boolean isValid(String symbol) {
    		return validStocks.containsKey(symbol.toUpperCase());
    }
    
    public void initializeStocks() {
		validStocks.put("FB"  , "Facebook");
		validStocks.put("AMZN", "Amazon");
		validStocks.put("AAPL", "Apple");
		validStocks.put("NFLX", "Netfliw");
		validStocks.put("GOOG", "Google");
    }
}