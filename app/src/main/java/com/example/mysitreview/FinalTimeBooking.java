package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinalTimeBooking extends AppCompatActivity {
    private DatabaseReference mDatabaseRefUser; // 실시간 데이터베이스
    private DatabaseReference mDatabaseRefPlace; // 실시간 데이터베이스
    private FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증
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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRefUser = FirebaseDatabase.getInstance().getReference("myseat");
        mDatabaseRefPlace = FirebaseDatabase.getInstance().getReference("장소 글작성 정보");
        FirebaseUser User = mFirebaseAuth.getCurrentUser(); // 회원가입된 유저 가져옴
        mDatabaseRefUser.child("UserAccount").child(User.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FinalPlaceReservation fpr = snapshot.getValue(FinalPlaceReservation.class);
                nt.setText(fpr.getName());
                pt.setText(fpr.getPhNum());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Intent intent = getIntent();
        //intet에서 받아온 값의 출력 서식 지정
        String dateText = Integer.toString(intent.getIntExtra("년", 0))+"-"+format(intent.getIntExtra("월", 0))
                +"-"+format(intent.getIntExtra("일", 0));
        String timeText = format(intent.getIntExtra("분",0));

        //추후 데이터 연결 작업 필요
        bn.setText("장소명");
        bi.setText("장소 상세 정보");
        dt.setText(dateText);
        tt.setText(timeText);

        findViewById(R.id.pbooking_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FinalPlaceReservation fpr = new FinalPlaceReservation();
                fpr.setName(nt.getText().toString());
                fpr.setPhNum(pt.getText().toString());
                fpr.setDay(dateText);
                fpr.setTime(tt.getText().toString());

                mDatabaseRefUser.child("예약정보").child(User.getUid()).push().setValue(fpr);

                Toast.makeText(getApplicationContext(), "예약 완료되었습니다.", Toast.LENGTH_SHORT).show();
                //엑티비티 하나를 제외하고 나머지 히스토리를 모두 지우는 코드
                Intent i = new Intent(FinalTimeBooking.this, choice_menu.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
}