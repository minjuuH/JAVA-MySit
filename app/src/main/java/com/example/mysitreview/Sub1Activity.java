package com.example.mysitreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

//import com.example.mysitreview.MyReCyclerAdapter;

public class Sub1Activity extends AppCompatActivity implements rvInterface {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

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
        Intent subPage = new Intent(Sub1Activity.this, SubActivity.class);
        startActivity(subPage);
    }
}