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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class PostActivity extends AppCompatActivity {
    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private String text;
    private String bookName;
    private String category;
    private String time;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd hh:mm a");
        String datatime = dateFormat.format(c.getTime());
        spinner();
        time = ((TextView) findViewById(R.id.imageTime)).getText().toString();
        time = datatime;

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


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
                    Log.e("게시글저장", "클릭");
                    save();
                    break;
                case R.id.galleryButton:
                    Log.e("갤러리접근", "클릭");
                    Toast.makeText(PostActivity.this, "개발중.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cameraButton:
                    Log.e("카메라접근", "클릭");
                    Toast.makeText(PostActivity.this, "개발중.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    public void spinner() {
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

        spinner2 = (Spinner) findViewById(R.id.categoryView);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = arrayList.get(i);
                Log.e(category, "클릭");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void save() {


        text = ((EditText) findViewById(R.id.inputText)).getText().toString();
        bookName = ((EditText) findViewById(R.id.inputBook)).getText().toString();
        FirebaseUser user = mAuth.getCurrentUser();
        HashMap result = new HashMap<>();
        result.put("작성시간", time);
        result.put("책 제목", bookName);
        result.put("카테고리", category);
        result.put("글", text);
        writeNewPost(time, bookName, category, text);
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

    private void writeNewPost(String time, String bookName, String category, String text) {
        Post post = new Post(time, bookName, category, text);

        mDatabase.child("post").child(bookName).setValue(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(PostActivity.this, "업로드를 성공했습니다.", Toast.LENGTH_SHORT).show();
                        myStartActivity(MainActivity.class);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(PostActivity.this, "업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}