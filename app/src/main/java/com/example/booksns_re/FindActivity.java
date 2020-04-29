package com.example.booksns_re;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class FindActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);


        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.findpw).setOnClickListener(onClickListener);
        findViewById(R.id.gotologin).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.findpw:
                    find();
                    break;

                case R.id.gotologin:
                    Log.e("로그인으로 돌아가기", "클릭");
                    showToast(FindActivity.this, "로그인 창으로 이동합니다");
                    myStartActivity(MainActivity.class);
                    break;
            }
        }
    };

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    private void find() {
        String email = ((EditText) findViewById(R.id.mail)).getText().toString();

        showToast(FindActivity.this, "위에 입력하신 이메일함을 확인해주세요!");

        if (email.length() > 0) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                showToast(FindActivity.this, "이메일함을 열어보세요!");
                            }
                            else{
                                showToast(FindActivity.this,"에러가 발생했어요! 재시도하거나 개발자에게 연락하세요.");
                            }
                        }
                    });
        }
        else{
            showToast(FindActivity.this,"이메일을 입력해주세요!");
        }
    }
}