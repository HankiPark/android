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

public class InsertActivity extends AppCompatActivity {
    EditText txtName, txtNum,txtPhone,txtAge,txtResult2;
    Button btnIns, btnSel,btnDel,btnUpd,btnCac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        txtPhone = findViewById(R.id.txtPhone);
        txtNum = findViewById(R.id.txtNum);
        txtResult2=findViewById(R.id.txtOList);
        btnIns = findViewById(R.id.btnIns);
        btnSel = findViewById(R.id.btnSel);
        btnDel = findViewById(R.id.btnDel);
        btnUpd = findViewById(R.id.btnUpd);
        btnCac = findViewById(R.id.btnCac);
        Intent intent = getIntent();
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        System.out.println(intent.getExtras().getString("name"));
        txtName.setText(intent.getExtras().getString("name"));
        txtAge.setText(intent.getExtras().getString("age"));
       txtPhone.setText(intent.getExtras().getString("mobile"));
       txtNum.setText(intent.getExtras().getString("_id"));


        btnDel.setOnClickListener(v->{

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.delete("emp", "_id=?", new String[]{txtNum.getText().toString()});   db.close();

        });
        btnUpd.setOnClickListener(v->{
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("name", txtName.getText().toString());
            contentValues.put("age", txtAge.getText().toString());
            contentValues.put("mobile", txtPhone.getText().toString());
            String id = txtNum.getText().toString();
            db.update("emp", contentValues, "_id=?", new String[]{id}) ;
            intent2.putExtra("toast","2");
            setResult(2,intent2);
            db.close();
            finish();
        });

        btnIns.setOnClickListener(v->{
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            String sqlInsert = "INSERT INTO emp " +
                    "(_id,NAME, age, mobile) VALUES (" +
                    txtNum.getText().toString()+",'" + txtName.getText().toString() + "',"+ txtAge.getText().toString() +" ,'"+txtPhone.getText().toString()+"')" ;
            // prepareStatement처럼 ?로 비워두고 나중에 값을 삽입하여도 상관이 없다. 이럴경우에는
            // db.execSQL(sqlInsert , new Object{ name,20,"aaa"}); 이런식으로 작성한다.- > 뒤쪽은 배열로만
            db.execSQL(sqlInsert) ;

            intent2.putExtra("toast","1");
            setResult(1,intent2);


            db.close();
            finish();
        });
        btnSel.setOnClickListener(v->{
            String a="";
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            String sql = "select _id, name, age, mobile from emp where _id="+txtNum.getText().toString() +" order by _id desc " ;
            Cursor cursor=db.rawQuery(sql, null);
            while (cursor.moveToNext()){

                a+="id : "+cursor.getString(0)+" name : "+cursor.getString(1)+" age : "+cursor.getString(2)+" mobile : "+cursor.getString(3)+"\n";

            }
            txtResult2.setText(a);
            db.close();


        });




    }
}