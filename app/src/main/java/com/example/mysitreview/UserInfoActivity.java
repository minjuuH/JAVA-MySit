package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserInfoActivity extends AppCompatActivity implements rvInterface {
    private ArrayList<Board_Book> arrayList;
    private UserSeatAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mDatabaseRef, mDatabase;
    String uid;
    FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ImageView profileimage = (ImageView)findViewById(R.id.profileImage);
        profileimage.setImageResource(R.drawable.profile_photo);

        TextView reviseBnt = (TextView)findViewById(R.id.reviseBnt);

        TextView name = (TextView)findViewById(R.id.Topusername);
        TextView email = (TextView)findViewById(R.id.Topemail);

        EditText username = (EditText)findViewById(R.id.username);
        EditText userphone = (EditText)findViewById(R.id.userphone);
        EditText userpw = (EditText)findViewById(R.id.userpw);
        EditText userem = (EditText)findViewById(R.id.useremail);

        Button pwcheck = (Button)findViewById(R.id.checkBnt);
        Button infocomplete = (Button)findViewById(R.id.completeBnt);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("myseat").child("UserAccount").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UserAccount user = dataSnapshot.getValue(UserAccount.class);
                    username.setText(user.name);
                    userphone.setText(user.phNum);
                    userpw.setText(user.password);
                    userem.setText(user.idToken);
                    name.setText(user.name);
                    email.setText(user.idToken);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
});
        //수정 텍스트뷰 클릭 시 실행 이벤트
        reviseBnt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //비밀번호 입력 edittext 객체를 활성화
                userpw.setEnabled(true);
                userpw.setVisibility(View.VISIBLE);
                userpw.setText("");

                //비밀번호 확인 버튼 활성화
                pwcheck.setVisibility(View.VISIBLE);
                pwcheck.setEnabled(true);
            }
        });

        //비밀번호 확인 버튼 클릭 시 실행 이벤트
        pwcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviseBnt.setVisibility(View.GONE);

                //이름 입력 edittext 활성화
                username.setEnabled(true);
                username.setTextColor(getResources().getColor(R.color.black));

                //전화번호 입력 edittext 활성화
                userphone.setEnabled(true);
                userphone.setTextColor(getResources().getColor(R.color.black));

                //비밀번호 입력 edittext 설정 변경
                userpw.setText("비밀번호");
                userpw.setTextColor(getResources().getColor(R.color.black));

                pwcheck.setVisibility(View.GONE);
                pwcheck.setEnabled(false);

                //정보 수정 완료 버튼 활성화
                infocomplete.setVisibility(View.VISIBLE);
                infocomplete.setEnabled(true);
            }
        });

        //정보 수정 완료 버튼 클릭 시 실행 이벤트
        infocomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            //회원 정보 수정 작업 후 모든 edittext를 원상태(수정하지 못하는 상태)로 되돌리는 작업
            //실제 DB와 연결하는 작업 연결 필요
            public void onClick(View view) {
                reviseBnt.setVisibility(View.VISIBLE);

                //이름 입력 edittext 원상복귀
                username.setText(username.getText());
                username.setTextColor(getResources().getColor(R.color.text_gray));
                username.setEnabled(false);

                //전화번호 입력 edittext 원상복귀
                userphone.setText(userphone.getText());
                userphone.setTextColor(getResources().getColor(R.color.text_gray));
                userphone.setEnabled(false);

                //비밀번호 입력 edittext 숨기기
                userpw.setVisibility(View.GONE);
                userpw.setEnabled(false);

                infocomplete.setVisibility(View.GONE);
                infocomplete.setEnabled(false);

                Toast.makeText(getApplicationContext(), "수정 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        //예약내역 확인
        recyclerView = (RecyclerView) findViewById(R.id.seatingRecycle);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Divider 추가
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        //recyclerView.addItemDecoration(dividerItemDecoration);

        arrayList = new ArrayList<>();
        mainAdapter = new UserSeatAdapter(arrayList, this);
        recyclerView.setAdapter(mainAdapter);       //설정한 adapter를 recyclerView에 설정


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("book").child(uid);
        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Board_Book bb = datasnapshot.getValue(Board_Book.class);
                    arrayList.add(bb);
                }
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("SelectFreePost", String.valueOf(error.toException()));
            }

        });

        //추후에 예외처리해야할 부분 -> 예약내역이 없을 경우 보이게, 예약내역이 있을 경우 보이지 않게
        findViewById(R.id.emptyBooking).setVisibility(View.GONE);
        findViewById(R.id.emptyBooking).setEnabled(false);


        findViewById(R.id.logoutBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();

                signOut();

                //엑티비티 하나를 제외하고 나머지 히스토리를 모두 지우는 코드
                Intent i = new Intent(UserInfoActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
            }
        });
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onItemClick(int position) {
        //리사이클러 아이템클릭리스너

        //예약 종류에 따라 다른 액티비티로 전환(자유->UserSeatingInfo_free / 장소->UserSeatingInfo_place)
        Intent intent = new Intent(UserInfoActivity.this, UserSeatingInfo_free.class);
        intent.putExtra("title", arrayList.get(position).getTitle());
        intent.putExtra("uid", uid);
        startActivity(intent);



    }
}
