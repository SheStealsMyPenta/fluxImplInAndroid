package com.pd.config.myapplication.services;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by SONG on 2018/5/3 13:50.
 * The final explanation right belongs to author
 */
public class MTPUtils {
    private static final String ACTION_MEDIA_SCANNER_SCAN_DIR = "android.intent.action.MEDIA_SCANNER_SCAN_DIR";

    /**
     * Intent.ACTION_MEDIA_SCANNER_SCAN_FILE：扫描指定文件
     *
     * @param context  Context
     * @param filePath 文件路径
     */
    public static void scanFileAsync(Context context, String filePath) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(Uri.fromFile(new File(filePath)));
        context.sendBroadcast(scanIntent);
    }

    /**
     * 刷新MTP，刷新指定文件夹路径下的所有文件（只是根目录下的文件）
     *
     * @param context Context
     * @param dir     文件夹路径
     */
    public static void scanMtpAsync(Context context, String dir) {
        File[] files = new File(dir).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });

        String[] paths = new String[files.length];
        for (int co = 0; co < files.length; co++) {
            paths[co] = files[co].getAbsolutePath();

            scanFileAsync(context, paths[co]);
        }
    }

    /**
     * 刷新MTP,刷新整个sdcard目录下,消耗资源
     *
     * @param context Context
     */
    public static void scanMtpAsync(Context context) {

        String dir = "/sdcard/";
        File[] files = new File(dir).listFiles();
        if(files!=null){
            String[] paths = new String[files.length];
            for (int co = 0; co < files.length; co++) {
                paths[co] = files[co].getAbsolutePath();

                if (new File(paths[co]).isDirectory()) {
                    scanMtpAsync(context, paths[co]);

                } else {

                    scanFileAsync(context, paths[co]);
                }
            }
        }

    }
}
