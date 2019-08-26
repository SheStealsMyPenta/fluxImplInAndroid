package com.pd.config.myapplication.statics;

/**
 * Created by stormingshadow on 2017/12/6.
 */

public class StateOfTheIntenet {
    //判断是否建立了网路链接
    public static Boolean isConnected = false;
    //判断是否发送了获取版本号数据
    public static Boolean isMessageSendAble=false;
    //判断是否接收到了数据。true为是 并且不再获取数据 false为获取数据
    public static Boolean isMessageReceived= false;
    //是否发送采集数据的消息
    public static Boolean startGathering=false;
    //采集信号输出是否打开。true为打开。false为关闭
    public static Boolean GatherSignalOn = true;
    //采集信号输出是否已经存在
    public static Boolean GatherSignalExsist = true;
    //是否采集到一个数据
    public static Boolean toGetTheData= true;
    //判断是否存在一个线程
    public static Boolean ifThreadExist = false;
    //是否关闭线程
    public static Boolean ToCloseTheThread =false;

}
