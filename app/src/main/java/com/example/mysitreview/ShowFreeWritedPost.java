package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShowFreeWritedPost extends AppCompatActivity {
    DatabaseReference mDatabaseRef;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String uid, max, now;
    int maxp, nowp;
    public String date, phNum, name, content,title, dtime, etime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_free_writed_post);

        Intent intent = new Intent();
        String title = getIntent().getStringExtra("title");

        TextView titletv = findViewById(R.id.titleEditText);
        TextView introtv = findViewById(R.id.tv_introduce);
        TextView contenttv = findViewById(R.id.tv_content);
        TextView popultv = findViewById(R.id.nowcount);
        TextView stimetv = findViewById(R.id.tv_openTime);
        TextView etimetv = findViewById(R.id.tv_endTime);
        TextView Eventstart = findViewById((R.id.datetime));

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("board");
        mDatabaseRef.child(title).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board_Show bs = snapshot.getValue(Board_Show.class);
                introtv.setText(bs.getIntro());
                contenttv.setText(bs.getContent());
                popultv.setText(bs.getNow_popul()+" / "+bs.getMax_popul());
                stimetv.setText(bs.getDate());
                etimetv.setText(bs.getEtime());
                Eventstart.setText(bs.getSdate() + "  " + bs.getStime());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        titletv.setText(title);




        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                FirebaseUser fuser = mAuth.getCurrentUser();
                uid = fuser.getUid();

                UserAccount useraccount = new UserAccount();
                User user = UserAccount.userClass;

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy.MM.dd HH:mm");

                Date time = new Date();

                String today = dateFormat.format(time);

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
                            nowp++;
                            now = Integer.toString(nowp);

                            Map<String, Object> taskMap = new HashMap<String, Object>();
                            taskMap.put("now_popul", now);

                            mDatabaseRef.child("board").child(title).updateChildren(taskMap);

                            return ;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });






                Board_Book bb = new Board_Book();
                bb.setTitle(title);
                bb.setContent("1");
                bb.setName("1");
                bb.setPhNum("1");
                bb.setRtime(today);
                bb.setDate("1");
                bb.setUid(uid);
                bb.setDtime("1");
                bb.setType("자유");
                bb.setEtime("1");

                mDatabase.child("book").child(uid).child(title).setValue(bb);


                mDatabase.child("myseat").child("UserAccount").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            User user = dataSnapshot.getValue(User.class);
                            phNum = user.phNum;
                            name = user.name;
                            Map<String, Object> taskMap = new HashMap<String, Object>();
                            taskMap.put("name", name);
                            taskMap.put("phNum", phNum);

                            mDatabase.child("book").child(uid).child(title).updateChildren(taskMap);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
                mDatabase.child("board").child(title).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Board board = dataSnapshot.getValue(Board.class);
                            content = board.intro;
                            dtime = board.stime;
                            date = board.sdate;
                            etime = board.etime;




                            Map<String, Object> taskMap = new HashMap<String, Object>();
                            taskMap.put("content", content);
                            taskMap.put("date",date);
                            taskMap.put("dtime",dtime);
                            taskMap.put("etime",etime);


                            mDatabase.child("book").child(uid).child(title).updateChildren(taskMap);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });




                //Toast.makeText(getApplicationContext(), date +  dtime + "에 시작하는 "+ title +"에예약되었습니다.", Toast.LENGTH_SHORT).show();
                //값을 전달하기 위한 intent 생성
                Intent intent = new Intent(ShowFreeWritedPost.this, FinalBooking.class);

                intent.putExtra("uid", uid);
                intent.putExtra("title", title);
                intent.putExtra("now", now);
                intent.putExtra("max", max);

                //time_booking_final 시작
                startActivity(intent);

                finish();

            }
        });
    }
}