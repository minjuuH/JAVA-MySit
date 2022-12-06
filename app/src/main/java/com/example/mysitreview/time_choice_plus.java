package com.example.mysitreview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class time_choice_plus extends AppCompatActivity {
    String[] items = {"---시간 선택---", "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};

    RadioButton rdoCal, rdoTime;
    Button btnEnd;
    Spinner spinner1;
    TextView tvResult;
    CalendarView calView1;
    int selectYear, selectMonth, selectDay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_choice_plus);
        btnEnd = findViewById(R.id.btnEnd);
        rdoCal = findViewById(R.id.rdoCal);
        rdoTime = findViewById(R.id.rdoTime);
        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner1.setAdapter(adapter);
        tvResult = findViewById(R.id.tvResult);
        calView1 = findViewById(R.id.calView1);

        rdoCal.setOnClickListener(v -> {
            calView1.setVisibility(View.VISIBLE);
            spinner1.setVisibility(View.INVISIBLE);
        });

        rdoTime.setOnClickListener(v -> {
            calView1.setVisibility(View.INVISIBLE);
            spinner1.setVisibility(View.VISIBLE);
        });

        calView1.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectYear = year;
            selectMonth = month + 1;
            selectDay = dayOfMonth;
        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView < ? > parent, View view, int position, long id) {

                if (selectYear == 0)
                {
                    btnEnd.setOnClickListener(v -> tvResult.setText("날짜와 시간을 선택해야 예약이 가능합니다."));
                }

                else {
                    String s = (String) parent.getItemAtPosition(position);
                    btnEnd.setOnClickListener(v -> {
                        tvResult.setText(selectYear + "년" + selectMonth + "월" + selectDay + "일 " + s + "에 예약되었습니다.");

                        //값을 전달하기 위한 intent 생성
                Intent intent = new Intent(time_choice_plus.this, FinalTimeBooking.class);

                //time_booking_final로 값 전달
                intent.putExtra("년", selectYear); intent.putExtra("월", selectMonth); intent.putExtra("일", selectDay);
                intent.putExtra("분", s);
                intent.putExtra("title", getIntent().getStringExtra("title"));
                intent.putExtra("introduce", getIntent().getStringExtra("introduce"));

                //time_booking_final 시작
                startActivity(intent);
        });
                }}

            @Override
            public void onNothingSelected (AdapterView < ? > parent){
                btnEnd.setOnClickListener(v -> tvResult.setText("날짜와 시간을 선택해야 예약이 가능합니다."));
            }});
    }
}
