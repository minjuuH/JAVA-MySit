package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FinalTimeBooking extends AppCompatActivity {

    //두자리 서식 지정을 위한 함수 지정
    public String format(int n){
        if(n<10)
            return "0"+Integer.toString(n);
        return Integer.toString(n);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_booking_final);

        //이미지 출력
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.ic_launcher);

        //텍스트필드 객체 받아옴
        TextView bn = (TextView)findViewById(R.id.bookingname);
        TextView bi = (TextView)findViewById(R.id.bookinginfo);
        TextView nt = (TextView)findViewById(R.id.nametext);
        TextView pt = (TextView)findViewById(R.id.phonetext);
        TextView dt = (TextView)findViewById(R.id.datetext);
        TextView tt = (TextView)findViewById(R.id.timetext);

        Intent intent = getIntent();

        //intet에서 받아온 값의 출력 서식 지정
        String dateText = Integer.toString(intent.getIntExtra("년", 0))+"-"+format(intent.getIntExtra("월", 0))
                +"-"+format(intent.getIntExtra("일", 0));
        String timeText = format(intent.getIntExtra("분",0));

        //추후 데이터 연결 작업 필요
        bn.setText("장소명");
        bi.setText("장소 상세 정보");
        nt.setText("예약자");
        pt.setText("010-xxxx-xxxx");
        dt.setText(dateText);
        tt.setText(timeText);

        findViewById(R.id.pbooking_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "예약 완료되었습니다.", Toast.LENGTH_SHORT).show();

                //엑티비티 하나를 제외하고 나머지 히스토리를 모두 지우는 코드
                Intent i = new Intent(FinalTimeBooking.this, choice_menu.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
}