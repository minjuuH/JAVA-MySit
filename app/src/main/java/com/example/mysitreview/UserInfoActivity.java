package com.example.mysitreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ImageView profileimage = (ImageView)findViewById(R.id.profileImage);
        profileimage.setImageResource(R.drawable.profile_photo);

        TextView reviseBnt = (TextView)findViewById(R.id.reviseBnt);

        EditText username = (EditText)findViewById(R.id.username);
        EditText userphone = (EditText)findViewById(R.id.userphone);
        EditText userpw = (EditText)findViewById(R.id.userpw);

        Button pwcheck = (Button)findViewById(R.id.checkBnt);
        Button infocomplete = (Button)findViewById(R.id.completeBnt);

        //수정 텍스트뷰 클릭 시 실행 이벤트
        reviseBnt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //비밀번호 입력 edittext 객체를 활성화
                userpw.setEnabled(true);
                userpw.setVisibility(View.VISIBLE);
                userpw.setText("");

                //비밀번호 확인 버튼 활성화
                pwcheck.setVisibility(View.VISIBLE);
                pwcheck.setEnabled(true);
            }
        });

        //비밀번호 확인 버튼 클릭 시 실행 이벤트
        pwcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviseBnt.setVisibility(View.GONE);

                //이름 입력 edittext 활성화
                username.setEnabled(true);
                username.setTextColor(getResources().getColor(R.color.black));

                //전화번호 입력 edittext 활성화
                userphone.setEnabled(true);
                userphone.setTextColor(getResources().getColor(R.color.black));

                //비밀번호 입력 edittext 설정 변경
                userpw.setText("비밀번호");
                userpw.setTextColor(getResources().getColor(R.color.black));

                pwcheck.setVisibility(View.GONE);
                pwcheck.setEnabled(false);

                //정보 수정 완료 버튼 활성화
                infocomplete.setVisibility(View.VISIBLE);
                infocomplete.setEnabled(true);
            }
        });

        //정보 수정 완료 버튼 클릭 시 실행 이벤트
        infocomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            //회원 정보 수정 작업 후 모든 edittext를 원상태(수정하지 못하는 상태)로 되돌리는 작업
            //실제 DB와 연결하는 작업 연결 필요
            public void onClick(View view) {
                reviseBnt.setVisibility(View.VISIBLE);

                //이름 입력 edittext 원상복귀
                username.setText(username.getText());
                username.setTextColor(getResources().getColor(R.color.text_gray));
                username.setEnabled(false);

                //전화번호 입력 edittext 원상복귀
                userphone.setText(userphone.getText());
                userphone.setTextColor(getResources().getColor(R.color.text_gray));
                userphone.setEnabled(false);

                //비밀번호 입력 edittext 숨기기
                userpw.setVisibility(View.GONE);
                userpw.setEnabled(false);

                infocomplete.setVisibility(View.GONE);
                infocomplete.setEnabled(false);

                Toast.makeText(getApplicationContext(), "수정 완료되었습니다.", Toast.LENGTH_SHORT).show();
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
