package com.pd.config.myapplication.flux_frame_impl.stores;

import android.graphics.Color;

import com.pd.config.myapplication.flux_frame_impl.actions.ControllerAction;
import com.pd.config.myapplication.flux_frame_impl.actions.LineAction;
import com.pd.config.myapplication.flux_frame_java.actions.Action;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.pd.config.myapplication.pojo.LineController;

import java.util.ArrayList;
import java.util.List;

public class ControllerStore extends Store {
    private static ControllerStore controllerStore;

    public List<LineController> getListOfController() {
        return listOfController;
    }

    private  ControllerStore(){
        listOfController = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
            listOfController.add(new LineController(j + 1, true, false, "null",getColor(j)));
        }
    }
    public static ControllerStore getAControllerStore(){
        if(controllerStore==null){
            controllerStore =new ControllerStore();
        }
        return controllerStore;
    }
    private List<LineController> listOfController;

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent("controller");
    }

    @Override
    public void onAction(Action action) {
      switch (action.getType()){
          case ControllerAction.CHANGE_CONTROLLER_STATE:
              LineController controller = (LineController) action.getData();
              listOfController.set(controller.getPostion()-1, controller);

              break;
          case ControllerAction.DELETE_CONTROLLER:
              int delPosition = (int) action.getData();
              listOfController.set(delPosition-1,new LineController(delPosition, true, false, "null",getColor(delPosition)));
              break;
          case ControllerAction.CHANGE_STATE:
              int position = (int) action.getData();
              boolean visible = (boolean) action.getData1();
              listOfController.get(position-1).setVisible(visible);
              break;
      }
      emitStoreChange();
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
}
