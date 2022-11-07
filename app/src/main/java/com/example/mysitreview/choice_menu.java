package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class choice_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_menu);

        findViewById(R.id.buttonReserv).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "자유 예약으로 이동합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(choice_menu.this, make_reserv_post.class));
            }
        });

        findViewById(R.id.buttonPlace).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "시간 선택으로 이동합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(choice_menu.this, time_choice.class));
            }
        });
    }
}