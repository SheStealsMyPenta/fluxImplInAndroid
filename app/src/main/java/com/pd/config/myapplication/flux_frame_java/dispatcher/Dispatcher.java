package com.pd.config.myapplication.flux_frame_java.dispatcher;

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
            store.onAction(action);
        }

    }
}
