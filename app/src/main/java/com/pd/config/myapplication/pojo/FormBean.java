package com.pd.config.myapplication.pojo;

public class FormBean {
    private String substation;
    private String transformer;
    private String typeOfRotate;
    private String position;
    private String input;
    private String output;
    private String range;
    private int point;
    private String mode;

    public FormBean() {
    }

    public FormBean(String substation, String transformer, String typeOfRotate, String position, String input, String output, String range, int point, String mode) {
        this.substation = substation;
        this.transformer = transformer;
        this.typeOfRotate = typeOfRotate;
        this.position = position;
        this.input = input;
        this.output = output;
        this.range = range;
        this.point = point;
        this.mode = mode;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public String getTransformer() {
        return transformer;
    }

    public void setTransformer(String transformer) {
        this.transformer = transformer;
    }

    public String getTypeOfRotate() {
        return typeOfRotate;
    }

    public void setTypeOfRotate(String typeOfRotate) {
        this.typeOfRotate = typeOfRotate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
