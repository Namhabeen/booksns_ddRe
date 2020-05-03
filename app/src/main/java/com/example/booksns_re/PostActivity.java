package com.example.booksns_re;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PostActivity extends AppCompatActivity {
    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private String text;
    private String bookName;
    private String bookcategory;
    private TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Calendar c= Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd hh:mm a");
        String datatime = dateFormat.format(c.getTime());
        spinner();
        time= (TextView)findViewById(R.id.imageTime);
        time.setText("게시시간: "+datatime);
        text= ((EditText) findViewById(R.id.inputText)).getText().toString();
        bookName = ((EditText) findViewById(R.id.inputBook)).getText().toString();
        findViewById(R.id.backButton).setOnClickListener(onClickListener);
        findViewById(R.id.saveButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    Log.e("작성취소", "클릭");
                    myStartActivity(MainActivity.class);
                    finish();
                    break;
                case R.id.saveButton:
                    Log.e("게시글저장","클릭");
                    save();
                    break;
            }
        }
    };

    private void spinner(){
        arrayList = new ArrayList<>();
        arrayList.add("소설");
        arrayList.add("국내소설");
        arrayList.add("국외소설");
        arrayList.add("에세이");
        arrayList.add("시");
        arrayList.add("인문");
        arrayList.add("자기계발");
        arrayList.add("경제/경영");
        arrayList.add("역사/문화");
        arrayList.add("운동");
        arrayList.add("어린이만화");
        arrayList.add("컴퓨터/공학");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner2 = (Spinner)findViewById(R.id.categoryView);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bookcategory=arrayList.get(i);
                Log.e(bookcategory, "클릭");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void save(){
        //파이어베이스에 값 넘겨주고
        //카테고리:bookCategory, 게시글:text, 책제목: bookName, 현재시간: time
        //현재창 finish해서 종료시키기
        //MainActivity로 이동
        //toast로 "작성완료되었습니다."
    }

    //-------설정-------//
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}