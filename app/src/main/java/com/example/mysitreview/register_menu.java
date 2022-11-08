package com.example.mysitreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register_menu extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd;      // 회원가입 입력필드
    private Button mBtnRegister;            // 회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("myseat");

        mEtEmail = findViewById(R.id.edit_id);
        mEtPwd = findViewById(R.id.edit_pw);
        mBtnRegister = findViewById(R.id.register);
        EditText UserName = findViewById(R.id.edit_username);
        EditText PhoneNumber = findViewById(R.id.edit_phnum);
        PhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strUserName = UserName.getText().toString();
                String strPhNum = PhoneNumber.getText().toString();


                // Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(register_menu.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); // 회원가입된 유저 가져옴
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setIdToken(firebaseUser.getEmail());
                            account.setPassword(strPwd);
                            account.setName(strUserName);
                            account.setPhNum(strPhNum);

                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            Toast.makeText(register_menu.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(register_menu.this, MainActivity.class));
                        } else {
                            Toast.makeText(register_menu.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}