package com.pd.config.myapplication.statics;

import com.pd.config.myapplication.pojo.FormBean;

import org.jetbrains.annotations.NotNull;

public class CacheData {
    public static String lastOpenFile;
    public static String lastOpenData;
    public static String lastOpenSub;
    public static String lastOpenTrans;
    public static boolean isAddTrans = false;
    public static int pickedLayout = 1;
    public static FormBean cacheFormBean = new FormBean("", "", "A相", "1", "A相（a相）", "B相（b相）", "1kHz - 1MHz", 1000, "变压器设备");
    public static String typeOfTheRotate="高压绕组";
    public static int scanMode=-1;
}
