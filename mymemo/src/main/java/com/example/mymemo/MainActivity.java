package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText txtResult;
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

        txtResult = findViewById(R.id.txtResult);
        btn1=findViewById(R.id.btn1);
        btnASel = findViewById(R.id.btnASel);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());





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
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql = "select _id, name, age, mobile from emp order by _id desc ";
        Cursor cursor=db.rawQuery(sql, null);
        while (cursor.moveToNext()){

            a+="id : "+cursor.getString(0)+" name : "+cursor.getString(1)+" age : "+cursor.getString(2)+" mobile : "+cursor.getString(3)+"\n";

        }
        txtResult.setText(a);
        db.close();
    });












    }
}