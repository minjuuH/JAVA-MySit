package com.example.mysitreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowFreeWritedPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_free_writed_post);

        String title = getIntent().getStringExtra("title");
        String intro = getIntent().getStringExtra("introduce");

        TextView titleTextView = findViewById(R.id.titleEditText);
        TextView introTextView = findViewById(R.id.tv_introduce);

        titleTextView.setText(title);
        introTextView.setText(intro);

        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ShowFreeWritedPost.this, FinalBooking.class));
            }
        });
    }
}