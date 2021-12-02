package com.example.mydiary;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class WriteActivity extends AppCompatActivity {
    DBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Button btn1 = findViewById(R.id.btnSave);
        Button btn2 = findViewById(R.id.btnImage);
        TextView edTitle = findViewById(R.id.editTitle);
        TextView edContent = findViewById(R.id.editContent);
        dbhelper = new DBHelper(getApplicationContext());
        Intent intent2 = getIntent();
        if(intent2.getExtras().getString("_id").equals("abc")) { //hasExtras() 아니면 getExtras()만
            btn1.setOnClickListener(v -> {

                DiaryVO diaryVO = new DiaryVO();
                diaryVO.setTitle(edTitle.getText().toString());
                diaryVO.setContent(edContent.getText().toString());
                DiaryDAO.insert(dbhelper, diaryVO);

                setResult(2, null);
                finish();
            });
        }else{


            edTitle.setText(intent2.getExtras().getString("title"));
            edContent.setText(intent2.getExtras().getString("content"));
            btn1.setOnClickListener(v -> {

                DiaryVO diaryVO = new DiaryVO();
                diaryVO.setId(intent2.getExtras().getString("_id"));
                diaryVO.setTitle(edTitle.getText().toString());
                diaryVO.setContent(edContent.getText().toString());
                DiaryDAO.update(dbhelper, diaryVO);
                setResult(1, null);
                finish();
            });
        }


    }
}