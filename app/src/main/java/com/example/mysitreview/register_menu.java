package com.example.mysitreview;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        mEtEmail = findViewById(R.id.txt_Id);
        mEtPwd = findViewById(R.id.txt_Pwd);
        mBtnRegister = findViewById(R.id.register);
        EditText UserName = findViewById(R.id.txt_UserName);
        EditText PhoneNumber = findViewById(R.id.txt_PhNum);
        PhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strUserName = UserName.getText().toString();
                String strPhNum = PhoneNumber.getText().toString();
                String passwordCheck = ((EditText)findViewById(R.id.txt_PwdCheck)).getText().toString();


                // Firebase Auth 진행
                if(strEmail.length()>0 && strPwd.length()>0 && passwordCheck.length()>0 && strUserName.length()>0 && strPhNum.length()>12){
                    if(strPwd.equals(passwordCheck)){
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
                                    emailVerification();
                                } else {
                                    Toast.makeText(register_menu.this, "중복된 아이디이거나 비밀번호가 6자리 미만입니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(register_menu.this, "비밀번호가 일치하지 않습니다.",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(register_menu.this, "빈칸을 입력해주세요.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //확인 메일 날리는 곳
    public void emailVerification(){
        final FirebaseUser user = mFirebaseAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(register_menu.this , "확인 이메일을 보냈습니다 확인부탁드립니다" , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(register_menu.this, RegisterComplete.class));
                }else{
                    Toast.makeText(register_menu.this, "메일 발송에 실패했습니다" + user.getEmail() + "입니다", Toast.LENGTH_SHORT).show();
                    try {
                        task.getResult();
                    }catch (Exception e){
                        Log.d("Fail send_email" , e.getMessage());
                    }finally {
                        return;
                    }
                }

            }
        });

    }
}