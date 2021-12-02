package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    TextView txt1,input1,input2,input3,input4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);
        txt1 = findViewById(R.id.txt1);
        RequestQueue queue = Volley.newRequestQueue(this);
        btn1.setOnClickListener(v->{
            //String url="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101";
            String url="http://10.0.2.2/users?id="+input1.getText().toString();
            StringRequest request =new StringRequest(url,
                    response -> {
                //map이나 uservo 사용
             Map<String,Object> map = gson.fromJson(response,Map.class);
                        System.out.println(map);
                       // System.out.println(map.keySet().iterator());
                       txt1.setText("name :"+map.get("name") +" password : "+map.get("password"));


                    },
                    error -> {
                Log.d("request", error.toString());
            });
        queue.add(request);
        });
        btn2.setOnClickListener(v->{
            String url = "http://10.0.2.2/insertUser";
            StringRequest request=new StringRequest(Request.Method.POST,url, s->{
                Map<String,Object> map= gson.fromJson(s,Map.class);

                txt1.setText("id : "+ map.get("id")+" name : "+map.get("name")+" password : "+map.get("password")+ " role : "+map.get("role"));
            }, e->{
                Log.d("i", e.toString());
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<String,String>();
                    map.put("id",input1.getText().toString());
                    map.put("name",input2.getText().toString());
                    map.put("password",input3.getText().toString());
                    map.put("role",input4.getText().toString());
                    return map;
                }
            };
            queue.add(request);
        });
        btn3.setOnClickListener(v->{
            String url = "http://10.0.2.2/updateUser";
            StringRequest request = new StringRequest(Request.Method.POST,url,s->{
                Map<String,Object> map= gson.fromJson(s,Map.class);

                txt1.setText("id : "+ map.get("id")+" name : "+map.get("name")+" password : "+map.get("password")+ " role : "+map.get("role"));
            },e->{
                Log.d("u", e.toString());
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<String,String>();
                    map.put("id",input1.getText().toString());
                    map.put("name",input2.getText().toString());
                    map.put("password",input3.getText().toString());
                    map.put("role",input4.getText().toString());
                    return map;
            }};
            queue.add(request);
        });
        btn4.setOnClickListener(v->{
            String url = "http://10.0.2.2/deleteUser?id="+input1.getText().toString();

            StringRequest request = new StringRequest(url,s->{
                Map<String,Object> map = gson.fromJson(s,Map.class);
                txt1.setText("id : "+ map.get("id")+" name : "+map.get("name")+" password : "+map.get("password")+ " role : "+map.get("role"));
            },e->{
                Log.d("e", e.toString());
            });
            queue.add(request);
        });
    }
}