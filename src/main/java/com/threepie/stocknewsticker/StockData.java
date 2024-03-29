/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.threepie.stocknewsticker;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Controller
@SpringBootApplication
public class StockData {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  private final String USER_AGENT = "Mozilla/5.0";

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(StockData.class, args);
    System.out.println(args.toString());
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

  @RequestMapping("/getStockData")
  String getStockData(Map<String, Object> model) {
    try {

      URL obj = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=60min&apikey=F0FYC65W5BBQBQI4");
  
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
  
      // optional default is GET
      con.setRequestMethod("GET");
  
      //add request header
      con.setRequestProperty("User-Agent", USER_AGENT);
  
      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=60min&apikey=F0FYC65W5BBQBQI4");
      System.out.println("Response Code : " + responseCode);
  
      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
  
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
  
      //print result
      System.out.println(response.toString());
      return response.toString();

    } catch (Exception e) {
      System.out.println(e);
    }
    return "Sorry no data available due to internal error";
  }


  @RequestMapping("/hello")
  String dbb(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add(model.toString() + "Vishal read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }


	/*
	 * In stockhandler before
	 * public JSONObject getStockData(
  		String symbol) {
		JSONObject result = new JSONObject();
		try {
			final String stockUri = 
					Constants.STOCK_BASE_URL + Constants.STOCK_API +
					"?apikey=" + Constants.STOCK_API_KEY +
					"&symbol=" + symbol +
					"&function=TIME_SERIES_INTRADAY" +
					"&interval=5min" +
					"&outputsize=full";
			JSONObject stockNode = new JSONObject(ApiCaller.callApi(stockUri));
			JSONObject metadata = (JSONObject) stockNode.get("Meta Data");
			String date = metadata.getString("3. Last Refreshed");
			String currentTime = date.replace(" ", "T");

			result.put("data", stockNode);
			result.put("news", getStockNews(symbol, currentTime));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getStockNews(
  		String symbol,
  		String time) {
		try {
			final String newsUri = 
					Constants.NEWS_BASE_URL + Constants.NEWS_API +
					"?apiKey=" + Constants.NEWS_API_KEY +
					"&q=" + symbol +
					"&from=" + time.substring(0, 10) +
					"&to=" + time.substring(0, 10) +
					"&language=en" +
					"&sortBy=popularity";
			JSONObject obj = new JSONObject(ApiCaller.callApi(newsUri));
			JSONObject result  = new JSONObject();
			result.put("data", obj.get("articles"));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
