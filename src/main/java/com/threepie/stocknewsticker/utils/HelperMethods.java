package com.threepie.stocknewsticker.utils;

import java.text.DateFormat;
import java.util.Date;

public class HelperMethods {

	public static void main(String[] args) {
		HelperMethods n = new HelperMethods();
		System.out.println(new Date().toString() + "\n" + 
		DateFormat.AM_PM_FIELD + "\n" +
		DateFormat.DATE_FIELD + "\n" +
		DateFormat.DAY_OF_WEEK_FIELD + "\n" +
		DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD + "\n");
	}
	public static String newsStockParams(String symbol) {
		return "&q=" + symbol;
	}
}
