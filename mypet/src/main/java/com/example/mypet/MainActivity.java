package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox cb;
    TextView tv;
    RadioGroup rbo;
    RadioButton rbo1,rbo2,rbo3;
    Button btn;
    ImageView iv;
    int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb = findViewById(R.id.checkBox);
        tv = findViewById(R.id.textView);
        rbo = findViewById(R.id.rdo);
        rbo1 = findViewById(R.id.rbtn);
        rbo2 = findViewById(R.id.rbtn2);
        rbo3 = findViewById(R.id.rbtn3);
        btn = findViewById(R.id.btnOK);
        iv = findViewById(R.id.imgPet);

        cb.setOnClickListener(v -> {
            if (cb.isChecked()) {
                tv.setVisibility(View.VISIBLE);
                rbo.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.drawable.dog);
            } else {
                tv.setVisibility(View.INVISIBLE);
                rbo.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.INVISIBLE);
                iv.setVisibility(View.INVISIBLE);
            }
        });
        btn.setOnClickListener(v -> {
            switch(num){
                case 1: iv.setImageResource(R.drawable.dog);break;
                case 2: iv.setImageResource(R.drawable.dog2);break;
                case 3: iv.setImageResource(R.drawable.boxer);break;

            }
        });
        rbo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                           @Override
                                           public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                               if (i == R.id.rbtn) {
                                                   num=1;
                                               } else if (i == R.id.rbtn2) {
                                                   num=2;
                                               } else if (i == R.id.rbtn3) {
                                                  num=3;
                                               } else {

                                               }
                                           }
                                       }
        );

    }





    }
