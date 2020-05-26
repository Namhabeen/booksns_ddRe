package com.example.booksns_re;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.postPageButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.postPageButton:
                    Log.e("게시글", "작성");
                    showToast(HomeActivity.this, "게시글을 작성합니다.");
                    myStartActivity(PostActivity.class);
                    break;
                case R.id.heartTab:
                    Log.e("좋아요 탭", "이동");
                    //myStartActivity(LikeActivity.class); 좋아요 탭 만들기
                    break;
                case R.id.homeTab:
                    Log.e("홈 탭", "이동");
                    showToast(HomeActivity.this, "현재 탭입니다.");
                    break;
                case R.id.searchTab:
                    Log.e("검색 탭", "이동");
                    myStartActivity(SearchActivity.class);
                    break;
                case R.id.profileTab:
                    Log.e("프로필  탭", "이동");
                    myStartActivity(ProfileOneActivity.class);
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
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        //Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }
}
