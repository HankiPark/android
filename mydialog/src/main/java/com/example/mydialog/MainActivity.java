package com.example.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1,btn2,btn3,btn4;

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        String[] city = new String[]{"서울","경기","수원","부산"};
        btn1.setOnClickListener(v->{

            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("aleeeeert").setMessage("alert")
                    .setPositiveButton("positive", (dialogInterface, i) -> {
                        Log.d("alert","수정버튼");}
                        )
                    .setNegativeButton("negative", (dialogInterface, i) -> {Log.d("cancel","취소버튼");}).create().show();
        });
        ArrayList selectedItems = new ArrayList<Integer>();
        btn2.setOnClickListener(v->{
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("도시이름").setMultiChoiceItems(city,null,(d,i,b)->{
                if (b) {
                    // If the user checked the item, add it to the selected items
                    selectedItems.add(i);
                } else if (selectedItems.contains(i)) {
                    // Else, if the item is already in the array, remove it
                    selectedItems.remove(i);
                }
                Log.d("city",city[i]);
                for(int k=0;k<selectedItems.size();k++){
                    Log.d("abc",city[Integer.parseInt(selectedItems.get(k).toString())]);
                };

            }).setPositiveButton("y", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                //    Arrays.stream(selectedItems).filter();
                    for(Object k:selectedItems){
                        Log.d("abc",city[Integer.parseInt(k.toString())]);//city[Integer.parseInt(k.toString())]
                    };  // User clicked OK, so save the selectedItems results somewhere
                    // or return them to the component that opened the dialog

                }
            })
                    .setNegativeButton("c", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    }).create().show();
        });
        //custom dialog
        btn3.setOnClickListener(v->{
        customModal();
        });
        btn4.setOnClickListener(v->{

        });


    }

    private void customModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(R.layout.activity_login).create().show();
    }


}