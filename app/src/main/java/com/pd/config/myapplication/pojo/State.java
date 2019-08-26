package com.pd.config.myapplication.pojo;

public class State {
    private String typeOfRotate= "绕组类型\n高压绕组";
    private String typeOfShowUp = "显示模式\n线性显示";
    private String rangeOfShowUp = "全频段";
    private boolean enabled = true;
    private int currentPickPosition= -1;

    public int getCurrentPickPosition() {
        return currentPickPosition;
    }

    public void setCurrentPickPosition(int currentPickPosition) {
        this.currentPickPosition = currentPickPosition;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTypeOfRotate() {
        return typeOfRotate;
    }

    public void setTypeOfRotate(String typeOfRotate) {
        this.typeOfRotate = typeOfRotate;
    }

    public String getTypeOfShowUp() {
        return typeOfShowUp;
    }

    public void setTypeOfShowUp(String typeOfShowUp) {
        this.typeOfShowUp = typeOfShowUp;
    }

    public String getRangeOfShowUp() {
        return rangeOfShowUp;
    }

    public void setRangeOfShowUp(String rangeOfShowUp) {
        this.rangeOfShowUp = rangeOfShowUp;
    }
}
