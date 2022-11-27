package com.example.mysitreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPwdActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_Send).setOnClickListener(onClickListener);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            send();
        }
    };

    public void send() {
        String email = ((EditText)findViewById(R.id.txt_Email)).getText().toString();
        if(email.length()>0){
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPwdActivity.this, "이메일을 보냈습니다.",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                if(task.getException()!=null)
                                    Toast.makeText(ResetPwdActivity.this, "이메일 전송을 실패했습니다..",
                                            Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(ResetPwdActivity.this, "이메일을 입력해주세요.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}