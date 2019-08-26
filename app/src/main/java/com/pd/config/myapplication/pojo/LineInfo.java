package com.pd.config.myapplication.pojo;

import java.io.Serializable;
import java.util.List;

public class LineInfo implements Serializable {
    private int sort;
    private boolean visible;
    private int colorOfTheLine;
    private float width;
    private float circleRadius;
    private float valueTextSize;
    private boolean drawFilled;
    private float formLineWidhth;

    public LineInfo(int sort, boolean visible, int colorOfTheLine, List<Float> listOfX, List<Float> listOfY) {
        this.sort = sort;
        this.visible = visible;
        this.colorOfTheLine = colorOfTheLine;
        this.listOfX = listOfX;
        this.listOfY = listOfY;

    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(float circleRadius) {
        this.circleRadius = circleRadius;
    }

    public float getValueTextSize() {
        return valueTextSize;
    }

    public void setValueTextSize(float valueTextSize) {
        this.valueTextSize = valueTextSize;
    }

    public boolean isDrawFilled() {
        return drawFilled;
    }

    public void setDrawFilled(boolean drawFilled) {
        this.drawFilled = drawFilled;
    }

    public float getFormLineWidhth() {
        return formLineWidhth;
    }

    public void setFormLineWidhth(float formLineWidhth) {
        this.formLineWidhth = formLineWidhth;
    }

    private List<Float> listOfX;
    private List<Float> listOfY;

    public boolean isVisible() {
        return visible;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getColorOfTheLine() {
        return colorOfTheLine;
    }

    public void setColorOfTheLine(int colorOfTheLine) {
        this.colorOfTheLine = colorOfTheLine;
    }

    public List<Float> getListOfX() {
        return listOfX;
    }

    public void setListOfX(List<Float> listOfX) {
        this.listOfX = listOfX;
    }

    public List<Float> getListOfY() {
        return listOfY;
    }

    public void setListOfY(List<Float> listOfY) {
        this.listOfY = listOfY;
    }
}
