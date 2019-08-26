package com.pd.config.myapplication.flux_frame_impl.actions;

import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.pojo.DataInfo;

import java.util.ArrayList;
import java.util.List;

public class InfoAction extends Action {
    public final static String SET_DATA_INFO ="SET_DATA_INFO";
    public final  static String SWAP_DATA_INFO ="SWAP_DATA_INFO";
    public final static String DELETE_DATA_INFO = "DELETE_DATA_INFO";
    public final static String CHANGE_SELECTED_ITEM="CHANGE_SELECTED_ITEM";
    public final static ArrayList<String > listOfInfoAction  = new ArrayList();
    static{
        listOfInfoAction.add(SET_DATA_INFO);
        listOfInfoAction.add(SWAP_DATA_INFO);
        listOfInfoAction.add(DELETE_DATA_INFO);
        listOfInfoAction.add(CHANGE_SELECTED_ITEM);
    }
    public InfoAction(String type, DataInfo data) {
        super(type, data);
    }
    public InfoAction(String type, List<DataInfo> data){
        super(type,data);
    }
    public  InfoAction(String type,Object object){
        super(type,object);
    }

}
