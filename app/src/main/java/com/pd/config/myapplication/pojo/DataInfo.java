package com.pd.config.myapplication.pojo;

import java.io.Serializable;
import java.util.List;

public class DataInfo implements Serializable {
    public DataInfo(String sortNum) {
        this.sortNum = sortNum;
    }

    public DataInfo() {
    }

    private String sortNum;



    private String  dataFile; //数据文件
    private String nameOfTransformer; //变压器 || 设备
    private String nameOfCompany;//制造厂家 ||单位
    private String dataInput;//输入端
    private String dataOutput;//输出端
    private String testFrequency; //测试频率
    private String postion;//分接位置;
    private List<Float> valuesOfPointX; //x轴坐标
    private List<Float> ValuesOfPointY; //y轴坐标
    private String typeOfRotate; //绕组选项 1，高压 2，中压 3，低压
    private String tap; //分接档位
    private String tester; //测试人员
    private String temporeture; //测试温度
    private String typeOfTest; //测试性质
    private String typeOfTheScan; //线性扫描或者对数扫描
    private String testTime; //测试时间

    public String getTestSpecificTime() {
        return testSpecificTime;
    }

    public void setTestSpecificTime(String testSpecificTime) {
        this.testSpecificTime = testSpecificTime;
    }

    private String testSpecificTime; //精确时间
    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }
    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }


    public String getNameOfTransformer() {
        return nameOfTransformer;
    }

    public void setNameOfTransformer(String nameOfTransformer) {
        this.nameOfTransformer = nameOfTransformer;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public String getDataInput() {
        return dataInput;
    }

    public void setDataInput(String dataInput) {
        this.dataInput = dataInput;
    }

    public String getDataOutput() {
        return dataOutput;
    }

    public void setDataOutput(String dataOutput) {
        this.dataOutput = dataOutput;
    }

    public String getTestFrequency() {
        return testFrequency;
    }

    public void setTestFrequency(String testFrequency) {
        this.testFrequency = testFrequency;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public List<Float> getValuesOfPointX() {
        return valuesOfPointX;
    }

    public void setValuesOfPointX(List<Float> valuesOfPointX) {
        this.valuesOfPointX = valuesOfPointX;
    }

    public List<Float> getValuesOfPointY() {
        return ValuesOfPointY;
    }

    public void setValuesOfPointY(List<Float> valuesOfPointY) {
        ValuesOfPointY = valuesOfPointY;
    }

    public String getTypeOfRotate() {
        return typeOfRotate;
    }

    public void setTypeOfRotate(String typeOfRotate) {
        this.typeOfRotate = typeOfRotate;
    }

    public String getTap() {
        return tap;
    }

    public void setTap(String tap) {
        this.tap = tap;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getTemporeture() {
        return temporeture;
    }

    public void setTemporeture(String temporeture) {
        this.temporeture = temporeture;
    }

    public String getTypeOfTest() {
        return typeOfTest;
    }

    public void setTypeOfTest(String typeOfTest) {
        this.typeOfTest = typeOfTest;
    }

    public String getTypeOfTheScan() {
        return typeOfTheScan;
    }

    public void setTypeOfTheScan(String typeOfTheScan) {
        this.typeOfTheScan = typeOfTheScan;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }
}
