package com.pd.config.myapplication.flux_frame_impl.stores;

import android.graphics.Color;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.pd.config.myapplication.R;
import com.pd.config.myapplication.flux_frame_impl.actions.LineAction;
import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.pd.config.myapplication.pojo.ChartInfo;
import com.pd.config.myapplication.pojo.LineController;
import com.pd.config.myapplication.pojo.LineInfo;

import java.util.ArrayList;
import java.util.List;

public class LineStore extends Store {
    private List<LineInfo> listOfLines;
    private final ChartInfo chartInfo;
    private List<LineController> listOfController;
    private LineInfo dynamicLineInfo;
    private static LineStore lineStore;

    private int positon = -1;

    public List<LineController> getListOfController() {
        return listOfController;
    }

    public List<LineInfo> getListOfLines() {
        return listOfLines;
    }

    public ChartInfo getChartInfo() {
        return chartInfo;
    }

    private LineStore() {
        listOfLines = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listOfLines.add(new LineInfo(i + 1, false, -1, null, null));
        }
        ArrayList<Float> listX = new ArrayList();
        ArrayList<Float> listY = new ArrayList();
        listX.add(3000f);
        listY.add(20f);
        listX.add(3000f);
        listY.add(-80f);
        listOfLines.add(new LineInfo(7,true, Color.WHITE,listX,listY));
        listOfController = new ArrayList<>();
//        for (int j = 0; j < 6; j++) {
//            listOfController.add(new LineController(j + 1, true, false, "null",));
//        }
        chartInfo = new ChartInfo();
        dynamicLineInfo = new LineInfo(-1,true,Color.BLACK,new ArrayList<Float>(),new ArrayList<Float>());
    }


    public static LineStore getALineStore() {
        if (lineStore == null) {
            lineStore = new LineStore();
        }
        return lineStore;
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent("line");
    }

    public LineInfo getDynamicLineInfo() {
        return dynamicLineInfo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case LineAction.CHANGE_LINES:
                this.listOfLines = (List<LineInfo>) action.getData();
                break;
            case LineAction.ADD_LINES:
                LineInfo info = (LineInfo) action.getData();
                listOfLines.set(info.getSort()-1, info);
                break;
            case LineAction.DELETE_LINES:
                int delPosition = (int) action.getData();
                listOfLines.set(delPosition-1, new LineInfo(delPosition, false, -1, null, null));
//                listOfController.set(delPosition-1,new LineController(delPosition, true, false, "null"));
                break;

            case LineAction.CHANGE_LINE_STATE:
                int position = (int) action.getData();
                boolean visible = (boolean) action.getData1();
                lineStore.getListOfLines().get(position-1).setVisible(visible);
//                listOfController.get(position-1).setVisible(visible);
                break;
            case LineAction.ADD_POINT_TO_TEST_LINE:
                if((Float)action.getData()==-1){
                    dynamicLineInfo.getListOfX().clear();
                    dynamicLineInfo.getListOfY().clear();
                }else {
                    dynamicLineInfo.getListOfX().add((Float) action.getData());
                    dynamicLineInfo.getListOfY().add((Float) action.getData1());
                }

                break;
            default:
                break;
        }
        emitStoreChange();

    }
}
