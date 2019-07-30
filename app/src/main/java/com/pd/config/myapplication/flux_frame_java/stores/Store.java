package com.pd.config.myapplication.flux_frame_java.stores;

import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.squareup.otto.Bus;

/**
 * a store module for Flux
 *  Create by zujian on 30/7/2019
 */
public abstract class Store {
    //bus is used to carry messages that Activity can receive a event obj
    private static final Bus bus = new Bus();

    protected Store() {
    }
    //register view to bus, if event happens activity will response a callback function.
    public void register(final Object view) {
        bus.register(view);
    }

    public void unregister(final Object view) {
        bus.register(view);
    }
   //use bus to post a event to notify activity invoke it's callback function.
   protected   void emitStoreChange() {
        bus.post(changeEvent());
    }

    public abstract StoreChangeEvent changeEvent();

    // A Store have to override the onAction so it can handle different action to update it's store.
    public abstract void onAction(Action action);

    // a class that present a change event .
    public class StoreChangeEvent {
        public  String type;
        public StoreChangeEvent(String type){
            this.type = type;
        }

    }
}
