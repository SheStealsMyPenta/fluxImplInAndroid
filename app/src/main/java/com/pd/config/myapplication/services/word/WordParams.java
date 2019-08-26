package com.pd.config.myapplication.services.word;

import android.graphics.Bitmap;

import java.io.Serializable;

public class WordParams implements Serializable {
   //变压器基本信息
    private String trans_name="";
    private String trans_type="";
    private String trans_made="";
    private String trans_date="";
    private String trans_sn="";
    private String chart_title="";
    private String table_title="";

    public String getTable_title() {
        return table_title;
    }

    public void setTable_title(String table_title) {
        this.table_title = table_title;
    }

    //相关系数
    private String R21_LF="";
    private String R21_MF="";
    private String R21_HF="";
    private String R21_FF="";
    private String R31_LF="";
    private String R31_MF="";
    private String R31_HF="";
    private String R31_FF="";
    private String R32_LF="";
    private String R32_MF="";
    private String R32_HF="";
    private String R32_FF="";
    //测试信息;
    private  String tester="";
    private String result="";
    private String report_sh="";
    private String report_pz="";
    private String report_date="";
    private String detail1="";
    private String detail2="";
    private String detail3="";
    private String detail4="";
    private String detail5="";
    private String detail6="";

    public String getDetail1() {
        return detail1;
    }

    public void setDetail1(String detail1) {
        this.detail1 = detail1;
    }

    public String getDetail2() {
        return detail2;
    }

    public void setDetail2(String detail2) {
        this.detail2 = detail2;
    }

    public String getDetail3() {
        return detail3;
    }

    public void setDetail3(String detail3) {
        this.detail3 = detail3;
    }

    public String getDetail4() {
        return detail4;
    }

    public void setDetail4(String detail4) {
        this.detail4 = detail4;
    }

    public String getDetail5() {
        return detail5;
    }

    public void setDetail5(String detail5) {
        this.detail5 = detail5;
    }

    public String getDetail6() {
        return detail6;
    }

    public void setDetail6(String detail6) {
        this.detail6 = detail6;
    }

    private Bitmap bitmap = null;
    private String base64Img=  null;

    public String getBase64Img() {
        return base64Img;
    }

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public WordParams() {
    }

    public WordParams(String trans_name, String trans_type, String trans_made, String trans_date, String trans_sn, String chart_title, String r21_LF, String r21_MF, String r21_HF, String r21_FF, String r31_LF, String r31_MF, String r31_HF, String r31_FF, String r32_LF, String r32_MF, String r32_HF, String r32_FF, String tester, String result, String report_sh, String report_pz, String report_date) {

        this.trans_name = trans_name;
        this.trans_type = trans_type;
        this.trans_made = trans_made;
        this.trans_date = trans_date;
        this.trans_sn = trans_sn;
        this.chart_title = chart_title;
        R21_LF = r21_LF;
        R21_MF = r21_MF;
        R21_HF = r21_HF;
        R21_FF = r21_FF;
        R31_LF = r31_LF;
        R31_MF = r31_MF;
        R31_HF = r31_HF;
        R31_FF = r31_FF;
        R32_LF = r32_LF;
        R32_MF = r32_MF;
        R32_HF = r32_HF;
        R32_FF = r32_FF;
        this.tester = tester;
        this.result = result;
        this.report_sh = report_sh;
        this.report_pz = report_pz;
        this.report_date = report_date;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReport_sh() {
        return report_sh;
    }

    public void setReport_sh(String report_sh) {
        this.report_sh = report_sh;
    }

    public String getReport_pz() {
        return report_pz;
    }

    public void setReport_pz(String report_pz) {
        this.report_pz = report_pz;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public String getTrans_name() {
        return trans_name;
    }

    public void setTrans_name(String trans_name) {
        this.trans_name = trans_name;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getTrans_made() {
        return trans_made;
    }

    public void setTrans_made(String trans_made) {
        this.trans_made = trans_made;
    }

    public String getTrans_date() {
        return trans_date;
    }

    public void setTrans_date(String trans_date) {
        this.trans_date = trans_date;
    }

    public String getTrans_sn() {
        return trans_sn;
    }

    public void setTrans_sn(String trans_sn) {
        this.trans_sn = trans_sn;
    }

    public String getChart_title() {
        return chart_title;
    }

    public void setChart_title(String chart_title) {
        this.chart_title = chart_title;
    }

    public String getR21_LF() {
        return R21_LF;
    }

    public void setR21_LF(String r21_LF) {
        R21_LF = r21_LF;
    }

    public String getR21_MF() {
        return R21_MF;
    }

    public void setR21_MF(String r21_MF) {
        R21_MF = r21_MF;
    }

    public String getR21_HF() {
        return R21_HF;
    }

    public void setR21_HF(String r21_HF) {
        R21_HF = r21_HF;
    }

    public String getR21_FF() {
        return R21_FF;
    }

    public void setR21_FF(String r21_FF) {
        R21_FF = r21_FF;
    }

    public String getR31_LF() {
        return R31_LF;
    }

    public void setR31_LF(String r31_LF) {
        R31_LF = r31_LF;
    }

    public String getR31_MF() {
        return R31_MF;
    }

    public void setR31_MF(String r31_MF) {
        R31_MF = r31_MF;
    }

    public String getR31_HF() {
        return R31_HF;
    }

    public void setR31_HF(String r31_HF) {
        R31_HF = r31_HF;
    }

    public String getR31_FF() {
        return R31_FF;
    }

    public void setR31_FF(String r31_FF) {
        R31_FF = r31_FF;
    }

    public String getR32_LF() {
        return R32_LF;
    }

    public void setR32_LF(String r32_LF) {
        R32_LF = r32_LF;
    }

    public String getR32_MF() {
        return R32_MF;
    }

    public void setR32_MF(String r32_MF) {
        R32_MF = r32_MF;
    }

    public String getR32_HF() {
        return R32_HF;
    }

    public void setR32_HF(String r32_HF) {
        R32_HF = r32_HF;
    }

    public String getR32_FF() {
        return R32_FF;
    }

    public void setR32_FF(String r32_FF) {
        R32_FF = r32_FF;
    }
}
