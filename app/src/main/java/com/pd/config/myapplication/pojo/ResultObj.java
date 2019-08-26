package com.pd.config.myapplication.pojo;

import java.io.Serializable;

public class ResultObj implements Serializable {
    private boolean exist= false;
    private float result ;

    public ResultObj(boolean exist, float result) {
        this.exist = exist;
        this.result = result;
    }

    public boolean isExist() {

        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }
}
