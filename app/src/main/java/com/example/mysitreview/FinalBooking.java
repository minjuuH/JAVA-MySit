package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinalBooking extends AppCompatActivity {
    DatabaseReference mDatabaseRef;

    public String uid,title, now, max;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_final);

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        title = intent.getStringExtra("title");
        now = intent.getStringExtra("now"); max = intent.getStringExtra("max");

        //추후 데이터 연결 작업 필요
        String name = "예약자";
        String phoneNum = "010-xxxx-xxxx";
        String date = "20xx.xx.xx - 20xx.xx.xx";
        //int full = Integer.parseInt(max); int real = Integer.parseInt(now);

        //텍스트필드 객체 받아옴
        TextView bn = (TextView)findViewById(R.id.bookingname);
        TextView bi = (TextView)findViewById(R.id.bookinginfo);
        TextView nt = (TextView)findViewById(R.id.nametext);
        TextView pt = (TextView)findViewById(R.id.phonetext);
        TextView dt = (TextView)findViewById(R.id.datetext);
        TextView ct = (TextView)findViewById(R.id.counttext);
        TextView rv = (TextView)findViewById(R.id.remainView);
        TextView reserv = (TextView)findViewById(R.id.date);
        TextView start = (TextView)findViewById(R.id.time);



        mDatabaseRef = FirebaseDatabase.getInstance().getReference("book");
        mDatabaseRef.child(uid).child(title).addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board_Book bb = snapshot.getValue(Board_Book.class);
                bn.setText(title);
                bi.setText(bb.getContent());
                nt.setText(bb.getName());
                pt.setText(bb.getPhNum());
                dt.setText(" ~ "+bb.getEtime());
                ct.setText(bb.getDate() + "  "+bb.getDtime());
                reserv.setText("모집기간");
                start.setText(title+" 시작");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));

        //rv.setText("잔여 : "+Integer.toString(full-real));
        rv.setVisibility(View.GONE);
        rv.setEnabled(false);

        findViewById(R.id.booking_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "예약 완료되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FinalBooking.this, choice_menu.class));
            }
        });
    }
}

