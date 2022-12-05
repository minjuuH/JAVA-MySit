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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class time_choice_free extends AppCompatActivity {
    String[] items = {"---시간 선택---", "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};

    RadioButton rdoCal, rdoTime;
    Button btnEnd;
    Spinner spinner1;
    TextView tvResult;
    CalendarView calView1;
    int selectYear, selectMonth, selectDay;
    public int posit;
    public String date, phNum, name, content,title;
    DatabaseReference mDatabaseRef;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String uid;





    ArrayList<String> writeKey = null;
    ArrayList<String> writeValue = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_choice_free);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");

        btnEnd = findViewById(R.id.btnEnd);
        rdoCal = findViewById(R.id.rdoCal);
        rdoTime = findViewById(R.id.rdoTime);
        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner1.setAdapter(adapter);
        tvResult = findViewById(R.id.tvResult);
        calView1 = findViewById(R.id.calView1);

        writeKey = new ArrayList<>();
        writeValue = new ArrayList<>();

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

                posit = position;

                if (selectYear == 0) {
                    btnEnd.setOnClickListener(v -> tvResult.setText("날짜를 선택해야 예약이 가능합니다."));
                }
                btnEnd.setOnClickListener(v -> btnEventListener());
            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){
                btnEnd.setOnClickListener(v -> tvResult.setText("시간을 선택해야 예약이 가능합니다."));
            }});
    }
    public void btnEventListener(){

        date = (selectYear +"년"+ selectMonth +"월" +selectDay+"일");

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









        Board_Book bb = new Board_Book();
        bb.setTitle(title);
        bb.setContent("1");
        bb.setName("1");
        bb.setPhNum("1");
        bb.setRtime(today);
        bb.setDate(date);
        bb.setUid(uid);
        bb.setDtime("1");

        mDatabase.child("book").child(uid).child(title).setValue(bb);


        mDatabase.child("myseat").child("UserAccount").child(uid).addValueEventListener(new ValueEventListener() {
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
        mDatabase.child("board").child(title).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Board board = dataSnapshot.getValue(Board.class);
                    content = board.intro;
                    Map<String, Object> taskMap = new HashMap<String, Object>();
                    taskMap.put("content", content);

                    mDatabase.child("book").child(uid).child(title).updateChildren(taskMap);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        Map<String, Object> taskMap = new HashMap<String, Object>();
        taskMap.put("dtime", items[posit]);

        mDatabase.child("book").child(uid).child(title).updateChildren(taskMap);


        Toast.makeText(getApplicationContext(), date +  items[posit] + "에 예약되었습니다.", Toast.LENGTH_SHORT).show();
        //값을 전달하기 위한 intent 생성
        Intent intent = new Intent(time_choice_free.this, FinalBooking.class);




        intent.putExtra("uid", uid);
        intent.putExtra("title",title);


        //time_booking_final 시작
        startActivity(intent);
    }
}
