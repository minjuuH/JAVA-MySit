package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//회원 등록 정보 삭제 후 다시 테스트
public class RegisterComplete extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complete);

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //엑티비티 하나를 제외하고 나머지 히스토리를 모두 지우는 코드
                Intent i = new Intent(RegisterComplete.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
}
