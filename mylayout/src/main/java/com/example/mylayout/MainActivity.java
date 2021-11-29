package com.example.mylayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridLayout lin;
    static int startNum =1;
    static int checkNum=0;
    Button rebtn;
    ArrayList<Integer> fillNum = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lin= findViewById(R.id.linear);
        rebtn = findViewById(R.id.restart);

      //  lin.setOrientation(LinearLayout.VERTICAL);

        List<Integer> num = new ArrayList<Integer>();
        List<Integer> nextNum = new ArrayList<Integer>();
        List<Integer> reNum = new ArrayList<Integer>();

        for(int i=1;i<=25;i++){num.add(i);}
        for(int i=1;i<=25;i++){reNum.add(i);}
        for(int i=26;i<=50;i++){nextNum.add(i);}
        Collections.shuffle(num);
        Collections.shuffle(reNum);



        View.OnClickListener handler=v->{
          //  System.out.println(Integer.parseInt(((Button)v).getText().toString()));
            if(((Button)v).getText().toString()!="") {
                if (Integer.parseInt(((Button) v).getText().toString()) == startNum) {
                    ((Button) v).setText("");
                    startNum++;
                    checkNum++;
                    System.out.println("선택" + v.getId());
                    fillNum.add(v.getId());
                    if (checkNum >= 5 && checkNum < 30) {
                        Collections.shuffle(fillNum);
                        Button raBtn = (Button) findViewById(fillNum.get(3));
                        raBtn.setText("" + nextNum.get(startNum - 6));

                        System.out.println(raBtn.getId());
//                        System.out.println("시작");
//                        for (Object object : fillNum) {
//                            System.out.println(object);
//                        }

                        fillNum.remove(3);

                    }
                }
                if (startNum == 51) {
                    Toast.makeText(this, "게임종료", Toast.LENGTH_LONG).show();
                    startNum = 0;
                }
                //클릭한 버튼의 숫자값을 비교 startnum이 같다면 버튼의 text 리셋
            }
        };



        for(int i =0;i<25;i++){

            Button btn = new Button(this);


            btn.setText(""+ num.get(i));
            btn.setId(num.get(i));
            btn.setTextSize(30);

            btn.setOnClickListener(handler);
            lin.addView(btn,150,150);

        }

        rebtn.setOnClickListener(v->{
            startNum =1;
            checkNum=0;
            fillNum.clear();

            Collections.shuffle(reNum);
            for(int i =0;i<25;i++){

                Button btn = (Button) findViewById(num.get(i));
               // btn.setText(""+ reNum.get(i));
                lin.removeView(btn);
            }
            for(int i =0;i<25;i++){

                Button btn = new Button(this);


                btn.setText(""+ reNum.get(i));
                btn.setId(reNum.get(i));
                btn.setTextSize(30);

                btn.setOnClickListener(handler);
                lin.addView(btn,150,150);

            }


        });

    }
}