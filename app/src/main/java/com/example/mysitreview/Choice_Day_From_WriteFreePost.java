package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Choice_Day_From_WriteFreePost extends AppCompatActivity {

    CalendarView calendarView1, calendarView2;
    TextView today1, today2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_day_from_write_free_post);
        today1 = findViewById(R.id.TVchdate);
        today2 = findViewById(R.id.TVchdate2);
        calendarView1 = findViewById(R.id.calendar);//캘린더뷰
        calendarView2 = findViewById(R.id.calendar2);//캘린더뷰
    DateFormat formatter = new SimpleDateFormat("yyyy년MM월dd일");
        Date date1 = new Date(calendarView1.getDate());
        Date date2 = new Date(calendarView2.getDate());
        today1.setText(formatter.format(date1));
        today2.setText(formatter.format(date2));
        calendarView1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String day;
                day = year + "년" + (month+1) + "월" + dayOfMonth +"일";
                today1.setText(day);
            }
        });
        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String day;
                day = year + "년" + (month+1) + "월" + dayOfMonth +"일";
                today2.setText(day);
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String date1, date2;
                date1 = today1.getText().toString();
                date2 = today2.getText().toString();
                Toast.makeText(getApplicationContext(), "날이 선택되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Choice_Day_From_WriteFreePost.this, WriteFreePostActivity.class);
                intent.putExtra("StartDate", date1);
                intent.putExtra("EndDate", date2);
                startActivity(intent);
            }
        });
    }

}