package com.pd.config.myapplication.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stormingshadow on 2017/11/23.
 */

public class CSVUtils {
    public static File createCSVFIle(List exportData, ArrayList<Map<String, String>> headOfTheExportData, ArrayList<Map<String, String>> endOfTheExportData, String outputPath, String filename) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {

            csvFile = new File(outputPath + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
            boolean newFile = csvFile.createNewFile();
            //使用GB2312使正确读取分隔符
            for (Iterator iterator = headOfTheExportData.iterator(); iterator.hasNext(); ) {
                LinkedHashMap rowMapper = (LinkedHashMap) iterator.next();

                for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator.hasNext(); ) {
                    Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
                    csvFileOutputStream.write(propertyEntry.getValue().toString());
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }

                }

                csvFileOutputStream.newLine();

            }
            //写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext(); ) {
                LinkedHashMap row = (LinkedHashMap) iterator.next();
                for (Iterator propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
                    Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
                    csvFileOutputStream.write(propertyEntry.getValue().toString());
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }

                csvFileOutputStream.newLine();

            }
            for (Iterator iterator = endOfTheExportData.iterator(); iterator.hasNext(); ) {
                LinkedHashMap row = (LinkedHashMap) iterator.next();
                for (Iterator propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
                    Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
                    csvFileOutputStream.write(propertyEntry.getValue().toString());
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }
}
