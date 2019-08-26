package com.pd.config.myapplication.flux_frame_impl.stores;

import com.pd.config.myapplication.flux_frame_impl.actions.MainActivityAction;
import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.pd.config.myapplication.pojo.ParameterSaver;
import com.pd.config.myapplication.pojo.State;

public class MainActivityStore extends Store {
    private boolean isTesting = false;
    private State state;
    private ParameterSaver curretnTestParams;

    public ParameterSaver getCurretnTestParams() {
        return curretnTestParams;
    }

    private MainActivityStore() {
        state = new State();
    }

    public State getState() {
        return state;
    }

    private static MainActivityStore mainActivityStore;

    public static MainActivityStore getAMainActivityStore() {
        if (mainActivityStore == null) {
            mainActivityStore = new MainActivityStore();
        }
        return mainActivityStore;
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent("state");
    }

    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case MainActivityAction
                    .SET_POSITION:
                this.state.setCurrentPickPosition((Integer) action.getData());
                break;
            case MainActivityAction.SET_STATE:
                this.state.setEnabled((Boolean) action.getData());
                break;
            case MainActivityAction.SET_TEST_PARAMS:
                this.curretnTestParams = (ParameterSaver) action.getData();
                break;
            case MainActivityAction.CHANGE_STATE_BTN:
                if(state.getRangeOfShowUp().equals("全频段")){
                    state.setRangeOfShowUp("低频段");
                }else if(state.getRangeOfShowUp().equals("低频段")){
                    state.setRangeOfShowUp("中频段");
                }else if(state.getRangeOfShowUp().equals("中频段")){
                    state.setRangeOfShowUp("高频段");
                }else {
                    state.setRangeOfShowUp("全频段");
                }
                break;
        }
        emitStoreChange();
    }
}
