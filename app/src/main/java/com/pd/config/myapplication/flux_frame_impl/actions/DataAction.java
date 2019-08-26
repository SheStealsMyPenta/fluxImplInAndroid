package com.pd.config.myapplication.flux_frame_impl.actions;

import com.pd.config.myapplication.flux_frame_java.actions.Action;

import java.util.ArrayList;
import java.util.List;

public class DataAction extends Action {
    public final static String UPDATE_DATA="UPDATE_DATA";
    public final static String UPDATE_COLUMN_ONE="UPDATE_COLUMN_ONE";
    public final static String UPDATE_COLOUMN_TWO= "UPDATE_COLUMN_TWO";
    public final static String UPDATE_COLOUMN_THREE ="UPDATE_COLUMN_THREE";
    public final static String UPDATE_COLOUMN_FOUR ="UPDATE_COLUMN_FOUR";
    public static final String UPDATE_COLOUMN_FIVE = "UPDATE_COLUMN_FIVE";
    public final static String UPDATE_COLOUMN_SIX= "UPDATE_COLOUMN_SIX";
    public final static String UPDATE_COLOUMN_SEVEN ="UPDATE_COLOUMN_SEVEN";
    public final static String UPDATE_COLOUMN_EIGHT ="UPDATE_COLOUMN_EIGHT";
    public static final String UPDATE_COLOUMN_NINE = "UPDATE_COLOUMN_NINE";
    public static final List<String> listOfDataAction= new ArrayList<>();
    static {
        listOfDataAction.add(UPDATE_DATA);
        listOfDataAction.add(UPDATE_COLUMN_ONE);
        listOfDataAction.add(UPDATE_COLOUMN_TWO);
        listOfDataAction.add(UPDATE_COLOUMN_THREE);
        listOfDataAction.add(UPDATE_COLOUMN_FOUR);
        listOfDataAction.add(UPDATE_COLOUMN_FIVE);
        listOfDataAction.add(UPDATE_COLOUMN_SIX);
        listOfDataAction.add(UPDATE_COLOUMN_SEVEN);
        listOfDataAction.add(UPDATE_COLOUMN_EIGHT);
        listOfDataAction.add(UPDATE_COLOUMN_NINE);

    }

    public DataAction(String type, Object data) {
        super(type, data);
    }

    public DataAction(String type, Object data, Object data1) {
        super(type, data, data1);
    }
}
