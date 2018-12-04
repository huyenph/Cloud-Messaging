package com.devutil.cloudmessaging.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestNotification {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    public RestNotification(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title != null ? title : "";
    }

    public String getBody() {
        return body != null ? body : "";
    }
}
