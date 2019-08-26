package com.pd.config.myapplication.flux_frame_java.dispatcher;

import android.widget.LinearLayout;

import com.pd.config.myapplication.activities.MainActivity;
import com.pd.config.myapplication.flux_frame_impl.actions.ControllerAction;
import com.pd.config.myapplication.flux_frame_impl.actions.DataAction;
import com.pd.config.myapplication.flux_frame_impl.actions.InfoAction;
import com.pd.config.myapplication.flux_frame_impl.actions.LineAction;
import com.pd.config.myapplication.flux_frame_impl.actions.MainActivityAction;
import com.pd.config.myapplication.flux_frame_impl.actions.VitalSettingsAction;
import com.pd.config.myapplication.flux_frame_impl.stores.ControllerStore;
import com.pd.config.myapplication.flux_frame_impl.stores.DataStore;
import com.pd.config.myapplication.flux_frame_impl.stores.InfoStore;
import com.pd.config.myapplication.flux_frame_impl.stores.LineStore;
import com.pd.config.myapplication.flux_frame_impl.stores.MainActivityStore;
import com.pd.config.myapplication.flux_frame_impl.stores.VitalSettingsStore;
import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.flux_frame_java.stores.Store;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    private static Dispatcher instance;
    private final List<Store> stores = new ArrayList<>();

    public static Dispatcher getADispather() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

    Dispatcher() {

    }
    //register a store to list , so dispatcher will post a action to store.
    public void register(final Store store) {
        if (!stores.contains(store)) {
            stores.add(store);
        }
    }

    public void unregister(final Store store) {
        stores.remove(store);
    }
    //call this function to post the action to each store for changing the state of stores and then notify activity update it's view.
    public void dispatchAction(Action action) {
        post(action);
    }

    private void post(Action action) {
        //invoke each  action's callback function
        for (Store store : stores) {
            if(store!=null){
                if (action.getType().equals(LineAction.ADD_POINT_TO_TEST_LINE)||action.getType().equals(LineAction.ADD_LINES)||action.getType().equals(LineAction.CHANGE_LINE_STATE)||action.getType().equals(LineAction.DELETE_LINES)){
                     if(store instanceof LineStore){
                         store.onAction(action);
                     }
                }else if(DataAction.listOfDataAction.contains(action.getType())) {
                    if(store instanceof DataStore){
                        store.onAction(action);
                    }
                } else if(ControllerAction.listOfControllerAction.contains(action.getType())){
                   if (store instanceof ControllerStore){
                       store.onAction(action);
                   }
                } else if(InfoAction.listOfInfoAction.contains(action.getType())){
                    if(store instanceof InfoStore){
                        store.onAction(action);
                    }
                }else if(MainActivityAction.CHANGE_STATE_BTN.equals(action.getType())||MainActivityAction.SET_POSITION.equals(action.getType())||MainActivityAction.SET_STATE.equals(action.getType())||MainActivityAction.SET_TEST_PARAMS.equals(action.getType())){
                    if(store instanceof MainActivityStore){
                        store.onAction(action);
                    }
                } else if(VitalSettingsAction.SET_VITAL_SETTINGS.equals(action.getType())||VitalSettingsAction.NOTIFY.equals(action.getType())||VitalSettingsAction.SET_OPERATION.equals(action.getType())||VitalSettingsAction.NET_WORK_ERROR.equals(action.getType())){
                   if(store instanceof VitalSettingsStore){
                       store.onAction(action);
                   }
                }
            }

        }

    }
}
