package com.example.booksns_re;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.app.Activity;
import android.widget.Toast;

import java.util.HashMap;

public class SignUpTwoActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private String passwordCheck;
    private String name;
    private String birth;
    private String userId;

    private DatabaseReference mDatabase;// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_two);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.nextButton2).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.nextButton2:
                    Log.e("회원가입", "클릭");
                    signUp();
                    break;
            }
        }
    };

    private void signUp() {
        userId = ((EditText) findViewById(R.id.nameInput)).getText().toString();
        email = ((EditText) findViewById(R.id.idInput2)).getText().toString();
        password = ((EditText) findViewById(R.id.editText12)).getText().toString();
        passwordCheck = ((EditText) findViewById(R.id.checkPwInput)).getText().toString();
        name = ((EditText) findViewById(R.id.nameInput)).getText().toString();
        birth = ((EditText) findViewById(R.id.birthInput)).getText().toString();

        if (email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0 && name.length() > 0 && birth.length() > 0) {
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    HashMap result = new HashMap<>();
                                    result.put("email", email);
                                    result.put("password", password);
                                    result.put("name",name);
                                    result.put("birth",birth);
                                    writeNewUser(userId, email, password, name, birth);

                                    myStartActivity(SignUpThreeActivity.class);
                                    finish();
                                } else {
                                    if (task.getException() != null) {
                                        showToast(SignUpTwoActivity.this, "이메일 주소와 비밀번호(6자리 이상)을 확인해주세요!");
                                    }
                                }
                            }
                        });
            } else {
                showToast(SignUpTwoActivity.this, "비밀번호가 일치하지 않아요. 확인해주세요!");
            }
        } else {
            showToast(SignUpTwoActivity.this, "빈 칸이 발견되었습니다. 다 채우셨나요?");
        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    private void writeNewUser(String userId, String email, String password,String name,String birth) {
        Users user = new Users(email, password,name,birth);

        mDatabase.child("users").child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(SignUpTwoActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(SignUpTwoActivity.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}