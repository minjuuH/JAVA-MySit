package com.example.mysitreview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_booking_final);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.ic_launcher);
    }
}