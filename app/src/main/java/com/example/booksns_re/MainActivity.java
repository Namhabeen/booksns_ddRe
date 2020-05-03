package com.example.booksns_re;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.forgotGo).setOnClickListener(onClickListener);
        findViewById(R.id.signUpGo).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loginButton:
                    login();
                    break;

                case R.id.forgotGo:
                    showToast(MainActivity.this, "비밀번호 찾기 창으로 이동합니다.");
                    myStartActivity(FindActivity.class);
                    finish();
                    break;

                case R.id.signUpGo:
                    showToast(MainActivity.this, "회원가입 창으로 이동합니다!");
                    myStartActivity(SignUpTwoActivity.class);
                    finish();
                    break;

            }
        }
    };

    private void login() {
        String email = ((EditText) findViewById(R.id.idInput)).getText().toString();

        String password = ((EditText) findViewById(R.id.pwInput)).getText().toString();

        if (email.length() > 0 && password.length() > 0) {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                showToast(MainActivity.this, "로그인에 성공하였습니다.");
                                myStartActivity(PostActivity.class);
                                finish();
                            } else {
                                if (task.getException() != null) {
                                    showToast(MainActivity.this, "아이디 또는 비밀번호를 확인해주세요");
                                }
                            }
                        }
                    });
        } else {
            showToast(MainActivity.this, "아이디 또는 비밀번호를 입력해 주세요.");
        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        //Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }
}