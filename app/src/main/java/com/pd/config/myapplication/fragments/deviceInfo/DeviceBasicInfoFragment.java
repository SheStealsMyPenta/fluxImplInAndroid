package com.pd.config.myapplication.fragments.deviceInfo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pd.config.myapplication.R;

public class DeviceBasicInfoFragment extends Fragment {

    public static DeviceBasicInfoFragment getInstance(){
        DeviceBasicInfoFragment deviceBasicInfoFragment = new DeviceBasicInfoFragment();
        return deviceBasicInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_basic_device_info,null);

        return view;
    }
}
