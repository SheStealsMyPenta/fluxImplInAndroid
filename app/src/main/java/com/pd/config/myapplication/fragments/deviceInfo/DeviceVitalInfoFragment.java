package com.pd.config.myapplication.fragments.deviceInfo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.pojo.PrivateSettings;
import com.pd.config.myapplication.services.LogicService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceVitalInfoFragment extends Fragment {
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresher;
    @BindView(R.id.inputAmplitude)
    Spinner inputAmlitude;
    @BindView(R.id.inputImpedance)
    Spinner inputImpedance;
    @BindView(R.id.outputImpedance)
    Spinner outputImpedance;
    @BindView(R.id.inputKB)
    EditText inputKB;
    @BindView(R.id.outputKB)
    EditText outputKB;

    private LogicService logicService;

    public static DeviceVitalInfoFragment getInstance() {
        DeviceVitalInfoFragment deviceVitalInfoFragment = new DeviceVitalInfoFragment();
        deviceVitalInfoFragment.logicService = LogicService.getALogicService();
        return deviceVitalInfoFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vital_device_info, null);
        ButterKnife.bind(this, v);
        Spinner spinner =  v.findViewById(R.id.inputAmplitude);
        List<String> list = new ArrayList<String>();
        list.add("1V");
        list.add("5V");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_style,list);
        spinner.setAdapter(dataAdapter);
        //
        Spinner spinner1 =  v.findViewById(R.id.inputImpedance);
        List<String> list1 = new ArrayList<String>();
        list1.add("50Ω");
        list1.add("1MΩ");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(),R.layout.spinner_style,list1);
        spinner1.setAdapter(dataAdapter1);
        Spinner spinner2 =  v.findViewById(R.id.outputImpedance);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),R.layout.spinner_style,list1);
        spinner2.setAdapter(dataAdapter2);
        initFunc();
        return v;
    }

    private void initFunc() {
        refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              logicService.fetchPrivateSettings();
            }
        });
    }

    public void setValue(PrivateSettings settings){
      inputAmlitude.setSelection(settings.getInputAmplitude());
      inputImpedance.setSelection(settings.getInputImpedance());
      outputImpedance.setSelection(settings.getOutputImpedance());
      inputKB.setText(settings.getInputChnKB()+"");
      outputKB.setText(settings.getOutputChnKB()+"");
    }
    public PrivateSettings getValue(){
        PrivateSettings settings = new PrivateSettings();
        settings.setInputAmplitude(inputAmlitude.getSelectedItemPosition());
        settings.setInputImpedance(inputImpedance.getSelectedItemPosition());
        settings.setOutputImpedance(outputImpedance.getSelectedItemPosition());
        settings.setInputChnKB(Float.parseFloat(inputKB.getText().toString()));
        settings.setOutputChnKB(Float.parseFloat(outputKB.getText().toString()));
        return settings;
    }
    public void stopSwipe(){
        refresher.setRefreshing(false);
    }
}
