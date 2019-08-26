package com.pd.config.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pd.config.myapplication.R;

import java.util.List;


public class AdapterForDateReview extends ArrayAdapter {
    private Context context;
    private List<String> names;
    private int selectedPosition=-1;
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public List<String> getNames() {
        return names;
    }

    public AdapterForDateReview(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.names=objects;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(this.context);
            convertView= mInflater.inflate(R.layout.adapter_history_review,null);
            viewHolder.textView = convertView.findViewById(R.id.text1);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(names!=null){
            if(selectedPosition==position){
                viewHolder.textView.setText(names.get(position));
                viewHolder.textView.setBackground(context.getDrawable(R.drawable.a_shape));
            }else {
                viewHolder.textView.setText(names.get(position));
                  viewHolder.textView.setBackground(null);

            }
        }
        return convertView;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    class ViewHolder{
        private TextView textView;
    }
}
