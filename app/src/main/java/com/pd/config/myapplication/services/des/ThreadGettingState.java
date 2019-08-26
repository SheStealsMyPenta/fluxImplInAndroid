package com.pd.config.myapplication.services.des;

import android.os.Bundle;
import android.os.Message;

import com.pd.config.myapplication.async.Net_Handler;
import com.pd.config.myapplication.statics.StateOfTheIntenet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * Created by Administrator on 2018/1/24 0024.
 */

public class ThreadGettingState implements Runnable {
    Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Net_Handler handler;
    public ThreadGettingState(Net_Handler handler){
        this.handler = handler;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                connect();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void connect() {
        try {
            socket = new Socket("192.168.10.2", 8000);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            if(inputStream!=null&&outputStream!=null){
                sendTheMessage(true);
                StateOfTheIntenet.isConnected = true;
            }else {
              sendTheMessage(false);
                StateOfTheIntenet.isConnected = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendTheMessage(boolean isConnect) {
        Message msg = Message.obtain();
        msg.what = 0x108;
        Bundle bundle = new Bundle();
        bundle.putBoolean("state", isConnect);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
