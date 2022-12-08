package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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


public class WriteFreePostActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private final int SELECT_MOVIE = 2;
    public String name1;

    ImageView imageView;
    Button button;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String uid;


    EditText write_title , write_content, write_intro, write_min_Popul, write_max_popul, write_stime, write_etime, editText1, editText2;
    // 제목 , 내용, 한줄소개, 최소인원, 최대인원, 시작시간, 종료시간

    ArrayList<String> writeKey = null;
    ArrayList<String> writeValue = null;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //finish();//인텐트 종료
        overridePendingTransition(0, 0);//인텐트 효과 없애기
        Intent intent = getIntent(); //인텐트
        //startActivity(intent); //액티비티 열기
        overridePendingTransition(0, 0);//인텐트 효과 없애기

        setContentView(R.layout.activity_write_free_post);
        String StartDay = getIntent().getStringExtra("StartDate");
        String EndDay = getIntent().getStringExtra("EndDate");;
        editText1 = findViewById(R.id.StartDay);
        editText2 = findViewById(R.id.EndDay);
        editText1.setText(StartDay);
        editText2.setText(EndDay);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        write_title = (EditText) findViewById(R.id.titleEditText);
        write_content = (EditText) findViewById(R.id.contentsEditText);
        write_intro = (EditText) findViewById(R.id.tv_introduce);
        write_min_Popul = (EditText) findViewById(R.id.tv_openTime);
        write_max_popul = (EditText) findViewById(R.id.numOfpeople2);





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
        /*findViewById(R.id.StartDay).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "시작 날을 선택합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WriteFreePostActivity.this, Choice_Day_From_WriteFreePost.class));
            }
        });
        findViewById(R.id.EndDay).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "시작 날을 선택합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WriteFreePostActivity.this, Choice_Day_From_WriteFreePost.class));
            }
        });*/



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
            Toast.makeText(this , "시작시간을 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(write_max_popul.getText().toString().equals("")){
            Toast.makeText(this , "최대인원을 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }

        if(write_intro.getText().toString().equals("")){
            Toast.makeText(this , "한줄소개를 입력하세요" , Toast.LENGTH_SHORT).show();
            return;
        }

        UserAccount useraccount = new UserAccount();
        // 전역으로 쓰는 user에 저장된 값을 불러옴

        //날짜 포맷
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy년MM월dd일 HH:mm:ss");

        Date time = new Date();

        String today = dateFormat.format(time);
        String order_today = dateFormat2.format(time);



        Board board= new Board();
        board.setTitle(write_title.getText().toString());
        board.setContent(write_content.getText().toString());
        board.setIntro(write_intro.getText().toString());
        board.setSdate(editText1.getText().toString());
        board.setMax_popul(write_max_popul.getText().toString());
        board.setEtime(editText2.getText().toString());
        board.setUid(uid);
        board.setDate(today);
        board.setOrder_date(order_today);
        board.setName("1");
        board.setNow_popul("0");
        board.setType("자유");
        board.setStime(write_min_Popul.getText().toString());

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