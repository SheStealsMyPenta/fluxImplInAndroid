package com.pd.config.myapplication.flux_frame_impl.actions;

import com.pd.config.myapplication.flux_frame_java.actions.Action;

import java.util.ArrayList;
import java.util.List;

public class ControllerAction extends Action {
    public final static String CHANGE_CONTROLLER_STATE = "CHANGE_CONFROLLER_STATE";
    public final static String CHANGE_STATE = "CHANGE_STATE";
    public final static String DELETE_CONTROLLER = "DELETE_CONTROLLER";
    public final static List<String> listOfControllerAction = new ArrayList();
    static {
        listOfControllerAction.add(CHANGE_CONTROLLER_STATE);
        listOfControllerAction.add(CHANGE_STATE);
        listOfControllerAction.add(DELETE_CONTROLLER);
    }

    public ControllerAction(String type, Object data) {
        super(type, data);
    }

    public ControllerAction(String type, Object data, Object data1) {
        super(type, data, data1);
    }
}
