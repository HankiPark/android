package com.example.myhttp;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello");
        //Log.d("test","hello");
        Gson gson = new Gson();
        EmpVO vo = new EmpVO();
        vo.setAge(10);
        vo.setFname("chol");
        String result = gson.toJson(vo);
        System.out.println(result);


        String response="{\"fname\":\"hanki\",\"age\":29}";
        EmpVO emp = gson.fromJson(response, EmpVO.class);
        System.out.println(emp.getFname());

        Map<String,Object> map = gson.fromJson(response,Map.class);
        System.out.println(map.get("fname"));

        response ="{\"data\":[{\"fname\":\"김유신\",\"age\":10}]}";
        map = gson.fromJson(response,Map.class);
        // List list =(List) map.get("data");
        //Map std =(Map)list.get(0);
        //std.get("fname")
        System.out.println(((Map)((List<Object>)map.get("data")).get(0)).get("fname"));

        ListEmp listEmp = gson.fromJson(response,ListEmp.class);
        System.out.println(listEmp.data.get(0).getFname());


    }








}
