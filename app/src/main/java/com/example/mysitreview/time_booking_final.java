package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class time_booking_final extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_booking_final);

        //텍스트필드 객체 받아옴
        TextView bn = (TextView)findViewById(R.id.bookingname);
        TextView bi = (TextView)findViewById(R.id.bookinginfo);
        TextView nt = (TextView)findViewById(R.id.nametext);
        TextView pt = (TextView)findViewById(R.id.phonetext);
        TextView dt = (TextView)findViewById(R.id.datetext);
        TextView tt = (TextView)findViewById(R.id.timetext);

        //추후 데이터 연결 작업 필요
        bn.setText("장소명");
        bi.setText("장소 상세 정보");
        nt.setText("예약자");
        pt.setText("010-xxxx-xxxx");
        dt.setText("20xx.xx.xx(일)");
        tt.setText("00:00 - 00:00");

        findViewById(R.id.pbooking_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "예약 완료되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(time_booking_final.this, choice_menu.class));
            }
        });
    }
}