package com.pd.config.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.statics.CacheData;


public class TypeOfRoatateSetFrag extends Fragment {
    private View view; // top view of the Fragment
    private Button confirmBtn;
    private TextView typeOfRoatate;
    private RadioGroup group;
    RadioButton button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_rotate_type, container, false);
        initComponentAndFun();
        initFromCache();
        return view;

    }

    private void initFromCache() {
        for(int i =0 ;i<group.getChildCount();i++){
         RadioButton button=    (RadioButton)group.getChildAt(i);
            if(button.getText().equals(CacheData.cacheFormBean.getTypeOfRotate())){
                button.setChecked(true);
            }else {
                button.setChecked(false);
            }

        }


    }

    private void initComponentAndFun() {
        typeOfRoatate = getActivity().findViewById(R.id.rotateType);
        group = view.findViewById(R.id.radioForRotate);
        String currentType = (String) typeOfRoatate.getText();
        switch (currentType) {
            case "高压绕组": {
                group.check(R.id.hiFre);
                break;
            }
            case "中压绕组": {
                group.check(R.id.midFre);
                break;
            }
            case "低压绕组": {
                group.check(R.id.lowFre);
                break;
            }
            case "低压II绕组": {
                group.check(R.id.lowFreExtend);
                break;
            }
        }
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn = view.findViewById(group.getCheckedRadioButtonId());
                typeOfRoatate.setText(btn.getText());
                CacheData.cacheFormBean.setTypeOfRotate((String) btn.getText());
            }
        });
    }
}
