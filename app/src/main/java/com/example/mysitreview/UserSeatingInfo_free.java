package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserSeatingInfo_free extends AppCompatActivity {
    //자유 예약 상세정보 액티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_final);

        Intent intent = getIntent();

        TextView bn = (TextView)findViewById(R.id.bookingname);
        TextView dt = (TextView)findViewById(R.id.datetext);

        bn.setText(intent.getStringExtra("title"));
        dt.setText(intent.getStringExtra("sdate"));


        Button cancleBnt = (Button) findViewById(R.id.booking_btn);
        cancleBnt.setText("예약 취소");

        cancleBnt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //추후 실제 취소 기능 연결 필요
                Toast.makeText(getApplicationContext(), "예약 취소되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserSeatingInfo_free.this, UserInfoActivity.class));
            }
        });
    }
}
