package com.pd.config.myapplication.flux_frame_impl.stores;

import com.pd.config.myapplication.flux_frame_impl.actions.VitalSettingsAction;
import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.pd.config.myapplication.pojo.PrivateSettings;

public class VitalSettingsStore extends Store {
    private static VitalSettingsStore vitalSettingsStore;
    private PrivateSettings privateSettings;
    private String message;
    private String typeOfOperation;

    public String getTypeOfOperation() {
        return typeOfOperation;
    }

    public void setTypeOfOperation(String typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PrivateSettings getPrivateSettings() {
        return privateSettings;
    }

    private VitalSettingsStore() {
        privateSettings = new PrivateSettings();
    }

    public static VitalSettingsStore getAVitalSettingStore() {
        if (vitalSettingsStore == null) {
            vitalSettingsStore = new VitalSettingsStore();
        }
        return vitalSettingsStore;
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent("vitalSetting");
    }

    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case VitalSettingsAction
                    .SET_VITAL_SETTINGS:
                privateSettings = (PrivateSettings) action.getData();
                break;
            case VitalSettingsAction.NOTIFY:
                this.message = "设置参数成功!";
                break;
            case VitalSettingsAction.SET_OPERATION:
                this.typeOfOperation = (String) action.getData();
                break;
            case VitalSettingsAction.NET_WORK_ERROR:
                this.message = "网络错误";
                break;
        }
        emitStoreChange();
    }
}
