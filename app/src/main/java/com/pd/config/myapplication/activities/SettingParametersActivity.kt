package com.pd.config.myapplication.activities


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.pd.config.myapplication.R
import com.pd.config.myapplication.fragments.*
import com.pd.config.myapplication.pojo.ParameterSaver
import com.pd.config.myapplication.services.DataManage
import com.pd.config.myapplication.statics.CacheData


class SettingParametersActivity : FragmentActivity(), View.OnClickListener {
    var transformerLayout: LinearLayout? = null
    var rotateTypeLayout: LinearLayout? = null
    var positionLayout: LinearLayout? = null
    var inAndOutLayout: LinearLayout? = null
    var enhanceAttrLayout: LinearLayout? =null
    var scanAttr_InfoLayout: LinearLayout? = null
    private var m_objCurrentFragmnt: Fragment? = null
    //ensure and cancel button
    var ensureBtn: Button? = null
    var cancelBtn: Button? = null
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.transformer_layout -> {
                showTheSelectOneToBlue(1)
                setFragment(1)
                CacheData.pickedLayout=1

            }
            R.id.rotateTypeLayout -> {
                showTheSelectOneToBlue(2)
                setFragment(2)
                CacheData.pickedLayout=2
            }
            R.id.positionLayout -> {
                showTheSelectOneToBlue(3)
                setFragment(3)
                CacheData.pickedLayout=3
            }

            R.id.inputAndOutput -> {
                showTheSelectOneToBlue(4)
                setFragment(4)
                CacheData.pickedLayout=4
            }
            R.id.scanAttr_InfoLayout -> {
                showTheSelectOneToBlue(5)
                setFragment(5)
                CacheData.pickedLayout=5
            }
            R.id.cancel-> {
                this.finish()
            }
      R.id.confirm->{
          val substation = findViewById<TextView>(R.id.nameOfSubstation)
          val transformer = findViewById<TextView>(R.id.nameOfTheTransformer)
          val position = findViewById<TextView>(R.id.positions)
          val typeOfRoatate =  findViewById<TextView>(R.id.rotateType)
          val input = findViewById<TextView>(R.id.input)
          val output = findViewById<TextView>(R.id.output)
          val range = findViewById<TextView>(R.id.frequency)
          val points = findViewById<TextView>(R.id.numberOfPointss)
          val mode = findViewById<TextView>(R.id.scanMode)
          val parameters = ParameterSaver(substation.text.toString(),transformer.text.toString(),typeOfRoatate.text.toString(),position.text.toString(),input.text.toString(),output.text.toString(), range.text.toString(),points.text.toString(),mode.text.toString())
          val split = CacheData.cacheFormBean.range.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
          if(parameters.typeOfScan.equals("其他设备")){
              parameters.begin =   split[0].split("k")[0].trim().toInt()
              parameters.end = split[1].split("k")[0].trim().toInt()
              parameters.numberOfPoint = CacheData.cacheFormBean.point
          }

          var intent = Intent()
          intent.putExtra("parameter",parameters)
          intent.putExtra("flag","startTest")
          intent.setClass(this,MainActivity::class.java)
          if(substation.text==""||transformer.text==""){
              Toast.makeText(this,"请确定填写变压器和变电站名称",Toast.LENGTH_LONG).show()
          }else if(input.text.split("-")[0]==output.text.split("-")[0]){
              Toast.makeText(this,"输入端输出端参数不可一直请确认!",Toast.LENGTH_LONG).show()
          }
          else{
              startActivity(intent)
          }
      }
        }
    }

    private fun setFragment(id: Int) {
        val fm = supportFragmentManager
        val fragments = fm.fragments
        if (fragments != null) {
            for (fragment in fragments) {
                if (fragment != null && fragment.isVisible)
                    m_objCurrentFragmnt = fragment
            }
        }
        when (id) {
            1 -> {
                val FragmentTransaction = fm.beginTransaction()
                FragmentTransaction.replace(R.id.rightLayout, transformerPickFragment())
                FragmentTransaction!!.commit()
            }
            2->{
                val FragmentTransaction = fm.beginTransaction()
                FragmentTransaction.replace(R.id.rightLayout, TypeOfRoatateSetFrag())
                FragmentTransaction!!.commit()
            }
            3->{
                val FragmentTransaction = fm.beginTransaction()
                FragmentTransaction.replace(R.id.rightLayout, SetDistributeFragment())
                FragmentTransaction!!.commit()
            }
            4->{
                val FragmentTransaction = fm.beginTransaction()
                FragmentTransaction.replace(R.id.rightLayout, InAndOutputSetFragment())
                FragmentTransaction!!.commit()
            }
            5->{
                val FragmentTransaction = fm.beginTransaction()
                FragmentTransaction.replace(R.id.rightLayout, Scan_frequencyAndMode())
                FragmentTransaction!!.commit()
            }

        }
    }

    private fun showTheSelectOneToBlue(number: Int) {
        when (number) {
            1 -> {
                transformerLayout!!.background = getDrawable(R.drawable.shape_settig)
                rotateTypeLayout!!.background = null
                positionLayout!!.background = null
                inAndOutLayout!!.background = null
                scanAttr_InfoLayout!!.background = null
               /* transformerLayout!!.setPadding(5, 5, 5, 5)*/

             /*   transformerLayout!!.background = getDrawable(R.drawable.linear_layout_shadow)
                transformerLayout!!.elevation = 20.0f
                transformerLayout!!.translationZ=20.0f*/

            }
            2 -> {
                transformerLayout!!.background = null
                rotateTypeLayout!!.background = getDrawable(R.drawable.shape_settig)
                positionLayout!!.background = null
                inAndOutLayout!!.background = null
                scanAttr_InfoLayout!!.background = null
            }
            3 -> {
                transformerLayout!!.background = null
                rotateTypeLayout!!.background = null
                positionLayout!!.background = getDrawable(R.drawable.shape_settig)
                inAndOutLayout!!.background = null
                scanAttr_InfoLayout!!.background = null
            }
            4 -> {
                transformerLayout!!.background = null
                rotateTypeLayout!!.background = null
                positionLayout!!.background = null
                inAndOutLayout!!.background = getDrawable(R.drawable.shape_settig)
                scanAttr_InfoLayout!!.background = null
            }
            5 -> {
                transformerLayout!!.background = null
                rotateTypeLayout!!.background = null
                positionLayout!!.background = null
                inAndOutLayout!!.background = null
                scanAttr_InfoLayout!!.background = getDrawable(R.drawable.shape_settig)
            }
            6 -> {

            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataManage.makeDirs()
        setContentView(R.layout.activity_set_test_params)
        initView()
        initViewFun()
        setFirstFragment()
        initFromCache()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.navigationBarColor = getColor(R.color.title)
//            //getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
//            //getWindow().setNavigationBarColor(Color.BLUE);
//        }
    }

    private fun initFromCache() {

        when(CacheData.pickedLayout){
            1->{
                showTheSelectOneToBlue(1)
                setFragment(1)
            }
            2->{
                showTheSelectOneToBlue(2)
                setFragment(2)
            }
            3->{
                showTheSelectOneToBlue(3)
                setFragment(3)
            }
            4->{
                showTheSelectOneToBlue(4)
                setFragment(4)
            }
            5->{
                showTheSelectOneToBlue(5)
                setFragment(5)
            }
            6->{
                showTheSelectOneToBlue(6)
                setFragment(6)
            }

        }
        val substation = findViewById<TextView>(R.id.nameOfSubstation)
        val transformer = findViewById<TextView>(R.id.nameOfTheTransformer)
        val position = findViewById<TextView>(R.id.positions)
        val typeOfRoatate =  findViewById<TextView>(R.id.rotateType)
        val input = findViewById<TextView>(R.id.input)
        val output = findViewById<TextView>(R.id.output)
        val range = findViewById<TextView>(R.id.frequency)
        val points = findViewById<TextView>(R.id.numberOfPointss)
        val mode = findViewById<TextView>(R.id.scanMode)

        substation.text = CacheData.cacheFormBean.substation
        transformer.text = CacheData.cacheFormBean.transformer
        position.text = CacheData.cacheFormBean.position
        typeOfRoatate.text=CacheData.cacheFormBean.typeOfRotate
        input.text=CacheData.cacheFormBean.input
        output.text=CacheData.cacheFormBean.output
        range.text=CacheData.cacheFormBean.range
        points.text=CacheData.cacheFormBean.point.toString()+"点"
        mode.text = CacheData.cacheFormBean.mode
    }

    private fun setFirstFragment() {
        val fm = supportFragmentManager
        val fragments = fm.fragments
        val FragmentTransaction = fm.beginTransaction()
        FragmentTransaction.replace(R.id.rightLayout, transformerPickFragment())
        FragmentTransaction!!.commit()
    }

    private fun initViewFun() {
        transformerLayout!!.setOnClickListener(this)
        rotateTypeLayout!!.setOnClickListener(this)
        positionLayout!!.setOnClickListener(this)
        inAndOutLayout!!.setOnClickListener(this)
         scanAttr_InfoLayout!!.setOnClickListener(this)

    }

    private fun initView() {
        cancelBtn = findViewById(R.id.cancel)
        ensureBtn = findViewById(R.id.confirm)
        cancelBtn!!.setOnClickListener(this)
        ensureBtn!!.setOnClickListener(this)
        transformerLayout = findViewById(R.id.transformer_layout)
        rotateTypeLayout = findViewById(R.id.rotateTypeLayout)
        positionLayout = findViewById(R.id.positionLayout)
        inAndOutLayout = findViewById(R.id.inputAndOutput)
        scanAttr_InfoLayout = findViewById(R.id.scanAttr_InfoLayout)
        cancelBtn = findViewById(R.id.cancelBtn)
    }


}