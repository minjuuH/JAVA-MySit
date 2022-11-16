package com.example.mysitreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

//import com.example.mysitreview.MyReCyclerAdapter;
//리사이클러뷰 연습 코드
//activity_sub와 연계

public class SelectPlace extends AppCompatActivity implements rvInterface {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Divider 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList, this);
        recyclerView.setAdapter(mainAdapter);       //설정한 adapter를 recyclerView에 설정

        for(int i=0;i<10;i++){
            MainData mainData = new MainData(R.mipmap.ic_launcher, "title", "잔여");
            arrayList.add(mainData);
            mainAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent subPage = new Intent(SelectPlace.this, time_choice_plus.class);
        startActivity(subPage);
    }

    public void onButtonClick(View v) {
        Toast.makeText(getApplicationContext(), "글 등록으로 이동합니다.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SelectPlace.this, make_reserv_post.class));
    }
}