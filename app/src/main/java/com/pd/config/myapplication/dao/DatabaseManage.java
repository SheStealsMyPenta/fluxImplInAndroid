package com.pd.config.myapplication.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by Administrator on 2017/11/22 0022.
 */

public class DatabaseManage extends SQLiteOpenHelper {
    public DatabaseManage(Context context) {
            super(context,  Environment.getExternalStorageDirectory().getAbsolutePath()+"/thedatas/"+"IT_DATA", null, 56);
    }
    public DatabaseManage(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("create table if not exists transformer_info(" +
             "id integer primary key autoincrement," +
             "nameOfTheSubstation varchar(51)," +  //变电站名称
             "nameOfTheInstrument varchar(50)," +   //变电器名称
             "typeOfTheInstrument varchar(50)," +   //变电器类型
             "numOfProduction varchar(50),"    +   // 出厂序号
             "phase varchar(30)," +                 //相数
             "numberOfRotate varchar(30)," +        //绕组数
             "nameOfTheCompany varchar(30)," +       //制造厂家
             "dateOfCreation varchar(30)," +//制造时间
             "photoUrl varchar(150))");//图片地址

     db.execSQL("create table if not exists transformer_data(" +
             "id integer primary key autoincrement," +
             "data_file varchar(20)," +  //文件数据
             "data_format varchar(20),"+  //文件格式
             "data_Input varchar(20)," +    //输入端
             "data_Output varchar(20)," +   //输出端
             "test_frequency varchar(30)," + //测试频率
             "testRound int(4)," +
             "transformer_id int(4)," +   //外键变电器id
             "testTime varchar(20)," +  //测试时间
             "testDate varcahr(20)," +  //测试日期
             "typeOfRotate varchar(20)," +//绕组方式 1，高压绕组 2，中压绕组， 3，低压绕组
             "tester varchar(20)," +   //测试人员
             "testTemporature varchar(20)," + //测试温度
             "testType varchar(20))"); //测试类型
          //测试时期
     db.execSQL("create table if not exists transformer_frequency_dbValue(" +
              "id integer  primary key autoincrement," +
              "frequency blob,"+
              "pointsValue blob," +
              "data_id int(4))"); //外键
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists transformer_data");
        db.execSQL("drop table if exists transformer_frequency_dbValue");
        db.execSQL("drop table if exists transformer_info");
        this.onCreate(db);
/*
        String sql = "drop table if exist transformer_frequency_dbValue";
              db.execSQL(sql);
              this.onCreate(db);*/
    }
}
