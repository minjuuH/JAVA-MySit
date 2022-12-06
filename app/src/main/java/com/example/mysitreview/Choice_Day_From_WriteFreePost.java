package com.example.mysitreview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;


public class Choice_Day_From_WriteFreePost extends AppCompatActivity {

    Button add;
    CalendarView calendarView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_day_from_write_free_post);
        add = (Button)findViewById(R.id.button7);
        calendarView = (CalendarView)findViewById(R.id.calendar);//캘린더뷰
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재의 날짜와 시간을 가지고 있는 캘린더 객체 생성
                Calendar calendar = Calendar.getInstance();
                //캘린더뷰에서 날짜값 읽어오기
                Date date = new Date(calendarView.getDate());
                //캘린더 객체에 캘린더뷰 값을 넣음
                calendar.setTime(date);
                //캘린더뷰 값을 넣은 캘린더 객체의 날짜값을 문자열로 변환!
                // ex)2021-03-14
                String caldate = Integer.toString(calendar.get(Calendar.YEAR))+ "-" + Integer.toString(calendar.get(Calendar.MONTH)) + "-" +Integer.toString(calendar.get(Calendar.DATE));
                textView.setText(caldate);
            }
        });
    }

}