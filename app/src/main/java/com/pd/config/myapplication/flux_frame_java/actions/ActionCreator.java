package com.pd.config.myapplication.flux_frame_java.actions;


import com.pd.config.myapplication.flux_frame_impl.actions.ControllerAction;
import com.pd.config.myapplication.flux_frame_impl.actions.DataAction;
import com.pd.config.myapplication.flux_frame_impl.actions.InfoAction;
import com.pd.config.myapplication.flux_frame_impl.actions.LineAction;
import com.pd.config.myapplication.flux_frame_impl.actions.MainActivityAction;
import com.pd.config.myapplication.flux_frame_impl.actions.VitalSettingsAction;
import com.pd.config.myapplication.flux_frame_java.dispatcher.Dispatcher;
import com.pd.config.myapplication.pojo.DataInfo;
import com.pd.config.myapplication.pojo.LineController;
import com.pd.config.myapplication.pojo.LineInfo;
import com.pd.config.myapplication.pojo.ParameterSaver;
import com.pd.config.myapplication.pojo.PrivateSettings;

import java.util.ArrayList;

public class ActionCreator {
    private static ActionCreator instance;
    final Dispatcher dispatcher;

    ActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionCreator getActionCreator(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ActionCreator(dispatcher);
        }
        return instance;
    }

    public void setAInfo(DataInfo info) {
        dispatcher.dispatchAction(new InfoAction(InfoAction.SET_DATA_INFO, info));
    }

    public void setALine(LineInfo lineInfo) {
        dispatcher.dispatchAction(new LineAction(LineAction.ADD_LINES, lineInfo));
    }

    public void setCurrentSelectedPosition(int i) {
        dispatcher.dispatchAction(new MainActivityAction(MainActivityAction.SET_POSITION, i));
    }


    public void changeControllerState(int currentPickPosition, LineController controller) {
        dispatcher.dispatchAction(new ControllerAction(ControllerAction.CHANGE_CONTROLLER_STATE, controller));
    }

    public void changeLineShowUpState(int postion, boolean isChecked) {
        dispatcher.dispatchAction(new LineAction(LineAction.CHANGE_LINE_STATE, postion, isChecked));
        dispatcher.dispatchAction(new ControllerAction(ControllerAction.CHANGE_STATE, postion, isChecked));

    }

    public void deleteAInfo(int postion) {
        dispatcher.dispatchAction(new InfoAction(InfoAction.DELETE_DATA_INFO, postion));
    }

    public void deleteALine(int postion) {
        dispatcher.dispatchAction(new LineAction(LineAction.DELETE_LINES, postion));
    }

    public void setDataColumnOne(ArrayList<Double> columnList, String action) {
        dispatcher.dispatchAction(new DataAction(action, columnList));
    }

    public void deleteAController(int position) {
        dispatcher.dispatchAction(new ControllerAction(ControllerAction.DELETE_CONTROLLER, position));
    }

    public void addAPointToDynamicLine(float x, float y) {
        dispatcher.dispatchAction(new LineAction(LineAction.ADD_POINT_TO_TEST_LINE, x, y));
    }

    public void changeaState(boolean state) {
        dispatcher.dispatchAction(new MainActivityAction(MainActivityAction.SET_STATE, state));
    }

    public void setATestParams(ParameterSaver saver) {
        dispatcher.dispatchAction(new MainActivityAction(MainActivityAction.SET_TEST_PARAMS, saver));
    }

    public void changeStateOfScopeBtn() {
        dispatcher.dispatchAction(new MainActivityAction(MainActivityAction.CHANGE_STATE_BTN, null));
    }

    public void setPrivateSetting(PrivateSettings settings) {
        dispatcher.dispatchAction(new VitalSettingsAction(VitalSettingsAction.SET_VITAL_SETTINGS, settings));
    }

    public void sendMessage() {
        dispatcher.dispatchAction(new VitalSettingsAction(VitalSettingsAction.NOTIFY, null));
    }

    public void setOperation(String setting) {
        dispatcher.dispatchAction(new VitalSettingsAction(VitalSettingsAction.SET_OPERATION, setting));
    }

    public void networkError() {
        dispatcher.dispatchAction(new VitalSettingsAction(VitalSettingsAction.NET_WORK_ERROR, null));
    }
}
