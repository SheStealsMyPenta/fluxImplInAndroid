package com.pd.config.myapplication.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pd.config.myapplication.R;

import com.pd.config.myapplication.adapters.AdapterForPickTransOrSubstation;
import com.pd.config.myapplication.adapters.AdapterForTransformer;
import com.pd.config.myapplication.dao.DaoImpl;
import com.pd.config.myapplication.pojo.TransformerBean;
import com.pd.config.myapplication.statics.CacheData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




public class transformerPickFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    //initializeButtons
    private Button addTransBtn;
    private Button chooseTransBtn;
    private Button modifTransBtn;
    private Button deleteTransBtn;
    private View view; // fragment main view
    //initialize two ListViews and adapters
    private ListView listViewSubstation;
    private ListView listViewTransformer;
    private AdapterForPickTransOrSubstation adapterForSubstation;
    private AdapterForTransformer adapterForTransformer;
    //initialize the database helper
    private DaoImpl impl;
    //two object that been selected
    private TransformerBean currentTrans;
    private String currentSubstation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_transforer_fragment, container, false);
        initComponents();//initialize components
        initComponentsFun(); //initialize component's functional operation
        setUpTwoListViews();
       setFromCache();
        return view;
    }

    private void setFromCache() {
          if(CacheData.cacheFormBean.getSubstation()!=null&&CacheData.cacheFormBean.getTransformer()!=null){
              List<String> substations = impl.findSubstations();
              if(substations.contains(CacheData.cacheFormBean.getSubstation())){
                  currentSubstation = CacheData.cacheFormBean.getSubstation();
                  adapterForSubstation.setSelectedPosition(substations.indexOf(CacheData.cacheFormBean.getSubstation()));
                  adapterForSubstation.notifyDataSetInvalidated();
                 ArrayList <TransformerBean> transformers = impl.findTransformers(CacheData.cacheFormBean.getSubstation());
                  adapterForTransformer =new AdapterForTransformer(getActivity(), R.layout.adapater_for_slipview, R.id.text1,transformers);
                  listViewTransformer.setAdapter(adapterForTransformer);
                  listViewTransformer.invalidate();
                  listViewTransformer.setOnItemClickListener(this);
                  Iterator<TransformerBean> iterator = transformers.iterator();
                  ArrayList<String> listOfName= new ArrayList<>();
                  while(iterator.hasNext()){
                      listOfName.add(iterator.next().getNameOfTheTransformer());
                  }
                  if(listOfName.contains(CacheData.cacheFormBean.getTransformer())){
                      int i = listOfName.indexOf(CacheData.cacheFormBean.getTransformer());
                      currentTrans = transformers.get(i);
                      adapterForTransformer.setSelectedPosition(i);
                      adapterForTransformer.notifyDataSetInvalidated();

                  }
              }


          }
    }


    private void setUpTwoListViews() {
        List<String> substations = impl.findSubstations();
        listViewSubstation.setDivider(null);
        adapterForSubstation = new AdapterForPickTransOrSubstation(getActivity(), R.layout.adapater_for_slipview, R.id.text1, substations);
        listViewSubstation.setAdapter(adapterForSubstation);
        listViewSubstation.setOnItemClickListener(this);
    }

    private void initComponentsFun() {
        addTransBtn.setOnClickListener(this);
        chooseTransBtn.setOnClickListener(this);
        deleteTransBtn.setOnClickListener(this);
        addTransBtn.setOnClickListener(this);
        modifTransBtn.setOnClickListener(this);

    }

    private void initComponents() {
        impl = new DaoImpl(getActivity());
        addTransBtn = view.findViewById(R.id.addTransBtn);
        chooseTransBtn = view.findViewById(R.id.chooseTransBtn);
        modifTransBtn = view.findViewById(R.id.modifyTrans);
        deleteTransBtn  =view.findViewById(R.id.deleteTransBtn);
        listViewSubstation = view.findViewById(R.id.substationPicker);
        listViewTransformer = view.findViewById(R.id.transformerPicker);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addTransBtn: {
                CacheData.isAddTrans=true;
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.rightLayout, new TransformerAddFragment()).commit();
                break;
            }
            case R.id.chooseTransBtn:{
             TextView nameOfSubstation = getActivity().findViewById(R.id.nameOfSubstation);
             TextView nameOfTrans  =getActivity().findViewById(R.id.nameOfTheTransformer);
             if(currentSubstation!=null&&currentTrans!=null){
                 nameOfSubstation.setText(currentSubstation);
                 nameOfTrans.setText(currentTrans.getNameOfTheTransformer());
                 CacheData.cacheFormBean.setTransformer(currentTrans.getNameOfTheTransformer());
                 CacheData.cacheFormBean.setSubstation(currentSubstation);
             }else {
                 Toast.makeText(getActivity(),"请先选择选择变压器以及变电站",Toast.LENGTH_LONG).show();

             }

             break;
            }
            case R.id.modifyTrans:{
                if(currentTrans==null){
                    Toast.makeText(getActivity(),"请选择需要修改的变压器",Toast.LENGTH_LONG).show();
                }else {
                    CacheData.isAddTrans=false;
                    android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                    //注意v4包的配套使用
                    Fragment fragment = new TransformerAddFragment();
                    ((TransformerAddFragment) fragment).setCurrentTransformerBean(currentTrans);
                    fm.beginTransaction().replace(R.id.rightLayout, fragment).commit();
                }
                break;
            }
            case  R.id.deleteTransBtn:{
                if(currentTrans!=null){
                    final android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("警告");
                    builder.setMessage("您当前要删除此变压器，此操不可恢复，请确认");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            impl.deleteThisTransformer(currentTrans.getNameOfTheTransformer(),currentTrans.getNameOfTheSubstation());
                            Fragment fragment = new TransformerAddFragment();
                            fm.beginTransaction().replace(R.id.rightLayout, new transformerPickFragment()).commit();
                        }
                    });
                    builder.setNegativeButton("取消",null);
                    builder.create().show();

                }else {
                    Toast.makeText(getActivity(),"请选择需要删除的变压器",Toast.LENGTH_LONG).show();
                }
                break;

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() instanceof AdapterForPickTransOrSubstation) {
            ArrayList<TransformerBean> listOfname = new ArrayList<>();
            currentSubstation = (String) listViewSubstation.getAdapter().getItem(position);
            ArrayList<TransformerBean> transformers = impl.findTransformers(currentSubstation);
            Iterator<TransformerBean> iterator = transformers.iterator();
            while (iterator.hasNext()) {
                listOfname.add(iterator.next());
            }
            adapterForSubstation.setSelectedPosition(position);
            adapterForSubstation.notifyDataSetInvalidated();
            adapterForTransformer = new AdapterForTransformer(getActivity(), R.layout.adapater_for_slipview, R.id.text1, listOfname);
            listViewTransformer.setAdapter(adapterForTransformer);
            listViewTransformer.setOnItemClickListener(this);
            CacheData.cacheFormBean.setSubstation(currentSubstation);
        } else if(parent.getAdapter() instanceof AdapterForTransformer) {
            currentTrans = (TransformerBean) listViewTransformer.getAdapter().getItem(position);
            adapterForTransformer.setSelectedPosition(position);
            adapterForTransformer.notifyDataSetInvalidated();
            chooseTransBtn.callOnClick();
            CacheData.cacheFormBean.setTransformer(currentTrans.getNameOfTheTransformer());

        }

    }
}
