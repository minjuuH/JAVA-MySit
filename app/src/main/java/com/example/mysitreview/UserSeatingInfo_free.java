package com.example.mysitreview;

import static java.sql.Types.NULL;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import kotlin.reflect.KType;

public class UserSeatingInfo_free extends AppCompatActivity {
    DatabaseReference mDatabaseRef, mDatabase;

    public String uid,title, type , type2, max, now;
    public int nowp, maxp,i=0;


    //자유 예약 상세정보 액티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_final);

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        title = intent.getStringExtra("title");

        TextView bn = (TextView)findViewById(R.id.bookingname);
        TextView bi = (TextView)findViewById(R.id.bookinginfo);
        TextView nt = (TextView)findViewById(R.id.nametext);
        TextView pt = (TextView)findViewById(R.id.phonetext);
        TextView dt = (TextView)findViewById(R.id.datetext);
        TextView ct = (TextView)findViewById(R.id.counttext);
        TextView rv = (TextView)findViewById(R.id.remainView);
        TextView reserv = (TextView)findViewById(R.id.date);
        TextView start = (TextView)findViewById(R.id.time);

        rv.setVisibility(View.GONE);
        rv.setEnabled(false);

        type2 = "자유";
        String type3 = "장소";

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("book");
        mDatabaseRef.child(uid).child(title).addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board_Book bb = snapshot.getValue(Board_Book.class);
                bn.setText(title);
                if(i==0) {
                    type = bb.getType();
                    i++;
                }
                if (type.equals(type2)) {
                    bi.setText(bb.getContent());
                    nt.setText(bb.getName());
                    pt.setText(bb.getPhNum());
                    dt.setText(" ~ " + bb.getEtime());
                    ct.setText(bb.getDate() + "  " + bb.getDtime());
                    reserv.setText("모집기간");
                    start.setText(title + " 시작");
                }
                else if (type.equals(type3)){
                    bi.setText(bb.getContent());
                    nt.setText(bb.getName());
                    pt.setText(bb.getPhNum());

                    dt.setText(bb.getDate());
                    ct.setText(bb.getDtime());
                    reserv.setText("예약날짜");
                    start.setText("예약시간");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));


        Button cancleBnt = (Button) findViewById(R.id.booking_btn);
        cancleBnt.setText("예약 취소");

        cancleBnt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                mDatabaseRef.child("board").child(title).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Board board = dataSnapshot.getValue(Board.class);
                            max = board.max_popul;
                            now = board.now_popul;
                            maxp = Integer.parseInt(max);
                            nowp = Integer.parseInt(now);
                            nowp--;
                            now = Integer.toString(nowp);

                            Map<String, Object> taskMap = new HashMap<String, Object>();
                            taskMap.put("now_popul", now);

                            mDatabaseRef.child("board").child(title).updateChildren(taskMap);

                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("book").child(uid).child(title).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Board_Book board = dataSnapshot.getValue(Board_Book.class);
                            Map<String, Object> taskMap = new HashMap<String, Object>();
                            taskMap.put("content", NULL);
                            taskMap.put("date", NULL);
                            taskMap.put("dtime", NULL);
                            taskMap.put("etime", NULL);

                            mDatabase.child("book").child(uid).child(title).updateChildren(taskMap);



                            mDatabase.child("book").child(uid).removeValue();
                            mDatabase.child("book").child(uid).removeValue();

                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });


                //추후 실제 취소 기능 연결 필요
                Toast.makeText(getApplicationContext(), "예약 취소되었습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UserSeatingInfo_free.this, UserInfoActivity.class);
                startActivity(intent);

                finish();

            }
        });
    }
}
