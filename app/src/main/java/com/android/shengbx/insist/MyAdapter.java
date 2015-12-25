package com.android.shengbx.insist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by shengbx on 12/25/15.
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    LayoutInflater inflater;
    private List<String> mTitleList = new ArrayList<>();

    public MyAdapter(Context context) {
        super();
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void add(String title){
        mTitleList.add(title);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if(convertView == null){
            holder = new Holder();
            convertView = inflater.inflate(R.layout.grid_item,parent,false);
            holder.iv = (ImageView) convertView.findViewById(R.id.icon);
            holder.tv = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
            holder.iv = (ImageView) convertView.findViewById(R.id.icon);
            holder.tv = (TextView) convertView.findViewById(R.id.title);
        }

        holder.iv.setImageResource(R.drawable.icon);
        holder.tv.setText(mTitleList.get(position));
        return convertView;
    }

    class Holder{
        ImageView iv;
        TextView tv;
    }

}
