package com.pd.config.myapplication.flux_frame_impl.actions;

import com.pd.config.myapplication.flux_frame_java.actions.Action;

public class VitalSettingsAction extends Action {
    public static final  String SET_VITAL_SETTINGS = "SET_VITAL_SETTINGS";
    public static final String NOTIFY ="NOTIFY";
    public static final String SET_OPERATION = "SET_OPERATION";
    public static final String NET_WORK_ERROR = "NET_WORK_ERROR";

    public VitalSettingsAction(String type, Object data) {
        super(type, data);
    }
}
