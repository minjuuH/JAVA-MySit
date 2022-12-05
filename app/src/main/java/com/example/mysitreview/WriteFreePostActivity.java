package com.example.mysitreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class WriteFreePostActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private final int SELECT_MOVIE = 2;
    public String name1;

    ImageView imageView;
    Button button;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String uid;


    EditText write_title , write_content, write_intro, write_min_Popul, write_max_popul, write_stime, write_etime;
    // 제목 , 내용, 한줄소개, 최소인원, 최대인원, 시작시간, 종료시간

    ArrayList<String> writeKey = null;
    ArrayList<String> writeValue = null;

    // 영상선택
    private void doSelectMovie()
    {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/*");
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try
        {
            startActivityForResult(i, SELECT_MOVIE);
        } catch (android.content.ActivityNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_free_post);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        write_title = (EditText) findViewById(R.id.titleEditText);
        write_content = (EditText) findViewById(R.id.contentsEditText);
        write_intro = (EditText) findViewById(R.id.tv_introduce);
        write_min_Popul = (EditText) findViewById(R.id.numOfpeople);
        write_max_popul = (EditText) findViewById(R.id.numOfpeople2);
        write_stime = (EditText) findViewById(R.id.tv_openTime);
        write_etime = (EditText) findViewById(R.id.tv_endTime);

        writeKey = new ArrayList<>();
        writeValue = new ArrayList<>();


        imageView = findViewById(R.id.image);
        button = (Button)findViewById(R.id.imagebutton);
        // 이미지 선택 버튼
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });



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
        if(write_min_Popul.getText().toString().equals("")){
            Toast.makeText(this , "최소인원을 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(write_max_popul.getText().toString().equals("")){
            Toast.makeText(this , "최대인원을 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(write_stime.getText().toString().equals("")){
            Toast.makeText(this , "시작시간을 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(write_etime.getText().toString().equals("")){
            Toast.makeText(this , "종료시간을 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(write_intro.getText().toString().equals("")){
            Toast.makeText(this , "한줄소개를 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }

        UserAccount useraccount = new UserAccount();
        // 전역으로 쓰는 user에 저장된 값을 불러옴

        //날짜 포맷
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        Date time = new Date();

        String today = dateFormat.format(time);
        String order_today = dateFormat2.format(time);



        Board board= new Board();
        board.setTitle(write_title.getText().toString());
        board.setContent(write_content.getText().toString());
        board.setIntro(write_intro.getText().toString());
        board.setMin_Popul(write_min_Popul.getText().toString());
        board.setMax_popul(write_max_popul.getText().toString());
        board.setStime(write_stime.getText().toString());
        board.setEtime(write_etime.getText().toString());
        board.setUid(uid);
        board.setDate(today);
        board.setOrder_date(order_today);
        board.setName("1");

        mDatabase.child("board").child(write_title.getText().toString()).setValue(board);

        String UserAccount = getIntent().getStringExtra("UserAccount");

        mDatabase.child("myseat").child("UserAccount").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    name1 = user.name;
                    Map<String, Object> taskMap = new HashMap<String, Object>();
                    taskMap.put("name", name1);

                    mDatabase.child("board").child(write_title.getText().toString()).updateChildren(taskMap);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });






        Intent i = new Intent(WriteFreePostActivity.this , SelectFreePost.class);
        startActivity(i);
        finish();

    }


}