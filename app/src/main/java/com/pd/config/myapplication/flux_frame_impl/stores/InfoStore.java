package com.pd.config.myapplication.flux_frame_impl.stores;

import com.pd.config.myapplication.adapters.InfoStoreAdapter;
import com.pd.config.myapplication.flux_frame_impl.actions.InfoAction;
import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.pd.config.myapplication.pojo.DataInfo;

import java.util.ArrayList;
import java.util.List;

import static com.pd.config.myapplication.flux_frame_impl.actions.InfoAction.*;

@SuppressWarnings("ALL")
public class InfoStore extends Store {
    private static InfoStore infoStore;
    private List<DataInfo> dataInfos;
    private InfoStoreAdapter adapter;
    private int currentItem;
    private InfoStore() {
        //初始化infos集合,集合有六个可显示的对象
        dataInfos = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if(i==0){
                DataInfo info = new DataInfo();
                info.setSortNum("序号");
                info.setDataFile("数据文件");
                info.setNameOfTransformer("设备");
                info.setNameOfCompany("单位");
                info.setDataInput("输出端");
                info.setDataOutput("输入端");
                info.setTestTime("测试日期");
                dataInfos.add(info);
            }else {
                dataInfos.add(new DataInfo(String.valueOf(i)));
            }
        }

        currentItem=-1;
    }

    public static InfoStore getAInfoStore() {
        if (infoStore == null) {
            infoStore = new InfoStore();
        }
        return infoStore;
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent("info");
    }

    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case SET_DATA_INFO:
                DataInfo info = (DataInfo) action.getData();
                dataInfos.set(Integer.parseInt(info.getSortNum()), info);
                break;
            case SWAP_DATA_INFO:
                dataInfos = (List<DataInfo>) action.getData();
                break;
            case DELETE_DATA_INFO:
                int position = (int) action.getData();
                dataInfos.set(position, new DataInfo(String.valueOf(position)));
                break;
            case CHANGE_SELECTED_ITEM:
                currentItem = (int) action.getData();
                break;
            default:
                break;

        }
        emitStoreChange();
    }

    public List<DataInfo> getDataInfos() {
        return dataInfos;
    }
}
