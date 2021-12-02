package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Button btnASel,btn1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            Toast.makeText(getApplicationContext(),"등록되었습니다.",Toast.LENGTH_LONG).show();

        }
        else if(resultCode ==2){
            Toast.makeText(getApplicationContext(),"수정되었습니다.",Toast.LENGTH_LONG).show();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        btn1=findViewById(R.id.btn1);
        btnASel = findViewById(R.id.btnASel);
        lv = findViewById(R.id.lv);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        List<Map<String,String>> list= new ArrayList<>();
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","홍길동");
        map.put("addr","대구");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name","김기자");
        map.put("addr","제천");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name","소나무");
        map.put("addr","설");
        list.add(map);


        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),list
                ,android.R.layout.simple_list_item_2,new String[]{"name","addr"},new int[]{android.R.id.text1,android.R.id.text2});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
           Toast.makeText(getApplicationContext(),list.get(i).get("addr"),Toast.LENGTH_LONG).show();
        });

        btn1.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
            intent.putExtra("_id","50");
            intent.putExtra("name","홍길동");
            intent.putExtra("age","40");
            intent.putExtra("mobile","01010101010");
            startActivityForResult(intent,0);
        });


    btnASel.setOnClickListener(v->{
        String a="";



        String sql = "select _id, name, age, mobile from emp order by _id desc ";
        Cursor cursor=db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            a = " name : " + cursor.getString(1);
          //  list.add(a);
        }
       // String[] data = new String[]{a};

    //    lv.getAdapter().notifyDataSetChanged();
        db.close();



    });












    }
}