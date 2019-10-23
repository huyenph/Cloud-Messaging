package com.devutil.cloudmessaging.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.devutil.cloudmessaging.R;
import com.devutil.cloudmessaging.model.RestNotification;
import com.devutil.cloudmessaging.model.RestResponse;
import com.devutil.cloudmessaging.model.RestSender;
import com.devutil.cloudmessaging.network.RetrofitClient;
import com.devutil.cloudmessaging.service.MyFirebaseMessaging;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etTitle, etMessage;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etTitle);
        etMessage = findViewById(R.id.etMessage);
        Log.d("aaa", "onCreate: ");
        Log.d("aaa", "onCreate: ");

        findViewById(R.id.tvSendToken).setOnClickListener(this);
        findViewById(R.id.tvSendTopic).setOnClickListener(this);

        //TODO: Get token
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {
                    token = task.getResult().getToken();
                    Log.d("aaa", token);
                }
            }
        });

        //TODO: Send topic (not token)
        FirebaseMessaging.getInstance().subscribeToTopic("MyTopic");


    }

    @Override
    public void onClick(View v) {
        RestNotification notification = new RestNotification(etTitle.getText().toString(), etMessage.getText().toString());
        RestSender sender = new RestSender(notification, MyFirebaseMessaging.token);
        switch (v.getId()) {
            case R.id.tvSendToken:
                sender = new RestSender(notification, MyFirebaseMessaging.token);
                break;
            case R.id.tvSendTopic:
                sender = new RestSender(notification, "/topics/MyTopic");
                break;
        }
        Call<RestResponse> call = RetrofitClient.getData().sendNotification(sender);
        call.enqueue(new Callback<RestResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestResponse> call, @NonNull retrofit2.Response<RestResponse> response) {
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        Toast.makeText(MainActivity.this, "Send token success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
