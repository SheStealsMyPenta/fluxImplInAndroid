package com.pd.config.myapplication.pojo;

public class LineController {
    private int postion;
    private boolean isVisible;
    private boolean isController;
    private String nameOfData;
    private  int color ;
    public String getNameOfData() {
        return nameOfData;
    }

    public void setNameOfData(String nameOfData) {
        this.nameOfData = nameOfData;
    }

    public int getColor() {
        return color;
    }

    public LineController(int i, boolean b, boolean b1, String name, int color) {
        this.postion = i;
        this.isVisible = b;
        this.isController = b1;
        this.nameOfData = name;
        this.color = color;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isController() {
        return isController;
    }

    public void setController(boolean controller) {
        isController = controller;
    }
}
