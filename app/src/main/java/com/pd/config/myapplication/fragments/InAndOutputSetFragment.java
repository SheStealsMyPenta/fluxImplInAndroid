package com.pd.config.myapplication.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.statics.CacheData;


public class InAndOutputSetFragment extends Fragment implements RadioGroup.OnCheckedChangeListener { //
    private RadioGroup topPhaseGroups;
    private RadioGroup botPhaseGroups;
    private RadioGroup topCellsGroups;
    private RadioGroup botCellsGroups;
    private TextView input;
    private TextView output;
    private View view;// fragment top level view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_set_input_output, container, false);
        initComponent();//initialize four radio groups and set the conditions
        initComponentFun();
        initFromCache();
        return view;
    }

    private void initFromCache() {
        if(CacheData.cacheFormBean.getInput()!=null&&CacheData.cacheFormBean.getOutput()!=null){
            String[] input = CacheData.cacheFormBean.getInput().split("-");
            String inputPhase = input[0];
//            String inputCell = input[1];
            String[] output = CacheData.cacheFormBean.getOutput().split("-");
            String outputPhase = output[0];
//            String outputCell = output[1];
          int  topPhaseCount=  topPhaseGroups.getChildCount();
          int topCellCount = topCellsGroups.getChildCount();
          int botPhaseCount = botPhaseGroups.getChildCount();
          int botCellCount = botCellsGroups.getChildCount();
          for(int i=0;i<topPhaseCount;i++){
             RadioButton btn= (RadioButton) topPhaseGroups.getChildAt(i);
              String topPhastText = (String) btn.getText();
              if(topPhastText.contains(inputPhase)){
                  btn.setChecked(true);
              }
          }
          for(int i = 0;i<topCellCount;i++){
              RadioButton btn= (RadioButton) topCellsGroups.getChildAt(i);
              String topCellstText = (String) btn.getText();
//              if(topCellstText.contains(inputCell)){
//                  btn.setChecked(true);
//              }
          }
            for(int i=0;i<botPhaseCount;i++){
                RadioButton btn= (RadioButton) botPhaseGroups.getChildAt(i);
                String botPhastText = (String) btn.getText();
                if(botPhastText.contains(outputPhase)){
                    btn.setChecked(true);
                }
            }
            for(int i = 0;i<botCellCount;i++){
                RadioButton btn= (RadioButton) botCellsGroups.getChildAt(i);
                String botCellstText = (String) btn.getText();
//                if(botCellstText.contains(outputCell)){
//                    btn.setChecked(true);
//                }
            }
        }
    }

    private void initComponentFun() {
        topPhaseGroups.setOnCheckedChangeListener(this);
        topCellsGroups.setOnCheckedChangeListener(this);
        botPhaseGroups.setOnCheckedChangeListener(this);
        botCellsGroups.setOnCheckedChangeListener(this);
//        swapTheConditionOfTopPhaseGroup();
//        swapTheConditionOfBotPhaseGroup();
//        swapTheConditionOfBotCellsGroup();
//        swapTheConditionOfTopCellGroup();

    }



    private void initComponent() {
        input = getActivity().findViewById(R.id.input);
        output=getActivity().findViewById(R.id.output);
        topPhaseGroups = view.findViewById(R.id.topPhaseGroup);
        botPhaseGroups = view.findViewById(R.id.botPhaseGroup);
        topCellsGroups = view.findViewById(R.id.topCellsGroup);
        botCellsGroups = view.findViewById(R.id.botCellsGroup);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group == topPhaseGroups) {
//            swapTheConditionOfTopPhaseGroup();
            setTheInput();
        } else if (group == botPhaseGroups) {
//            swapTheConditionOfBotPhaseGroup();
            setTheOutPut();
        } else if (group == topCellsGroups) {
            setTheInput();
//           swapTheConditionOfBotCellsGroup();
        } else {
            setTheOutPut();
//            swapTheConditionOfTopCellGroup();
        }
    }

    private void setTheInput() {
        StringBuilder builder = new StringBuilder();
        RadioButton phaseBtn= view.findViewById(topPhaseGroups.getCheckedRadioButtonId());
//        RadioButton cellBtn = view.findViewById(topCellsGroups.getCheckedRadioButtonId());
        builder.append(phaseBtn.getText());
//        builder.append(cellBtn.getText());
        input.setText(builder.toString());
        CacheData.cacheFormBean.setInput(builder.toString());
    }
    private void setTheOutPut(){
        StringBuilder builder = new StringBuilder();
        RadioButton phaseBtn= view.findViewById(botPhaseGroups.getCheckedRadioButtonId());
//        RadioButton cellBtn = view.findViewById(botCellsGroups.getCheckedRadioButtonId());
        builder.append(phaseBtn.getText());
//        builder.append(cellBtn.getText());
        output.setText(builder.toString());
        CacheData.cacheFormBean.setOutput(builder.toString());

    }

    private void swapTheConditionOfBotPhaseGroup() {
        switch (botPhaseGroups.getCheckedRadioButtonId()) {
            case R.id.botAPhase: {
                topPhaseGroups.findViewById(R.id.topAPhase).setEnabled(false);
                topPhaseGroups.findViewById(R.id.topBPhase).setEnabled(true);
                topPhaseGroups.findViewById(R.id.topCPhase).setEnabled(true);
                topPhaseGroups.findViewById(R.id.topOPhase).setEnabled(true);
                break;
            }
            case R.id.botBPhase: {
                topPhaseGroups.findViewById(R.id.topBPhase).setEnabled(false);
                topPhaseGroups.findViewById(R.id.topCPhase).setEnabled(true);
                topPhaseGroups.findViewById(R.id.topOPhase).setEnabled(true);
                topPhaseGroups.findViewById(R.id.topAPhase).setEnabled(true);
                break;
            }
            case R.id.botCPhase: {
                topPhaseGroups.findViewById(R.id.topCPhase).setEnabled(false);
                topPhaseGroups.findViewById(R.id.topAPhase).setEnabled(true);
                topPhaseGroups.findViewById(R.id.topOPhase).setEnabled(true);
                topPhaseGroups.findViewById(R.id.topBPhase).setEnabled(true);
                break;
            }
            case R.id.botDPhase: {
                topPhaseGroups.findViewById(R.id.topOPhase).setEnabled(false);
                topPhaseGroups.findViewById(R.id.topAPhase).setEnabled(true);
                topPhaseGroups.findViewById(R.id.topBPhase).setEnabled(true);
                topPhaseGroups.findViewById(R.id.topCPhase).setEnabled(true);
                break;
            }


        }
    }

    private void swapTheConditionOfTopPhaseGroup() {
        switch (topPhaseGroups.getCheckedRadioButtonId()) {
            case R.id.topAPhase: {
                botPhaseGroups.findViewById(R.id.botAPhase).setEnabled(false);
                botPhaseGroups.findViewById(R.id.botBPhase).setEnabled(true);
                botPhaseGroups.findViewById(R.id.botCPhase).setEnabled(true);
                botPhaseGroups.findViewById(R.id.botDPhase).setEnabled(true);
                break;
            }
            case R.id.topBPhase: {
                botPhaseGroups.findViewById(R.id.botBPhase).setEnabled(false);
                botPhaseGroups.findViewById(R.id.botCPhase).setEnabled(true);
                botPhaseGroups.findViewById(R.id.botDPhase).setEnabled(true);
                botPhaseGroups.findViewById(R.id.botAPhase).setEnabled(true);
                break;
            }
            case R.id.topCPhase: {
                botPhaseGroups.findViewById(R.id.botCPhase).setEnabled(false);
                botPhaseGroups.findViewById(R.id.botAPhase).setEnabled(true);
                botPhaseGroups.findViewById(R.id.botDPhase).setEnabled(true);
                botPhaseGroups.findViewById(R.id.botBPhase).setEnabled(true);
                break;
            }
            case R.id.topOPhase: {
                botPhaseGroups.findViewById(R.id.botDPhase).setEnabled(false);
                botPhaseGroups.findViewById(R.id.botAPhase).setEnabled(true);
                botPhaseGroups.findViewById(R.id.botBPhase).setEnabled(true);
                botPhaseGroups.findViewById(R.id.botCPhase).setEnabled(true);
                break;
            }


        }
    }
    private void swapTheConditionOfTopCellGroup() {
        switch (botCellsGroups.getCheckedRadioButtonId()) {
            case R.id.botFirstCell: {
                topCellsGroups.findViewById(R.id.topFirstCell).setEnabled(false);
                topCellsGroups.findViewById(R.id.topSecondCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topThirdCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topForthCell).setEnabled(true);
                break;
            }
            case R.id.botSecondCell: {
                topCellsGroups.findViewById(R.id.topFirstCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topSecondCell).setEnabled(false);
                topCellsGroups.findViewById(R.id.topThirdCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topForthCell).setEnabled(true);
                break;
            }
            case R.id.botThirdCell: {
                topCellsGroups.findViewById(R.id.topFirstCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topSecondCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topThirdCell).setEnabled(false);
                topCellsGroups.findViewById(R.id.topForthCell).setEnabled(true);
                break;
            }
            case R.id.botForthCell: {
                topCellsGroups.findViewById(R.id.topFirstCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topSecondCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topThirdCell).setEnabled(true);
                topCellsGroups.findViewById(R.id.topForthCell).setEnabled(false);
                break;
            }

        }
    }

    private void swapTheConditionOfBotCellsGroup() {
        switch (topCellsGroups.getCheckedRadioButtonId()) {
            case R.id.topFirstCell: {
                botCellsGroups.findViewById(R.id.botFirstCell).setEnabled(false);
                botCellsGroups.findViewById(R.id.botSecondCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botThirdCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botForthCell).setEnabled(true);
                break;
            }
            case R.id.topSecondCell: {
                botCellsGroups.findViewById(R.id.botFirstCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botSecondCell).setEnabled(false);
                botCellsGroups.findViewById(R.id.botThirdCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botForthCell).setEnabled(true);
                break;
            }
            case R.id.topThirdCell: {
                botCellsGroups.findViewById(R.id.botFirstCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botSecondCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botThirdCell).setEnabled(false);
                botCellsGroups.findViewById(R.id.botForthCell).setEnabled(true);
                break;
            }
            case R.id.topForthCell: {
                botCellsGroups.findViewById(R.id.botFirstCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botSecondCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botThirdCell).setEnabled(true);
                botCellsGroups.findViewById(R.id.botForthCell).setEnabled(false);
                break;
            }

        }
    }
}
