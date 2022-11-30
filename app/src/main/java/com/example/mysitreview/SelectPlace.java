package com.example.mysitreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//import com.example.mysitreview.MyReCyclerAdapter;
//리사이클러뷰 연습 코드
//activity_select_place와 연계

public class SelectPlace extends AppCompatActivity implements rvInterface {

    private ArrayList<DataPlace> arrayList;
    private AdapterPlace placeAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        findViewById(R.id.SelectButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectPlace.this, WritePostActivity.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Divider 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        arrayList = new ArrayList<>();
        placeAdapter = new AdapterPlace(arrayList, this);
        recyclerView.setAdapter(placeAdapter);       //설정한 adapter를 recyclerView에 설정

        String deadline[] = new String[]{"11-01 ~ 11-24", "11-10 ~ 11-20", "12-01 ~ 12-08", "11-11 ~ 11-24", "11-01 ~ 11-05"};

        for(int i=0;i<5;i++){
            DataPlace placeData = new DataPlace(R.mipmap.ic_launcher, "title"+Integer.toString(i),"상세정보"+Integer.toString(i));
            arrayList.add(placeData);
            placeAdapter.notifyDataSetChanged();
        }

        //출력할 데이터가 없을 경우
        if(arrayList.size()==0){
            //noneList textView 객체를 받아와 recyclerView 대신 보이게 설정
            TextView noneText = (TextView) findViewById(R.id.noneList);
            noneText.setVisibility(View.VISIBLE);
            noneText.setEnabled(true);

            //recyclerView를 화면에 보이지 않게 설정
            recyclerView.setVisibility(View.GONE);
            recyclerView.setEnabled(false);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SelectPlace.this, ShowWritedPost.class);

        intent.putExtra("title", arrayList.get(position).getTitle());
        intent.putExtra("introduce", arrayList.get(position).getIntroduce());

        startActivity(intent);
    }

    public void onButtonClick(View v) {
        Toast.makeText(getApplicationContext(), "글 등록으로 이동합니다.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SelectPlace.this, make_reserv_post.class));
    }
}