package com.pd.config.myapplication.services;

import android.os.Environment;

import com.pd.config.myapplication.pojo.DataInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;



public class FileManager {
    public static String mkCSVFle(DataInfo dataInfo){
        mkDirs(dataInfo);
        return extractToCsv(dataInfo);
    }

    private static void mkDirs(DataInfo dataInfo) {
        File fileSubstation =new File(Environment.getExternalStorageDirectory()+"/频响数据"+dataInfo.getNameOfCompany());
        if(fileSubstation.exists()&&fileSubstation.isDirectory()){
            File fileTransforer = new File(Environment.getExternalStorageDirectory()+"/频响数据"+File.separator+dataInfo.getNameOfCompany()+File.separator+dataInfo.getNameOfTransformer());
            if(fileTransforer.exists()){
                File fileDate = new File(Environment.getExternalStorageDirectory()+"/频响数据"+File.separator+dataInfo.getNameOfCompany()+File.separator+dataInfo.getNameOfTransformer()+File.separator+dataInfo.getTestTime());
            }else {
                fileTransforer.mkdir();
                File fileDate = new File(Environment.getExternalStorageDirectory()+"/频响数据"+File.separator+dataInfo.getNameOfCompany()+File.separator+dataInfo.getNameOfTransformer()+File.separator+dataInfo.getTestTime());
                if(fileDate.exists()&&fileDate.isDirectory()){

                }else {
                    fileDate.mkdir();
                }
            }
        }else {
            fileSubstation.mkdir();
            File fileTransforer = new File(Environment.getExternalStorageDirectory()+"/频响数据"+File.separator+dataInfo.getNameOfCompany()+File.separator+dataInfo.getNameOfTransformer());
            fileTransforer.mkdir();
            File fileDate = new File(Environment.getExternalStorageDirectory()+"/频响数据"+File.separator+dataInfo.getNameOfCompany()+File.separator+dataInfo.getNameOfTransformer()+File.separator+dataInfo.getTestTime());
            fileDate.mkdir();
        }

    }

    private static String extractToCsv(DataInfo dataInfo) {
       dataInfo.setDataFile(findTheExists(dataInfo));
        ArrayList<Map<String,String>> body = new ArrayList<>();
        LinkedHashMap<String,String> pointsMap;
        for (int i=0;i<dataInfo.getValuesOfPointX().size();i++){
            pointsMap = new LinkedHashMap<>();
            pointsMap.put("1",dataInfo.getValuesOfPointX().get(i).toString());
            pointsMap.put("2",dataInfo.getValuesOfPointY().get(i).toString());
            pointsMap.put("3","0");
            body.add(pointsMap);
        }
        ArrayList<Map<String,String>> head = new ArrayList<>();
        ArrayList<Map<String,String>> bot = new ArrayList<>();
        LinkedHashMap<String,String> headMapRow01 = new LinkedHashMap<>();
        headMapRow01.put("1","kHz");
        headMapRow01.put("2","dB");
       if(dataInfo.getValuesOfPointX().size()==1000){
                   headMapRow01.put("3","1000");
                   headMapRow01.put("4","1000000");
                   headMapRow01.put("5","1000");
       }else if(dataInfo.getValuesOfPointX().size()==1100){
            headMapRow01.put("3","1000");
                   headMapRow01.put("4","2000000");
                   headMapRow01.put("5","1100");
       }else if(dataInfo.getValuesOfPointX().size()==1099){
        headMapRow01.put("3","10");
                   headMapRow01.put("4","1000000");
                   headMapRow01.put("5","1099");
       }else if(dataInfo.getValuesOfPointX().size()==1199){
            headMapRow01.put("3","10");
                   headMapRow01.put("4","2000000");
                   headMapRow01.put("5","1199");
       }else if(dataInfo.getValuesOfPointX().size()==1900){
            headMapRow01.put("3","1000");
                   headMapRow01.put("4","10000000");
                   headMapRow01.put("5","1900");
       }else if(dataInfo.getValuesOfPointX().size()==1999){
            headMapRow01.put("3","10");
                   headMapRow01.put("4","10000000");
                   headMapRow01.put("5","1999");
       }else if(dataInfo.getValuesOfPointX().size()==100){
           headMapRow01.put("3","1000");
           headMapRow01.put("4","100000");
           headMapRow01.put("5","100");
       }else if(dataInfo.getValuesOfPointX().size()==200){
           headMapRow01.put("3","10");
           headMapRow01.put("4","5000");
           headMapRow01.put("5","200");
       }

        head.add(headMapRow01);
        LinkedHashMap<String,String> headMapRow02 = new LinkedHashMap<>();
        headMapRow02.put("1", "");
        headMapRow02.put("2", "");
        headMapRow02.put("3", ";版本号");
        head.add(headMapRow02);
        LinkedHashMap<String,String> botMapRow01 = new LinkedHashMap<>();
        botMapRow01.put("1", "");
        botMapRow01.put("2", "");
        botMapRow01.put("3", ";"+dataInfo.getNameOfTransformer());
        LinkedHashMap<String,String> botMapRow02 = new LinkedHashMap<>();
        botMapRow02.put("1", "");
        botMapRow02.put("2", "");
        botMapRow02.put("3", ";"); //+dataInfo.getDataFile().substring(4,5)
        LinkedHashMap<String,String> botMapRow03 = new LinkedHashMap<>();
        botMapRow03.put("1", "");
        botMapRow03.put("2", "");
//        if (dataInfo.getPosition().equals("9a")||dataInfo.getPosition().equals("9b")||dataInfo.getPosition().equals("9c")) {
//            botMapRow03.put("3",";"+dataInfo.getPosition());
//        }else {
//            if(Integer.parseInt(dataInfo.getPosition())<10){
//                botMapRow03.put("3", ";0"+dataInfo.getPosition());
//            }else {
//                botMapRow03.put("3", ";"+dataInfo.getPosition());
//            }
//        }
        botMapRow03.put("3", ";"+"");
        LinkedHashMap<String,String> botMapRow04 = new LinkedHashMap<>();
        botMapRow04.put("1", "");
        botMapRow04.put("2", "");
        botMapRow04.put("3", ";"+dataInfo.getDataInput());
        LinkedHashMap<String,String> botMapRow05 = new LinkedHashMap<>();
        botMapRow05.put("1", "");
        botMapRow05.put("2", "");
        botMapRow05.put("3", ";"+dataInfo.getDataOutput());
        LinkedHashMap<String,String> botMapRow06 = new LinkedHashMap<>();
        botMapRow06.put("1", "");
        botMapRow06.put("2", "");
        botMapRow06.put("3", ";"+dataInfo.getTestSpecificTime());
        LinkedHashMap<String,String> botMapRow07 = new LinkedHashMap<>();
        botMapRow07.put("1", "");
        botMapRow07.put("2", "");
        botMapRow07.put("3", ";"+"备注信息");
        LinkedHashMap<String,String> botMapRow08 = new LinkedHashMap<>();
        botMapRow08.put("1", "");
        botMapRow08.put("2", "");
        botMapRow08.put("3", ";"+"");
        LinkedHashMap<String,String> botMapRow09 = new LinkedHashMap<>();
        botMapRow09.put("1", "");
        botMapRow09.put("2", "");
        botMapRow09.put("3", ";"+dataInfo.getNameOfCompany());
        bot.add(botMapRow01);
        bot.add(botMapRow02);
        bot.add(botMapRow03);
        bot.add(botMapRow04);
        bot.add(botMapRow05);
        bot.add(botMapRow06);
        bot.add(botMapRow07);
        bot.add(botMapRow08);
        bot.add(botMapRow09);
        CSVUtils.createCSVFIle(body,head,bot, Environment.getExternalStorageDirectory()+"/频响数据"+File.separator+dataInfo.getNameOfCompany()+File.separator+dataInfo.getNameOfTransformer()+File.separator+dataInfo.getTestTime()+File.separator+dataInfo.getDataFile() , dataInfo.getDataFile());
         return dataInfo.getDataFile();
    }

    private static String findTheExists(DataInfo dataInfo) {
        String dataFileName = "";
        ArrayList<String> nameOfFiles =new ArrayList<>();
        File fileDate = new File(Environment.getExternalStorageDirectory()+"/频响数据"+File.separator+dataInfo.getNameOfCompany()+File.separator+dataInfo.getNameOfTransformer()+File.separator+dataInfo.getTestTime());
             if(fileDate.exists()&&fileDate.isDirectory()){
                 String[] list = fileDate.list();
                 if(list.length==0){
                     return dataFileName=dataInfo.getDataFile()+"01";
                 }
                 nameOfFiles.addAll(Arrays.asList(list));
             }
             boolean complete = false;
             int i=1;
            while(!complete){

                 if(i<10){
                   dataFileName = dataInfo.getDataFile()+"0"+i;
                 }else {
                     dataFileName=dataInfo.getDataFile()+i;
                 }
              if(!nameOfFiles.contains(dataFileName+".csv")){
                   complete=true;
              }
                i++;
             }
        return dataFileName;
    }
}
