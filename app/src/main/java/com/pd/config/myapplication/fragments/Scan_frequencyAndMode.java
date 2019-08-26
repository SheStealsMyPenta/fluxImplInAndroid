package com.pd.config.myapplication.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.statics.CacheData;
import com.pd.config.myapplication.statics.Constants;
import com.pd.config.myapplication.views.MyRadioGroup;

import java.util.Objects;

public class Scan_frequencyAndMode extends Fragment implements MyRadioGroup.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, ViewTreeObserver.OnGlobalLayoutListener, View.OnClickListener {
    private View view;// fragment top level view;

    private MyRadioGroup radioGroupForFrequency;
    private RadioGroup radioGroupForTypeScan;
    private TextView frequencyText;
    private TextView numberOfPoints;
    private TextView scanRangeTextTitle;
    private TextView scanPointsTitle;
    private TextView modeText;
    private EditText starFre;
    private EditText endFre;
    private EditText points;
    private View.OnKeyListener keyListener;
    private String currentText = "start";
    private ViewGroup viewGroup;

    @Override
    public void onDestroyView() {
        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_set_scan_attr, container, false);
        viewGroup = container;
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        initComponentAndFun();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initFromCache();
        return view;
    }

    private void initFromCache() {
        if (CacheData.cacheFormBean.getRange() != null) {
            boolean exist = false;
            int typeCount = radioGroupForTypeScan.getChildCount();
            int rangeCount = radioGroupForFrequency.getChildCount();
            for (int i = 0; i < typeCount; i++) {
                RadioButton btn = (RadioButton) radioGroupForTypeScan.getChildAt(i);
                String result = (String) btn.getText();
                if (btn.getText().toString().equals(CacheData.cacheFormBean.getMode())) {
                    btn.setChecked(true);
                }
            }
            int position = -1;
            if(CacheData.cacheFormBean.getPoint()==1000&&CacheData.cacheFormBean.getMode().equals("变压器设备")){
                position = 0;
            }else if(CacheData.cacheFormBean.getPoint()==100){
                position = 1;
            }else if(CacheData.cacheFormBean.getPoint()==200) {
                position = 2;
            } else {
                position = 4;
            }
            for (int i = 0; i < rangeCount; i++) {
                LinearLayout view = (LinearLayout) radioGroupForFrequency.getChildAt(i);
                int childCount = view.getChildCount();
                for (int j = 0; j < childCount; j++) {
                    if (view.getChildAt(j) instanceof RadioButton) {
                        RadioButton radioButton = (RadioButton) view.getChildAt(j);
                       //System.out.println("位置"+j+"点数"+radioButton.getText());
                       if(j==position){
                           radioButton.setChecked(true);
//                           exist=true;
                           return;
                       }
                    }
                }

            }
            if (!exist) {
                RadioButton bttn = (RadioButton) findView(R.id.frequencyModeCustom);
                bttn.setChecked(true);
                String[] split = CacheData.cacheFormBean.getRange().split("-");
                starFre.setText(split[0].split("k")[0]);
                endFre.setText(split[1].split("k")[0]);
                points.setText(CacheData.cacheFormBean.getPoint() + "");

            }
        }
    }

    private void initComponentAndFun() {
        radioGroupForFrequency = view.findViewById(R.id.frequencyGroup);
        radioGroupForTypeScan = view.findViewById(R.id.typeOfScan);
        scanPointsTitle = getActivity().findViewById(R.id.scanPointTitle);
        scanRangeTextTitle = getActivity().findViewById(R.id.scanRangeTitle);
        frequencyText = getActivity().findViewById(R.id.frequency);
        modeText = getActivity().findViewById(R.id.scanMode);
        numberOfPoints = getActivity().findViewById(R.id.numberOfPointss);
        starFre = view.findViewById(R.id.startFrequency);
        endFre = view.findViewById(R.id.endFrequency);
        points = view.findViewById(R.id.numberOfPoints);
        radioGroupForTypeScan.setOnCheckedChangeListener(this);
        radioGroupForFrequency.setOnCheckedChangeListener(this);
        starFre.setOnClickListener(this);
        endFre.setOnClickListener(this);
//        numberOfPoints.setOnClickListener(this);
        View.OnKeyListener onKey = new View.OnKeyListener() {

            @SuppressLint("SetTextI18n")
            @Override

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_BACK) {
                    RadioButton btn = (RadioButton) findView(R.id.frequencyModeCustom);
                    switch (v.getId()) {
                        case R.id.startFrequency:
                            if (btn.isChecked()) {
                                if (endFre.getText() != null) {
                                    frequencyText.setText(starFre.getText() + " kHz " + "-" + " " + endFre.getText() + " kHz");
                                } else {
                                    frequencyText.setText(starFre.getText() + " kHz " + "-");
                                }
                                CacheData.cacheFormBean.setRange(starFre.getText() + " kHz " + "-" + endFre.getText() + " kHz");
                            }
                            break;
                        case R.id.endFrequency:
                            if (btn.isChecked()) {
                                if (frequencyText.getText().toString().split("-").length != 1) {
                                    String[] result = frequencyText.getText().toString().split("-");
                                    result[1] = " " + endFre.getText() + " kHz";
                                    String frequency = result[0] + "-" + result[1];
                                    frequencyText.setText(frequency);
                                    CacheData.cacheFormBean.setRange(frequency);
                                } else {
                                    String frequency = (String) frequencyText.getText();
                                    frequency += " " + endFre.getText() + " kHz";
                                    CacheData.cacheFormBean.setRange(frequency);
                                    frequencyText.setText(frequency);
                                }
                            }
                            break;
                        case R.id.numberOfPoints:
                            if (btn.isChecked()) {
                                numberOfPoints.setText(points.getText().toString() + "点");
                                CacheData.cacheFormBean.setPoint(Integer.parseInt(points.getText().toString()));
                            }
                            break;
                    }
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;

                }
                return false;
            }
        };
        starFre.setOnKeyListener(onKey);
        endFre.setOnKeyListener(onKey);
        points.setOnKeyListener(onKey);
//                starFre.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        frequencyText.setText(v.getText() + " kHz " + "--");
//                        return true;
//                    }
//                });
//                endFre.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        String frequency = (String) frequencyText.getText();
//                        frequency += v.getText() + " kHz";
//                        frequencyText.setText(frequency
//                        );
//                        return true;
//                    }
//                });
//                points.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        numberOfPoints.setText(v.getText() + "点");
//                        return true;
//                    }
//                });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (radioGroupForTypeScan.getCheckedRadioButtonId() == R.id.logScan) {
            modeText.setText("对数扫描");
            CacheData.cacheFormBean.setMode("对数扫描");
        } else {
            modeText.setText("线性扫描");
            CacheData.cacheFormBean.setMode("线性扫描");
        }
    }


    private void changeTheStateOfEnable(boolean isEnable) {
        EditText textstart = (EditText) findView(R.id.startFrequency);
        EditText textEnd = (EditText) findView(R.id.endFrequency);
        EditText points = (EditText) findView(R.id.numberOfPoints);
        if (isEnable) {
            textstart.setFocusableInTouchMode(true);
            textEnd.setFocusable(true);
            points.setFocusable(true);

        } else {
            textstart.setFocusableInTouchMode(false);
            textEnd.setFocusable(false);
            points.setFocusable(false);
        }
        textstart.invalidate();
    }

    private View findView(int frequencyModeOne) {
        return view.findViewById(frequencyModeOne);
    }


    @Override
    public void onCheckedChanged(MyRadioGroup group, int checkedId) {
        if (group == radioGroupForFrequency) {
            switch (radioGroupForFrequency.getCheckedRadioButtonId()) {
                case R.id.frequencyModeOne: {
                    RadioButton btn = (RadioButton) findView(R.id.frequencyModeOne);
                    if(btn.isChecked()){
                        modeText.setText("变压器设备");
                        CacheData.cacheFormBean.setMode("变压器设备");
                        frequencyText.setVisibility(View.GONE);
                        scanRangeTextTitle.setVisibility(View.GONE);
                        scanPointsTitle.setVisibility(View.GONE);
                        numberOfPoints.setVisibility(View.GONE);
                        frequencyText.setText(Constants.MODE_ONE);
                        CacheData.cacheFormBean.setRange(Constants.MODE_ONE);
                        numberOfPoints.setText("1000点");
                        CacheData.cacheFormBean.setPoint(1000);
                    }

                    break;
                }
                case R.id.frequencyModeTwo: {
                    CacheData.cacheFormBean.setRange(Constants.MODE_TWO);
                    RadioButton btn = (RadioButton) findView(R.id.frequencyModeTwo);
                    if(btn.isChecked()){
                        frequencyText.setText(Constants.MODE_TWO);
                        numberOfPoints.setText("100点");
                        CacheData.cacheFormBean.setPoint(100);
                    }


                    break;
                }
                case R.id.frequencyModeThree: {
                    CacheData.cacheFormBean.setRange(Constants.MODE_THREE);
                    CacheData.cacheFormBean.setMode("互感器设备");
                    modeText.setText("互感器设备");
                    RadioButton btn = (RadioButton) findView(R.id.frequencyModeThree);

                    frequencyText.setVisibility(View.GONE);
                    scanRangeTextTitle.setVisibility(View.GONE);
                    scanPointsTitle.setVisibility(View.GONE);
                    numberOfPoints.setVisibility(View.GONE);
                    if(btn.isChecked()){
                        frequencyText.setText(Constants.MODE_THREE);
                        numberOfPoints.setText("200点");
                        CacheData.cacheFormBean.setPoint(200);
                    }

                    break;
                }
                case R.id.frequencyModeFour: {
                    CacheData.cacheFormBean.setRange(Constants.MODE_FOUR);
                    RadioButton btn = (RadioButton) findView(R.id.frequencyModeFour);
                    if(btn.isChecked()){
                        frequencyText.setText(Constants.MODE_FOUR);
                        numberOfPoints.setText("1099点");
                        CacheData.cacheFormBean.setPoint(1099);
                    }
                    break;
                }
                case R.id.frequencyModeFive: {
                    CacheData.cacheFormBean.setRange(Constants.MODE_FIVE);
                    RadioButton btn = (RadioButton) findView(R.id.frequencyModeFive);
                    if(btn.isChecked()){
                        frequencyText.setText(Constants.MODE_FIVE);
                        numberOfPoints.setText("1199点");
                        CacheData.cacheFormBean.setPoint(1199);
                    }
                    break;
                }
                case R.id.frequencyModeSix: {
                    CacheData.cacheFormBean.setRange(Constants.MODE_Six);
                    RadioButton btn = (RadioButton) findView(R.id.frequencyModeSix);
                    if(btn.isChecked()){
                        frequencyText.setText(Constants.MODE_Six);
                        numberOfPoints.setText("1999点");
                        CacheData.cacheFormBean.setPoint(1999);
                    }
                    break;

                }
                case R.id.frequencyModeCustom: {
                    frequencyText.setVisibility(View.VISIBLE);
                    scanRangeTextTitle.setVisibility(View.VISIBLE);
                    scanPointsTitle.setVisibility(View.VISIBLE);
                    numberOfPoints.setVisibility(View.VISIBLE);
                    modeText.setText("其他设备");
                    CacheData.cacheFormBean.setMode("其他设备");
                    if (starFre.getText() != null) {
                        starFre.getText().toString();
                        if (endFre.getText() != null) {
                            frequencyText.setText(starFre.getText() + " kHz " + "-" + " " + endFre.getText() + " kHz");
                        } else {
                            frequencyText.setText(starFre.getText() + " kHz " + "-");
                        }
                        CacheData.cacheFormBean.setRange(starFre.getText() + "kHz " + "-" + endFre.getText() + " kHz");
                    }
                    if (endFre.getText() != null) {
                        if (frequencyText.getText().toString().split("-").length != 1) {
                            String[] result = frequencyText.getText().toString().split("-");
                            result[1] = " " + endFre.getText() + " kHz";
                            String frequency = result[0] + "-" + result[1];
                            frequencyText.setText(frequency);
                            CacheData.cacheFormBean.setRange(frequency);
                        } else {
                            String frequency = (String) frequencyText.getText();
                            frequency += " " + endFre.getText() + " kHz";
                            CacheData.cacheFormBean.setRange(frequency);
                            frequencyText.setText(frequency);
                        }
                    }
                    if (points.getText() != null) {
                        numberOfPoints.setText(points.getText().toString() + "点");
                        CacheData.cacheFormBean.setPoint(Integer.parseInt(points.getText().toString()));

                    }
                    break;
                }
            }
        }
    }

    @Override
    public void onGlobalLayout() {

        int heig1 = view.getRootView().getHeight();
        int htig2 = viewGroup.getHeight();
        Rect r = new Rect();
        RadioButton btn = (RadioButton) findView(R.id.frequencyModeCustom);
        Objects.requireNonNull(getActivity()).getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int visibleHeigt = r.height();
        int heightDiff = view.getRootView().getHeight() - visibleHeigt;
        if (heightDiff < 100) {
            //大小超过100时，一般为显示虚拟键盘事件
            if (currentText == "start") {

                if (btn.isChecked()) {
                    if (endFre.getText() != null) {
                        frequencyText.setText(starFre.getText() + " kHz " + "-" + " " + endFre.getText() + " kHz");
                    } else {
                        frequencyText.setText(starFre.getText() + " kHz " + "-");
                    }
                    CacheData.cacheFormBean.setRange(starFre.getText() + "kHz " + "-" + endFre.getText() + " kHz");
                }
            } else if (currentText == "end") {
                if (btn.isChecked()) {
                    if (frequencyText.getText().toString().split("-").length != 1) {
                        String[] result = frequencyText.getText().toString().split("-");
                        result[1] = " " + endFre.getText() + " kHz";
                        String frequency = result[0] + "-" + result[1];
                        frequencyText.setText(frequency);
                        CacheData.cacheFormBean.setRange(frequency);
                    } else {
                        String frequency = (String) frequencyText.getText();
                        frequency += " " + endFre.getText() + " kHz";
                        CacheData.cacheFormBean.setRange(frequency);
                        frequencyText.setText(frequency);
                    }
                }
            } else {
                if (btn.isChecked()) {
                    numberOfPoints.setText(points.getText().toString() + "点");
                    CacheData.cacheFormBean.setPoint(Integer.parseInt(points.getText().toString()));
                }
            }
        } else {
            //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startFrequency:
                currentText = "start";
                break;
            case R.id.endFrequency:
                currentText = "end";
                break;
            case R.id.numberOfPoints:
                currentText = "point";
                break;
        }
    }
}

