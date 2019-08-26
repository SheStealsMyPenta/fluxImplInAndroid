package com.pd.config.myapplication.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.pd.config.myapplication.R
import com.pd.config.myapplication.adapters.AdapterForDateReview
import com.pd.config.myapplication.pojo.DataInfo
import com.pd.config.myapplication.services.DataManage
import com.pd.config.myapplication.services.MTPUtils
import com.pd.config.myapplication.statics.CacheData



import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.*

class DataManagerActivity : Activity(), AdapterView.OnItemClickListener, View.OnClickListener {
    var numberOfPoints: Int? = null

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        MTPUtils.scanMtpAsync(this)
        if (CacheData.lastOpenData != null && CacheData.lastOpenFile != null && CacheData.lastOpenSub != null && CacheData.lastOpenTrans != null) {
            adapterForSubstation!!.selectedPosition = adapterForSubstation!!.names.indexOf(CacheData.lastOpenSub)
            currentSub = CacheData.lastOpenSub
            adapterForSubstation!!.notifyDataSetInvalidated()
            var listOfTrans: ArrayList<String> = DataManage.findTheDir(2, CacheData.lastOpenSub, null, null)
            adapterForTransformer = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfTrans)
            adapterForTransformer!!.selectedPosition = adapterForTransformer!!.names.indexOf(CacheData.lastOpenTrans)
            currentTrans = CacheData.lastOpenTrans
            listViewForTransFormer!!.adapter = adapterForTransformer
            var listOfDate: ArrayList<String> = DataManage.findTheDir(3, CacheData.lastOpenSub, CacheData.lastOpenTrans, null)
            adapterForDate = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfDate)
            adapterForDate!!.selectedPosition = adapterForDate!!.names.indexOf(CacheData.lastOpenData)
            currentDate = CacheData.lastOpenData
            listViewForDate!!.adapter = adapterForDate
            var listOfFile: ArrayList<String> = DataManage.findTheDir(4, CacheData.lastOpenSub, CacheData.lastOpenTrans, CacheData.lastOpenData)
            adapterForFile = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfFile)
            adapterForFile!!.selectedPosition = listOfFile.indexOf(CacheData.lastOpenFile)
            currentFile = CacheData.lastOpenFile
            listViewForCSVData!!.adapter = adapterForFile
        }
    }

    //    override fun overridePendingTransition(enterAnim: Int, exitAnim: Int) {
//        super.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.deleteTheFile -> {
                //选择删除的文件名。
                if (adapterForFile != null && adapterForFile!!.selectedPosition != -1 && adapterForDate!!.selectedPosition != -1 && adapterForSubstation!!.selectedPosition != -1) {
                    AlertDialog.Builder(this).setTitle("警告")
                            .setMessage("你当前要删除此数据，并且不能恢复，请确认").setPositiveButton("确认") { _, _ ->
                                DataManage.deleteFile(currentSub, currentTrans, currentDate, adapterForFile!!.getItem(adapterForFile!!.selectedPosition) as String?)
                                var listOfFile: ArrayList<String> = DataManage.findTheDir(4, currentSub, currentTrans, currentDate)
                                adapterForFile = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfFile)
                                listViewForCSVData!!.adapter = adapterForFile
                                adapterForFile!!.selectedPosition = -1
                            }.setNegativeButton("取消", null).create().show()
                } else if (adapterForDate!=null&&adapterForDate!!.selectedPosition != -1 && adapterForSubstation!!.selectedPosition != -1 && adapterForTransformer!!.selectedPosition != -1) {
                    //未选择数据但是选择了文件将爱
                    AlertDialog.Builder(this).setTitle("警告")
                            .setMessage("你当前要删除此日期文件夹，并且不能恢复，请确认").setPositiveButton("确认") { _, _ ->
                                AlertDialog.Builder(this).setTitle("警告").setMessage("请再次确认").setNegativeButton("取消", null).setPositiveButton("确认") { _, _ ->
                                    DataManage.deleteDirectory(currentSub, currentTrans, currentDate)
                                    var listOfFile: ArrayList<String> = DataManage.findTheDir(3, currentSub, currentTrans, null)
                                    adapterForDate = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfFile)
                                    //删除文件夹，重新获取文件夹信息并且让文件的listVie为空
                                    adapterForFile = null
                                    listViewForCSVData!!.adapter = adapterForFile
                                    listViewForDate!!.adapter = adapterForDate
//                                listViewForDate!!.onItemClickListener = this
//                                adapterForFile!!.selectedPosition =-1
                                    adapterForDate!!.selectedPosition = -1
                                }.create().show()

//
                            }.setNegativeButton("取消", null).create().show()
                } else if (adapterForTransformer!=null&&adapterForSubstation!!.selectedPosition != -1 && adapterForTransformer!!.selectedPosition != -1) {
                    //删除变压器已经下面所有数据
                    AlertDialog.Builder(this).setTitle("警告")
                            .setMessage("你当前要删除此变压器文件夹，并且不能恢复，请确认").setPositiveButton("确认") { _, _ ->
                                AlertDialog.Builder(this).setTitle("警告").setMessage("请再次确认").setNegativeButton("取消", null).setPositiveButton("确认") { _, _ ->
                                    //文件路径
                                    val path = Environment.getExternalStorageDirectory().path + File.separator + "/频响数据" + File.separator + currentSub + File.separator + currentTrans
                                    DataManage.deleteDirectory(path)
                                    var listOfFile: ArrayList<String> = DataManage.findTheDir(2, currentSub, null, null)
                                    adapterForTransformer = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfFile)
                                    //删除文件夹，重新获取文件夹信息并且让文件的listVie为空
                                    adapterForFile = null
                                    adapterForDate = null
                                    listViewForCSVData!!.adapter = adapterForFile
                                    listViewForDate!!.adapter = adapterForDate
                                    listViewForTransFormer!!.adapter = adapterForTransformer
//                                listViewForDate!!.onItemClickListener = this
//                                adapterForFile!!.selectedPosition =-1
                                    adapterForTransformer!!.selectedPosition = -1
                                }.create().show()

//
                            }.setNegativeButton("取消", null).create().show()
                } else if (adapterForSubstation!=null&&adapterForSubstation!!.selectedPosition != -1) {
                    AlertDialog.Builder(this).setTitle("警告")
                            .setMessage("你当前要删除此变压器文件夹，并且不能恢复，请确认").setPositiveButton("确认") { _, _ ->
                                AlertDialog.Builder(this).setTitle("警告").setMessage("请再次确认").setNegativeButton("取消", null).setPositiveButton("确认") { _, _ ->
                                    //文件路径
                                    val path = Environment.getExternalStorageDirectory().path + File.separator + "/频响数据" + File.separator + currentSub
                                    DataManage.deleteDirectory(path)
                                    var listOfFile: ArrayList<String> = DataManage.findTheDir(1,null, null, null)
                                    adapterForSubstation = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfFile)
                                    //删除文件夹，重新获取文件夹信息并且让文件的listVie为空
                                    adapterForFile = null
                                    adapterForDate = null
                                    adapterForTransformer = null
                                    listViewForCSVData!!.adapter = adapterForFile
                                    listViewForDate!!.adapter = adapterForDate
                                    listViewForTransFormer!!.adapter = adapterForTransformer
                                    listViewForSubstation!!.adapter = adapterForSubstation
                                    adapterForSubstation!!.selectedPosition = -1
                                }.create().show()

//
                            }.setNegativeButton("取消", null).create().show()

                }else{
                    Toast.makeText(this, "请选择需要删除的文件和文件夹", Toast.LENGTH_LONG).show()
                }
            }
            R.id.backToLast -> {
                val intent = Intent()
                intent.setClass(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.openTheFile -> {
                if (currentFile != null && adapterForFile!!.selectedPosition != -1 && adapterForDate!!.selectedPosition != -1 && adapterForSubstation!!.selectedPosition != -1) {
                    var mode = 0
                    val reader = BufferedReader(InputStreamReader(FileInputStream(Environment.getExternalStorageDirectory().path + "/频响数据"
                            + File.separator + currentSub + File.separator + currentTrans + File.separator + currentDate + File.separator + currentFile), "Gbk"))//换成你的文件名
                    var split1 = reader.readLine().split(",")
                    var frequency=""
                    try {
                       frequency= split1[4]
                    }catch (e: Exception) {
                        //打不开文件异常处理
                        Toast.makeText(this, "该文件格式不正确,不能被打开!", Toast.LENGTH_LONG).show()
                        return
                    }

                    var frequencyRang = ""

                    if ((split1[2].toFloat() == 1000.000f && split1[3].toFloat() == 1000000.000f)) {
                        frequencyRang = "1kHz - 1MHz"
                    } else if ((split1[2].toFloat() == 10.0f && split1[3].toFloat() == 1000000.000f)) {
                        frequencyRang = "10Hz - 1MHz"
                    } else if(split1[2].toFloat() == 1000.0f && split1[3].toFloat() == 10000000.000f){
                        frequencyRang = "1kHz - 10MHz"
                    } else if(split1[2].toFloat() == 1000.0f && split1[3].toFloat() == 2000000f){
                        frequencyRang = "1kHz - 2MHz"
                    } else if(split1[2].toFloat() == 10f && split1[3].toFloat() == 2000000f){
                        frequencyRang = "10Hz - 2MHz"
                    } else if(split1[2].toFloat() ==10f && split1[3].toFloat() == 10000000.000f){
                        frequencyRang = "10Hz - 10MHz"
                    }
                    numberOfPoints = frequency.toInt()
                    reader.readLine()
                    var line: String? = null
                    val x = ArrayList<Float>()
                    val y = ArrayList<Float>()
                    var i = 0

//                  if(mode==1){
//                      while (i<numberOfPoints){
//                          val values = reader.readLine()
//                          val split = values.split(",")
//                          val xValue = split[0].toFloat()
//                          val yValue = split[1].toFloat()
//                          x.add(xValue)
//                          y.add(yValue)
//                          i++
//                      }
//                  }else if(mode==2){
//                      while (i<2000){
//                          val values = reader.readLine()
//                          val split = values.split(",")
//                          val xValue = split[0].toFloat()
//                          val yValue = split[1].toFloat()
//                          x.add(xValue)
//                          y.add(yValue)
//                          i++
//                      }
//                  }else{
//                      while (i<10000){
//                          val values = reader.readLine()
//                          val split = values.split(",")
//                          val xValue = split[0].toFloat()
//                          val yValue = split[1].toFloat()
//                          x.add(xValue)
//                          y.add(yValue)
//                          i++
//                      }
//                  }
                    while (i < this.numberOfPoints!!) {
                        val values = reader.readLine()
                        val split = values.split(",")
                        try {
                            val xValue = split[0].toFloat()
                            val yValue = split[1].toFloat()
                            x.add(xValue)
                            y.add(yValue)
                            i++
                        } catch (e: Exception) {
                            //打不开文件异常处理
                            Toast.makeText(this, "该文件格式不正确,不能被打开!", Toast.LENGTH_LONG).show()
                            return
                        }
                    }
                    val nameOfTransformerString = reader.readLine()
                    val split = nameOfTransformerString.split(",")
                    val list = split[2].split(";")
                    val barr = byteArrayOf()
                    barr.size
//                  Convert.String2bytes(list[1],barr);

//                  val nameOfTrans = String(list[1].toByteArray(),Charset.forName("gbk"))  //;Convert.Unicodebytes2String(   list[1].toByteArray(),true)
                    val nameOfTrans = list[1]
                    val times = reader.readLine().split(",")[2].split(";")[1]
                    val position = reader.readLine().split(",")[2].split(";")[1]
                    val input = reader.readLine().split(",")[2].split(";")[1]
                    val output = reader.readLine().split(",")[2].split(";")[1]
                    val date = reader.readLine().split(",")[2].split(";")[1]
                    val typeOfTest = reader.readLine().split(",")[2].split(";")[1]
                    val typeOfTransformer = reader.readLine().split(",")[2].split(";")[1]
//                    val nameOfCompany = reader.readLine().split(",")[2].split(";")[1]
                    var nameOfCompany =""
                    val readLine = reader.readLine()
                    if(readLine!=null){
                       nameOfCompany =  readLine.split(",")[2].split(";")[1]
                    }
                    val dataInfo = DataInfo()
                    dataInfo.nameOfTransformer= nameOfTrans
                    dataInfo.dataFile = currentFile!!.split(".")[0]
                    dataInfo.dataInput = input
                    dataInfo.postion = position
                    dataInfo.dataOutput = output
                    dataInfo.testTime = date
                    dataInfo.nameOfCompany = nameOfCompany
                    val intent = Intent()
                    intent.putExtra("listOfX", x)
                    intent.putExtra("listOfY", y)
                    intent.putExtra("dataInfo", dataInfo)
                    intent.putExtra("flag", "dataManage")
                    intent.setClass(this, MainActivity::class.java)
//                    if (CacheData.currentPickController == -1) {
//                        intent.putExtra("noControllerYet", "null")
//                    }
//                    if (numberOfPoints == 1000 || numberOfPoints == 1099) {
//                        CacheData.range = 1000
//                    } else if (numberOfPoints == 1100 || numberOfPoints == 1199) {
//                        CacheData.range = 2000
//                    } else {
//                        CacheData.range = 10000
//                    }
                    CacheData.lastOpenFile = currentFile
                    CacheData.lastOpenSub = currentSub
                    CacheData.lastOpenTrans = currentTrans
                    CacheData.lastOpenData = currentDate
                    startActivity(intent)
                } else {

                    Toast.makeText(this, "请选择要打开的文件", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private var adapterForSubstation: AdapterForDateReview? = null
    private var adapterForTransformer: AdapterForDateReview? = null
    private var adapterForDate: AdapterForDateReview? = null
    private var adapterForFile: AdapterForDateReview? = null
    private var currentSub: String? = null
    private var currentTrans: String? = null
    private var currentDate: String? = null
    private var currentFile: String? = null
    var openBtn: Button? = null
    var deleteBtn: Button? = null
    var backToLast: Button? = null
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent!!.adapter == adapterForSubstation) {
            currentSub = (adapterForSubstation!!.getItem(position) as String)
            adapterForSubstation!!.selectedPosition = position
            adapterForSubstation!!.notifyDataSetInvalidated()
            var listOfTrans: ArrayList<String> = DataManage.findTheDir(2, currentSub, null, null)
            adapterForTransformer = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfTrans)
            listViewForTransFormer!!.adapter = adapterForTransformer
            listViewForDate!!.adapter = null
            listViewForDate!!.invalidate()
            listViewForCSVData!!.adapter = null
            listViewForCSVData!!.invalidate()
        } else if (parent.adapter == adapterForTransformer) {
            adapterForTransformer!!.selectedPosition = position
            adapterForTransformer!!.notifyDataSetInvalidated()
            listViewForDate!!.adapter = null
            listViewForDate!!.invalidate()
            listViewForCSVData!!.adapter = null
            listViewForCSVData!!.invalidate()
            currentTrans = (adapterForTransformer!!.getItem(position) as String)
            var listOfDate: ArrayList<String> = DataManage.findTheDir(3, currentSub, currentTrans, null)
            adapterForDate = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfDate)
            listViewForDate!!.adapter = adapterForDate
        } else if (parent.adapter == adapterForDate) {

            adapterForDate!!.selectedPosition = position
            adapterForDate!!.notifyDataSetInvalidated()
            currentDate = adapterForDate!!.getItem(position) as String
            var listOfFile: ArrayList<String> = DataManage.findTheDir(4, currentSub, currentTrans, currentDate)
            var selectedBefore = -1
//            if (CacheData.currentPickController == 1) {
//                selectedBefore = listOfFile.indexOf(CacheData.lastOpenFile)
//            }
            adapterForFile = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfFile)
            listViewForCSVData!!.adapter = adapterForFile
            if (selectedBefore != -1) {
                adapterForFile!!.selectedPosition = selectedBefore
                currentFile = adapterForFile!!.getItem(position) as String
                adapterForFile!!.notifyDataSetInvalidated()
            }
        } else if (parent.adapter == adapterForFile) {
            adapterForFile!!.selectedPosition = position
            currentFile = adapterForFile!!.getItem(position) as String
            adapterForFile!!.notifyDataSetInvalidated()
        }
    }

    var listOfname: ArrayList<String>? = null
    var listViewForSubstation: ListView? = null
    var listViewForTransFormer: ListView? = null
    var listViewForDate: ListView? = null
    var listViewForCSVData: ListView? = null
    var linearLayoutForSubstation: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_manage_activity)
        findResource()
        initRes()
        MTPUtils.scanMtpAsync(this)
        if (CacheData.lastOpenData != null && CacheData.lastOpenFile != null && CacheData.lastOpenSub != null && CacheData.lastOpenTrans != null) {
            adapterForSubstation!!.selectedPosition = adapterForSubstation!!.names.indexOf(CacheData.lastOpenSub)
            currentSub = CacheData.lastOpenSub
            adapterForSubstation!!.notifyDataSetInvalidated()
            var listOfTrans: ArrayList<String> = DataManage.findTheDir(2, CacheData.lastOpenSub, null, null)
            adapterForTransformer = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfTrans)
            adapterForTransformer!!.selectedPosition = adapterForTransformer!!.names.indexOf(CacheData.lastOpenTrans)
            currentTrans = CacheData.lastOpenTrans
            listViewForTransFormer!!.adapter = adapterForTransformer
            var listOfDate: ArrayList<String> = DataManage.findTheDir(3, CacheData.lastOpenSub, CacheData.lastOpenTrans, null)
            adapterForDate = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfDate)
            adapterForDate!!.selectedPosition = adapterForDate!!.names.indexOf(CacheData.lastOpenData)
            currentDate = CacheData.lastOpenData
            listViewForDate!!.adapter = adapterForDate
            var listOfFile: ArrayList<String> = DataManage.findTheDir(4, CacheData.lastOpenSub, CacheData.lastOpenTrans, CacheData.lastOpenData)
            adapterForFile = AdapterForDateReview(this, R.layout.adapter_history_review, R.id.text1, listOfFile)
            adapterForFile!!.selectedPosition = listOfFile.indexOf(CacheData.lastOpenFile)
            currentFile = CacheData.lastOpenFile
            listViewForCSVData!!.adapter = adapterForFile
        }

    }

    private fun initRes() {
        listViewForSubstation = findViewById(R.id.listOfSubstation)
        listViewForTransFormer = findViewById(R.id.listOfTransformer)
        openBtn = findViewById(R.id.openTheFile)
        deleteBtn = findViewById(R.id.deleteTheFile)
        backToLast = findViewById(R.id.backToLast)
        listViewForDate = findViewById(R.id.listOfdate)
        listViewForCSVData = findViewById(R.id.listOfCSVData)
        openBtn!!.setOnClickListener(this)
        deleteBtn!!.setOnClickListener(this)
        backToLast!!.setOnClickListener(this)
        listViewForSubstation!!.onItemClickListener = this
        listViewForTransFormer!!.onItemClickListener = this
        listViewForDate!!.onItemClickListener = this
        listViewForCSVData!!.onItemClickListener = this
        adapterForSubstation = AdapterForDateReview(this,
                R.layout.adapter_history_review, R.id.text1, this.listOfname!!)
        listViewForSubstation!!.adapter = adapterForSubstation
        listViewForSubstation!!.divider = null
        listViewForCSVData!!.divider = null
        listViewForDate!!.divider = null
        listViewForTransFormer!!.divider = null
    }

    private fun findResource() {
        listOfname = DataManage.findTheDir(1, null, null, null)
    }
}