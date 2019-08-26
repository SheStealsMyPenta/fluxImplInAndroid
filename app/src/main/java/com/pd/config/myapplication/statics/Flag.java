package com.pd.config.myapplication.statics;

public class Flag {
   public static  int LagTime = 0;
   public static boolean inPowerOn=false;
   public static boolean outPowerOn=false ;

    public static final boolean Wait = true;
    public static boolean OnScan =false  ;
    public static int TimeOutTimesCellOne =0 ; //单元1 超市
    public static int TimeOutTimesCellTwo = 0 ;
    public static boolean TimeOutCellOne = false ;//单元1超时
    public static boolean TimeOutCellTwo = false;
    public static boolean Ping = true ;
    public static int TypeOfOrder = -1 ;
    public static boolean sendOrder=false;
    public static int getDeviceInfo =  1;
    public static int setScanRange = 2;
    public static boolean openSocket=false;
    public static boolean scanHotSpot=true;
    public static boolean getList=true;
    public static boolean getPathNum=true;
    public static boolean emptyTheSocket=false;
    public static boolean power=false;
    public static boolean outputPrepared= false ;
    public static boolean inputPrepared= false ;
    public static boolean powerOff= false;
    public static boolean getFromDevice= true;
    public static boolean hasSocket= false;
    public static boolean checkPower= false;
    public static boolean scanTimeOut= false;
    public static int  scanTimeOutTimes = 0;
    public static boolean powerOffReceive= false;
    public static boolean exit=false;
    public static boolean exitScan = false;
    public static int TwoOrFor=1;
    public static boolean isFirstPoint=true;
    public static boolean overTime=false ;
}
