package com.devutil.cloudmessaging.network;

import com.devutil.cloudmessaging.model.RestResponse;
import com.devutil.cloudmessaging.model.RestSender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAA609ZdiY:APA91bFHqbf907DTcaNIZOJs89Wkw6xyGhdTJ4MmJrJz_E7qa8-MYhcEhVGMsJO68h1WgHOhDct9hM2jsvssUvFZ1bBO3ZMuXcVoaCs_SzeOmJrCsMh3q-f003_aK3WQhoJGWNlPNoy1G2i9aIPIuB-jUZZL4daU3g"
    })
    @POST("fcm/send")
    Call<RestResponse> sendNotification(@Body RestSender sender);
}
