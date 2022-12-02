package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectFreePost extends AppCompatActivity implements rvInterface {

    private ArrayList<DataFreePost> arrayList;
    private AdapterFreePost mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        //SearchView serch1 = findViewById(R.id.search_view);

        findViewById(R.id.SelectButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectFreePost.this, WriteFreePostActivity.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Divider 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        arrayList = new ArrayList<>();
        mainAdapter = new AdapterFreePost(arrayList, this);
        recyclerView.setAdapter(mainAdapter);       //설정한 adapter를 recyclerView에 설정

        //임시 데이터
        String deadline[] = new String[]{"11-01 ~ 11-24", "11-10 ~ 11-20", "12-01 ~ 12-08", "11-11 ~ 11-24", "11-01 ~ 11-05"};
        int remain[] = new int[]{5, 1, 3, 2, 4};

        for(int i=0;i<5;i++){
            DataFreePost freePostData = new DataFreePost(R.mipmap.ic_launcher, "title"+Integer.toString(i),"상세정보"+Integer.toString(i), deadline[i], "잔여: "+Integer.toString(remain[i]));
            arrayList.add(freePostData);
            mainAdapter.notifyDataSetChanged();
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
        //rvInterface를 상속함으로서 구현해야할 onItemClick 이벤트
        Intent intent = new Intent(SelectFreePost.this, ShowFreeWritedPost.class);

        //다음 액티비티에 전달할 데이터 저장
        intent.putExtra("title", arrayList.get(position).getTitle());
        intent.putExtra("introduce", arrayList.get(position).getIntroduce());
        intent.putExtra("deadline", arrayList.get(position).getDeadline());
        intent.putExtra("remain", arrayList.get(position).getRemain());

        startActivity(intent);
    }

    public void onButtonClick(View v) {
        Toast.makeText(getApplicationContext(), "글 등록으로 이동합니다.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SelectFreePost.this, make_reserv_post.class));
    }
}