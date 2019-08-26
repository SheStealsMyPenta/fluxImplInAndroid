package com.pd.config.myapplication.flux_frame_java;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pd.config.myapplication.R;
import com.pd.config.myapplication.flux_frame_java.actions.ActionCreator;
import com.pd.config.myapplication.flux_frame_java.dispatcher.Dispatcher;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.squareup.otto.Subscribe;

public abstract class BaseFluxActivity extends Activity {
    public Dispatcher dispatcher;
    public ActionCreator actionCreator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    protected abstract void initDependencies();


    protected abstract void onStoreChange(Store.StoreChangeEvent event);

    protected void console(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

}
