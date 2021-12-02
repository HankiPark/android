package com.example.mylist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecycleActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
    rv =findViewById(R.id.rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);

        ArrayList<MemoVO> list= new ArrayList<MemoVO>();
        MemoVO map = new MemoVO();
        map.setTitle("1번");
        map.setContent("1번내용");
        list.add(map);
        map = new MemoVO();
        map.setTitle("2번");
        map.setContent("2번내용");
        list.add(map);
        map = new MemoVO();
        map.setTitle("3번");
        map.setContent("3번내용");
        list.add(map);




    rv.setAdapter(new MyRecycleAdapter(list));

    rv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("아이템 선택됨").create().show();  // click event

        }
    }


//                        .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // FIRE ZE MISSILES!
//                            }
//                        })
//                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        });
        // Create the AlertDialog object and return it


    );


    }
}