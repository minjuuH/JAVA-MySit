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
    DatabaseReference mDatabaseRef;

    public String uid,title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_booking_final);

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        title = intent.getStringExtra("title");


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
        TextView ct = (TextView)findViewById(R.id.timetext);
        TextView Dday = (TextView)findViewById(R.id.date);
        TextView time = (TextView)findViewById(R.id.time);



        mDatabaseRef = FirebaseDatabase.getInstance().getReference("book");
        mDatabaseRef.child(uid).child(title).addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board_Book bb = snapshot.getValue(Board_Book.class);
                bn.setText(title);
                bi.setText(bb.getContent());
                nt.setText(bb.getName());
                pt.setText(bb.getPhNum());
                dt.setText(bb.getDate());
                ct.setText(bb.getDtime());
                Dday.setText("예약날짜");
                time.setText("예약시간");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));



        findViewById(R.id.pbooking_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "예약 완료되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FinalTimeBooking.this, choice_menu.class));
                finish();
            }
        });
    }
}
