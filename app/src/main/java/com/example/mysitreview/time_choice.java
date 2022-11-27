package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class time_choice extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_choice);

        findViewById(R.id.timechoice).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(time_choice.this, time_booking_final.class));
            }
        });
    }
}
