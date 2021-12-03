package com.example.mydiary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class DiaryDAO {
    DBHelper dbhelper;
    String tableName="diary";

    public static ArrayList<DiaryVO> selectAll(DBHelper dbhelper){
        ArrayList<DiaryVO> list = new ArrayList<DiaryVO>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql = "select * from diary order by _id desc";
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            DiaryVO diaryVO = new DiaryVO();
            diaryVO.setId(cursor.getString(0));
            diaryVO.setTitle(cursor.getString(1));
            diaryVO.setContent(cursor.getString(2));
            diaryVO.setTime(cursor.getString(3));
      //      Log.d("TAG", cursor.getString(4));
            if(cursor.getColumnCount()==5){
                Log.d("TAG", "selectAll: null?");
            diaryVO.setImg(cursor.getString(4));}

            list.add(diaryVO);

        }
    db.close();


return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void insert(DBHelper dbhelper, DiaryVO diaryVO){

        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title",diaryVO.getTitle());
        contentValues.put("content",diaryVO.getContent());
        if(diaryVO.getImg()!=null){
            contentValues.put("img",diaryVO.getImg());
        }else{contentValues.put("img","null");}
        LocalDate dt = LocalDate.now();
        dt.format(DateTimeFormatter.BASIC_ISO_DATE);
        contentValues.put("time",diaryVO.getTime());

        db.insert("diary",null,contentValues);
        db.close();
    }

    public static void delete(DBHelper dbhelper,DiaryVO diaryVO){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete("diary","_id=?",new String[]{diaryVO.getId()});
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void update(DBHelper dbhelper, DiaryVO diaryVO){
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title",diaryVO.getTitle());
        contentValues.put("content",diaryVO.getContent());
        if(diaryVO.getImg()!=null){
            contentValues.put("img",diaryVO.getImg());
        }else{contentValues.put("img","null");}

        LocalDate dt = LocalDate.now();
        dt.format(DateTimeFormatter.BASIC_ISO_DATE);
        contentValues.put("time",diaryVO.getTime());

        db.update("diary",contentValues,"_id=?",new String[]{diaryVO.getId()});
        db.close();
    }



}
