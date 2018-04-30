package com.threepie.stocknewsticker.datamodel;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Maps to an Article object from the NewsAPI.
 */
public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public void setSource(Source source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        if (Objects.equals(author, "")) {
            this.author = null;
        }
        else {
            this.author = author;
        }
    }

    public void setTitle(String title) {
        if (Objects.equals(title, "")) {
            this.title = null;
        }
        else {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        if (Objects.equals(description, "")) {
            this.description = null;
        }
        else {
            this.description = description;
        }
    }

    public void setUrl(String url) {
        if (Objects.equals(url, "")) {
            this.url = null;
        }
        else {
            this.url = url;
        }
    }

    public void setUrlToImage(String urlToImage) {
        if (Objects.equals(urlToImage, "")) {
            this.urlToImage = null;
        }
        else {
            this.urlToImage = urlToImage;
        }
    }

    public void setPublishedAt(String publishedAt) {
        if (Objects.equals(publishedAt, "")) {
            this.publishedAt = null;
        }
        else {
            this.publishedAt = publishedAt;
        }
    }

    /**
     * @return Source that this article is from.
     */
    public Source source() {
        return this.source;
    }

    /**
     * @return Author of this article
     */
    public String author() {
        this.setAuthor(this.author);
        return this.author;
    }

    /**
     * @return Title of this article
     */
    public String title() {
        this.setTitle(this.title);
        return this.title;
    }

    /**
     * @return Description of the current article
     */
    public String description() {
        this.setDescription(this.description);
        return this.description;
    }

    /**
     * @return String URL at which this article can be found
     */
    public String url() {
        this.setUrl(this.url);
        return this.url;
    }

    /**
     * @return String URL to main image of this article
     */
    public String urlToImage() {
        this.setUrlToImage(this.urlToImage);
        return this.urlToImage;
    }

    /**
     * @return String date during which this article was published, in ISO 8601 format:
     * yyyy-MM-ddTHH:mm:ssZ
     * e.g., 2017-12-25T18:32:56Z for December 25, 2017 at 06:32:56 PM UTC
     */
    public String publishedAt() {
        this.setPublishedAt(this.publishedAt);
        return this.publishedAt;
    }

    public JSONObject getString() {
    		JSONObject obj = new JSONObject();
		try {
			obj.put("source", source.getString());
			obj.put("author", author);
			obj.put("title", title);
			obj.put("description", description);
			obj.put("url", url);
			obj.put("urlToImage", urlToImage);
			obj.put("publishedAt", publishedAt);
			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
    }
}
