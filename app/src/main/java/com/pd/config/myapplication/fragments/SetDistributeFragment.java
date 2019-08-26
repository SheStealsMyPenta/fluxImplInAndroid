package com.pd.config.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.statics.CacheData;
import com.pd.config.myapplication.views.MyRadioGroup;

import java.util.Objects;



public class SetDistributeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private View view;// fragment top level view;
    private TextView position;
    private EditText custom;
    private MyRadioGroup group;
    private RadioButton autoSetting;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        view = inflater.inflate(R.layout.fragment_set_position, container, false);
        initComponentAndFun();
        initFromCache();

        return view;

    }
    private void initFromCache() {
        if(CacheData.cacheFormBean.getPosition()!=null){
            int count = group.getChildCount();
            boolean exist = false;
            for(int i=0;i<count;i++){
              LinearLayout view = (LinearLayout) group.getChildAt(i);
                int childCount = view.getChildCount();
                for(int j=0;j<childCount;j++){
                    View cView= view.getChildAt(j);
                    if(cView instanceof RadioButton){
                        RadioButton btn = (RadioButton)cView;
                        String s = btn.getText().toString();
                        if(s.equals(CacheData.cacheFormBean.getPosition())){
                            exist=true;
                            btn.setChecked(true);
                        }else {
                            btn.setChecked(false);

                        }
                    }
                }
            }
            if(!exist){
                RadioButton button =   view.findViewById(R.id.autoSetting);
                button.setChecked(true);
                custom.setText(CacheData.cacheFormBean.getPosition());
            }else {
                RadioButton button =   view.findViewById(R.id.autoSetting);
                button.setChecked(false);
            }
        }
    }
    private void initComponentAndFun() {
        autoSetting = view.findViewById(R.id.autoSetting);
        autoSetting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(custom.getText()!=null){
                        position.setText(custom.getText());
                    }
                }
            }
        });
        group= view.findViewById(R.id.positionGroup);
        group.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                int id=    group.getCheckedRadioButtonId();
                RadioButton btn = group.findViewById(id);
                position.setText(btn.getText());
                CacheData.cacheFormBean.setPosition(btn.getText().toString());
                RadioButton button =   view.findViewById(R.id.autoSetting);
                button.setChecked(false);

            }
        });
        final RadioButton button =   view.findViewById(R.id.autoSetting);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    RadioButton button1 = view.findViewById( group.getCheckedRadioButtonId());
                    if(button1!=null){
                        button1.setChecked(false);
                    }
                    buttonView.setChecked(true);
                }
            }
        });
       position = getActivity().findViewById(R.id.positions);
       custom=  view.findViewById(R.id.customSet);
        View.OnKeyListener onKey = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    RadioButton button =   view.findViewById(R.id.autoSetting);
                    if(button.isChecked()){
                        position.setText(custom.getText());
                        CacheData.cacheFormBean.setPosition(custom.getText().toString());
                    }
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }else {
                    return false;
                }
            }
        };
        custom.setOnKeyListener(onKey);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
             int id=  group.getCheckedRadioButtonId();
             RadioButton btn = group.findViewById(id);
             position.setText(btn.getText());
             CacheData.cacheFormBean.setPosition(custom.getText().toString());
    }
}
