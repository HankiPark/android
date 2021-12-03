package com.example.mydiary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper dbhelper ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ListView lv = findViewById(R.id.listDiary);
        dbhelper = new DBHelper(getApplicationContext());

        if (resultCode==2) {
            Toast.makeText(getApplicationContext(),"등록되었습니다.",Toast.LENGTH_LONG).show();
            ArrayList<DiaryVO> list= DiaryDAO.selectAll(dbhelper);
            MyAdapter adapter = new MyAdapter(list);
            lv.setAdapter(adapter);

        }
        if (resultCode==1) {
            Toast.makeText(getApplicationContext(),"수정되었습니다.",Toast.LENGTH_LONG).show();
            ArrayList<DiaryVO> list= DiaryDAO.selectAll(dbhelper);
            MyAdapter adapter = new MyAdapter(list);
            lv.setAdapter(adapter);

        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = findViewById(R.id.listDiary);
        Button btn1 = findViewById(R.id.btnWriteForm);
        dbhelper = new DBHelper(getApplicationContext());

        ArrayList<DiaryVO> list= DiaryDAO.selectAll(dbhelper);
        MyAdapter adapter = new MyAdapter(list);
        lv.setAdapter(adapter);

        btn1.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
            intent.putExtra("_id","abc");
            startActivityForResult(intent,0);
        });






        //list view 초기화 -> adapter 지정->
        //아이템클릭 이벤트 : 수정 삭제
        //쓰기버튼 이벤트 지정
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
//                        Log.d("num","");
                    ArrayList<DiaryVO> list= DiaryDAO.selectAll(dbhelper);
                    DiaryVO diaryVO = new DiaryVO();
                    diaryVO.setId(""+list.get(i).getId());
                   Log.d("del", getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+(Uri.parse(list.get(i).getImg()).getPath()).substring(16));
                    Log.d("del2", list.get(i).getImg());
                    if(list.get(i).getImg()!=null){

                        File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+(Uri.parse(list.get(i).getImg()).getPath()).substring(16));
                        file2.delete();}
                    DiaryDAO.delete(dbhelper,diaryVO);
                    list= DiaryDAO.selectAll(dbhelper);
                    MyAdapter adapter = new MyAdapter(list);
                    lv.setAdapter(adapter);
                    Log.d("root", getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());


                    //list.remove(i);
                    //(diaryAdapter)lv.getAdapter().notify();
                    //((diaryAdapter)lv.getAdapter().notifyDataSetChanged();
                }
            })
                    .setNegativeButton("수정", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            ArrayList<DiaryVO> list= DiaryDAO.selectAll(dbhelper);
                            Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                            intent.putExtra("_id",""+list.get(i).getId());
                            intent.putExtra("title",""+list.get(i).getTitle());
                            intent.putExtra("content",""+list.get(i).getContent());
                            intent.putExtra("img",""+list.get(i).getImg());
                            startActivityForResult(intent,1);
                        }
                    }).create().show();



        });










    }




}