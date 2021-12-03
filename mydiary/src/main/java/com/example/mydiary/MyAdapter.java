package com.example.mydiary;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    List<DiaryVO> data;

    public MyAdapter(){}
    public MyAdapter(List<DiaryVO> data) {
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.listview_item,viewGroup,false);
        TextView txtTitle=view.findViewById(R.id.txtTitle);
        TextView txtContent= view.findViewById(R.id.txtContent);
        ImageView imageView = view.findViewById(R.id.imageView);
        txtTitle.setText(data.get(i).getTitle());
        txtContent.setText(data.get(i).getContent());
        if(data.get(i).getImg()!=null){
        imageView.setImageURI(Uri.parse(data.get(i).getImg()));}

        return view;

    }
}
