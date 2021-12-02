package com.example.myfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    DatePicker dp;
    TextView txt1;
    static String fileName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        dp = findViewById(R.id.datePicker);
        txt1=findViewById(R.id.txt1);


        dp.init(2021,11,02,(datePicker, i, i1, i2) -> {
            Log.d("abc", ""+i+i1+i2);
            try {
                i1=i1+1;
                fileName= ""+i +((i1<10)?"0"+i1:i1)+((i2<10)?"0"+i2:i2);
                FileInputStream inFs = openFileInput(fileName+".txt");
                byte[] txt = new byte[30];
                inFs.read(txt);
                String str = new String(txt);
                txt1.setText(str);
                
            } catch (Exception e) {
                txt1.setText("");
                Toast.makeText(getApplicationContext(), "파일이없습니다", Toast.LENGTH_SHORT).show();

            }

        });
        btn1.setOnClickListener(v->{
            try {

                FileOutputStream outFs = openFileOutput(fileName+".txt", Context.MODE_PRIVATE);

               outFs.write(txt1.getText().toString().getBytes());
                outFs.close();
               txt1.setText("");
                Toast.makeText(getApplicationContext(), "txt가 생성됨", Toast.LENGTH_LONG).show();
            }catch(IOException e){}
        });

        btn2.setOnClickListener(v->{
try{
    FileInputStream inFs = openFileInput("file.txt");
    byte[] txt = new byte[30];
    inFs.read(txt);
    String str = new String(txt);
    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    inFs.close();
}catch(Exception e){

}

        });
    }
}