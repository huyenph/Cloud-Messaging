package com.devutil.cloudmessaging.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestSender {
    @SerializedName("notification")
    @Expose
    private RestNotification notification;
    @SerializedName("to")
    @Expose
    private String to;

    public RestSender(RestNotification notification, String to) {
        this.notification = notification;
        this.to = to;
    }

    public RestNotification getNotification() {
        return notification;
    }

    public String getTo() {
        return to != null ? to : "";
    }
}
