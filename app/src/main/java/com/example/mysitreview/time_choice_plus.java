package com.example.mysitreview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class time_choice_plus extends AppCompatActivity {

    Chronometer chrono1;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    TimePicker tPicker1;
    TextView tvResult;
    CalendarView calView1;

    int selectYear, selectMonth, selectDay;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_choice_plus);
        chrono1=findViewById(R.id.chrono1);
        btnStart=findViewById(R.id.btnStart);
        btnEnd=findViewById(R.id.btnEnd);
        rdoCal=findViewById(R.id.rdoCal);
        rdoTime=findViewById(R.id.rdoTime);
        tPicker1=findViewById(R.id.tPicker1);
        tvResult=findViewById(R.id.tvResult);
        calView1=findViewById(R.id.calView1);

        btnStart.setOnClickListener(v -> {
            chrono1.setBase(SystemClock.elapsedRealtime());
            chrono1.start();
            chrono1.setTextColor(Color.RED);
        });

        rdoCal.setOnClickListener(v -> {
            calView1.setVisibility(View.VISIBLE);
            tPicker1.setVisibility(View.INVISIBLE);
        });

        rdoTime.setOnClickListener(v -> {
            calView1.setVisibility(View.INVISIBLE);
            tPicker1.setVisibility(View.VISIBLE);
        });

        btnEnd.setOnClickListener(v -> {
            chrono1.stop();
            chrono1.setTextColor(Color.BLACK);
            tvResult.setText(selectYear+"년"+selectMonth+"월"+selectDay+"일"+tPicker1.getCurrentHour()+"시"+tPicker1.getCurrentMinute()+"분에 예약되었습니다.");

            //값을 전달하기 위한 intent 생성
            Intent intent = new Intent(time_choice_plus.this, time_booking_final.class);

            //time_booking_final로 값 전달
            intent.putExtra("년", selectYear); intent.putExtra("월", selectMonth); intent.putExtra("일", selectDay);
            intent.putExtra("시", tPicker1.getCurrentHour()); intent.putExtra("분", tPicker1.getCurrentMinute());

            //time_booking_final 시작
            startActivity(intent);
        });

       calView1.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
           selectYear=year;
           selectMonth=month+1;
           selectDay=dayOfMonth;
       });


    }
}