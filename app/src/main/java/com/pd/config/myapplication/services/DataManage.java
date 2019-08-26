package com.pd.config.myapplication.services;

import android.os.Environment;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;



public class DataManage {

    public static void makeDirs() {
        String path = Environment.getExternalStorageDirectory().getPath();
        path = path + File.separator + "/频响数据";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
            path = path + File.separator + "/高新变电站";
            file = new File(path);
            if (!file.exists()) {
                file.mkdir();
                path = path + File.separator + "/一号变压器";
                file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                    path = path + File.separator + "/20180516";
                    file = new File(path);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                }
            }
        }
    }

    public static ArrayList<String> findTheDir(int fileLevel, @Nullable String fileNameSub, @Nullable String fileNameTrans, @Nullable String fileDate) {
        ArrayList<String> listOfFiles = new ArrayList<>();
        switch (fileLevel) {
            case 1: {
                String path = Environment.getExternalStorageDirectory().getPath();
                path = path + File.separator + "频响数据";
                File file = new File(path);
                File[] files = file.listFiles();
               if(files!=null){
                   for (File file1 : files) {
                       listOfFiles.add(file1.getName());
                   }
               }

                break;
            }
            case 2: {
                String path = Environment.getExternalStorageDirectory().getPath();
                path = path + File.separator + "/频响数据";
                path = path + File.separator + fileNameSub;
                File file = new File(path);
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    listOfFiles.add(files[i].getName());
                }
                break;
            }
            case 3: {
                String path = Environment.getExternalStorageDirectory().getPath();
                path = path + File.separator + "/频响数据";
                path = path + File.separator + fileNameSub + File.separator + fileNameTrans;
                File file = new File(path);
                File[] files = file.listFiles();
                if (files.length != 0) {
                    for (int i = 0; i < files.length; i++) {
                        listOfFiles.add(files[i].getName());
                    }
                }
                break;
            }
            case 4: {
                String path = Environment.getExternalStorageDirectory().getPath();
                path = path + File.separator + "/频响数据";
                path = path + File.separator + fileNameSub + File.separator + fileNameTrans + File.separator + fileDate;
                File file = new File(path);
                boolean isFile =   file.isDirectory();
                File[] files = file.listFiles();
                if (files.length != 0) {
                    for (int i = 0; i < files.length; i++) {
                        listOfFiles.add(files[i].getName());
//                        switch (CacheData.typeOfTheRotate){
//                            case  Constants.HIFREQUENCY:
//                                if(files[i].getName().startsWith("H"))
//                                listOfFiles.add(files[i].getName());
//                                break;
//                            case Constants.MIDFREQUENCY:
//                                if(files[i].getName().startsWith("M"))
//                                    listOfFiles.add(files[i].getName());
//                                break;
//                                case Constants.LOWFREQUENCY:
//                                    if(files[i].getName().startsWith("L"))
//                                        listOfFiles.add(files[i].getName());
//                                    break;
//                            }
                        }
                    }
                }
                break;
            }
        return listOfFiles;
        }



    public static void deleteFile(String currentSub, @Nullable String currentTrans, @Nullable String currentDate, @Nullable String currentFile) {
        String path = Environment.getExternalStorageDirectory().getPath()+File.separator+"/频响数据" + File.separator + currentSub + File.separator + currentTrans + File.separator + currentDate+File.separator+currentFile;
        File file = new File(path);
        boolean isFile = file.isFile();
        if(isFile){
            file.delete();
        }

    }
    public static void deleteDirectory(@Nullable String currentSub, @Nullable String currentTrans, @Nullable String currentDate){
        String path = Environment.getExternalStorageDirectory().getPath()+File.separator+"/频响数据" + File.separator + currentSub + File.separator + currentTrans + File.separator + currentDate;
        File dir = new File(path);
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirectory(currentSub,currentTrans,currentDate); // 递规的方式删除文件夹
        }
        boolean isDir = dir.isDirectory();
        if(isDir){
            dir.delete();
        }

    }
    public static void deleteDirectory(@Nullable String currentSub, @Nullable String currentTrans){
        String path = Environment.getExternalStorageDirectory().getPath()+File.separator+"/频响数据" + File.separator + currentSub + File.separator + currentTrans;
        File dir = new File(path);
       if (dir.list().length>0){
           for (File file : dir.listFiles()) {
               if (file.isFile())
                   file.delete(); // 删除所有文件
               else if (file.isDirectory())
                   deleteDirectory(currentSub,currentTrans); // 递规的方式删除文件夹
           }
        }else {
           dir.delete();
       }

        boolean isDir = dir.isDirectory();
        if(isDir){
            dir.delete();
        }

    }
    public static void deleteDirectory(String path){
        File dir = new File(path);
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirectory(file.getPath()); // 递规的方式删除文件夹
        }
        boolean isDir = dir.isDirectory();
        if(isDir){
            dir.delete();
        }
    }
}
