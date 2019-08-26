package com.pd.config.myapplication.flux_frame_impl.stores;

import android.graphics.Color;
import android.provider.ContactsContract;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.flux_frame_impl.actions.DataAction;
import com.pd.config.myapplication.flux_frame_impl.models.MyMessage;
import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.pd.config.myapplication.pojo.MyAnalyzer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DataStore extends Store {
    private List<MyAnalyzer> analyziers;
    private static DataStore dataStore;
    private List<Double> columnOneArrayList;
    private List<Double> columnTwoArrayList;
    private List<Double> columnThreeArrayList;
    private List<Double> columnFourArrayList;
    private List<Double> columnFiveArrayList;
    private List<Double> columnSixArrayList;
    private List<Double> columnSevenArrayList;
    private List<Double> columnEightArrayList;
    private List<Double> columnNineArrayList;



    public List<MyAnalyzer> getAnalyziers() {
        return analyziers;
    }

    private DataStore(List<MyAnalyzer> analyziers) {
        this.analyziers = analyziers;
        columnOneArrayList = new ArrayList<>();
        columnTwoArrayList= new ArrayList<>();
        columnThreeArrayList= new ArrayList<>();
        columnFourArrayList = new ArrayList<>();
        columnFiveArrayList = new ArrayList<>();
        columnSixArrayList = new ArrayList<>();
        columnSevenArrayList = new ArrayList<>();
        columnEightArrayList = new ArrayList<>();
        columnNineArrayList = new ArrayList<>();
    }

    public static DataStore getADataStore() {
        if (dataStore == null) {
            List<MyAnalyzer> listOfAnalyzer = getListOfAnalyzer();
            dataStore = new DataStore(listOfAnalyzer);

        }
        return dataStore;
    }

    private static List<MyAnalyzer> getListOfAnalyzer() {
        MyAnalyzer analyzer1 = new MyAnalyzer("相关系数", "R21", "R31", "R32", "R41", "R52", "R63", "R54", "R64", "R65", "颜色含义", Color.WHITE);
        MyAnalyzer analyzer2 = new MyAnalyzer("低频LF:1-10%", "", "", "", "", "", "", "", "", "", "严重差异", Color.RED);
        MyAnalyzer analyzer3 = new MyAnalyzer("中频MF:10-60%", "", "", "", "", "", "", "", "", "", "明显差异", Color.YELLOW);
        MyAnalyzer analyzer4 = new MyAnalyzer("高频HF:60-100%", "", "", "", "", "", "", "", "", "", "轻微差异", Color.BLUE);
        MyAnalyzer analyzer5 = new MyAnalyzer("全频HF:1-100%", "", "", "", "", "", "", "", "", "", "正常差异", Color.GRAY);
        List<MyAnalyzer> listOfAnalyzer = new ArrayList<>();
        listOfAnalyzer.add(analyzer1);
        listOfAnalyzer.add(analyzer2);
        listOfAnalyzer.add(analyzer3);
        listOfAnalyzer.add(analyzer4);
        listOfAnalyzer.add(analyzer5);
        return listOfAnalyzer;
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent("data");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAction(Action action) {

        DecimalFormat  df =  new DecimalFormat("0.00");
        switch (action.getType()) {
            case DataAction.UPDATE_DATA:
                analyziers = (List<MyAnalyzer>) action.getData();
                break;
            case DataAction.UPDATE_COLUMN_ONE:
                this.columnOneArrayList = (ArrayList) action.getData();
                if(columnOneArrayList!=null){
                    analyziers.get(1).setR21(df.format(columnOneArrayList.get(0)));
                    analyziers.get(2).setR21(df.format(columnOneArrayList.get(1)));
                    analyziers.get(3).setR21(df.format(columnOneArrayList.get(2)));
                    analyziers.get(4).setR21(df.format(columnOneArrayList.get(3)));
                }else {
                    analyziers.get(1).setR21("");
                    analyziers.get(2).setR21("");
                    analyziers.get(3).setR21("");
                    analyziers.get(4).setR21("");
                }

                break;
            case DataAction.UPDATE_COLOUMN_TWO:
                this.columnTwoArrayList = (ArrayList) action.getData();
                if(columnTwoArrayList!=null){
                    analyziers.get(1).setR31(df.format(columnTwoArrayList.get(0)));
                    analyziers.get(2).setR31(df.format(columnTwoArrayList.get(1)));
                    analyziers.get(3).setR31(df.format(columnTwoArrayList.get(2)));
                    analyziers.get(4).setR31(df.format(columnTwoArrayList.get(3)));
                }else {
                    analyziers.get(1).setR31("");
                    analyziers.get(2).setR31("");
                    analyziers.get(3).setR31("");
                    analyziers.get(4).setR31("");
                }

                break;
            case DataAction.UPDATE_COLOUMN_THREE:
                this.columnThreeArrayList = (ArrayList) action.getData();
                if(columnThreeArrayList!=null){
                    analyziers.get(1).setR32(df.format(columnThreeArrayList.get(0)));
                    analyziers.get(2).setR32(df.format(columnThreeArrayList.get(1)));
                    analyziers.get(3).setR32(df.format(columnThreeArrayList.get(2)));
                    analyziers.get(4).setR32(df.format(columnThreeArrayList.get(3)));
                }else {
                    analyziers.get(1).setR32("");
                    analyziers.get(2).setR32("");
                    analyziers.get(3).setR32("");
                    analyziers.get(4).setR32("");
                }

                break;
            case DataAction.UPDATE_COLOUMN_FOUR:
                this.columnFourArrayList = (ArrayList) action.getData();
                if(columnFourArrayList !=null){
                    analyziers.get(1).setR41(df.format(columnFourArrayList.get(0)));
                    analyziers.get(2).setR41(df.format(columnFourArrayList.get(1)));
                    analyziers.get(3).setR41(df.format(columnFourArrayList.get(2)));
                    analyziers.get(4).setR41(df.format(columnFourArrayList.get(3)));
                }else {
                    analyziers.get(1).setR41("");
                    analyziers.get(2).setR41("");
                    analyziers.get(3).setR41("");
                    analyziers.get(4).setR41("");
                }

                break;
            case DataAction.UPDATE_COLOUMN_FIVE:
                this.columnFiveArrayList = (ArrayList) action.getData();
                if(columnFiveArrayList!=null){
                    analyziers.get(1).setR52(df.format(columnFiveArrayList.get(0)));
                    analyziers.get(2).setR52(df.format(columnFiveArrayList.get(1)));
                    analyziers.get(3).setR52(df.format(columnFiveArrayList.get(2)));
                    analyziers.get(4).setR52(df.format(columnFiveArrayList.get(3)));
                }else {
                    analyziers.get(1).setR52("");
                    analyziers.get(2).setR52("");
                    analyziers.get(3).setR52("");
                    analyziers.get(4).setR52("");
                }

                break;
            case DataAction.UPDATE_COLOUMN_SIX:
                this.columnSixArrayList = (ArrayList) action.getData();
                if(columnSixArrayList!=null){
                    analyziers.get(1).setR63(df.format(columnSixArrayList.get(0)));
                    analyziers.get(2).setR63(df.format(columnSixArrayList.get(1)));
                    analyziers.get(3).setR63(df.format(columnSixArrayList.get(2)));
                    analyziers.get(4).setR63(df.format(columnSixArrayList.get(3)));
                }else {
                    analyziers.get(1).setR63("");
                    analyziers.get(2).setR63("");
                    analyziers.get(3).setR63("");
                    analyziers.get(4).setR63("");
                }

                break;
            case DataAction.UPDATE_COLOUMN_SEVEN:
                this.columnSevenArrayList = (ArrayList) action.getData();
                if(columnSevenArrayList!=null){
                    analyziers.get(1).setR54(df.format(columnSevenArrayList.get(0)));
                    analyziers.get(2).setR54(df.format(columnSevenArrayList.get(1)));
                    analyziers.get(3).setR54(df.format(columnSevenArrayList.get(2)));
                    analyziers.get(4).setR54(df.format(columnSevenArrayList.get(3)));
                }else {
                    analyziers.get(1).setR54("");
                    analyziers.get(2).setR54("");
                    analyziers.get(3).setR54("");
                    analyziers.get(4).setR54("");
                }

                break;
            case DataAction.UPDATE_COLOUMN_EIGHT:
                this.columnEightArrayList = (ArrayList) action.getData();
                if(columnEightArrayList!=null){
                    analyziers.get(1).setR64(df.format(columnEightArrayList.get(0)));
                    analyziers.get(2).setR64(df.format(columnEightArrayList.get(1)));
                    analyziers.get(3).setR64(df.format(columnEightArrayList.get(2)));
                    analyziers.get(4).setR64(df.format(columnEightArrayList.get(3)));
                }else {
                    analyziers.get(1).setR64("");
                    analyziers.get(2).setR64("");
                    analyziers.get(3).setR64("");
                    analyziers.get(4).setR64("");
                }

                break;
            case DataAction.UPDATE_COLOUMN_NINE:
                this.columnNineArrayList = (ArrayList) action.getData();
                if(columnNineArrayList!=null){
                    analyziers.get(1).setR65(df.format(columnNineArrayList.get(0)));
                    analyziers.get(2).setR65(df.format(columnNineArrayList.get(1)));
                    analyziers.get(3).setR65(df.format(columnNineArrayList.get(2)));
                    analyziers.get(4).setR65(df.format(columnNineArrayList.get(3)));
                }else {
                    analyziers.get(1).setR65("");
                    analyziers.get(2).setR65("");
                    analyziers.get(3).setR65("");
                    analyziers.get(4).setR65("");
                }

                break;

        }
        emitStoreChange();
    }
}
