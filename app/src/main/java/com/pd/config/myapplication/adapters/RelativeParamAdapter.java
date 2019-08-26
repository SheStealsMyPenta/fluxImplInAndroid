package com.pd.config.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Analyzer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.pojo.MyAnalyzer;
import com.pd.config.myapplication.pojo.RelativeParameterList;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17 0017.
 */

public class RelativeParamAdapter extends ArrayAdapter {
    private int color;
    private int resourceId;
    private List<MyAnalyzer> theAttr_infos;
    private Context context;
    public RelativeParamAdapter(@NonNull Context context, int resource, List<MyAnalyzer> relativeParamAdapters) {
        super(context, resource);
        this.context = context;
        this.theAttr_infos = relativeParamAdapters;
    }
    private static class ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;
        TextView textView11;
    }

    @Override
    public int getCount() {
        return theAttr_infos.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
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
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.adapter_relative_param,null);
            viewHolder.textView1 = convertView.findViewById(R.id.text1);
            viewHolder.textView2 = convertView.findViewById(R.id.text2);
            viewHolder.textView3 = convertView.findViewById(R.id.text3);
            viewHolder.textView4 = convertView.findViewById(R.id.text4);
            viewHolder.textView5 = convertView.findViewById(R.id.text5);
            viewHolder.textView6 = convertView.findViewById(R.id.text6);
            viewHolder.textView7 = convertView.findViewById(R.id.text7);
            viewHolder.textView8 = convertView.findViewById(R.id.text8);
            viewHolder.textView9= convertView.findViewById(R.id.text9);
            viewHolder.textView10 = convertView.findViewById(R.id.text10);
            viewHolder.textView11 = convertView.findViewById(R.id.text11);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyAnalyzer parameterList = theAttr_infos.get(position);
        if(parameterList !=null){
            viewHolder.textView1.setText(parameterList.getR21());
            viewHolder.textView1.setTextColor(Color.BLACK);
            if(!parameterList.getR21().equals("∞")&&!parameterList.getR21().equals("R21")&&!parameterList.getR21().equals("")) viewHolder.textView1.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR21())));
            viewHolder.textView2.setText(parameterList.getR31());
            viewHolder.textView2.setTextColor(Color.BLACK);
            if(!parameterList.getR31().equals("∞")&&!parameterList.getR31().equals("R31")&&!parameterList.getR31().equals("")) viewHolder.textView2.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR31())));
            viewHolder.textView3.setText(parameterList.getR32());
            viewHolder.textView3.setTextColor(Color.BLACK);
            if(!parameterList.getR32().equals("∞")&&!parameterList.getR32().equals("R32")&&!parameterList.getR32().equals("")) viewHolder.textView3.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR32())));
            viewHolder.textView4.setText(parameterList.getR41());
            viewHolder.textView4.setTextColor(Color.BLACK);
            if(!parameterList.getR41().equals("∞")&&!parameterList.getR41().equals("R41")&&!parameterList.getR41().equals("")) viewHolder.textView4.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR41())));
            viewHolder.textView5.setText(parameterList.getR52());
            viewHolder.textView5.setTextColor(Color.BLACK);
            if(!parameterList.getR52().equals("∞")&&!parameterList.getR52().equals("R52")&&!parameterList.getR52().equals("")) viewHolder.textView5.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR52())));
            viewHolder.textView6.setText(parameterList.getR63());
            viewHolder.textView6.setTextColor(Color.BLACK);
            if(!parameterList.getR63().equals("∞")&&!parameterList.getR63().equals("R63")&&!parameterList.getR63().equals("")) viewHolder.textView6.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR63())));
            viewHolder.textView7.setText(parameterList.getR54());
            viewHolder.textView7.setTextColor(Color.BLACK);
            if(!parameterList.getR54().equals("∞")&&!parameterList.getR54().equals("R54")&&!parameterList.getR54().equals("")) viewHolder.textView7.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR54())));
            viewHolder.textView8.setText(parameterList.getR64());
            viewHolder.textView8.setTextColor(Color.BLACK);
            if(!parameterList.getR64().equals("∞")&&!parameterList.getR64().equals("R64")&&!parameterList.getR64().equals("")) viewHolder.textView8.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR64())));
            viewHolder.textView9.setText(parameterList.getTitle());
            viewHolder.textView9.setTextColor(Color.BLACK);
            viewHolder.textView10.setText(parameterList.getR65());
            viewHolder.textView10.setTextColor(Color.BLACK);
            if(!parameterList.getR65().equals("∞")&&!parameterList.getR65().equals("R65")&&!parameterList.getR65().equals("")) viewHolder.textView10.setBackgroundColor(findColorByValue(parameterList.getMeaningOfColor(), Float.parseFloat(parameterList.getR65())));
            viewHolder.textView11.setText(parameterList.getMeaningOfColor());
            viewHolder.textView11.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            viewHolder.textView11.setTextColor(Color.BLACK);
            if(parameterList.getMeaningOfColor().equals("颜色含义")){
                color = 1;
            }else if (parameterList.getMeaningOfColor().equals("严重差异")){
                color = 2;
            }else if (parameterList.getMeaningOfColor().equals("明显差异")){
                color = 3;
            }else if (parameterList.getMeaningOfColor().equals("轻微差异")){
                color = 4;
            }else if (parameterList.getMeaningOfColor().equals("正常差异")){
                color = 5;
            }
            if (color ==1){
                TextView textView = convertView.findViewById(R.id.text11);
                textView.setBackgroundColor(Color.WHITE);
            } else if(color==2){
                TextView textView = convertView.findViewById(R.id.text11);
                textView.setBackgroundColor(Color.RED);
            }else if(color==3){
                TextView textView = convertView.findViewById(R.id.text11);
                textView.setBackgroundColor(Color.YELLOW);
            }else if(color==4){
                TextView textView = convertView.findViewById(R.id.text11);
                textView.setBackgroundColor(convertView.getResources().getColor(R.color.lightBlue));
            }else if(color==5){
                TextView textView = convertView.findViewById(R.id.text11);
                textView.setBackgroundColor(convertView.getResources().getColor(R.color.grey));
            }
        }
        return convertView;
    }
    public int findColorByValue(String type,float value){
        if (type.equals("严重差异")){
            if(value<0.6){
                return Color.RED;
            }else if(value<1&&value<=0.6){
                return Color.YELLOW;
            }else if(value<2&&value>=1){
                return getContext().getResources().getColor(R.color.lightBlue);
            }else if(value>=2){
                return getContext().getResources().getColor(R.color.grey);
            }
        }else if (type.equals("明显差异")){
            if(value<0.6){
                return Color.YELLOW;
            }else if(value>=0.6&&value<1.0){
                return  getContext().getResources().getColor(R.color.lightBlue);
            }else if(value>=1.0){
                return getContext().getResources().getColor(R.color.grey);
            }
        }else if (type.equals("轻微差异")){
          if(value>=.6){
              return getContext().getResources().getColor(R.color.grey);
          }
        }else if (type.equals("正常差异")){
            color = 5;
        }

        return  0;
    }
}
