package com.pd.config.myapplication.flux_frame_impl.actions;

import com.pd.config.myapplication.flux_frame_java.actions.Action;

public class LineAction extends Action {
    public final static String CHANGE_LINES = "CHANGE_LINES";
    public final static String DELETE_LINES = "DELETE_LINES";
    public final static String ADD_LINES = "ADD_LINES";
    public final static String CHANGE_LINE_STATE= "CHANGE_LINE_STATE";
    public final static String CHANGE_CONTROLLER_STATE="CHANGE_CONFROLLER_STATE";
    public final static String ADD_POINT_TO_TEST_LINE = "ADD_POINT_TO_TEST_LINE";
    public LineAction(String type, Object data) {
        super(type, data);
    }

    public LineAction(String type, Object data, Object data1) {
        super(type, data, data1);
    }
}
