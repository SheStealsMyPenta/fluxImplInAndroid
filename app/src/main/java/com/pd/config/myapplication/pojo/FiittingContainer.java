package com.pd.config.myapplication.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class FiittingContainer implements Serializable {

    private ArrayList<Float> listOfXLine1;
    private ArrayList<Float> listOfXLine2;
    private ArrayList<Float> listOfYLine1;
    private ArrayList<Float> listOfYLine2;

    public FiittingContainer() {

    }

    public FiittingContainer(ArrayList<Float> listOfXLine1, ArrayList<Float> listOfXLine2, ArrayList<Float> listOfYLine1, ArrayList<Float> listOfYLine2) {
        this.listOfXLine1 = listOfXLine1;
        this.listOfXLine2 = listOfXLine2;
        this.listOfYLine1 = listOfYLine1;
        this.listOfYLine2 = listOfYLine2;
    }

    public ArrayList<Float> getListOfXLine1() {
        return listOfXLine1;
    }

    public void setListOfXLine1(ArrayList<Float> listOfXLine1) {
        this.listOfXLine1 = listOfXLine1;
    }

    public ArrayList<Float> getListOfXLine2() {
        return listOfXLine2;
    }

    public void setListOfXLine2(ArrayList<Float> listOfXLine2) {
        this.listOfXLine2 = listOfXLine2;
    }

    public ArrayList<Float> getListOfYLine1() {
        return listOfYLine1;
    }

    public void setListOfYLine1(ArrayList<Float> listOfYLine1) {
        this.listOfYLine1 = listOfYLine1;
    }

    public ArrayList<Float> getListOfYLine2() {
        return listOfYLine2;
    }

    public void setListOfYLine2(ArrayList<Float> listOfYLine2) {
        this.listOfYLine2 = listOfYLine2;
    }

}
