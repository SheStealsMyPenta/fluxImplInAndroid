package com.pd.config.myapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.pd.config.myapplication.pojo.DataInfo;
import com.pd.config.myapplication.pojo.TransformerBean;
import com.pd.config.myapplication.statics.CacheData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



/**
 * Created by stormingshadow on 2017/11/22.
 */

public class DaoImpl {

    private DatabaseManage manager;
    private SQLiteDatabase sdb;
    private TransformerBean info;

    public DaoImpl(Context context) {
        manager = new DatabaseManage(context);
    }

    public ArrayList<TransformerBean> findTransformers(String nameOfTheSubstation) {
        sdb = manager.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select * from transformer_info where nameOfTheSubstation ='" + nameOfTheSubstation + "'", null);
        ArrayList<TransformerBean> list = new ArrayList<TransformerBean>();
        while (cursor.moveToNext()) {
            info = new TransformerBean();
            info.setId(cursor.getInt(cursor.getColumnIndex("id")));
            info.setNameOfTheTransformer(cursor.getString(cursor.getColumnIndex("nameOfTheInstrument")));
            String a = info.getNameOfTheTransformer();
            System.out.println(info.getNameOfTheTransformer());
            info.setTypeOfTheTransformer(cursor.getString(cursor.getColumnIndex("typeOfTheInstrument")));
            info.setNameOfTheSubstation(cursor.getString(cursor.getColumnIndex("nameOfTheSubstation")));
            info.setNameOfCompany(cursor.getString(cursor.getColumnIndex("nameOfTheCompany")));
            info.setNumberOfRotate(cursor.getString(cursor.getColumnIndex("numberOfRotate")));
            info.setDateOfCreation(cursor.getString(cursor.getColumnIndex("dateOfCreation")));
            info.setPhase(cursor.getString(cursor.getColumnIndex("phase")));
            info.setNumOfCreation(cursor.getString(cursor.getColumnIndex("numOfProduction")));
            info.setUrl(cursor.getString(cursor.getColumnIndex("photoUrl")));
            list.add(info);
        }
        return list;
    }
//nameOfTheSubstation ='" + nameOfTheSubstation + "'" + " and
    public TransformerBean findSpecificTransformer(String nameOfTheSubstation, String nameOfTransFormer) {
        sdb = manager.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select * from transformer_info where  nameOfTheInstrument = '" + nameOfTransFormer + "'", null);
       if (cursor.moveToNext()) {
            info = new TransformerBean();
            info.setId(cursor.getInt(cursor.getColumnIndex("id")));
            info.setNameOfTheTransformer(cursor.getString(cursor.getColumnIndex("nameOfTheInstrument")));
            String a = info.getNameOfTheTransformer();
            System.out.println(info.getNameOfTheTransformer());
            info.setTypeOfTheTransformer(cursor.getString(cursor.getColumnIndex("typeOfTheInstrument")));
            info.setNameOfTheSubstation(cursor.getString(cursor.getColumnIndex("nameOfTheSubstation")));
            info.setNameOfCompany(cursor.getString(cursor.getColumnIndex("nameOfTheCompany")));
            info.setNumberOfRotate(cursor.getString(cursor.getColumnIndex("numberOfRotate")));
            info.setDateOfCreation(cursor.getString(cursor.getColumnIndex("dateOfCreation")));
            info.setPhase(cursor.getString(cursor.getColumnIndex("phase")));
            info.setNumOfCreation(cursor.getString(cursor.getColumnIndex("numOfProduction")));
            info.setUrl(cursor.getString(cursor.getColumnIndex("photoUrl")));
            return info;
        }else{
            return  new TransformerBean();
        }

    }

    /***
     * 查找特定的变压器
     *
     * @param transformerBean
     */
    /*
     * 添加一个变压器
     * */
    public void addATransformer(TransformerBean transformerBean) {
        StringBuilder builder = new StringBuilder();
        if (transformerBean.getNameOfTheSubstation() == null) {
            transformerBean.setNameOfTheSubstation("null");
        }
        if (transformerBean.getNameOfTheTransformer() == null) {
            transformerBean.setNameOfTheTransformer("null");
        }
        if (transformerBean.getTypeOfTheTransformer() == null) {
            transformerBean.setTypeOfTheTransformer("null");
        }
        if (transformerBean.getNumOfCreation() == null) {
            transformerBean.setNumOfCreation("null");
        }
        if (transformerBean.getPhase() == null) {
            transformerBean.setPhase("null");
        }
        if (transformerBean.getNumberOfRotate() == null) {
            transformerBean.setNumberOfRotate("null");
        }
        if (transformerBean.getNameOfCompany() == null) {
            transformerBean.setNameOfCompany("null");
        }
        if (transformerBean.getDateOfCreation() == null) {
            transformerBean.setDateOfCreation("null");
        }
        if (transformerBean.getUrl() == null) {
            transformerBean.setUrl("null");
        }
        builder.append("insert into transformer_info(nameOfTheSubstation,nameOfTheInstrument,typeOfTheInstrument,numOfProduction" +
                ",phase,numberOfRotate,nameOfTheCompany,dateOfCreation,photoUrl) values");
        String format = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s')", transformerBean.getNameOfTheSubstation(), transformerBean.getNameOfTheTransformer(), transformerBean.getTypeOfTheTransformer(), transformerBean.getNumOfCreation(), transformerBean.getPhase(), transformerBean.getNumberOfRotate(), transformerBean.getNameOfCompany(), transformerBean.getDateOfCreation(), transformerBean.getUrl());
        builder.append(format);
        sdb = manager.getWritableDatabase();
        sdb.execSQL(builder.toString());
    }


    public void exportToExternalStorage() {
        System.out.println(Environment.getExternalStorageDirectory());
    }

    /*
     * 找到所有的变电站
     *
     * */
    public List<String> findSubstations() {

        sdb = manager.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select distinct nameOfTheSubstation from transformer_info", null);
        List<String> list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("nameOfTheSubstation")));
        }
        cursor.close();
        return list;

    }

    /*
     * 通过变压器的名字和变电站的名字找到变压器的详细信息
     * */
    public TransformerBean findDetails(String name, String nameOfTheSubstation) {
        sdb = manager.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select * from transformer_info where nameOfTheInstrument='" + name + "'" + " and " + "nameOfTheSubstation='" + nameOfTheSubstation + "'", null);
        cursor.moveToFirst();
        TransformerBean info = new TransformerBean();
        info.setNameOfTheTransformer(cursor.getString(cursor.getColumnIndex("nameOfTheInstrument")));
        String a = info.getNameOfTheTransformer();
        System.out.println(info.getNameOfTheTransformer());
        info.setNameOfTheSubstation(cursor.getString(cursor.getColumnIndex("nameOfTheSubstation")));
        info.setNameOfCompany(cursor.getString(cursor.getColumnIndex("nameOfTheCompany")));
        info.setNumberOfRotate(cursor.getString(cursor.getColumnIndex("numberOfRotate")));
        info.setDateOfCreation(cursor.getString(cursor.getColumnIndex("dateOfCreation")));
        info.setPhase(cursor.getString(cursor.getColumnIndex("phase")));
        info.setNumOfCreation(cursor.getString(cursor.getColumnIndex("numOfProduction")));
        info.setTypeOfTheTransformer(cursor.getString(cursor.getColumnIndex("typeOfTheInstrument")));
        info.setUrl(cursor.getString(cursor.getColumnIndex("photoUrl")));
        info.setId(cursor.getInt(cursor.getColumnIndex("id")));
        cursor.close();
        return info;
    }

    /*通过变压器的名字找到所对应的数据，并且通过时间分组显示*/
    public List<String> findThoseDate(String nameOfTheTransformer, String nameOfTheSubstation) {
        List<String> testTimes = new ArrayList<>();
        sdb = manager.getReadableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("select id from transformer_info where nameOfTheInstrument = ");
        String append1 = String.format("'%s'", nameOfTheTransformer);
        builder.append(append1);
        builder.append(" and nameOfTheSubstation =");
        builder.append(String.format("'%s'", nameOfTheSubstation));
        String toString = builder.toString();
        Cursor cursor1 = sdb.rawQuery(toString, null);
        cursor1.moveToFirst();
        int anInt = cursor1.getInt(0);
        StringBuilder builder1 = new StringBuilder();
        builder1.append("select testDate from transformer_data where transformer_id =");
        builder1.append(String.format("%d", anInt));
        builder1.append(" group by testDate");
        String theQuery = builder1.toString();
        Cursor cursor2 = sdb.rawQuery(theQuery, null);
        /* cursor2.moveToFirst();*/
        while (cursor2.moveToNext()) {
            String testDate = cursor2.getString(cursor2.getColumnIndex("testDate"));
            testTimes.add(testDate);
        }
        return testTimes;
    }

    /*
     * 绕组类型对所有数据进行筛选和归类并且显示到适配器里
     * */
    public List<String> findTestInfoGroupByDate(String typeOfRotate) {
        List<String> testTimes = new ArrayList<>();
        sdb = manager.getReadableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("select *from transformer_data where typeOfRotate = '");
        builder.append(typeOfRotate);
        builder.append("'");
        builder.append(" group by testDate");
        Cursor cursor = sdb.rawQuery(builder.toString(), null);
        while (cursor.moveToNext()) {
            String testDate = cursor.getString(cursor.getColumnIndex("testDate"));
            testTimes.add(testDate);
        }
        return testTimes;
    }

    public void deleteThisTransformer(String nameOfTheTransformer, String nameOfTheSubstation) {
        sdb = manager.getWritableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("delete from transformer_info where nameOfTheInstrument =");
        builder.append(String.format("'%s'", nameOfTheTransformer));
        builder.append(" and nameOfTheSubstation =");
        builder.append(String.format("'%s'", nameOfTheSubstation));
        String string = builder.toString();
        sdb.execSQL(string);

    }

    public void addPointsList(byte[] byteValuesForX, byte[] byteValuesForY, int idOfTheTransformer) {
        sdb = manager.getWritableDatabase();
        /*     StringBuilder builder = new StringBuilder();
         *//*ContentValues contentValues = new ContentValues();
        contentValues.put("frequency",byteValuesForX);
        contentValues.put("pointsValues",byteValuesForY);
        sdb.insert("transformer_frequency_dbValue",null,contentValues);*//*
     builder.append(byteValuesForX+",");
     builder.append(byteValuesForY+")");*/
        String sqlstr = "insert into transformer_frequency_dbValue(frequency,pointsValue,data_id) values(?,?,?)";
        Object[] args = new Object[]{byteValuesForX, byteValuesForY, idOfTheTransformer};
        sdb.execSQL(sqlstr, args);
       /* Set<Float> floats = map.keySet();
        Iterator<Float> iterator = floats.iterator();
        if(iterator.hasNext()){
            Float next = iterator.next();
            Float aFloat = map.get(next);
            builder.append("('");
            builder.append(next+"','");
            builder.append(aFloat+"'),");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        String addPointsList = builder.toString();
        sdb.execSQL(addPointsList);*/
    }

    public Map<byte[], byte[]> getPointsValueByTransformerId(int id) {
        Map<byte[], byte[]> map = new HashMap<>();
        sdb = manager.getReadableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("select*from transformer_frequency_dbValue where data_id = ");
        builder.append(id);
        Object[] args = new Object[]{id};
        String sentence = builder.toString();

        Cursor cursor = sdb.rawQuery(sentence, null);
        if (cursor.moveToNext()) {
            byte[] valuesForX = cursor.getBlob(cursor.getColumnIndex("frequency"));
            byte[] valuesForY = cursor.getBlob(cursor.getColumnIndex("pointsValue"));
            map.put(valuesForX, valuesForY);
        }
        return map;
    }

    public boolean theSpecificTrans(@NotNull String nameOfTheTransformer, @NotNull String nameOfTheSubstation) {
        sdb = manager.getReadableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("select*from transformer_info where nameOfTheSubstation = ");
        builder.append("'" + nameOfTheSubstation + "'");
        builder.append(" and nameOfTheInstrument = ");
        builder.append("'" + nameOfTheTransformer + "'");
        String s = builder.toString();
        Cursor cursor = sdb.rawQuery(s, null);
        return cursor.moveToNext();

    }

    public int theidOfTrans(@NotNull String nameOfTheTransformer, @NotNull String nameOfTheSubstation) {
        sdb = manager.getReadableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("select*from transformer_info where nameOfTheSubstation = ");
        builder.append("'" + nameOfTheSubstation + "'");
        builder.append(" and nameOfTheInstrument = ");
        builder.append("'" + nameOfTheTransformer + "'");
        String s = builder.toString();
        Cursor cursor = sdb.rawQuery(s, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("id"));
    }

//    public void addAInfoIntoTable(@NotNull DataInfo info) {
//        sdb = manager.getWritableDatabase();
//        /*Calendar instance = Calendar.getInstance();
//        Date d = instance.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
//        String dateNowStr= df.format(d);
//        String timeNowStr = sdf.format(d);
//        info.setTestTime(timeNowStr);
//        info.setTestDate(dateNowStr);*/
//        String sql = "insert into transformer_data(data_file,data_format,data_Input,data_Output,test_frequency,transformer_id,testTime,testDate,typeOfRotate,tester,testTemporature,testType) values(?,?,?,?,?,?,?,?,?,?,?,?)";
//        Object[] args = new Object[]{info.getDataFile(), info.getDataFormat(), info.getDataInput(), info.getDataOutput(), info.getTestFrequence(), info.getTransformerId(), info.getTestTime(), info.getTestDate(), info.getTypeOfRotate(), info.getTester(), info.getTemporeture(), info.getTypeOfTest()};
//        sdb.execSQL(sql, args);
//    }

    public int findLastInsertId() {
        sdb = manager.getReadableDatabase();
        String sql = "select last_insert_rowId() from transformer_data";
        Cursor cursor = sdb.rawQuery(sql, null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    //模糊查询查找一个数据文件的文件名的个数
    public int findNumberOfCommoinDataFile(String testDate, String dataInput, String dataOutput) {
        sdb = manager.getReadableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("select count(data_file) from transformer_data where data_input='");
        builder.append(dataInput);
        builder.append("' and data_output ='");
        builder.append(dataOutput);

        builder.append("' AND testDate = '");
        builder.append(testDate);
        builder.append("'");
        String theSql = builder.toString();
        Cursor cursor = sdb.rawQuery(theSql, null);
        int result = 0;

        if (cursor.moveToFirst()) {
            result = cursor.getInt(0);
        }
        return result;
    }

    //通过id找变压器
    private TransformerBean findTransformerById(int id) {
        TransformerBean bean = new TransformerBean();
        sdb = manager.getReadableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("select * from transformer_info where id = ");
        builder.append(id);
        Cursor cursor = sdb.rawQuery(builder.toString(), null);
        while (cursor.moveToNext()) {
            bean.setNameOfTheSubstation(cursor.getString(cursor.getColumnIndex("nameOfTheSubstation")));
            bean.setNameOfTheTransformer(cursor.getString(cursor.getColumnIndex("nameOfTheInstrument")));
        }
        return bean;
    }

//    public ArrayList<DataInfo> findDataByDateAndTypeOfRotate(@NotNull String testDate, String typeOfRotate, ArrayList<Integer> listOfId) {
//        ArrayList<DataInfo> infos = new ArrayList<>();
//        sdb = manager.getReadableDatabase();
//        StringBuilder builder = new StringBuilder();
//        builder.append("select*from transformer_data where testDate = '");
//        builder.append(testDate);
//        builder.append("'");
//        builder.append(" and typeOfRotate = '");
//        builder.append(typeOfRotate);
//        builder.append("'");
//        if (listOfId != null) {
//            Iterator<Integer> iterator = listOfId.iterator();
//            while (iterator.hasNext()) {
//                builder.append(" and id != ");
//                builder.append(iterator.next());
//            }
//        }
//        if (CacheData.typeOfTheRotate.equals("高压绕组")) {
//            Iterator<Integer> iterator = CacheData.listOfIdForHiFre.iterator();
//            while (iterator.hasNext()) {
//                builder.append(" and id != ");
//                builder.append(iterator.next());
//            }
//        } else if (CacheData.typeOfTheRotate.equals("中压绕组")) {
//            Iterator<Integer> iterator = CacheData.listOfIdForMidFre.iterator();
//            while (iterator.hasNext()) {
//                builder.append(" and id != ");
//                builder.append(iterator.next());
//            }
//        } else {
//            Iterator<Integer> iterator = CacheData.listOfIdForLowFre.iterator();
//            while (iterator.hasNext()) {
//                builder.append(" and id != ");
//                builder.append(iterator.next());
//            }
//        }
//        Cursor cursor = sdb.rawQuery(builder.toString(), null);
//        while (cursor.moveToNext()) {
//            DataInfo aInfo = new DataInfo();
//            int id = cursor.getInt(cursor.getColumnIndex("transformer_id"));
//            TransformerBean bean = findTransformerById(id);
//            aInfo.setNameOfCompany(bean.getNameOfTheSubstation());
//            aInfo.setNameOfTheTransformer(bean.getNameOfTheTransformer());
//            aInfo.setTestTime(cursor.getString(cursor.getColumnIndex("testTime")));
//            aInfo.setTestDate(cursor.getString(cursor.getColumnIndex("testDate")));
//            aInfo.setDataFile(cursor.getString(cursor.getColumnIndex("data_file")));
//            aInfo.setDataFormat(cursor.getString(cursor.getColumnIndex("data_format")));
//            aInfo.setDataInput(cursor.getString(cursor.getColumnIndex("data_Input")));
//            aInfo.setDataOutput(cursor.getString(cursor.getColumnIndex("data_Output")));
//            aInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
//            aInfo.setTester(cursor.getString(cursor.getColumnIndex("tester")));
//            aInfo.setTestFrequence(cursor.getString(cursor.getColumnIndex("test_frequency")));
//            aInfo.setTemporeture(cursor.getString(cursor.getColumnIndex("testTemporature")));
//            aInfo.setTypeOfTest(cursor.getString(cursor.getColumnIndex("testType")));
//            infos.add(aInfo);
//        }
//        return infos;
//    }

    public void findPointsById(int id) {

    }

    public void deleteALl() {
        sdb = manager.getWritableDatabase();
        sdb.execSQL("delete  from transformer_data");
        sdb.execSQL("delete  from transformer_info");
        sdb.execSQL("delete  from transformer_frequency_dbValue");
    }

    @Nullable
    public ArrayList<TransformerBean> findAllTransformers() {
        sdb = manager.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select * from transformer_info", null);
        ArrayList<TransformerBean> list = new ArrayList<TransformerBean>();
        while (cursor.moveToNext()) {
            info = new TransformerBean();
            info.setNameOfTheTransformer(cursor.getString(cursor.getColumnIndex("nameOfTheInstrument")));
            String a = info.getNameOfTheTransformer();
            System.out.println(info.getNameOfTheTransformer());
            info.setId(cursor.getInt(cursor.getColumnIndex("id")));
            info.setTypeOfTheTransformer(cursor.getString(cursor.getColumnIndex("typeOfTheInstrument")));
            info.setNameOfTheSubstation(cursor.getString(cursor.getColumnIndex("nameOfTheSubstation")));
            info.setNameOfCompany(cursor.getString(cursor.getColumnIndex("nameOfTheCompany")));
            info.setNumberOfRotate(cursor.getString(cursor.getColumnIndex("numberOfRotate")));
            info.setDateOfCreation(cursor.getString(cursor.getColumnIndex("dateOfCreation")));
            info.setPhase(cursor.getString(cursor.getColumnIndex("phase")));
            info.setNumOfCreation(cursor.getString(cursor.getColumnIndex("numOfProduction")));
            info.setUrl(cursor.getString(cursor.getColumnIndex("photoUrl")));
            list.add(info);
        }
        return list;
    }

    public void changeToTheOtherOne(TransformerBean bean) {
        sdb = manager.getWritableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("update transformer_info set nameOfTheInstrument='");
        builder.append(bean.getNameOfTheTransformer() + "', nameOfTheSubstation='");
        builder.append(bean.getNameOfTheSubstation() + "', nameOfTheCompany='");
        builder.append(bean.getNameOfCompany() + "', numberOfRotate='");
        builder.append(bean.getNumberOfRotate() + "', dateOfCreation='");
        builder.append(bean.getDateOfCreation() + "', phase='");
        builder.append(bean.getPhase() + "',numOfProduction='");
        builder.append(bean.getNumOfCreation() + "', photoUrl = '");
        builder.append(bean.getUrl() + "',typeOfTheInstrument='");
        builder.append(bean.getTypeOfTheTransformer() + "' where id =");
        builder.append(bean.getId());
        String sql = builder.toString();
        sdb.execSQL(sql);
    }

    public List<String> findTestInfoGroupByDateAndNames(String typeOfRotate, String nameOfTransformer, String nameOfSubstation) {
        List<String> testTimes = new ArrayList<>();
        sdb = manager.getReadableDatabase();
        Cursor cursor1 = sdb.rawQuery("select id from transformer_info where nameOfTheInstrument = '" + nameOfTransformer + "' and nameOfTheSubstation= '" + nameOfSubstation + "'", null);
        boolean a = cursor1.moveToFirst();
        int anInt = cursor1.getInt(0);
        StringBuilder builder = new StringBuilder();
        builder.append("select *from transformer_data where typeOfRotate = '");
        builder.append(typeOfRotate);
        builder.append("'");
        builder.append(" and transformer_id ==");
        builder.append(anInt);
        builder.append(" group by testDate");
        Cursor cursor = sdb.rawQuery(builder.toString(), null);
        while (cursor.moveToNext()) {
            String testDate = cursor.getString(cursor.getColumnIndex("testDate"));
            testTimes.add(testDate);
        }
        return testTimes;
    }

    public void deleInfosById(@NotNull ArrayList<Integer> arrayList) {
        sdb = manager.getWritableDatabase();
        StringBuilder builder = new StringBuilder();
        builder.append("delete from transformer_data where id in (");
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == arrayList.size() - 1) {
                builder.append(arrayList.get(i) + ")");
            } else {
                builder.append(arrayList.get(i) + ",");
            }

        }
        sdb.execSQL(builder.toString());
        StringBuilder builder1 = new StringBuilder();
        builder1.append("delete from transformer_frequency_dbValue where data_id in (");
        for (int j = 0; j < arrayList.size(); j++) {
            if (j == arrayList.size() - 1) {
                builder1.append(arrayList.get(j) + ")");
            } else {
                builder1.append(arrayList.get(j) + ",");
            }
        }
        sdb.execSQL(builder1.toString());
    }

    public void findAllSubstation() {
    }
}
