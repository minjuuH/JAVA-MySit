package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowWritedPost extends AppCompatActivity {
    DatabaseReference mDatabaseRef; // 실시간 데이터베이스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_writed_post);

        String title = getIntent().getStringExtra("title");
        String intro = getIntent().getStringExtra("introduce");

        TextView titleTextView = findViewById(R.id.titleEditText);
        TextView introTextView = findViewById(R.id.tv_introduce);
        TextView starttimetv = findViewById(R.id.tv_openTime);
        TextView endtimetv = findViewById(R.id.tv_endTime);
        TextView contenttv = findViewById(R.id.tv_content);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("장소 글작성 정보");
        mDatabaseRef.child(title).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                WritePostClass wpc = snapshot.getValue(WritePostClass.class);
                starttimetv.setText(wpc.getStarttime());
                endtimetv.setText(wpc.getEndtime());
                contenttv.setText(wpc.getContent());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        titleTextView.setText(title);
        introTextView.setText(intro);

        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ShowWritedPost.this, time_choice_plus.class));
            }
        });
    }
}