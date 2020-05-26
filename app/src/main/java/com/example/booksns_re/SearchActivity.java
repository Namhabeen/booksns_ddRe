package com.example.booksns_re;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.backButton).setOnClickListener(onClickListener);
        findViewById(R.id.searchButton).setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    myStartActivity(HomeActivity.class);
                    break;
                case R.id.searchButton:
                    Searching();
                    break;
            }
        }
    };

    private void Searching() {
        String text = ((EditText) findViewById(R.id.searchInput)).getText().toString();
        if(text.length()>0) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child(text).toString();
            showToast(SearchActivity.this, "검색화면으로 이동합니다.");
            //myStartActivity(SearchResultActivity.class); 이동페이지 만든뒤 설정!!
        }
         else {
        showToast(SearchActivity.this, "아이디 또는 비밀번호를 입력해 주세요.");
        }
    }


    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        //Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }
}
