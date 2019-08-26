package com.pd.config.myapplication.pojo;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

public class ChartInfo {
    private boolean isFirstInit;
    private XAxis xAxis;
    private YAxis yAxisRight;
    private YAxis yAxisLeft;

    public boolean isFirstInit() {
        return isFirstInit;
    }

    public void setFirstInit(boolean firstInit) {
        isFirstInit = firstInit;
    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public YAxis getyAxisRight() {
        return yAxisRight;
    }

    public void setyAxisRight(YAxis yAxisRight) {
        this.yAxisRight = yAxisRight;
    }

    public YAxis getyAxisLeft() {
        return yAxisLeft;
    }

    public void setyAxisLeft(YAxis yAxisLeft) {
        this.yAxisLeft = yAxisLeft;
    }
}
