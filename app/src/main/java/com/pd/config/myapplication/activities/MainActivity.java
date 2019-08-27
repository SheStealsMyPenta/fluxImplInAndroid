package com.pd.config.myapplication.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.constraint.solver.Cache;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.pd.config.myapplication.R;
import com.pd.config.myapplication.adapters.InfoStoreAdapter;
import com.pd.config.myapplication.adapters.RelativeParamAdapter;
import com.pd.config.myapplication.dao.DaoImpl;
import com.pd.config.myapplication.flux_frame_impl.actions.ControllerAction;
import com.pd.config.myapplication.flux_frame_impl.stores.ControllerStore;
import com.pd.config.myapplication.flux_frame_impl.stores.DataStore;
import com.pd.config.myapplication.flux_frame_impl.stores.InfoStore;
import com.pd.config.myapplication.flux_frame_impl.stores.LineStore;
import com.pd.config.myapplication.flux_frame_impl.stores.MainActivityStore;
import com.pd.config.myapplication.flux_frame_impl.stores.MessageStore;
import com.pd.config.myapplication.flux_frame_java.BaseFluxActivity;
import com.pd.config.myapplication.flux_frame_java.actions.ActionCreator;
import com.pd.config.myapplication.flux_frame_java.dispatcher.Dispatcher;
import com.pd.config.myapplication.flux_frame_java.stores.Store;
import com.pd.config.myapplication.pojo.DataInfo;
import com.pd.config.myapplication.pojo.LineController;
import com.pd.config.myapplication.pojo.LineInfo;
import com.pd.config.myapplication.pojo.MyAnalyzer;
import com.pd.config.myapplication.pojo.ParameterSaver;
import com.pd.config.myapplication.pojo.State;
import com.pd.config.myapplication.pojo.TransformerBean;
import com.pd.config.myapplication.services.AndroidShortConnnectionClient;
import com.pd.config.myapplication.services.LogicService;
import com.pd.config.myapplication.statics.CacheData;
import com.pd.config.myapplication.statics.Constants;

import com.squareup.otto.Subscribe;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseFluxActivity {
    private InfoStore infoStore;
    private MainActivityStore mainActivityStore;
    private DataStore dataStore;
    private LineStore lineStore;
    private ControllerStore controllerStore;
    private LogicService logicService;
    @BindView(R.id.setting)
    Button setting;
    @BindView(R.id.linechart)
    LineChart lineChart;
    @BindView(R.id.infostoreListview)
    ListView infoStoreListView;
    @BindView(R.id.dataView)
    ListView dataView;
    @BindView(R.id.manage)
    Button manage;
    @BindView(R.id.firstLine)
    TextView infoPicker1;
    @BindView(R.id.secondLine)
    TextView infoPicker2;
    @BindView(R.id.thirdLine)
    TextView infoPicker3;
    @BindView(R.id.forthLine)
    TextView infoPicker4;
    @BindView(R.id.fifthLine)
    TextView infoPicker5;
    @BindView(R.id.sixthLine)
    TextView infoPicker6;
    //线条控制器1
    @BindView(R.id.firstLineView)
    LinearLayout firstController;
    @BindView(R.id.checkbox01)
    CheckBox firstCheckBox;
    @BindView(R.id.text01)
    TextView firstTextView;
    @BindView(R.id.deleteBtn01)
    ImageButton firstDelButton;
    //线条控制器2
    @BindView(R.id.secondLineView)
    LinearLayout secondController;
    @BindView(R.id.checkbox02)
    CheckBox secondCheckBox;
    @BindView(R.id.text02)
    TextView secondTextView;
    @BindView(R.id.deleteBtn02)
    ImageButton secondDelButton;
    //线条控制器3
    @BindView(R.id.thirdLineView)
    LinearLayout thirdController;
    @BindView(R.id.checkbox03)
    CheckBox thirdCheckBox;
    @BindView(R.id.text03)
    TextView thirdTextView;
    @BindView(R.id.deleteBtn03)
    ImageButton thirdDelButton;
    //线条控制器4
    @BindView(R.id.forthLineView)
    LinearLayout forthController;
    @BindView(R.id.checkbox04)
    CheckBox forthCheckBox;
    @BindView(R.id.text04)
    TextView forthTextView;
    @BindView(R.id.deleteBtn04)
    ImageButton forthDelButton;
    //线条控制器5
    @BindView(R.id.fifthLineView)
    LinearLayout fifthController;
    @BindView(R.id.checkbox05)
    CheckBox fifthCheckBox;
    @BindView(R.id.text05)
    TextView fifthTextView;
    @BindView(R.id.deleteBtn05)
    ImageButton fifthDelButton;
    //线条控制6
    @BindView(R.id.sixthLineView)
    LinearLayout sixthController;
    @BindView(R.id.checkbox06)
    CheckBox sixthCheckBox;
    @BindView(R.id.text06)
    TextView sixthTextView;
    @BindView(R.id.deleteBtn06)
    ImageButton sixthDelButton;
    @BindView(R.id.trigger)
    Button trigger;
    @BindView(R.id.frequencyScope)
    Button frequencyScope;
    @BindView(R.id.connect)
    Button connect;
    @OnClick({R.id.connect,R.id.manage, R.id.firstLine, R.id.secondLine, R.id.thirdLine, R.id.forthLine, R.id.fifthLine, R.id.sixthLine, R.id.trigger, R.id.setting, R.id.frequencyScope})
    public void btnCallBack(View view) {
        switch (view.getId()) {
            case  R.id.connect:
                Intent intentDevice =new Intent();
                intentDevice.setClass(this,DeviceInfoActivity.class);
                startActivity(intentDevice);
                break;
            case R.id.manage:
                logicService.setCurrentSelectedPosition(-1);
                InfoStore infoStore = InfoStore.getAInfoStore();
                List<DataInfo> infos = infoStore.getDataInfos();
                int position = -1;
                for (int i = 1; i < infos.size(); i++) {
                    if (infos.get(i).getDataFile() == null) {
                        position = i;
                        break;
                    }
                }
                if (position != -1) {
                    Intent intent = getIntent().setClass(this, DataManagerActivity.class);
                    startActivity(intent);
                } else {
                    console("当前已经存在6条曲线!");
                }
                break;
            case R.id.firstLine:
                logicService.setCurrentSelectedPosition(1);
                startActivity(getIntent().setClass(this, DataManagerActivity.class));
                break;
            case R.id.secondLine:
                logicService.setCurrentSelectedPosition(2);
                startActivity(getIntent().setClass(this, DataManagerActivity.class));
                break;
            case R.id.thirdLine:
                logicService.setCurrentSelectedPosition(3);
                startActivity(getIntent().setClass(this, DataManagerActivity.class));
                break;
            case R.id.forthLine:
                logicService.setCurrentSelectedPosition(4);
                startActivity(getIntent().setClass(this, DataManagerActivity.class));
                break;
            case R.id.fifthLine:
                logicService.setCurrentSelectedPosition(5);
                startActivity(getIntent().setClass(this, DataManagerActivity.class));
                break;
            case R.id.sixthLine:
                logicService.setCurrentSelectedPosition(6);
                startActivity(getIntent().setClass(this, DataManagerActivity.class));
                break;
            case R.id.trigger:
                if (trigger.getText().equals("启  动")) {
                    int positionInfo = -1;
                    InfoStore store = InfoStore.getAInfoStore();
                    List<DataInfo> dataInfos = store.getDataInfos();
                    for (int i = 1; i < dataInfos.size(); i++) {
                        if (dataInfos.get(i).getDataFile() == null) {
                            positionInfo = i;
                        }
                    }
                    if (positionInfo != -1) {
                        Intent intentStartTest = getIntent().setClass(this, SettingParametersActivity.class);
                        startActivity(intentStartTest);
                    } else {
                        console("当前已存在6根曲线！");
                    }

                } else {
                    logicService.changeState(true);
                }

                break;
            case R.id.setting:
                LayoutInflater inflater = LayoutInflater.from(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final View dialogView = inflater.inflate(R.layout.create_report_dialog, null);
                final AlertDialog dialog = builder.setView(dialogView).create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                EditText transView = dialogView.findViewById(R.id.trans_name);
                EditText trans_type = dialogView.findViewById(R.id.trans_type);
                EditText trans_made = dialogView.findViewById(R.id.trans_made);
                EditText trans_sn = dialogView.findViewById(R.id.trans_sn);
                EditText trans_date = dialogView.findViewById(R.id.trans_date);
                DaoImpl dap = new DaoImpl(this);
                InfoStore store = InfoStore.getAInfoStore();
                List<DataInfo> dataInfos = store.getDataInfos();
                TransformerBean transformer = dap.findSpecificTransformer(dataInfos.get(0).getNameOfCompany(), dataInfos.get(0).getNameOfTransformer());
                if (transformer != null) {
                    transView.setText(transformer.getNameOfTheTransformer());
                    trans_type.setText(transformer.getTypeOfTheTransformer());
                    trans_made.setText(transformer.getNameOfTheSubstation());
                    trans_sn.setText(transformer.getNumOfCreation());
                    trans_date.setText(transformer.getDateOfCreation());
                }
                Button btn = dialogView.findViewById(R.id.createReport);
                Button cancelBtn = dialogView.findViewById(R.id.cancel);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logicService.createWordFile(dialogView);
                    }
                });
                break;
            case R.id.frequencyScope:
//                BottomSheetDialog mDialog = new BottomSheetDialog(this);
//                View theView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_content_bottom_sheet, null);
//                mDialog
//                        .setContentView(theView);
//                mDialog.setCancelable(true);
//                mDialog.show();
                logicService.changeStateOfScopeBtn();
                break;
            default:
                break;


        }
    }

//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_testpage);
        ButterKnife.bind(this);
        initService();
        initChart();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        initStores();
//        initDependencies();
    }

    private void initChart() {
        lineChart.setScaleEnabled(true);
        lineChart.setBackgroundColor(Color.WHITE);
        lineChart.setGridBackgroundColor(Color.GRAY);
        lineChart.setElevation(2);
        lineChart.setExtraBottomOffset(10);
        lineChart.setExtraRightOffset(10);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        makeXAixs(xAxis, 1000, 0);

        Description description = lineChart.getDescription();
        description.setEnabled(false);

        YAxis yAxisRight = lineChart.getAxisRight();
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = lineChart.getAxisLeft();
        //设置网格线为虚线效果
        yAxisLeft.setGranularity(10);
        yAxisLeft.setTextSize(14f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);

    }

    private void makeXAixs(XAxis xAxis, float max, float min) {
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        xAxis.setAxisMinimum(min);
        xAxis.setAxisMaximum(max);
        xAxis.setLabelCount(10);
        xAxis.setXOffset(10);
        xAxis.setTextSize(14f);
        xAxis.setDrawAxisLine(true);
        xAxis.setGridColor(Color.BLACK);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value == 0) {
                    return String.valueOf(0);
                }
                return (int) (value / 10) + "";
            }
        });

        xAxis.setAxisLineWidth(1.5f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setAxisMaximum(100);
    }

    private void initService() {
        logicService = LogicService.getALogicService();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        if (getIntent().getExtras() == null) {
            render();
        } else {
            getInfoFromIntent(getIntent());
        }
    }

    private float getFreq(int scanMode, int freMode, int index) {
        switch (scanMode) {
            case 1:
                switch (freMode) {
                    case 0:
                        return 1.0f + index;
                    case 1:       //1-2000kHz 1100点

                    case 2:       //1-10000kHz 1900点

                    case 7:       //1-20000kHz 2900点
                        if (index < 1000) {
                            return (float) (1.0 + index * 1.0);
                        } else {
                            return (float) (1000.0 + (index - 999) * 10.0);
                        }

                    case 3:                //0.01 - 1000kHz
                    case 4:                //0.01 - 2000kHz
                    case 5:                //0.01 - 10000kHz
                    case 8:                //0.01 - 20000kHz
                        if (index < 99) {
                            return (float) (0.01 + 0.01 * index);
                        } else if (index < 1099) {
                            return (float) (1.0 + (index - 99) * 1.0);
                        } else {
                            return (float) (1000.0 + (index - 1098) * 10.0);
                        }

                    default:
                        return (float) 1.0;
                }
            case 2:
                switch (freMode) {
                    case 0:                //1k - 1000k 1000
                        return (float) Math.pow(10.0, index * 3.0 / 999.0);
                    case 1:
                        if (index < 1000) {
                            return (float) Math.pow(10.0, index * 3.0 / 999.0);
                        } else {
                            double dbRatio = (Math.log10(2000.0) - Math.log10(1000.0)) / 100.0;
                            return (float) Math.pow(10.0, 3.0 + dbRatio * (index - 999));
                        }


                    case 2:                //10MHz
                        if (index < 1000) {
                            return (float) Math.pow(10.0, index * 3.0 / 999.0);
                        } else {
                            double dbRatio = (Math.log10(10000.0) - Math.log10(1000.0)) / 900.0;
                            return (float) Math.pow(10.0, 3.0 + dbRatio * (index - 999));
                        }


                    case 7:                //20MHz
                        if (index < 1000) {
                            return (float) Math.pow(10.0, index * 3.0 / 999.0);
                        } else {
                            double dbRatio = (Math.log10(20000.0) - Math.log10(1000.0)) / 1900.0;
                            return (float) Math.pow(10.0, 3.0 + dbRatio * (index - 999));
                        }


                    case 3:
                        if (index < 99) {
                            return (float) Math.pow(10.0, -2 + index * 0.02);
                        } else {
                            return (float) Math.pow(10.0, (index - 99) * 3.0 / 999.0);
                        }


                    case 4:
                        if (index < 99) {
                            return (float) Math.pow(10.0, -2 + index * 0.02);
                        } else if (index < 1099) {
                            return (float) Math.pow(10.0, (index - 99) * 3.0 / 999.0);
                        } else {
                            double dbRatio = (Math.log10(2000.0) - Math.log10(1000.0)) / 100.0;
                            return (float) Math.pow(10.0, 3 + dbRatio * (index - 1098));
                        }


                    case 5:
                        if (index < 99) {
                            return (float) Math.pow(10.0, -2 + index * 0.02);
                        } else if (index < 1099) {
                            return (float) Math.pow(10.0, (index - 99) * 3.0 / 999.0);
                        } else {
                            double dbRatio = (Math.log10(10000.0) - Math.log10(1000.0)) / 900.0;
                            return (float) Math.pow(10.0, 3 + dbRatio * (index - 1098));
                        }


                    case 8:                //20MHz
                        if (index < 99) {
                            return (float) Math.pow(10.0, -2 + index * 0.02);
                        } else if (index < 1099) {
                            return (float) Math.pow(10.0, (index - 99) * 3.0 / 999.0);
                        } else {
                            double dbRatio = (Math.log10(20000.0) - Math.log10(1000.0)) / 1900.0;
                            return (float) Math.pow(10.0, 3 + dbRatio * (index - 1098));
                        }


                    default:
                        return 1.0f;
                }
        }
        return 0;
    }

    public ArrayList<Float> getScanPoints(String mode) {
        ArrayList<Float> listOfFrequency = new ArrayList<>();
        switch (mode) {
            case Constants.MODE_ONE:
                for (int i = 1000; i <= 1000000; i += 1000) {
                    listOfFrequency.add((float) i);
                }
                break;
            case Constants.MODE_TWO:
                for (int i = 1000; i <= 100000; i += 1000) {
                    listOfFrequency.add((float) i);
                }
                break;
            case Constants.MODE_THREE:
                for (int i = 1; i <= 5000; i += 25) {
                    listOfFrequency.add((float) i);
                }
                break;
            case Constants.MODE_FOUR:
                for (int i = 0; i < 1099000; i += 1000) {
                    float freq = getFreq(CacheData.scanMode, 3, i / 1000);
                    listOfFrequency.add(freq * 1000);
                }
                break;
            case Constants.MODE_FIVE:
                for (int i = 0; i < 1199000; i += 1000) {
                    float freq = getFreq(CacheData.scanMode, 4, i / 1000);
                    listOfFrequency.add(freq * 1000);
                }
                break;
            case Constants.MODE_Six:
                for (int i = 0; i < 1999000; i += 1000) {
                    float freq = getFreq(CacheData.scanMode, 5, i / 1000);
                    listOfFrequency.add(freq * 1000);
                }


        }
//        System.out.println(listOfFrequency.get(listOfFrequency.size()-1));
        return listOfFrequency;
    }

    private void getInfoFromIntent(Intent intent) {
        if (intent.getExtras() != null) {
            if (("startTest").equals(intent.getExtras().get("flag"))) {
                //开始采集,更新界面状态
                render();
                logicService.changeState(false);
                Bundle testParams = intent.getExtras();
                ParameterSaver saver = (ParameterSaver) testParams.get("parameter");
                logicService.setTestParams(saver);
                String points = saver.getPoints();
                String range = saver.getRange();
                String scan = saver.getTypeOfScan();
                ArrayList<Float> points1;
                if (!"其他设备".equals(scan)) {
                    points1 = getScanPoints(range);
                } else {
                    points1 = getScanByValues(saver);
                }
                logicService.startTest(points1,scan);

            } else if (("dataManage").equals(intent.getExtras().get("flag"))) {
                //从数据管理到当前页面
                if (mainActivityStore.getState().getCurrentPickPosition() != -1) {
                    logicService.setInfo(mainActivityStore.getState().getCurrentPickPosition(), intent);
                    logicService.setALine(mainActivityStore.getState().getCurrentPickPosition(), intent);
                    logicService.setAController(mainActivityStore.getState().getCurrentPickPosition(), intent);

                } else {
                    logicService.determinedInfos(intent);
                    logicService.determinedControllers(intent);

                    if (!logicService.determinedLines(intent)) {
                        console("当前已经显示6条曲线！");
                    }
                }

            }
        }
    }

    private ArrayList<Float> getScanByValues(ParameterSaver saver) {
        ArrayList<Float> listOfTestPoint = new ArrayList<>();
        float range = (saver.getEnd()-saver.getBegin())+1;
        float points = saver.getNumberOfPoint();
        float step = range/points;
        for (float i = saver.getBegin(); i <= saver.getEnd(); i += step) {
           listOfTestPoint.add(i*1000);
        }

        return listOfTestPoint;
    }

    private void render() {
        // render the view by states in each store;
        renderByInfoStore();
        renderLineStore();
        renderLineController();
        renderDataStore();
        renderStateByMainActivityStore();

    }

    void renderDataStore() {
        List<MyAnalyzer> analyziers = dataStore.getAnalyziers();
        RelativeParamAdapter adapter = new RelativeParamAdapter(this, R.layout.adapter_relative_param, analyziers);
        dataView.setAdapter(adapter);
//       logicService.calculateRelativeParams();
    }

    private void renderLineController() {
        List<LineController> listOfController = controllerStore.getListOfController();
        for (final LineController controller : listOfController) {
            switch (controller.getPostion()) {
                case 1:
                    if (controller.isController()) {
                        infoPicker1.setVisibility(View.GONE);
                        firstController.setVisibility(View.VISIBLE);
                        firstCheckBox.setChecked(controller.isVisible());
                        firstTextView.setText(controller.getNameOfData());
                        firstDelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logicService.deleteALine(controller.getPostion());
                            }
                        });
                        firstCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                logicService.changeLineState(controller.getPostion(), isChecked);
                            }
                        });
                    } else {
                        firstController.setVisibility(View.GONE);
                        infoPicker1.setVisibility(View.VISIBLE);
                    }
                    break;
                case 2:
                    if (controller.isController()) {
                        infoPicker2.setVisibility(View.GONE);
                        secondController.setVisibility(View.VISIBLE);
                        secondCheckBox.setChecked(controller.isVisible());
                        secondTextView.setTextColor(controller.getColor());
                        secondTextView.setText(controller.getNameOfData());
                        secondDelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logicService.deleteALine(controller.getPostion());
                            }
                        });
                        secondCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                logicService.changeLineState(controller.getPostion(), isChecked);
                            }
                        });
                    } else {
                        secondController.setVisibility(View.GONE);
                        infoPicker2.setVisibility(View.VISIBLE);
                    }
                    break;
                case 3:
                    if (controller.isController()) {
                        infoPicker3.setVisibility(View.GONE);
                        thirdController.setVisibility(View.VISIBLE);
                        thirdCheckBox.setChecked(controller.isVisible());
                        thirdTextView.setText(controller.getNameOfData());
                        thirdTextView.setTextColor(controller.getColor());
                        thirdDelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logicService.deleteALine(controller.getPostion());
                            }
                        });
                        thirdCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                logicService.changeLineState(controller.getPostion(), isChecked);
                            }
                        });
                    } else {
                        thirdController.setVisibility(View.GONE);
                        infoPicker3.setVisibility(View.VISIBLE);
                    }
                    break;
                case 4:
                    if (controller.isController()) {
                        infoPicker4.setVisibility(View.GONE);
                        forthController.setVisibility(View.VISIBLE);
                        forthCheckBox.setChecked(controller.isVisible());
                        forthTextView.setText(controller.getNameOfData());
                        forthTextView.setTextColor(controller.getColor());
                        forthDelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logicService.deleteALine(controller.getPostion());
                            }
                        });
                        forthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                logicService.changeLineState(controller.getPostion(), isChecked);
                            }
                        });
                    } else {
                        forthController.setVisibility(View.GONE);
                        infoPicker4.setVisibility(View.VISIBLE);
                    }
                    break;
                case 5:
                    if (controller.isController()) {
                        infoPicker5.setVisibility(View.GONE);
                        fifthController.setVisibility(View.VISIBLE);
                        fifthCheckBox.setChecked(controller.isVisible());
                        fifthTextView.setText(controller.getNameOfData());
                        fifthTextView.setTextColor(controller.getColor());
                        fifthDelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logicService.deleteALine(controller.getPostion());
                            }
                        });
                        fifthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                logicService.changeLineState(controller.getPostion(), isChecked);
                            }
                        });
                    } else {
                        fifthController.setVisibility(View.GONE);
                        infoPicker5.setVisibility(View.VISIBLE);
                    }
                    break;
                case 6:
                    if (controller.isController()) {
                        infoPicker6.setVisibility(View.GONE);
                        sixthController.setVisibility(View.VISIBLE);
                        sixthCheckBox.setChecked(controller.isVisible());
                        sixthTextView.setText(controller.getNameOfData());
                        sixthTextView.setTextColor(controller.getColor());
                        sixthDelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logicService.deleteALine(controller.getPostion());
                            }
                        });
                        sixthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                logicService.changeLineState(controller.getPostion(), isChecked);
                            }
                        });
                    } else {
                        sixthController.setVisibility(View.GONE);
                        infoPicker6.setVisibility(View.VISIBLE);
                    }
                    break;

            }
        }
    }


    private void renderLineStore() {
        LineData lineData = new LineData();
        long start = System.currentTimeMillis();
        for (int i = 0; i < lineStore.getListOfLines().size(); i++) {
            List<Float> listOfX = lineStore.getListOfLines().get(i).getListOfX();
            List<Float> listOfY = lineStore.getListOfLines().get(i).getListOfY();
            if (listOfX != null && listOfX.size() != 0) {
                LineInfo lineInfo = lineStore.getListOfLines().get(i);
                if (lineInfo.isVisible()) {
                    List<Entry> entityList = new ArrayList<>();
                    for (int j = 0; j < listOfX.size(); j++) {
                        entityList.add(new Entry(listOfX.get(j), listOfY.get(j)));
                    }
                    LineDataSet lineDataSet = new LineDataSet(entityList, "");
                    lineDataSet.setDrawCircles(false);
                    lineDataSet.setColor(lineStore.getListOfLines().get(i).getColorOfTheLine());
                    lineDataSet.setVisible(lineStore.getListOfLines().get(i).isVisible());
                    lineData.addDataSet(lineDataSet);
                }
            }
        }

        LineInfo info = lineStore.getDynamicLineInfo();
        if (info.getListOfX().size() > 0) {
            List<Entry> entityList = new ArrayList<>();
            for (int i = 0; i < info.getListOfX().size(); i++) {
                entityList.add(new Entry(info.getListOfX().get(i), info.getListOfY().get(i)));
            }

            LineDataSet lineDataSet = new LineDataSet(entityList, "");
            lineDataSet.setDrawCircles(false);
            lineDataSet.setColor(logicService.getAColor());
            lineDataSet.setDrawValues(false);
//            System.out.println("画图");
            lineDataSet.setVisible(true);
            lineData.addDataSet(lineDataSet);
        }
        lineChart.setData(lineData);
        lineChart.invalidate();
        long end = System.currentTimeMillis();
        System.out.println("画图耗时" + (end - start));
    }

    public void drawLine(LineChart chart, List<Float> listOfX, List<Float> listOfY, int color) {
        LineData lineData = chart.getLineData();
        List<Entry> entityList = new ArrayList<>();
        for (int i = 0; i < listOfX.size(); i++) {
            entityList.add(new Entry(listOfX.get(i), listOfY.get(i)));
        }
        LineDataSet lineDataSet = new LineDataSet(entityList, "");
        lineDataSet.setColor(color);
        lineData.addDataSet(lineDataSet);
        chart.setData(lineData);
    }

    private void renderInfoStore() {
        List<DataInfo> dataInfos = infoStore.getDataInfos();

    }

    private void initStores() {
        MessageStore messageStore = new MessageStore();
        infoStore = InfoStore.getAInfoStore();
        mainActivityStore = MainActivityStore.getAMainActivityStore();
        lineStore = LineStore.getALineStore();
        dataStore = DataStore.getADataStore();
        controllerStore = ControllerStore.getAControllerStore();
//        messageStore.register(this);
        lineStore.register(this);
    }

    @Override
   public void onResume() {
        super.onResume();
        initStores();
        initDependencies();
        render();
    }

    @Override
  public void onPause()
    {

        super.onPause();
        lineStore.unregister(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        messageStore.unregister(this);


    }

    private void getInfoFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            DataInfo info = (DataInfo) bundle.getSerializable("dataInfo");
            actionCreator.setAInfo(info);
        }
    }

    @Override
   public void initDependencies() {
        dispatcher = Dispatcher.getADispather();
        actionCreator = ActionCreator.getActionCreator(dispatcher);
//        dispatcher.register(messageStore);
        dispatcher.register(lineStore);
        dispatcher.register(dataStore);
        dispatcher.register(infoStore);
        dispatcher.register(mainActivityStore);
        dispatcher.register(controllerStore);
        logicService.setActionCreator(actionCreator);

    }

    @Override
    @Subscribe
    public void onStoreChange(Store.StoreChangeEvent event) {
        if (event.type.equals("text")) {

        } else if (event.type.equals("info")) {
            renderByInfoStore();
        } else if (event.type.equals("line")) {
            renderLineStore();
        } else if (event.type.equals("data")) {
            renderDataStore();
        } else if (event.type.equals("controller")) {
            renderLineController();
        } else if (event.type.equals("state")) {
            renderStateByMainActivityStore();
        }
    }

    private void renderByInfoStore() {
        List<DataInfo> infos = infoStore.getDataInfos();
        InfoStoreAdapter adapter = new InfoStoreAdapter(this, 0, infos);
        infoStoreListView.setAdapter(adapter);
        infoStoreListView.invalidate();
        //把adapter设置成infos列表
    }

    private void renderStateByMainActivityStore() {
        State state = mainActivityStore.getState();
        if (state.isEnabled()) {
            trigger.setText("启  动");
        } else {
            trigger.setText("停止");
        }
        setting.setEnabled(state.isEnabled());
        manage.setEnabled(state.isEnabled());
        firstController.setEnabled(state.isEnabled());
        secondController.setEnabled(state.isEnabled());
        thirdController.setEnabled(state.isEnabled());
        forthController.setEnabled(state.isEnabled());
        fifthController.setEnabled(state.isEnabled());
        sixthController.setEnabled(state.isEnabled());
        firstCheckBox.setEnabled(state.isEnabled());
        secondCheckBox.setEnabled(state.isEnabled());
        thirdCheckBox.setEnabled(state.isEnabled());
        forthCheckBox.setEnabled(state.isEnabled());
        fifthCheckBox.setEnabled(state.isEnabled());
        sixthCheckBox.setEnabled(state.isEnabled());
        firstDelButton.setEnabled(state.isEnabled());
        secondDelButton.setEnabled(state.isEnabled());
        thirdDelButton.setEnabled(state.isEnabled());
        forthDelButton.setEnabled(state.isEnabled());
        fifthDelButton.setEnabled(state.isEnabled());
        sixthDelButton.setEnabled(state.isEnabled());
        XAxis xAxis = lineChart.getXAxis();
        xAxis.resetAxisMaximum();
        xAxis.resetAxisMinimum();
        switch (mainActivityStore.getState().getRangeOfShowUp()) {
            case "低频段":
                frequencyScope.setText("低频段");
//                xAxis.setAxisMaximum(100);
                makeXAixs(xAxis, 100, 0);
                break;
            case "中频段":
                frequencyScope.setText("中频段");

                makeXAixs(xAxis, 600, 100);
                break;
            case "高频段":
                frequencyScope.setText("高频段");
                makeXAixs(xAxis, 1000, 600);
                break;
            case "全频段":
                frequencyScope.setText("全频段");
                makeXAixs(xAxis, 1000, 0);
                break;

        }
        lineChart.zoomIn();
        lineChart.zoomOut();
        lineChart.resetZoom();
        lineChart.invalidate();
        //把activity的按钮样式设置成对应的样式
    }


}
