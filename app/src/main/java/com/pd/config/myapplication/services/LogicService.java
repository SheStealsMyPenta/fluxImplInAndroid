package com.pd.config.myapplication.services;

import android.os.Message;

import com.pd.config.myapplication.async.Net_Listener;
import com.pd.config.myapplication.flux_frame_java.actions.ActionCreator;

public class LogicService implements Net_Listener {
    private ActionCreator actionCreator;

    public void setActionCreator(ActionCreator actionCreator) {
        this.actionCreator = actionCreator;
    }

    @Override
    public void Net_Message_On(Message msg) {
        switch (msg.what){

        }
    }
}
