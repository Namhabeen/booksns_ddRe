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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.app.Activity;
import android.widget.Toast;

public class SignUpTwoActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_two);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.nextButton2).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.nextButton2:
                    Log.e("회원가입","클릭");
                    signUp();
                    break;
            }
        }
    };

    private void signUp() {
        String id = ((EditText)findViewById(R.id.idInput2)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText12)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.checkPwInput)).getText().toString();
        String name = ((EditText)findViewById(R.id.nameInput)).getText().toString();
        String birth =((EditText)findViewById(R.id.birthInput)).getText().toString();

        if(id.length() > 0 && password.length() > 0 && passwordCheck.length() > 0 && name.length()>0 && birth.length()>0){
            if(password.equals(passwordCheck)){
                mAuth.createUserWithEmailAndPassword(id, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    showToast(SignUpTwoActivity.this, "회원가입에 성공하셨습니다:)");
                                    myStartActivity(MainActivity.class);
                                } else {
                                    if(task.getException() != null){
                                        showToast(SignUpTwoActivity.this, "흠 무언가 단단히 잘못되었습니다! 개발자에게 문의하세요!");
                                    }
                                }
                            }
                        });
            }else{
                showToast(SignUpTwoActivity.this, "비밀번호가 일치하지 않아요. 확인해주세요!");
            }
        }else{
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
}

