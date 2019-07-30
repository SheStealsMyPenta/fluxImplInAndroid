package com.pd.config.myapplication.async;

import android.os.Handler;
import android.os.Message;

public class Net_Handler extends Handler {
    private Net_Listener listener;
    public synchronized void setListener(Net_Listener listener){
        this.listener = listener;
    }
    public Net_Handler(Net_Listener listener){
        super();
        this.listener = listener;
    }

    @Override
    public void handleMessage(Message msg) {
        if(listener!=null){
            super.handleMessage(msg);
        }
    }
}
