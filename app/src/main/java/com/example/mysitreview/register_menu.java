package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override // 아이디 비교
            public void onClick(View v) {
                EditText editText1 = (EditText) findViewById(R.id.edit_id);
                String enterId = editText1.getText().toString(); // enterID에 아이디값 저장
                Toast.makeText(getApplicationContext(), "사용가능한 ID입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override // 비밀번호 확인
            public void onClick(View v) {
                EditText editText2 = (EditText) findViewById(R.id.edit_pw);
                String enterpw = editText2.getText().toString(); // enterpw에 비밀번호값 저장
                Toast.makeText(getApplicationContext(), "유효한 비밀번호입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.pbooking_btn).setOnClickListener(new View.OnClickListener() {
            @Override // 회원가입
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "회원등록 되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(register_menu.this, MainActivity.class));
            }
        });

    }
}