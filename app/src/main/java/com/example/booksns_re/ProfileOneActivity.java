package com.example.booksns_re;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.booksns_re.MainActivity.showToast;

public class ProfileOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_one);

        findViewById(R.id.TimerButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    myStartActivity(HomeActivity.class);
                    break;
                case R.id.TimerButton:
                    showToast(ProfileOneActivity.this, "북타이머를 실행합니다.");
                    myStartActivity(TimerActivity.class);
                    break;
                case R.id.profileEdit:
                    showToast(ProfileOneActivity.this,"프로필편집을 실행합니다.");
                myStartActivity(EditProfileActivity.class);
                break;
                case R.id.calButton:
                    showToast(ProfileOneActivity.this,"기록을 실행합니다.");
                    myStartActivity(HistoryActivity.class);
                    break;

                case R.id.heartTab:
                    Log.e("좋아요 탭", "이동");
                    //myStartActivity(LikeActivity.class); 좋아요 탭 만들기
                    break;
                case R.id.homeTab:
                    Log.e("홈 탭", "이동");
                    myStartActivity(HomeActivity.class);
                    break;
                case R.id.searchTab:
                    Log.e("검색 탭", "이동");
                    myStartActivity(SearchActivity.class);
                    break;
                case R.id.profileTab:
                    Log.e("프로필  탭", "이동");
                    showToast(ProfileOneActivity.this, "현재 탭입니다.");
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