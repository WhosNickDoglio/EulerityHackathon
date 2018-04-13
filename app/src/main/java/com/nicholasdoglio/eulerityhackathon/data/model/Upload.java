package com.nicholasdoglio.eulerityhackathon.data.model;

import com.squareup.moshi.Json;

/**
 * @author Nicholas Doglio
 */
public class Upload {

    @Json(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}