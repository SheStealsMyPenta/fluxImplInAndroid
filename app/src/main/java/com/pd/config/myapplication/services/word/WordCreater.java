package com.pd.config.myapplication.services.word;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class WordCreater implements WordCreaterInterface {
    public WordCreater() {


    }

    @Override
    public void createWordFile(WordParams params) {
        String pathOfFile = Environment.getExternalStorageDirectory().getPath();
        Configuration configuration = new Configuration(new Version(2, 3, 0));
//        String basePath = "e:" + File.separator + "模板";
        configuration.setDefaultEncoding("utf-8");
        Bitmap bitmap = params.getBitmap();
        params.setBase64Img(bitmapToBase64(bitmap));
        try {
           SimpleDateFormat formatter = new  SimpleDateFormat("yyyy年MM月dd日_ HH时mm分 ");
           Date date =new Date();
            String dateStr = formatter.format(date);
            String path = Environment.getExternalStorageDirectory().getPath() + "/data/" + "report"+dateStr+".doc";
            File file = new File(pathOfFile);
            configuration.setDirectoryForTemplateLoading(file);
            Template t = configuration.getTemplate("report.ftl", "utf-8");
            Map<String, String> resultMap = createMap(params);

            if(!file.exists()){
                 file.createNewFile();
            }
            PrintWriter out = new PrintWriter(path);
            try {
                t.process(resultMap, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Map<String, String> createMap(WordParams params) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("trans_name",params.getTrans_name());
        map.put("trans_type",params.getTrans_type());
        map.put("trans_made",params.getTrans_made());
        map.put("trans_sn",params.getTrans_sn());
        map.put("trans_date",params.getTrans_date());
        map.put("chart_title",params.getChart_title());
        map.put("R21_LF",params.getR21_LF());
        map.put("R21_MF",params.getR21_MF());
        map.put("R21_FF",params.getR21_FF());
        map.put("R21_HF",params.getR21_HF());
        map.put("R31_LF",params.getR31_LF());
        map.put("R31_MF",params.getR31_MF());
        map.put("R31_HF",params.getR31_HF());
        map.put("R31_FF",params.getR31_FF());
        map.put("R32_LF",params.getR32_LF());
        map.put("R32_MF",params.getR32_MF());
        map.put("R32_HF",params.getR32_HF());
        map.put("R32_FF",params.getR32_FF());
        map.put("table_title",params.getTable_title());
        map.put("tester",params.getTester());
        map.put("result",params.getResult());
        map.put("report_sh",params.getReport_sh());
        map.put("report_pz",params.getReport_pz());
        map.put("report_date",params.getReport_date());
        map.put("image",params.getBase64Img());
        map.put("detail1",params.getDetail1());
        map.put("detail2",params.getDetail2());
        map.put("detail3",params.getDetail3());
        map.put("detail4","");
        map.put("detail5","");
        map.put("detail6","");
        return map;
    }

}

