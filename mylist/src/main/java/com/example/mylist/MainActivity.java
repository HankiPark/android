package com.example.mylist;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lvv);


        List<Map<String,String>> list= new ArrayList<Map<String,String>>();
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","홍길동");
        map.put("addr","대구");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name","김기자");
        map.put("addr","제천");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name","소나무");
        map.put("addr","설");
        list.add(map);

        MyAdapter adapter = new MyAdapter(list);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getApplicationContext(),list.get(i).get("addr"),Toast.LENGTH_LONG).show();
        });

    }
}