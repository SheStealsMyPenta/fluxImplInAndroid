package com.pd.config.myapplication.pojo;

import java.io.Serializable;

public class PrivateSettings implements Serializable {
    private int inputAmplitude;
    private int inputImpedance;
    private int outputImpedance;
    private float inputChnKB;
    private  float outputChnKB;

    public int getInputImpedance() {
        return inputImpedance;
    }

    public void setInputImpedance(int inputImpedance) {
        this.inputImpedance = inputImpedance;
    }

    public int getOutputImpedance() {
        return outputImpedance;
    }

    public void setOutputImpedance(int outputImpedance) {
        this.outputImpedance = outputImpedance;
    }

    public float getInputChnKB() {
        return inputChnKB;
    }

    public void setInputChnKB(float inputChnKB) {
        this.inputChnKB = inputChnKB;
    }

    public float getOutputChnKB() {
        return outputChnKB;
    }

    public void setOutputChnKB(float outputChnKB) {
        this.outputChnKB = outputChnKB;
    }

    public int getInputAmplitude() {
        return inputAmplitude;
    }

    public void setInputAmplitude(int inputAmplitude) {
        this.inputAmplitude = inputAmplitude;
    }
}
