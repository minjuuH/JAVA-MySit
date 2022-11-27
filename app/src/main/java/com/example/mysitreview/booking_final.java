package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class booking_final extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_final);

        //추후 데이터 연결 작업 필요
        String name = "예약자";
        String phoneNum = "010-xxxx-xxxx";
        String date = "20xx.xx.xx - 20xx.xx.xx";
        int full = 50; int real = 7;

        //텍스트필드 객체 받아옴
        TextView bn = (TextView)findViewById(R.id.bookingname);
        TextView bi = (TextView)findViewById(R.id.bookinginfo);
        TextView nt = (TextView)findViewById(R.id.nametext);
        TextView pt = (TextView)findViewById(R.id.phonetext);
        TextView dt = (TextView)findViewById(R.id.datetext);
        TextView ct = (TextView)findViewById(R.id.counttext);
        TextView rv = (TextView)findViewById(R.id.remainView);

        //추후 데이터 연결 작업 필요
        bn.setText("예약 이름");
        bi.setText("예약 상세 정보");
        nt.setText(name);
        pt.setText(phoneNum);
        dt.setText(date);
        ct.setText(Integer.toString(real)+"/"+Integer.toString(full));
        rv.setText("잔여 : "+Integer.toString(full-real));

        findViewById(R.id.booking_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "예약 완료되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(booking_final.this, choice_menu.class));
            }
        });
    }
}
