package com.pd.config.myapplication.flux_frame_impl.actions;

import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.pojo.DataInfo;

import java.util.List;

public class InfoAction extends Action {
    public final static String SET_DATA_INFO ="SET_DATA_INFO";
    public final  static String SWAP_DATA_INFO ="SWAP_DATA_INFO";
    public final static String DELETE_DATA_INFO = "DELETE_DATA_INFO";
    public InfoAction(String type, DataInfo data, int data1) {
        super(type, data, data1);
    }
    public InfoAction(String type, List<DataInfo> data){
        super(type,data);
    }
}
