package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "회원등록으로 이동합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, register_menu.class));
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(MainActivity.this, choice_menu.class));
            }
        });


    }
}