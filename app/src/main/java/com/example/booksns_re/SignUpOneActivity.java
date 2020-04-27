package com.example.booksns_re;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpOneActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_one);

        mAuth =FirebaseAuth.getInstance();

        findViewById(R.id.nextButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.nextButton:
                    accountCheck();
                    break;
            }
        }
    };

    private void accountCheck(){
        String mailId = ((EditText)findViewById(R.id.emailInput)).getText().toString();
        if(mailId.isEmpty()){
            Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_LONG).show();
        }
        else{
            //mailId는 비밀번호 찾기에서 복구이메일로 사용할거! (나중에 변경필요)
            Toast.makeText(getApplicationContext(), "회원가입을 진행합니다.", Toast.LENGTH_LONG).show();
            myStartActivity(MainActivity.class);
        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
