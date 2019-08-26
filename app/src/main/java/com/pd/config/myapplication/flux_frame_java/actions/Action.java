package com.pd.config.myapplication.flux_frame_java.actions;

public class Action<T> {
    private final String type;
    private final T data;
    private final T data1;
    public Action(String type, T data) {
        this.type = type;
        this.data = data;
        this.data1 = null;
    }
    public Action(String type, T data,T data1) {
        this.type = type;
        this.data = data;
        this.data1 = data1;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }
    public T getData1(){ return data1;}
}
