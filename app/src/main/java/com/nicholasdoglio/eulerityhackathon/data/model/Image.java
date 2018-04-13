package com.nicholasdoglio.eulerityhackathon.data.model;

import com.squareup.moshi.Json;

/**
 * @author Nicholas Doglio
 */
public class Image {

    @Json(name = "url")
    private String url;
    @Json(name = "created")
    private String created;
    @Json(name = "updated")
    private String updated;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
