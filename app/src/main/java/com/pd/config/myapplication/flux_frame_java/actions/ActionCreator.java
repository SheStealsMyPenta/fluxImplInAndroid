package com.pd.config.myapplication.flux_frame_java.actions;

import com.pd.config.myapplication.flux_frame_impl.actions.MessageAction;
import com.pd.config.myapplication.flux_frame_java.dispatcher.Dispatcher;

public class ActionCreator {
    private static ActionCreator instance;
    final Dispatcher dispatcher;
    ActionCreator(Dispatcher dispatcher){
        this.dispatcher = dispatcher;
    }
    public static ActionCreator getActionCreator(Dispatcher dispatcher){
        if(instance ==null){
            instance = new ActionCreator(dispatcher);
        }
        return instance;
    }
    public void setText(String message){
        dispatcher.dispatchAction(new MessageAction(MessageAction.ACTION_NEW_MESSAGE,message));
    }
}
