package com.devutil.cloudmessaging.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestResult {
    @SerializedName("message_id")
    @Expose
    private String messageId;

    public String getMessageId() {
        return messageId != null ? messageId : "";
    }
}
