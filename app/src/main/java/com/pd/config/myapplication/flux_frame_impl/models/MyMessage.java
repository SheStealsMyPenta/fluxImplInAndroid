package com.pd.config.myapplication.flux_frame_impl.models;

public class MyMessage {
    private String mText;

    public MyMessage(){}

    public MyMessage(String text) {
        mText = text;
    }

    public String getMessage() {
        return mText;
    }

    public void setMessage(String text) {
        mText = text;
    }
}
