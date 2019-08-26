package com.pd.config.myapplication.services;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.os.Message;
import android.support.constraint.solver.Cache;
import android.view.View;

import com.pd.config.myapplication.Threads.FetchPrivateSetting;
import com.pd.config.myapplication.Threads.SetPrivateSetting;
import com.pd.config.myapplication.async.Net_Handler;
import com.pd.config.myapplication.async.Net_Listener;
import com.pd.config.myapplication.dao.DaoImpl;
import com.pd.config.myapplication.flux_frame_impl.actions.DataAction;
import com.pd.config.myapplication.flux_frame_impl.stores.ControllerStore;
import com.pd.config.myapplication.flux_frame_impl.stores.DataStore;
import com.pd.config.myapplication.flux_frame_impl.stores.InfoStore;
import com.pd.config.myapplication.flux_frame_impl.stores.LineStore;
import com.pd.config.myapplication.flux_frame_impl.stores.MainActivityStore;
import com.pd.config.myapplication.flux_frame_impl.stores.MessageStore;
import com.pd.config.myapplication.flux_frame_java.actions.ActionCreator;
import com.pd.config.myapplication.pojo.Container;
import com.pd.config.myapplication.pojo.DataInfo;
import com.pd.config.myapplication.pojo.FiittingContainer;
import com.pd.config.myapplication.pojo.LineController;
import com.pd.config.myapplication.pojo.LineInfo;
import com.pd.config.myapplication.pojo.MyAnalyzer;
import com.pd.config.myapplication.pojo.ParameterSaver;
import com.pd.config.myapplication.pojo.PrivateSettings;
import com.pd.config.myapplication.pojo.TransformerBean;
import com.pd.config.myapplication.services.word.WordParams;
import com.pd.config.myapplication.statics.Flag;
import com.pd.config.myapplication.statics.StateOfTheIntenet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogicService implements Net_Listener {
    private ActionCreator actionCreator;
    private Net_Handler handler;
    private static LogicService logicService;
    private DaoImpl daoimpl;

    public void setActionCreator(ActionCreator actionCreator) {
        this.actionCreator = actionCreator;

    }

    private LogicService() {
        this.handler = new Net_Handler(this);
    }

    public static LogicService getALogicService() {
        if (logicService == null) {
            logicService = new LogicService();

        }
        return logicService;
    }

    public void calculateRelativeParams() {
        long start = System.currentTimeMillis();
        final LineStore lineStore = LineStore.getALineStore();
        final List<LineInfo> listOfLines = lineStore.getListOfLines();
        //先把x,y放进map里
        final ArrayList<Float> listOfYLine1 = (ArrayList<Float>) listOfLines.get(0).getListOfY();
        final ArrayList<Float> listOfXLine1 = (ArrayList<Float>) listOfLines.get(0).getListOfX();
        final ArrayList<Float> listOfYLine2 = (ArrayList<Float>) listOfLines.get(1).getListOfY();
        final ArrayList<Float> listOfXLine2 = (ArrayList<Float>) listOfLines.get(1).getListOfX();
        final ArrayList<Float> listOfYLine3 = (ArrayList<Float>) listOfLines.get(2).getListOfY();
        final ArrayList<Float> listOfXLine3 = (ArrayList<Float>) listOfLines.get(2).getListOfX();
        final ArrayList<Float> listOfYLine4 = (ArrayList<Float>) listOfLines.get(3).getListOfY();
        final ArrayList<Float> listOfXLine4 = (ArrayList<Float>) listOfLines.get(3).getListOfX();
        final ArrayList<Float> listOfYLine5 = (ArrayList<Float>) listOfLines.get(4).getListOfY();
        final ArrayList<Float> listOfXLine5 = (ArrayList<Float>) listOfLines.get(4).getListOfX();
        final ArrayList<Float> listOfYLine6 = (ArrayList<Float>) listOfLines.get(5).getListOfY();
        final ArrayList<Float> listOfXLine6 = (ArrayList<Float>) listOfLines.get(5).getListOfX();
        if (listOfYLine1 != null && listOfYLine2 != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (listOfYLine1.size() != 0 && listOfYLine2.size() != 0) {
                        if (listOfYLine1 != listOfYLine2 && listOfXLine1.size() == listOfXLine2.size() && listOfXLine1.size() > 999) {
                            Long start = System.currentTimeMillis();
                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine1, listOfXLine2, listOfYLine1, listOfYLine2);
                            Long end1 = System.currentTimeMillis();
                            System.out.println("计算拟合点耗时" + (end1 - start));
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            start = System.currentTimeMillis();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            end1 = System.currentTimeMillis();

                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);
                            Long End = System.currentTimeMillis();
                            sendAResultMessage(columnList, DataAction.UPDATE_COLUMN_ONE);
                        }
                    } else {

                    }
                }
            }).start();
        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLUMN_ONE);
        }
        if (listOfYLine3 != null && listOfYLine1 != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (listOfYLine3.size() != 0 && listOfYLine1.size() != 0 && listOfXLine1.size() == listOfXLine3.size() && listOfXLine1.size() > 999) {
                        if (listOfYLine3 != listOfYLine1) {

                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine1, listOfXLine3, listOfYLine1, listOfYLine3);
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);
                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_TWO);

                        }
                    }
                }
            }).start();

        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_TWO);
        }
        if (listOfYLine3 != null && listOfYLine2 != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (listOfYLine3.size() != 0 && listOfYLine2.size() != 0 && listOfXLine3.size() == listOfXLine2.size() && listOfXLine2.size() > 999) {
                        if (listOfYLine3 != listOfYLine2) {
                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine2, listOfXLine3, listOfYLine2, listOfYLine3);
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);

                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_THREE);
                        }
                    }
                }
            }).start();

        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_THREE);
        }
        if (listOfYLine4 != null && listOfYLine1 != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (listOfYLine4.size() != 0 && listOfYLine1.size() != 0 && listOfXLine4.size() == listOfXLine1.size() && listOfXLine4.size() > 999) {
                        if (listOfYLine4 != listOfYLine1) {
                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine1, listOfXLine4, listOfYLine1, listOfYLine4);
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);
                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_FOUR);
                        }
                    }
                }
            }).start();
        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_FOUR);
        }
//        if (listOfYLine3 != null && listOfYLine2 != null) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    if (listOfYLine3.size() != 0 && listOfYLine2.size() != 0) {
//                        if (listOfYLine3 != listOfYLine2) {
//                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine2, listOfXLine3, listOfYLine2, listOfYLine3);
//                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
//                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
//                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
//                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
//                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
//                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
//                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
//                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
//                            ArrayList<Double> columnList = new ArrayList<>();
//                            columnList.add(low);
//                            columnList.add(mid);
//                            columnList.add(high);
//                            columnList.add(whole);
//                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_FIVE);
//                        }
//                    }
//                }
//            }).start();
//        } else {
//            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_FIVE);
//        }
        if (listOfYLine5 != null && listOfYLine2 != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (listOfYLine5.size() != 0 && listOfYLine2.size() != 0 && listOfXLine2.size() == listOfXLine5.size() && listOfXLine5.size() > 999) {
                        if (listOfYLine5 != listOfYLine2) {
                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine2, listOfXLine5, listOfYLine2, listOfYLine5);
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);
                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_FIVE);

                        }
                    }
                }
            }).start();
        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_FIVE);
        }
        if (listOfYLine6 != null && listOfYLine3 != null && listOfXLine6.size() == listOfXLine3.size() && listOfXLine6.size() > 999) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (listOfYLine6.size() != 0 && listOfYLine3.size() != 0) {
                        if (listOfYLine6 != listOfYLine3) {
                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine3, listOfXLine6, listOfYLine3, listOfYLine6);
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);
                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_SIX);
                        }
                    }
                }
            }).start();
        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_SIX);
        }
        if (listOfYLine5 != null && listOfYLine4 != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (listOfYLine5.size() != 0 && listOfYLine4.size() != 0 && listOfXLine5.size() == listOfXLine4.size() && listOfXLine5.size() > 999) {
                        if (listOfYLine5 != listOfYLine4) {
                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine4, listOfXLine5, listOfYLine4, listOfYLine5);
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);
                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_SEVEN);
                        }
                    }
                }
            }).start();
        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_SEVEN);
        }
        if (listOfYLine6 != null && listOfYLine4 != null && listOfXLine6.size() == listOfXLine4.size() && listOfXLine4.size() > 999) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (listOfYLine6.size() != 0 && listOfYLine4.size() != 0) {
                        if (listOfYLine6 != listOfYLine4) {
                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine4, listOfXLine6, listOfYLine4, listOfYLine6);
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);
                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_EIGHT);
                        }
                    }
                }
            }).start();
        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_EIGHT);
        }
        if (listOfYLine6 != null && listOfYLine5 != null && listOfXLine6.size() == listOfXLine5.size() && listOfXLine5.size() > 999) {
            if (listOfYLine6.size() != 0 && listOfYLine5.size() != 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (listOfYLine6 != listOfYLine5) {
                            FiittingContainer fittedObj = CalculateRelativeCoefficientUtil.getFittingList(listOfXLine5, listOfXLine6, listOfYLine5, listOfYLine6);
                            ArrayList<Float> tempYList1 = fittedObj.getListOfYLine1();
                            ArrayList<Float> tempYList2 = fittedObj.getListOfYLine2();
                            ArrayList<Float> tempXList1 = fittedObj.getListOfXLine1();
                            ArrayList<Float> tempXList2 = fittedObj.getListOfXLine2();
                            double low = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForLowFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double mid = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForMidFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double high = CalculateRelativeCoefficientUtil.CalculateRelativeCoefficientForHighFrequency(tempYList1, tempYList2, tempXList1, tempXList2);
                            double whole = CalculateRelativeCoefficientUtil.CalculateRelatetiveCoefficientForWholeFrequency(tempYList1, tempYList2);
                            ArrayList<Double> columnList = new ArrayList<>();
                            columnList.add(low);
                            columnList.add(mid);
                            columnList.add(high);
                            columnList.add(whole);
                            sendAResultMessage(columnList, DataAction.UPDATE_COLOUMN_NINE);
                        }
                    }
                }).start();
            }
        } else {
            actionCreator.setDataColumnOne(null, DataAction.UPDATE_COLOUMN_NINE);
        }
        long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - start));
    }

    private void sendAResultMessage(ArrayList<Double> columnList, String action) {
        Message message = Message.obtain();
        message.what = 0x01;
        Bundle bundle = new Bundle();
        Container container = new Container();
        container.setList(columnList);
        bundle.putSerializable("list", container);
        bundle.putString("action", action);
        message.setData(bundle);
        handler.sendMessage(message);
    }

    @Override
    public void Net_Message_On(Message msg) {

        switch (msg.what) {
            case 0x01:
                Container container = (Container) msg.getData().getSerializable("list");
                String action = msg.getData().getString("action");
                if (container != null) {
                    ArrayList<Double> doubles = container.getList();
                    actionCreator.setDataColumnOne(doubles, action);
                }
                break;
            case 0x02:
                float xValue = (float) msg.getData().get("pointX");
                float yValue = (float) msg.getData().get("pointY");
                updateTheList(xValue, yValue);
                break;
            case 0x03:
                System.out.println("断开连接");
                changeState(true);

                break;
            case 0x04:
                //完成采集
                MainActivityStore activityStore = MainActivityStore.getAMainActivityStore();
                LineStore lineStore = LineStore.getALineStore();
                ParameterSaver params = activityStore.getCurretnTestParams();
                // TransformerBean details = daoimpl.findDetails(params.getNameOfTransformer(), params.getNameOfSubstation());
                DataInfo dataInfo = new DataInfo();
                dataInfo.setDataOutput(dataInfo.getDataOutput());
                dataInfo.setDataInput(dataInfo.getDataInput());
                dataInfo.setNameOfCompany(params.getNameOfSubstation());
                dataInfo.setNameOfTransformer(params.getNameOfTransformer());
                dataInfo.setValuesOfPointX(lineStore.getDynamicLineInfo().getListOfX());
                dataInfo.setValuesOfPointY(lineStore.getDynamicLineInfo().getListOfY());
                if (params.getTypeOfRotate().equals("A相")) {
                    dataInfo.setDataInput("A");
                    dataInfo.setDataOutput("O");
                } else if (params.getTypeOfRotate().equals("B相")) {
                    dataInfo.setDataInput("B");
                    dataInfo.setDataOutput("O");
                } else {
                    dataInfo.setDataInput("C");
                    dataInfo.setDataOutput("O");
                }
                dataInfo.setDataFile(dataInfo.getDataInput() + dataInfo.getDataOutput());
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                SimpleDateFormat formatSpeci = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
                dataInfo.setTestTime(format.format(new Date()));
                dataInfo.setTestSpecificTime(format.format(new Date()));
                dataInfo.setNameOfCompany(params.getNameOfSubstation());
                String nameOfTheDataInfo = FileManager.mkCSVFle(dataInfo);
                dataInfo.setDataFile(nameOfTheDataInfo);
                determinedInfos(dataInfo);
                determinedControllers(dataInfo);
                ArrayList<Float> listOfX = new ArrayList<>();
                ArrayList<Float> listOfY = new ArrayList<>();

                listOfX.addAll(lineStore.getDynamicLineInfo().getListOfX());
                listOfY.addAll(lineStore.getDynamicLineInfo().getListOfY());
                determinedLines(listOfX, listOfY);
                changeState(true);
                logicService.calculateRelativeParams();
                break;
            case 0x30:
                PrivateSettings settings = (PrivateSettings) msg.getData().getSerializable("privateSettings");
                actionCreator.setPrivateSetting(settings);
                break;
            case 0x31:
                // 网络错误
                actionCreator.setOperation("network");
                actionCreator.networkError();
                break;
            case 0x32:
                actionCreator.sendMessage();
                break;
        }
    }

    private void determinedLines(List<Float> listOfX, List<Float> listOfY) {
        if (actionCreator != null) {
            LineStore lineStore = LineStore.getALineStore();
            List<LineInfo> listOfLines = lineStore.getListOfLines();
            int position = -1;
            int color = -1;
            for (int i = 0; i < listOfLines.size(); i++) {
                if (listOfLines.get(i).getListOfX() == null) {
                    //找到第一个X为空的并插入
                    position = i + 1;
                    color = getColor(i + 1);
                    break;
                }
            }
            if (position != -1) {
                LineInfo lineInfo = new LineInfo(position, true, color, listOfX, listOfY);
                actionCreator.setALine(lineInfo);
            } else {

            }
        }
    }

    @SuppressWarnings("unchecked")
    public boolean determinedLines(Intent intent) {
        if (actionCreator != null) {
            LineStore lineStore = LineStore.getALineStore();
            List<LineInfo> listOfLines = lineStore.getListOfLines();
            int position = -1;
            int color = -1;
            for (int i = 0; i < listOfLines.size(); i++) {
                if (listOfLines.get(i).getListOfX() == null) {
                    //找到第一个X为空的并插入
                    position = i + 1;
                    color = getColor(i + 1);
                    break;
                }
            }
            if (position != -1) {
                List<Float> listOfX = (List<Float>) intent.getExtras().get("listOfX");
                List<Float> listOfY = (List<Float>) intent.getExtras().get("listOfY");
                LineInfo lineInfo = new LineInfo(position, true, color, listOfX, listOfY);
                actionCreator.setALine(lineInfo);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private int getColor(int i) {
        switch (i) {
            case 1:
                return Color.BLACK;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.GRAY;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.YELLOW;
            case 6:
                return Color.RED;
        }
        return 0;
    }

    public void determinedInfos(Intent intent) {
        if (intent.getExtras() != null) {
            DataInfo dataInfo = (DataInfo) intent.getExtras().get("dataInfo");
            InfoStore infoStore = InfoStore.getAInfoStore();
            List<DataInfo> infos = infoStore.getDataInfos();
            int position = 0;
            for (int i = 1; i < infos.size(); i++) {
                if (infos.get(i).getDataFile() == null) {
                    position = i;
                    break;
                }
            }
            if (dataInfo != null) {
                dataInfo.setSortNum(String.valueOf(position));
                actionCreator.setAInfo(dataInfo);
            }
        }
    }

    public void determinedInfos(DataInfo dataInfo) {

        InfoStore infoStore = InfoStore.getAInfoStore();
        List<DataInfo> infos = infoStore.getDataInfos();
        int position = 0;
        for (int i = 1; i < infos.size(); i++) {
            if (infos.get(i).getDataFile() == null) {
                position = i;
                break;
            }
        }
        if (dataInfo != null) {
            dataInfo.setSortNum(String.valueOf(position));
            actionCreator.setAInfo(dataInfo);
        }

    }

    public void setInfo(int currentPickPosition, Intent intent) {
        InfoStore infoStore = InfoStore.getAInfoStore();
        if (intent.getExtras() != null) {
            DataInfo dataInfo = (DataInfo) intent.getExtras().get("dataInfo");
            if (dataInfo != null) {
                dataInfo.setSortNum(String.valueOf(currentPickPosition));
                actionCreator.setAInfo(dataInfo);
            }
        }

    }

    public void setCurrentSelectedPosition(int i) {
        actionCreator.setCurrentSelectedPosition(i);
    }


    public void setALine(int currentPickPosition, Intent intent) {
        if (actionCreator != null) {
            if (currentPickPosition != -1) {
                List<Float> listOfX = (List<Float>) intent.getExtras().get("listOfX");
                List<Float> listOfY = (List<Float>) intent.getExtras().get("listOfY");
                LineInfo lineInfo = new LineInfo(currentPickPosition, true, getColor(currentPickPosition), listOfX, listOfY);
                actionCreator.setALine(lineInfo);
            }
        }


    }

    public void setAController(int currentPickPosition, Intent intent) {
        if (actionCreator != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                DataInfo dataInfo = (DataInfo) bundle.get("dataInfo");
                if (dataInfo != null) {
                    LineController controller = new LineController(currentPickPosition, true, true, dataInfo.getDataFile(), getColor(currentPickPosition));
                    actionCreator.changeControllerState(currentPickPosition, controller);
                }
            }
        }
    }

    public void updateTheList(float x, float y) {
        actionCreator.addAPointToDynamicLine(x, y);
    }

    public void changeLineState(int postion, boolean isChecked) {
        actionCreator.changeLineShowUpState(postion, isChecked);

    }

    public void deleteALine(int position) {
        actionCreator.deleteALine(position);
        actionCreator.deleteAInfo(position);
        actionCreator.deleteAController(position);
        calculateRelativeParams();
    }

    public void determinedControllers(Intent intent) {
        ControllerStore controllerStore = ControllerStore.getAControllerStore();
        List<LineController> controller1 = controllerStore.getListOfController();
        int position = -1;
        for (int i = 0; i < controller1.size(); i++) {
            if (!controller1.get(i).isController()) {
                position = i + 1;
                break;
            }
        }
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            DataInfo dataInfo = (DataInfo) bundle.get("dataInfo");
            if (dataInfo != null) {
                LineController controller = new LineController(position, true, true, dataInfo.getDataFile(), getColor(position));
                actionCreator.changeControllerState(position, controller);
            }
        }
    }

    public void determinedControllers(DataInfo dataInfo) {
        ControllerStore controllerStore = ControllerStore.getAControllerStore();
        List<LineController> controller1 = controllerStore.getListOfController();
        int position = -1;
        for (int i = 0; i < controller1.size(); i++) {
            if (!controller1.get(i).isController()) {
                position = i + 1;
                break;
            }
        }
        if (dataInfo != null) {
            LineController controller = new LineController(position, true, true, dataInfo.getDataFile(), getColor(position));
            actionCreator.changeControllerState(position, controller);
        }

    }

    public void startTest(ArrayList<Float> points1, String typeOfDevice) {

        AndroidShortConnnectionClient client = new AndroidShortConnnectionClient("192.168.10.2", 8000, points1);
        client.setCurrentDevice(typeOfDevice);
        Flag.exitScan = false;
        StateOfTheIntenet.ToCloseTheThread = false;
        client.setHandler(handler);
        new Thread(client).start();
    }

    public void changeState(boolean state) {
        if (state) {
            //退出扫描
            Flag.exitScan = true;
            StateOfTheIntenet.ToCloseTheThread = true;
            actionCreator.addAPointToDynamicLine(-1, -1);
        }
        actionCreator.changeaState(state);
    }

    public void setTestParams(ParameterSaver saver) {
        actionCreator.setATestParams(saver);
    }

    public int getAColor() {
        LineStore lineStore = LineStore.getALineStore();
        List<LineInfo> listOfLines = lineStore.getListOfLines();
        int color = -1;
        for (int i = 0; i < listOfLines.size(); i++) {
            if (listOfLines.get(i).getListOfX() == null) {
                //找到第一个X为空的并插入
                color = getColor(i + 1);
                break;
            }
        }
        return color;
    }

    public void changeStateOfScopeBtn() {
        actionCreator.changeStateOfScopeBtn();
    }

    public Bitmap viewConversionBitMap(View view) {
        int height = view.getHeight();
        int width = view.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.layout(0, 0, width, height);
        view.draw(canvas);
        return bitmap;
    }

    public void createWordFile(View dialogView) {
        WordParams wordParams = new WordParams();
    }

    public void fetchPrivateSettings() {
        actionCreator.setOperation("fetching");
        FetchPrivateSetting setting = new FetchPrivateSetting();
        setting.setNet_handler(handler);
        new Thread(setting).start();
    }

    public void setPrivateSettings(PrivateSettings settings) {
        actionCreator.setOperation("setting");
        SetPrivateSetting setting = new SetPrivateSetting();
        setting.setNet_handler(handler);
        setting.setPrivateSettings(settings);
        new Thread(setting).start();
    }
}
