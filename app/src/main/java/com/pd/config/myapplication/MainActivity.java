package com.pd.config.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.pd.config.myapplication.flux_frame_impl.stores.MessageStore;
import com.pd.config.myapplication.flux_frame_java.BaseFluxActivity;
import com.pd.config.myapplication.flux_frame_java.actions.ActionCreator;
import com.pd.config.myapplication.flux_frame_java.dispatcher.Dispatcher;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.squareup.otto.Subscribe;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class MainActivity extends BaseFluxActivity {
    private MessageStore messageStore;
    @BindView(R.id.fluxText)
    TextView textView;
    @BindView(R.id.clickme)
    Button clickme;
    @OnClick(R.id.clickme)
    public void btnCallBack(View view) {
        switch (view.getId()) {
            case R.id.clickme:
                callClickMe();
                break;
            default:
                break;
        }
    }
    private void callClickMe() {
        actionCreator.setText("zujian");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDependencies();
        initStores();
        render();
    }

    private void render() {
     // render the view by states in each store;

    }

    private void initStores() {
        messageStore = new MessageStore();
    }

    @Override
    protected void onResume() {
        super.onResume();
        messageStore.register(this);
    }
    @Override
    protected void initDependencies() {
        dispatcher = Dispatcher.getADispather();
        actionCreator = ActionCreator.getActionCreator(dispatcher);
        dispatcher.register(messageStore);
    }
    @Override
    @Subscribe
    protected void onStoreChange(Store.StoreChangeEvent event) {
        if (event.type.equals("text")) {
            renderText(messageStore);
        }
    }
    public void renderText(MessageStore store) {
        textView.setText(store.getMessage().getMessage());
    }
}
