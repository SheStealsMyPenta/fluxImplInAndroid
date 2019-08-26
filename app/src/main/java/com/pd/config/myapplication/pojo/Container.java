package com.pd.config.myapplication.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Container implements Serializable {
    private ArrayList<Double > list;

    public ArrayList<Double> getList() {
        return list;
    }

    public void setList(ArrayList<Double> list) {
        this.list = list;
    }
}
