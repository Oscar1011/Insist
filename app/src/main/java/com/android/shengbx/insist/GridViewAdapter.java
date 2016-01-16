package com.android.shengbx.insist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.shengbx.insist.sql.InsistInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shengbx on 12/25/15.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    LayoutInflater inflater;
    private List<InsistInfo> mInistList = new ArrayList<>();

    public GridViewAdapter(Context context) {
        super();
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void add(InsistInfo item){
        mInistList.add(item);
    }

    @Override
    public int getCount() {
        return mInistList.size();
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
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.insist_days = (TextView) convertView.findViewById(R.id.insist_days);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
//            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
//            holder.title = (TextView) convertView.findViewById(R.id.title);
//            holder.insist_days = (TextView) convertView.findViewById(R.id.insist_days);
        }

        holder.icon.setImageResource(R.drawable.icon);
        holder.title.setText(mInistList.get(position).getTitle());
        holder.insist_days.setText(mContext.getString(R.string.insist_days,mInistList.get(position).getDayNow()));
        return convertView;
    }

    public InsistInfo getListItemByIndex(int index){
        return mInistList.get(index);
    }

    class Holder{
        ImageView icon;
        TextView title;
        TextView insist_days;
    }

}
