package com.example.mylayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    GridLayout lin;
    static int startNum =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lin= findViewById(R.id.linear);

        View.OnClickListener handler=v->{
            System.out.println(v.getId());
            if(v.getId()==startNum){
                ((Button)v).setText("");
                startNum++;
            }
            if(startNum==17){
                Toast.makeText(this,"게임종료",Toast.LENGTH_LONG).show();
                startNum=0;
            }
            //클릭한 버튼의 숫자값을 비교 startnum이 같다면 버튼의 text 리셋

        };
      //  lin.setOrientation(LinearLayout.VERTICAL);
        int k=0;
        int a=0;
        Integer num[] = new Integer[16];
        Integer[] arr;

        while(k==0){

     for(int i =0;i<16;i++) {
         while (a == 0) {
             int ran = (int) Math.floor(Math.random() * 16 + 1);

             if (ran == 17) {
                 ran--;
             }

             num[i] = ran;
             Set<Integer> set = new HashSet<Integer>(Arrays.<Integer>asList(num));
             arr = set.toArray(new Integer[0]);
             System.out.println(num[i]);
             if(arr.length==num.length){a=1;}
         }
         a = 0;
     }
     if(num.length==16){k=1;}

 }
        for(int i =0;i<16;i++){

            Button btn = new Button(this);

            btn.setText(""+num[i]);
            btn.setId(num[i]);

            btn.setOnClickListener(handler);
            lin.addView(btn);

        }


    }
}