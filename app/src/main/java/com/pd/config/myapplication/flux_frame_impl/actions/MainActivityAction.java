package com.pd.config.myapplication.flux_frame_impl.actions;

import com.pd.config.myapplication.flux_frame_java.actions.Action;

public class MainActivityAction extends Action {
    public final static String SET_POSITION = "SET_POSITION";
    public final static String SET_STATE="SET_STATE";
    public static final String SET_TEST_PARAMS = "SET_TEST_PARAMS";
    public static final String CHANGE_STATE_BTN   =  "CHANGE_STATE_BTN";

    public MainActivityAction(String type, Object data) {
        super(type, data);
    }
}
