package com.devutil.cloudmessaging.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestResponse {
    @SerializedName("multicast_id")
    @Expose
    private String multicastId;
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("failure")
    @Expose
    private int failure;
    @SerializedName("canonical_ids")
    @Expose
    private int canonicalIds;
    @SerializedName("results")
    @Expose
    private List<RestResult> results = null;

    public String getMulticastId() {
        return multicastId != null ? multicastId : "";
    }

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }

    public int getCanonicalIds() {
        return canonicalIds;
    }

    public List<RestResult> getResults() {
        return results;
    }
}
