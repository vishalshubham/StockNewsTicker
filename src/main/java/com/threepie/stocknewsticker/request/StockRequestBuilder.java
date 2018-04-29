package com.threepie.stocknewsticker.request;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * StockRequestBuilder is a Builder class that constructs a request based on given request parameters.
 * This class is sent to Endpoint objects, which take the StockRequestBuilder and construct a
 * query-string out of the given StockRequestBuilder in order to issue the appropriate GET request to
 * NewsAPI's REST endpoints.
 */
public class StockRequestBuilder{
    private String function;
    private String symbol;
    private String interval;
    private String outputsize;
    private String datatype;
    private String apikey;

    static private final Set<String> validIntervals = Sets.newHashSet("1min", "5min", "15min",
    			"30min", "60min");
    static private final Set<String> validOutputSizes = Sets.newHashSet("compact", "full");
    static private final Set<String> validDatatypes = Sets.newHashSet("json", "csv");

    public String getFunction() {
		return function;
	}

	public StockRequestBuilder setFunction(String function) {
		this.function = function;
		return this;
	}

	public String getSymbol() {
		return symbol;
	}

	public StockRequestBuilder setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	public String getInterval() {
		return interval;
	}

	public StockRequestBuilder setInterval(String interval) {
        if (!validIntervals.contains(interval)) {
            throw new IllegalArgumentException("Invalid interval - must be one of the following: "
                    + String.join(", ", validIntervals));
        }
        this.interval = interval;
        return this;
    }

	public String getOutputsize() {
		return outputsize;
	}

	public StockRequestBuilder setOutputsize(String outputsize) {
        if (!validOutputSizes.contains(outputsize)) {
            throw new IllegalArgumentException("Invalid output size - must be one of the following: "
                    + String.join(", ", validOutputSizes));
        }
        this.outputsize = outputsize;
        return this;
    }

	public String getDatatype() {
		return datatype;
	}

	public StockRequestBuilder setDatatype(String datatype) {
        if (!validDatatypes.contains(datatype)) {
            throw new IllegalArgumentException("Invalid datatypes - must be one of the following: "
                    + String.join(", ", validDatatypes));
        }
        this.datatype = datatype;
        return this;
    }

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
}