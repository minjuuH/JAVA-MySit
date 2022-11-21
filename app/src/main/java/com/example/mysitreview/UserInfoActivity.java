package com.example.mysitreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        EditText username = (EditText)findViewById(R.id.username);
        EditText useremail = (EditText)findViewById(R.id.useremail);
        EditText userphone = (EditText)findViewById(R.id.userphone);
        EditText userpw = (EditText)findViewById(R.id.userpw);

        findViewById(R.id.reviseBnt).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "수정 텍스트뷰 클릭되었습니다.", Toast.LENGTH_SHORT).show();

                userpw.setEnabled(true);
                userpw.setHintTextColor(getResources().getColor(R.color.text_gray));
            }
        });

        findViewById(R.id.logoutBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();

                //엑티비티 하나를 제외하고 나머지 히스토리를 모두 지우는 코드
                Intent i = new Intent(UserInfoActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
            }
        });
    }
}
