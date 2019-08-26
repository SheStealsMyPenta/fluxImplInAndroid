package com.pd.config.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.pojo.DataInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17 0017.
 */


public class InfoStoreAdapter extends ArrayAdapter<DataInfo> {
    private int resourceId;
    private List<DataInfo> theAttr_infos;
    private Context context;

    public List<DataInfo> getTheAttr_infos() {
        return theAttr_infos;
    }

    public void setTheAttr_infos(List<DataInfo> theAttr_infos) {
        this.theAttr_infos = theAttr_infos;
    }

    public InfoStoreAdapter(@NonNull Context context, int resource, List<DataInfo> theAttr_infoList) {
        super(context, resource, theAttr_infoList);
        this.theAttr_infos = theAttr_infoList;
        this.context = context;
    }

    private static class ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;

    }

    @Override
    public int getCount() {
        return theAttr_infos.size();
    }

    @Nullable
    @Override
    public DataInfo getItem(int position) {
        return theAttr_infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.adapter_main_infostore, null);
            viewHolder.textView1 = convertView.findViewById(R.id.text1);
            viewHolder.textView2 = convertView.findViewById(R.id.text2);
            viewHolder.textView3 = convertView.findViewById(R.id.text3);
            viewHolder.textView4 = convertView.findViewById(R.id.text4);
            viewHolder.textView5 = convertView.findViewById(R.id.text5);
            viewHolder.textView6 = convertView.findViewById(R.id.text6);
            viewHolder.textView7 = convertView.findViewById(R.id.text7);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DataInfo info = theAttr_infos.get(position);
        if (info != null) {
            viewHolder.textView1.setText(info.getSortNum());
            viewHolder.textView1.setTextColor(Color.BLACK);
            viewHolder.textView2.setText(info.getDataFile());
            viewHolder.textView2.setTextColor(Color.BLACK);
            viewHolder.textView3.setText(info.getNameOfTransformer());
            viewHolder.textView3.setTextColor(Color.BLACK);
            viewHolder.textView4.setText(info.getNameOfCompany());
            viewHolder.textView4.setTextColor(Color.BLACK);
            viewHolder.textView5.setText(info.getDataInput());
            viewHolder.textView5.setTextColor(Color.BLACK);
            viewHolder.textView6.setText(info.getDataOutput());
            viewHolder.textView6.setTextColor(Color.BLACK);
            viewHolder.textView7.setText(info.getTestTime());
            viewHolder.textView7.setTextColor(Color.BLACK);
        }
        return convertView;
    }
}
