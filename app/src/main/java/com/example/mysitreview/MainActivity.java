package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증
    DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd;      // 회원가입 입력필드


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("myseat");

        mEtEmail = findViewById(R.id.edit_id);
        mEtPwd = findViewById(R.id.edit_pw);


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            if(user.isEmailVerified()){ //그 계정이 실제로 존재하는 계정인지
                                Log.d("login", "signInWithEmail:success" + user.getEmail());
                                Toast.makeText(MainActivity.this, "signInWithEmail:success." + user.getEmail(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, choice_menu.class);
                                startActivity(intent);
                                finish(); // 현재 액티비티 파괴
                            }else{
                                Toast.makeText(MainActivity.this, "인증이 되지 않은 이메일입니다 해당 이메일 주소에서 링크를 클릭해주세요", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();


                        }else{
                            Toast.makeText(MainActivity.this, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        findViewById(R.id.goregister).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "회원등록으로 이동합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, register_menu.class));
            }
        });
    }
    //최초 로그인시 DB 값 세팅
    public void DataSet(){
        ValueEventListener addValueEventListener;
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        final String uid = user.getUid(); //

        mDatabaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String firstLunch = (String)dataSnapshot.child("user").child(uid).child("firstLunch").getValue(); // 이 앱을 처음 사용하면 DB에 값을 넣어주기 값을 세팅
                if(firstLunch == null || firstLunch.equals("")){ // 값의 주소값이 없거나 , 그 값이 비어 있으면 처음 들어오는 사람으로 색인을 해줌
                    mDatabaseRef.child("user").child(uid).child("name").setValue("asd");
                    mDatabaseRef.child("user").child(uid).child("firstLunch").setValue("Y");
                }else{
                    return;
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}