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
//        String data = getIntent().getStringExtra("data");
//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                if (key.equals("title")) {
                    Toast.makeText(this, getIntent().getExtras().getString(key), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
