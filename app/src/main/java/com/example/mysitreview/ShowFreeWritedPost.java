package com.example.mysitreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowFreeWritedPost extends AppCompatActivity {
    DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_free_writed_post);

        //Intent intent = new Intent();
        String title = getIntent().getStringExtra("title");

        TextView titletv = findViewById(R.id.titleEditText);
        TextView introtv = findViewById(R.id.tv_introduce);
        TextView max_popultv = findViewById(R.id.nowcount);
        TextView stimetv = findViewById(R.id.tv_openTime);
        TextView etimetv = findViewById(R.id.tv_endTime);
        TextView contenttv = findViewById(R.id.tv_content);
        TextView min_popultv = findViewById(R.id.mincount);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("board");
        mDatabaseRef.child(title).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board_Show bs = snapshot.getValue(Board_Show.class);
                titletv.setText(bs.getTitle());
                introtv.setText(bs.getIntro());
                contenttv.setText(bs.getContent());
                max_popultv.setText("최대인원 : "+bs.getMax_popul());
                stimetv.setText(bs.getStime());
                etimetv.setText(bs.getEtime());
                min_popultv.setText(String.format("%s(명) 이상 모집될 경우 확정됩니다!", bs.getMin_popul()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //titletv.setText(title);




        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShowFreeWritedPost.this, time_choice_free.class);

                intent.putExtra("title", title);

                startActivity(intent);
            }
        });
    }
}