package com.pd.config.myapplication.flux_frame_impl.actions;

import com.pd.config.myapplication.flux_frame_java.actions.Action;

public class MessageAction  extends Action<String> {
    public static final String ACTION_NEW_MESSAGE = "new_message";

    public MessageAction(String type, String data) {
        super(type, data);
    }
}
