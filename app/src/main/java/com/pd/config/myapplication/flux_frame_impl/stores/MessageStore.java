package com.pd.config.myapplication.flux_frame_impl.stores;

import com.pd.config.myapplication.flux_frame_impl.actions.MessageAction;
import com.pd.config.myapplication.flux_frame_impl.models.MyMessage;
import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.squareup.otto.Subscribe;

public class MessageStore extends Store {
    private static MessageStore singletonMessage;

    private MyMessage message= new MyMessage();
    public MyMessage getMessage() {
        return message;
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
       switch (action.getType()){
           case MessageAction
                   .ACTION_NEW_MESSAGE:
               message.setMessage((String) action.getData());
             break;
            default:
       }
       emitStoreChange();
    }
    @Override
    public StoreChangeEvent changeEvent() { return  new StoreChangeEvent("text");
    }

}
