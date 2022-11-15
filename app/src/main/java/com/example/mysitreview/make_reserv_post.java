package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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


public class make_reserv_post extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String uid;

    EditText write_title , write_content; // 제목 , 내용

    ArrayList<String> writeKey = null;
    ArrayList<String> writeValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reserv_post);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        write_title = (EditText) findViewById(R.id.title_tv);
        write_content = (EditText) findViewById(R.id.editText_writeContent);

        writeKey = new ArrayList<>();
        writeValue = new ArrayList<>();

    }

    public void button_board_register(View view){

        if( write_title.getText().toString().equals("")){
            Toast.makeText(this , "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if(write_content.getText().toString().equals("")){
            Toast.makeText(this , "내용을 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }

        UserAccount useraccount = new UserAccount();
        User user = UserAccount.userClass; // 전역으로 쓰는 user에 저장된 값을 불러옴

        //날짜 포맷
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        Date time = new Date();

        String today = dateFormat.format(time);
        String order_today = dateFormat2.format(time);

        Board board= new Board();
        board.setTitle(write_title.getText().toString());
        board.setContent(write_content.getText().toString());
        board.setUid(uid);
        board.setDate(today);
        board.setOrder_date(order_today);

        mDatabase.child("board").child(uid).push().setValue(board);

        Intent i = new Intent(make_reserv_post.this , booking_final.class);
        startActivity(i);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}