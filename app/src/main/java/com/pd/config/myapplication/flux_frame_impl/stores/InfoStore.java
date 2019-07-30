package com.pd.config.myapplication.flux_frame_impl.stores;

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

    private InfoStore() {
        //初始化infos集合,集合有六个可显示的对象
        dataInfos = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dataInfos.add(new DataInfo(i + 1));
        }
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
                int postion = (int) action.getData1();
                DataInfo info = (DataInfo) action.getData();
                dataInfos.set(postion, info);
                break;
            case SWAP_DATA_INFO:
                dataInfos = (List<DataInfo>) action.getData();
                break;
            case DELETE_DATA_INFO:
                dataInfos.set((Integer) action.getData(), new DataInfo((Integer) action.getData()));
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
