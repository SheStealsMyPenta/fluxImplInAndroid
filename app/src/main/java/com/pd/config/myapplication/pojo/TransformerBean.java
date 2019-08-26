package com.pd.config.myapplication.pojo;

import java.io.Serializable;

/**
 * Created by stormingshadow on 2017/11/22.
 */

public class TransformerBean implements Serializable {
 private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransformerBean() {
    }

    public TransformerBean(String nameOfTheSubstation, String nameOfTheTransformer, String typeOfTheTransformer, String numOfCreation, String phase, String numberOfRotate, String nameOfCompany, String dateOfCreation) {
        this.nameOfTheSubstation = nameOfTheSubstation;
        this.nameOfTheTransformer = nameOfTheTransformer;
        this.typeOfTheTransformer = typeOfTheTransformer;
        this.numOfCreation = numOfCreation;
        this.phase = phase;
        this.numberOfRotate = numberOfRotate;
        this.nameOfCompany = nameOfCompany;
        this.dateOfCreation = dateOfCreation;

    }

    private String nameOfTheSubstation;
 private String nameOfTheTransformer;
 private String typeOfTheTransformer;
 private String numOfCreation;
 private String phase;
 private String numberOfRotate;
 private String nameOfCompany;
 private String dateOfCreation;
 private String Url;

    public TransformerBean(int id, String nameOfTheSubstation, String nameOfTheTransformer, String typeOfTheTransformer, String numOfCreation, String phase, String numberOfRotate, String nameOfCompany, String dateOfCreation, String url) {
        this.id = id;
        this.nameOfTheSubstation = nameOfTheSubstation;
        this.nameOfTheTransformer = nameOfTheTransformer;
        this.typeOfTheTransformer = typeOfTheTransformer;
        this.numOfCreation = numOfCreation;
        this.phase = phase;
        this.numberOfRotate = numberOfRotate;
        this.nameOfCompany = nameOfCompany;
        this.dateOfCreation = dateOfCreation;
        Url = url;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getNameOfTheSubstation() {
        return nameOfTheSubstation;
    }

    public void setNameOfTheSubstation(String nameOfTheSubstation) {
        this.nameOfTheSubstation = nameOfTheSubstation;
    }

    public String getNameOfTheTransformer() {
        return nameOfTheTransformer;
    }

    public void setNameOfTheTransformer(String nameOfTheTransformer) {
        this.nameOfTheTransformer = nameOfTheTransformer;
    }

    public String getTypeOfTheTransformer() {
        return typeOfTheTransformer;
    }

    public void setTypeOfTheTransformer(String typeOfTheTransformer) {
        this.typeOfTheTransformer = typeOfTheTransformer;
    }

    public String getNumOfCreation() {
        return numOfCreation;
    }

    public void setNumOfCreation(String numOfCreation) {
        this.numOfCreation = numOfCreation;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getNumberOfRotate() {
        return numberOfRotate;
    }

    public void setNumberOfRotate(String numberOfRotate) {
        this.numberOfRotate = numberOfRotate;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
