package com.pd.config.myapplication.activities;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fizzer.doraemon.passwordinputdialog.PassWordDialog;
import com.fizzer.doraemon.passwordinputdialog.impl.DialogCompleteListener;
import com.pd.config.myapplication.R;
import com.pd.config.myapplication.adapters.FragmentAdapter;
import com.pd.config.myapplication.flux_frame_impl.stores.VitalSettingsStore;
import com.pd.config.myapplication.flux_frame_java.BaseFluxActivity;
import com.pd.config.myapplication.flux_frame_java.dispatcher.Dispatcher;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.pd.config.myapplication.fragments.deviceInfo.DeviceBasicInfoFragment;
import com.pd.config.myapplication.fragments.deviceInfo.DeviceVitalInfoFragment;
import com.pd.config.myapplication.pojo.PrivateSettings;
import com.pd.config.myapplication.services.LogicService;
import com.pd.config.myapplication.views.NoScrollViewPage;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceInfoActivity extends FragmentActivity {
    private LogicService service;
    private VitalSettingsStore vitalSettingsStore;
    private DeviceVitalInfoFragment fragment;
    private DeviceBasicInfoFragment basicInfoFragment;
    private List<Fragment> listOfFragment = new ArrayList<>();
    @BindView(R.id.privateSetting)
    Button privateSetting;
    @BindView(R.id.tabLayout)
    TabLayout tabLaoutLayout;
    @BindView(R.id.viewPager)
    NoScrollViewPage viewPage;
    @BindView(R.id.config)
    Button config;
    @BindView(R.id.back)
    Button back;

    @OnClick({R.id.privateSetting, R.id.config, R.id.back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.privateSetting:

                new PassWordDialog(this).setTitle("请输入密码").setSubTitle("提示").setMoney("内部参数谨慎修改").setCompleteListener(new DialogCompleteListener() {
                    @Override
                    public void dialogCompleteListener(String money, String pwd) {
                        if (pwd.equals("123456")) {
                            viewPage.setCurrentItem(1);
                            config.setVisibility(View.VISIBLE);
                            privateSetting.setVisibility(View.INVISIBLE);
                            service.fetchPrivateSettings();
                        }
                    }
                }).show();

                break;
            case R.id.config:
                PrivateSettings value = fragment.getValue();
                service.setPrivateSettings(value);
                break;
            case R.id.back:
                if (viewPage.getCurrentItem() == 0) {
                    finish();
                } else {
                    viewPage.setCurrentItem(0);
                    config.setVisibility(View.INVISIBLE);
                    privateSetting.setVisibility(View.VISIBLE);
                }

                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        initDependencies();
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        basicInfoFragment = DeviceBasicInfoFragment.getInstance();
        fragment = DeviceVitalInfoFragment.getInstance();
        List<String> titles = new ArrayList<>();

        titles.add("内部参数");
        titles.add("基本参数");
        listOfFragment.add(basicInfoFragment);
        listOfFragment.add(fragment);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), listOfFragment, titles);
        viewPage.setAdapter(adapter);

        tabLaoutLayout.setupWithViewPager(viewPage);

    }

    @Override
    protected void onResume() {
        super.onResume();
        vitalSettingsStore = VitalSettingsStore.getAVitalSettingStore();
        vitalSettingsStore.register(this);
        Dispatcher dispather = Dispatcher.getADispather();
        dispather.register(vitalSettingsStore);

    }

    @Override
    protected void onPause() {
        super.onPause();
        vitalSettingsStore.unregister(this);
        Dispatcher dispather = Dispatcher.getADispather();
        dispather.unregister(vitalSettingsStore);
    }


    protected void initDependencies() {
        service = LogicService.getALogicService();

    }


    @Subscribe
    public void onStoreChange(Store.StoreChangeEvent event) {
        if (event.type.equals("vitalSetting")) {
            if (vitalSettingsStore.getTypeOfOperation().equals("fetching")) {
                PrivateSettings settings = vitalSettingsStore.getPrivateSettings();
                fragment.setValue(settings);
                fragment.stopSwipe();
            } else if (vitalSettingsStore.getTypeOfOperation().equals("setting")) {
                Toast.makeText(this, vitalSettingsStore.getMessage(), Toast.LENGTH_LONG).show();
            } else if (vitalSettingsStore.getTypeOfOperation().equals("network")) {
                Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
                fragment.stopSwipe();
            }

        }
    }
}
