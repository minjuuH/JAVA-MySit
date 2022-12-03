package com.example.mysitreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//리사이클러뷰 연습 코드
//activity_select_place와 연계
public class SelectPlace extends AppCompatActivity implements rvInterface {
    RecyclerView recyclerView;
    DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    PlaceAdapter placeAdapter;
    ArrayList<DataPlace> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        placeAdapter = new PlaceAdapter(list,this, this); //*
        //placeAdapter = new AdapterPlace(list, this);
        recyclerView.setAdapter(placeAdapter);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("장소 글작성 정보");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataPlace dp = dataSnapshot.getValue(DataPlace.class);
                    list.add(dp);
                }
                placeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
//        // 아이템 선택
//        public void onItemClick(int position) {
//            Intent intent = new Intent(SelectPlace.this, ShowWritedPost.class);
//            intent.putExtra("title", list.get(position).getTitle());
//
//            startActivity(intent);
//        }
        //Divider(구분선) 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // 글작성으로 이동
        findViewById(R.id.SelectButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectPlace.this, WritePostActivity.class));
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SelectPlace.this, ShowWritedPost.class);

        intent.putExtra("title", list.get(position).getTitle());
        intent.putExtra("introduce", list.get(position).getIntroduce());
        startActivity(intent);
    }
}
        //findViewById(R.id.search_view).setOnQueryTextListener(object : search_view.OnQueryTextListener{

        //override fun onQueryTextChange(newText: String?): Boolean {

        //검색어 입력 순간마다의 이벤트...

        //   return true

        //   }

        //String deadline[] = new String[]{"11-01 ~ 11-24", "11-10 ~ 11-20", "12-01 ~ 12-08", "11-11 ~ 11-24", "11-01 ~ 11-05"};

        //출력할 데이터가 없을 경우 => 활성화하면 리스트뷰가 안나와서 주석처리
//        if(list.size()==0){
//            //noneList textView 객체를 받아와 recyclerView 대신 보이게 설정
//            TextView noneText = (TextView) findViewById(R.id.noneList);
//            noneText.setVisibility(View.VISIBLE);
//            noneText.setEnabled(true);
//            //recyclerView를 화면에 보이지 않게 설정
//            recyclerView.setVisibility(View.GONE);
//            recyclerView.setEnabled(false);
//        }

