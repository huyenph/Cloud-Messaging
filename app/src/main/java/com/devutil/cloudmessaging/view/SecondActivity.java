package com.devutil.cloudmessaging.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.devutil.cloudmessaging.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (getIntent().hasExtra("data")) {
            Toast.makeText(this, getIntent().getExtras().getString("data", ""), Toast.LENGTH_SHORT).show();
        }
    }
}
