package com.pd.config.myapplication.pojo;

import java.io.Serializable;

public class ParameterSaver implements Serializable {
    private String nameOfSubstation;
    private String nameOfTransformer;
    private String typeOfRotate;
    private String position;
    private String input;
    private String output;
    private String range;
    private String points;
    private String typeOfScan;
    private int numberOfPoint;
    private int begin;
    private int end;

    public int getNumberOfPoint() {
        return numberOfPoint;
    }

    public void setNumberOfPoint(int numberOfPoint) {
        this.numberOfPoint = numberOfPoint;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ParameterSaver(String nameOfSubstation, String nameOfTransformer, String typeOfRotate, String position, String input, String output, String range, String points, String typeOfScan) {
        this.nameOfSubstation = nameOfSubstation;
        this.nameOfTransformer = nameOfTransformer;
        this.typeOfRotate = typeOfRotate;
        this.position = position;
        this.input = input;
        this.output = output;
        this.range = range;
        this.points = points;
        this.typeOfScan = typeOfScan;
    }

    public String getNameOfSubstation() {

        return nameOfSubstation;
    }

    public void setNameOfSubstation(String nameOfSubstation) {
        this.nameOfSubstation = nameOfSubstation;
    }

    public String getNameOfTransformer() {
        return nameOfTransformer;
    }

    public void setNameOfTransformer(String nameOfTransformer) {
        this.nameOfTransformer = nameOfTransformer;
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

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getTypeOfScan() {
        return typeOfScan;
    }

    public void setTypeOfScan(String typeOfScan) {
        this.typeOfScan = typeOfScan;
    }
}
