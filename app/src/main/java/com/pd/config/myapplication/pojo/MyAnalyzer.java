package com.pd.config.myapplication.pojo;

import java.io.Serializable;

public class MyAnalyzer implements Serializable {
    private String title; //第一个值
    private String R21;
    private String R31;
    private String R32;
    private String R41;
    private String R52;
    private String R63;
    private String R54;
    private String R64;
    private String R65;
    private String meaningOfColor;
    private int color;

    public MyAnalyzer(String title, String r21, String r31, String r32, String r41, String r52, String r63, String r54, String r64, String r65, String meaningOfColor, int color) {
        this.title = title;
        R21 = r21;
        R31 = r31;
        R32 = r32;
        R41 = r41;
        R52 = r52;
        R63 = r63;
        R54 = r54;
        R64 = r64;
        R65 = r65;
        this.meaningOfColor = meaningOfColor;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getR21() {
        return R21;
    }

    public void setR21(String r21) {
        R21 = r21;
    }

    public String getR31() {
        return R31;
    }

    public void setR31(String r31) {
        R31 = r31;
    }

    public String getR32() {
        return R32;
    }

    public void setR32(String r32) {
        R32 = r32;
    }

    public String getR41() {
        return R41;
    }

    public void setR41(String r41) {
        R41 = r41;
    }

    public String getR52() {
        return R52;
    }

    public void setR52(String r52) {
        R52 = r52;
    }

    public String getR63() {
        return R63;
    }

    public void setR63(String r63) {
        R63 = r63;
    }

    public String getR54() {
        return R54;
    }

    public void setR54(String r54) {
        R54 = r54;
    }

    public String getR64() {
        return R64;
    }

    public void setR64(String r64) {
        R64 = r64;
    }

    public String getR65() {
        return R65;
    }

    public void setR65(String r65) {
        R65 = r65;
    }

    public String getMeaningOfColor() {
        return meaningOfColor;
    }

    public void setMeaningOfColor(String meaningOfColor) {
        this.meaningOfColor = meaningOfColor;
    }
}
