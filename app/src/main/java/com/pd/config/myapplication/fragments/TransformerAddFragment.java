package com.pd.config.myapplication.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.dao.DaoImpl;
import com.pd.config.myapplication.pojo.TransformerBean;
import com.pd.config.myapplication.statics.CacheData;

import java.util.List;


public class TransformerAddFragment extends Fragment implements View.OnClickListener {
    private Button confirmBtn;
    private Button cancelBtn;
    private View view;// fragment top level view;
    private DaoImpl daoImpl;
    private boolean notNull;
    private  Activity mActivity;

    private TransformerBean currentTransformerBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        view = inflater.inflate(R.layout.fragment_add_transformer, container, false);
        if (!CacheData.isAddTrans) {
            initComponentsExtends();
        }
        initComponents();
        initComponentsFun();
        return view;
    }

    public TransformerBean getCurrentTransformerBean() {
        return currentTransformerBean;
    }


    public void setCurrentTransformerBean(TransformerBean currentTransformerBean) {
        this.currentTransformerBean = currentTransformerBean;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;//保存Context引用
    }

    private void initComponentsExtends() {
        TextView title = view.findViewById(R.id.title);
        title.setText("     修改设备");
        Button btn= view.findViewById(R.id.confirmBtn);
        btn.setText("修改");
        getActivity();
        EditText nameOfTheTransformer = view.findViewById(R.id.nameOfTheTransformer);
        nameOfTheTransformer.setText(currentTransformerBean.getNameOfTheTransformer());
       AutoCompleteTextView positionOfTheTransformer = view.findViewById(R.id.nameOfSubstation);
        positionOfTheTransformer.setText(currentTransformerBean.getNameOfTheSubstation());
        EditText typeOfTheInstrument = view.findViewById(R.id.typeOfInstrument);
        typeOfTheInstrument.setText(currentTransformerBean.getTypeOfTheTransformer());
        EditText serialNumber = view.findViewById(R.id.CreationNumber);
        serialNumber.setText(currentTransformerBean.getNumOfCreation());
        EditText manufactureName = view.findViewById(R.id.manufacturer);
        manufactureName.setText(currentTransformerBean.getNameOfCompany());
        EditText dateOfCreation = view.findViewById(R.id.dateOfCreation);
        dateOfCreation.setText(currentTransformerBean.getDateOfCreation());
        RadioGroup phaseNumber = view.findViewById(R.id.numberOfPhase);
        if(currentTransformerBean.getPhase().equals("三相")){
            phaseNumber.check(R.id.triPhase);
        }else {
            phaseNumber.check(R.id.singlePhase);
        }
        RadioGroup numberOfRotate = view.findViewById(R.id.numberOfRotate);
        if(currentTransformerBean.getNumberOfRotate().equals("三绕组")){
          numberOfRotate.check(R.id.threeRounds);
        }else {
            numberOfRotate.check(R.id.doubleRounds);
        }
    }
    private void initComponentsFun() {
        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
    }

    private void initComponents() {
        daoImpl = new DaoImpl(getActivity());
        confirmBtn = view.findViewById(R.id.confirmBtn);
        cancelBtn = view.findViewById(R.id.cancelBtn);
        List<String> substations = daoImpl.findSubstations();
        String[] autoCompleteOptions = new String[] { "Hello", "Hey", "Heja", "Hi", "Hola", "Bonjour", "Gday", "Goodbye", "Sayonara", "Farewell", "Adios" };
        ArrayAdapter autoCompleteAdapter = new ArrayAdapter(getContext(), R.layout.adapter_simple_line,substations);


        AutoCompleteTextView positionOfTheTransformer = view.findViewById(R.id.nameOfSubstation);
       positionOfTheTransformer.setAdapter(autoCompleteAdapter);
        positionOfTheTransformer.showDropDown();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmBtn:
                // code execute in add trans mode
                if(CacheData.isAddTrans){
                    TransformerBean bean = readAllInput();
                    if (notNull) {
                        TransformerBean transformer = daoImpl.findSpecificTransformer(bean.getNameOfTheSubstation(), bean.getNameOfTheTransformer());
                        if(transformer.getNameOfTheTransformer()==null){
                            daoImpl.addATransformer(bean);
                            android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                            //注意v4包的配套使用
                            Fragment fragment = new TransformerAddFragment();
                            fm.beginTransaction().replace(R.id.rightLayout, new transformerPickFragment()).commit();
                        }else {
                            Toast.makeText(getActivity(), "变压器名字不可重复！", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "至少填写变压器和变电站名称", Toast.LENGTH_LONG).show();
                    }
                }else {
                    TransformerBean bean = readAllInput();
                    bean.setId(currentTransformerBean.getId());
                    if(notNull){
                        daoImpl.changeToTheOtherOne(bean);
                        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                        //注意v4包的配套使用
                        fm.beginTransaction().replace(R.id.rightLayout, new transformerPickFragment()).commit();
                    }else {
                        Toast.makeText(getActivity(), "至少填写变压器和变电站名称", Toast.LENGTH_LONG).show();
                          }
                }
                break;
            case R.id.cancelBtn:
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                //注意v4包的配套使用
                fm.beginTransaction().replace(R.id.rightLayout, new transformerPickFragment()).commit();
                break;
        }
    }


    private TransformerBean readAllInput() {
        EditText nameOfTheTransformer =  view.findViewById(R.id.nameOfTheTransformer);
        String name = nameOfTheTransformer.getText().toString();
        EditText positionOfTheTransformer = view.findViewById(R.id.nameOfSubstation);
        String position = positionOfTheTransformer.getText().toString();
        EditText typeOfTheInstrument = view.findViewById(R.id.typeOfInstrument);
        String type = typeOfTheInstrument.getText().toString();
        EditText serialNumber = view.findViewById(R.id.CreationNumber);
        String serial = serialNumber.getText().toString();
        EditText manufactureName = view.findViewById(R.id.manufacturer);
        String manufacture = manufactureName.getText().toString();
        EditText dateOfCreation = view.findViewById(R.id.dateOfCreation);
        String date = dateOfCreation.getText().toString();
        RadioGroup phaseNumber = view.findViewById(R.id.numberOfPhase);
        RadioButton selectedBtn = view.findViewById(phaseNumber.getCheckedRadioButtonId());
        String phase = selectedBtn.getText().toString();
        RadioGroup numberOfRotate = view.findViewById(R.id.numberOfRotate);
        RadioButton selectedNumber = view.findViewById(numberOfRotate.getCheckedRadioButtonId());
        String rotateNum = selectedNumber.getText().toString();
        notNull = checkNull(nameOfTheTransformer, positionOfTheTransformer);
        return new TransformerBean(position, name, type, serial, phase, rotateNum, manufacture, date);

    }

    private boolean checkNull(EditText nameOfTheTransformer, EditText positionOfTheTransformer) {
        return nameOfTheTransformer.getText() != null && positionOfTheTransformer.getText() != null;
    }
}
