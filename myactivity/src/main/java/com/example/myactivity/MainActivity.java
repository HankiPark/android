package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);



        btn1.setOnClickListener(v->{
            Uri uri = Uri.parse("tel:010-2222-2222");
Intent intent = new Intent(Intent.ACTION_DIAL,uri);
startActivity(intent);
        });
btn2.setOnClickListener(v->{
Uri uri= Uri.parse("http://www.hanb.co.kr");
Intent intent = new Intent(Intent.ACTION_VIEW,uri);
startActivity(intent);
        });
btn3.setOnClickListener(v->{

        });
btn4.setOnClickListener(v->{

        });
btn5.setOnClickListener(v->{
    Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.putExtra("sms_body","안녕하세요?");
    intent.setData(Uri.parse("smsto:"+Uri.encode("010-1234-5678")));
    startActivity(intent);

        });
btn6.setOnClickListener(v->{
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivity(intent);
        });
btn7.setOnClickListener(v->{
    Intent intent = new Intent(getApplicationContext(),LifeActivity.class);
    startActivity(intent);





        });

    }
}