package com.example.mysitreview;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;



public class WritePostActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private static final int REQUEST_CODE = 0;
    private final int SELECT_MOVIE = 2;
    ImageView imageView;
    Button button;

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
        setContentView(R.layout.activity_write_post);

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
        EditText Title = findViewById(R.id.titleEditText);
        EditText Introduce = findViewById(R.id.tv_introduce);
        EditText Opentime = findViewById(R.id.tv_openTime);
        EditText Endtime = findViewById(R.id.tv_endTime);
        EditText Content = findViewById(R.id.contenttext);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("장소 글작성 정보");
        // 확인 버튼
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WritePostClass wp = new WritePostClass();
                wp.setTitle(Title.getText().toString());
                wp.setIntroduce(Introduce.getText().toString());
                wp.setStarttime(Opentime.getText().toString());
                wp.setEndtime(Endtime.getText().toString());
                wp.setContent(Content.getText().toString());
                mDatabaseRef.child(Title.getText().toString()).setValue(wp);
                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            // 파이어 스토어에
//            Uri file = data.getData();
//            StorageReference storageRef = storage.getReference();
//            StorageReference riversRef = storageRef.child("photo/1.png");
//            UploadTask uploadTask = riversRef.putFile(file);
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}