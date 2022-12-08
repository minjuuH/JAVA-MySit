package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectFreePost extends AppCompatActivity implements rvInterface {

    private ArrayList<BoardAdapt> arrayList;
    private AdapterFreePost mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        //SearchView serch1 = findViewById(R.id.search_view);

        findViewById(R.id.SelectButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectFreePost.this, Choice_Day_From_WriteFreePost.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        arrayList = new ArrayList<>();
        mainAdapter = new AdapterFreePost(arrayList,this);
        //mainAdapter = new AdapterFreePost(arrayList, this, this);
        recyclerView.setAdapter(mainAdapter);       //설정한 adapter를 recyclerView에 설정


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("board");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    BoardAdapt board = datasnapshot.getValue(BoardAdapt.class);
                    arrayList.add(board);
                }
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("SelectFreePost", String.valueOf(error.toException()));
            }

        });
        //Divider 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        //임시 데이터tring deadline[] = new String[]{"11-01 ~ 11-24", "11-10 ~ 11-20", "12-01 ~ 12-08", "11-11 ~ 11-24", "11-01 ~ 11-05"};
       /*int remain[] = new int[]{5, 1, 3, 2, 4};

        for(int i=0;i<5;i++){
            DataFreePost freePostData = new DataFreePost(R.mipmap.ic_launcher, "title"+Integer.toString(i),"상세정보"+Integer.toString(i), deadline[i], "잔여: "+Integer.toString(remain[i]));
            arrayList.add(freePostData);
            mainAdapter.notifyDataSetChanged();
        }*/

        //출력할 데이터가 없을 경우
        /*if (arrayList.size() == 0) {
            //noneList textView 객체를 받아와 recyclerView 대신 보이게 설정
            TextView noneText = (TextView) findViewById(R.id.noneList);
            noneText.setVisibility(View.VISIBLE);
            noneText.setEnabled(true);

            //recyclerView를 화면에 보이지 않게 설정
            recyclerView.setVisibility(View.GONE);
            recyclerView.setEnabled(false);
        }*/
    }

    @Override
    public void onItemClick(int position) {
        //rvInterface를 상속함으로서 구현해야할 onItemClick 이벤트
        Intent intent = new Intent(SelectFreePost.this, ShowFreeWritedPost.class);

        //다음 액티비티에 전달할 데이터 저장
        intent.putExtra("title", arrayList.get(position).getTitle());


        startActivity(intent);
    }
}