package com.example.booksns_re;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpThreeActivity extends AppCompatActivity {
    int count[]={0,0,0,0,0,0,0,0,0,0,0,0};
    int temp=0;
    int sub=0;
    int i=0;
    private ImageButton[] btn = new ImageButton[12];
    private ArrayList<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_three);

        mDataList = new ArrayList<String>();

        btn[0] = findViewById(R.id.novelButton);
        btn[1] = findViewById(R.id.novelIButton);
        btn[2] = findViewById(R.id.novelOButton);
        btn[3] = findViewById(R.id.essayButton);
        btn[4] = findViewById(R.id.poemButton);
        btn[5] = findViewById(R.id.humanButton);
        btn[6] = findViewById(R.id.selfButton);
        btn[7] = findViewById(R.id.moneyButton);
        btn[8] = findViewById(R.id.historyButton);
        btn[9] = findViewById(R.id.healthButton);
        btn[10] = findViewById(R.id.childButton);
        btn[11] = findViewById(R.id.comButton);
        for (int i = 0; i <= 11; i++)
            btn[i].setOnClickListener(onClickListener);
        findViewById(R.id.nextButton3).setOnClickListener(onClickListener);
        findViewById(R.id.resetButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.novelButton:
                    addList(0);
                    break;
                case R.id.novelIButton:
                   addList(1);
                    break;
                case R.id.novelOButton:
                    addList(2);
                    break;
                case R.id.essayButton:
                    addList(3);
                    break;
                case R.id.poemButton:
                    addList(4);
                    break;
                case R.id.humanButton:
                    addList(5);
                    break;
                case R.id.selfButton:
                    addList(6);
                    break;
                case R.id.moneyButton:
                    addList(7);
                    break;
                case R.id.historyButton:
                    addList(8);
                    break;
                case R.id.healthButton:
                    addList(9);
                    break;
                case R.id.childButton:
                    addList(10);
                    break;
                case R.id.comButton:
                    addList(11);
                    break;
                case R.id.nextButton3:
                    checkList();
                    break;
                case R.id.resetButton:
                    resetList();
                    break;
            }
        }

    };
    private void addList(int c){
        if(count[c]==0) {
            ++count[c];
            Toast.makeText(getApplicationContext(), "관심목록에 추가했어요.", Toast.LENGTH_SHORT).show();
        }else{
            --count[c];
            Toast.makeText(getApplicationContext(), "관심목록에서 해제했어요.", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkList(){
      for(i=0; i<=11; i++){
          temp += count[i];
      }
      sub=5-temp;
      if(sub<=0){
          Toast.makeText(getApplicationContext(), "관심목록추가: "+temp+"개, 등록되었습니다!", Toast.LENGTH_SHORT).show();
          temp=0;
          myStartActivity(MainActivity.class);
      }
      else{
          Toast.makeText(getApplicationContext(), "관심목록추가: "+temp+"개, "+sub+"개 더 추가해주세요!", Toast.LENGTH_SHORT).show();
          temp=0; sub=0;
      }
    }
    private void resetList(){
        for(i=0; i<=11; i++){
            count[i]=0;
        }
        Toast.makeText(getApplicationContext(), "관심목록 초기화되었습니다!", Toast.LENGTH_SHORT).show();
    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}