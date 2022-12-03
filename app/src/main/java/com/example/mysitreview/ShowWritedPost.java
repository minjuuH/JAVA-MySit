package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowWritedPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_writed_post);

        String title = getIntent().getStringExtra("title");
        String intro = getIntent().getStringExtra("introduce");

        TextView titleTextView = findViewById(R.id.titleEditText);
        TextView introTextView = findViewById(R.id.tv_introduce);

        titleTextView.setText(title);
        introTextView.setText(intro);

        findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ShowWritedPost.this, time_choice_plus.class));
            }
        });
    }
}