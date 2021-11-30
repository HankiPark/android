package com.example.mywidget;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {
    Button btn1,btn2,btn3,btn4;
    EditText txtNum1,txtNum2;
    TextView tv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_calc);
        btn1=findViewById(R.id.button24);
        btn2=findViewById(R.id.button27);
        btn3=findViewById(R.id.button26);
        btn4=findViewById(R.id.button25);
        txtNum1=findViewById(R.id.txtNum1);
        txtNum2=findViewById(R.id.txtNum2);
        tv = findViewById(R.id.textView5);
        //익명 클래스( 클래스 선언과 생성을 동시에)
        //람다식으로 표현 (하나의 abstract method가 있는 경우에만)
        txtNum1.setText("5");
        txtNum2.setText("6");


        View.OnClickListener handler= v->{
          int n1 =Integer.parseInt(txtNum1.getText().toString());
          int n2 =Integer.parseInt(txtNum2.getText().toString());
          double result=0.0;
          switch(v.getId()){
              case R.id.button24:
                  result= n1+n2 ; break;
              case R.id.button27:
                  result= n1-n2; break;
              case R.id.button26:
                  result= n1*n2; break;
              case R.id.button25:
                  result = (double)n1/(double)n2; break;
          }
            tv.setText(String.valueOf(result));

        };
        btn1.setOnClickListener(handler);
        btn2.setOnClickListener(handler);
        btn3.setOnClickListener(handler);
        btn4.setOnClickListener(handler);
    }

// class ClickHandler implements View.OnClickListener{
//
//    @Override
//    public void onClick(View view) {
//        System.out.println("클릭됨");
//        Toast.makeText(getApplicationContext(),"클릭됨",Toast.LENGTH_LONG).show();
//
//    }
//
//
//}


}

