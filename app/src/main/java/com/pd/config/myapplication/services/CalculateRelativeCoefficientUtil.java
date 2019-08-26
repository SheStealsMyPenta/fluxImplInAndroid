package com.pd.config.myapplication.services;

import com.pd.config.myapplication.pojo.FiittingContainer;
import com.pd.config.myapplication.pojo.ResultObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


/**
 * Created by stormingshadow on 2017/12/12.
 */

public class CalculateRelativeCoefficientUtil {
    public static double CalculateRelativeCoefficientForLowFrequency(ArrayList<Float> arrayList, ArrayList<Float> arrayList1, ArrayList<Float> arrayListX, ArrayList<Float> arrayList1X) {

        int[] size = findTheListLowerThanHundred(arrayListX); //找第一个集合100频率的点
        int[] sizeForAnother = findTheListLowerThanHundred(arrayList1X); //找第二个集合100频率的点
        double a = getDx(arrayList, size); //找第一个集合的DX
        double b = getDy(arrayList1, sizeForAnother); //算第二集合的Dx
        double cxy = getCxy(arrayList, arrayList1, size, sizeForAnother, arrayListX, arrayList1X);
        double LRxy = cxy / Math.sqrt(a * b);
        return -Math.log10(1 - LRxy);
    }

    private static int[] findTheListLowerThanHundred(ArrayList<Float> arrayList) {
        int indexOf1 = find1Index(arrayList);
        int indexOf100 = find100Index(arrayList);
//        for (int i = 0; i < arrayList.size(); i++) {
//            if (i < indexOf100) {
//                continue;
//            } else {
//                return new int[]{indexOf1,i-indexOf1};
//            }
//        }\
        return new int[]{indexOf1,indexOf100-indexOf1};

    }

    public static int find1Index(ArrayList<Float> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) >= 1) {
                return i;
            }
        }
        return -1;
    }

    public static double CalculateRelativeCoefficientForMidFrequency(ArrayList<Float> arrayList, ArrayList<Float> arrayList1, ArrayList<Float> arrayListX, ArrayList<Float> arrayList1X) {
        int[] arrayForListFirst = findTheListStartAt100AndEndAt600(arrayListX);
        int[] arrayForListSecond = findTheListStartAt100AndEndAt600(arrayList1X);

        double a = getDyMidToHifre(arrayList, arrayForListFirst);
        double b = getDyMidToHifre(arrayList1, arrayForListSecond);
        double cxy = getCxyInMidToHigh(arrayList, arrayList1, arrayForListFirst, arrayForListSecond, arrayListX, arrayList1X);
        double LRxy = cxy / Math.sqrt(a * b);
        return -Math.log10(1 - LRxy);
    }

    private static int[] findTheListStartAt100AndEndAt600(ArrayList<Float> arrayList) {
        //找到100的点的下标
        int indexOf100 = find100Index(arrayList);
        //找到600的下标
        int indexOf600 = find600Index(arrayList, indexOf100);
//        for (int j = indexOf100; j <= arrayList.size(); j++) {
//            if (j <= indexOf600) {
//                continue;
//            } else {
//
//            }
//        }
        return new int[]{indexOf100, indexOf600 - indexOf100};

    }

    public static int find600Index(ArrayList<Float> arrayList, int indexOf100) {
        for (int j = indexOf100; j <= arrayList.size(); j++) {
            if (arrayList.get(j) >= 600) {
                return j;
            }
        }
        return -1;
    }

   public static int find1000Index(ArrayList<Float> arrayList, int indexOf600) {
        for (int j = indexOf600; j < arrayList.size(); j++) {
            if (arrayList.get(j) >= 1000) {
                return j;
            }
            if(j==arrayList.size()-1){
                if(arrayList.get(j)<1000){
                    return arrayList.size()-1;
                }
            }
        }
        return -1;
    }
    public static int find1000IndexFromUndesinedList(ArrayList<Float> arrayList, int indexOf600) {
        for (int j = indexOf600; j < arrayList.size(); j++) {
            if (arrayList.get(j) >= 1000) {
                return j;
            }else {
                return arrayList.size()-1;
            }
        }
        return -1;
    }
    public static int find2000Index(ArrayList<Float> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) >=1000) {
                return i;
            }
        }
        return -1;
    }
    public static int find100Index(ArrayList<Float> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) >= 100) {
                return i;
            }
        }
        return -1;
    }

    public static double CalculateRelativeCoefficientForHighFrequency(ArrayList<Float> arrayList, ArrayList<Float> arrayList1, ArrayList<Float> arrayListX, ArrayList<Float> arrayList1X) {
        int[] arrayFirst = findTheListStartAt600AndEndAt1000(arrayListX);
        int[] arraySecond = findTheListStartAt600AndEndAt1000(arrayList1X);
        double a = getForHiFre(arrayList, arrayFirst);
        double b = getForHiFre(arrayList1, arraySecond);
        double cxy = getCxyIntoMax(arrayList, arrayList1, arrayFirst, arraySecond, arrayListX, arrayList1X);
        double LRxy = cxy / Math.sqrt(a * b);
        return -Math.log10(1 - LRxy);
    }

    private static int[] findTheListStartAt600AndEndAt1000(ArrayList<Float> arrayListX) {
        int indexOf100 = find100Index(arrayListX);
        int indexOf600 = find600Index(arrayListX, indexOf100);
        int indexOf1000 = find1000Index(arrayListX, indexOf600);
        int i = arrayListX.indexOf(601.0f);
//        for (int j = indexOf600; j <= arrayListX.size(); j++) {
//            if (j < indexOf1000) {
//                continue;
//            } else {

//            }
//        }
                        return new int[]{indexOf600,indexOf1000 - indexOf600};
    }

    public static double CalculateRelatetiveCoefficientForWholeFrequency(ArrayList<Float> arrayList, ArrayList<Float> arrayList1) {
        double a = getFor1To1000kHz(arrayList);
        double b = getFor1To1000kHz(arrayList1);
        double cxy = getCxyForWholeFre(arrayList, arrayList1);
        double LRxy = cxy / Math.sqrt(a * b);
        return -Math.log10(1 - LRxy);
    }

    private static double getCxyIntoMax(ArrayList<Float> arrayList, ArrayList<Float> arrayList1, int[] array, int[] array1, ArrayList<Float> arrayListX, ArrayList<Float> arrayList1X) {
        double avgForList = getAvgOfAListHifre(arrayList, array);
        double avgForList1 = getAvgOfAListHifre(arrayList1, array1);
        int i = 0;
        double total = 0;
        try {
            int size = array[1];
            int start = array[0];
            for (i = start; i < start + size; i++) {
                int j = arrayList1X.indexOf(arrayListX.get(i));
                total += (arrayList.get(i) - avgForList) * (arrayList1.get(j) - avgForList1);
            }
            return total / size;
        } catch (Exception e) {
            return 0.0;
        }
    }

    private static double getAvgOfAListHifre(ArrayList<Float> arrayList, int[] array) {
        int i = 0;
        double total = 0;
        try {
            for (i = array[0]; i < array[1] + array[0]; i++) {
                total += arrayList.get(i);
            }
            return total / array[1];
        } catch (Exception e) {
            return 0.0;
        }

    }

    private static double getCxyInMidToHigh(ArrayList<Float> arrayList, ArrayList<Float> arrayList1, int[] array, int[] array1, ArrayList<Float> arrayListX, ArrayList<Float> arrayList1X) {
        int start = array[0];
        int size = array[1];
        double avgForList = getAvgOfAListMid(arrayList, array);
        double avgForList1 = getAvgOfAListMid(arrayList1, array1);
        int i = start;
        double total = 0;
        try {
            for (i = start; i < size + start; i++) {
                int j = arrayListX.indexOf(arrayList1X.get(i));
                total += (arrayList.get(i) - avgForList) * (arrayList1.get(j) - avgForList1);
            }
            return total / size;
        } catch (Exception e) {
            return 0.0;
        }

    }

    private static double getAvgOfAListMid(ArrayList<Float> arrayList, int[] array) {
        int start = array[0];
        int size = array[1];
        int i = 0;
        double total = 0;
        for (i = start; i < size + start; i++) {
            total += arrayList.get(i);
        }
        return total / size;
    }

    private static double getCxy(ArrayList<Float> arrayList, ArrayList<Float> arrayList1, int[] arr, int[] arrForAnother, ArrayList<Float> arrayListX, ArrayList<Float> arrayListXAnother) {
       int size = arr[1];
       int start = arr[0];
       int sizeForAnother = arrForAnother[1];
       int startOfAnother = arrForAnother[0];
        double avgForList = getAvgOfAListLowFre(arrayList,start, size);
        double avgForList1 = getAvgOfAListLowFre(arrayList1,startOfAnother, sizeForAnother);
        float total = 0;
        int i = 0;
        try {
            for (i =start; i < sizeForAnother+start; i++) {
                int j = arrayListX.indexOf(arrayListXAnother.get(i));
                total += (arrayList.get(j) - avgForList) * (arrayList1.get(i) - avgForList1);

            }
            return total / sizeForAnother;
        } catch (Exception e) {
            return 0.0;
        }


    }

    private static double getAvgOfAListLowFre(ArrayList<Float> arrayList,int start, int size) {
        int i ;
        double total = 0;
        for (i = start; i < size+start; i++) {
            total += arrayList.get(i);
        }
        return total / size;
    }


    private static double getCxyForWholeFre(ArrayList<Float> arrayList, ArrayList<Float> arrayList1) {
        double avgForList = getAvgOfAList(arrayList);
        double avgForList1 = getAvgOfAList(arrayList1);
        float total = 0;
        int i = 0;
        for (i = 0; i < arrayList.size(); i++) {
            total += (arrayList.get(i) - avgForList) * (arrayList1.get(i) - avgForList1);
        }
        return total / arrayList.size();
    }

    private static double getDy(ArrayList<Float> arrayList, int[] arr) {
        double total = 0;
      int size=  arr[1];
   int start= arr[0];
        int i;
        for (i =start; i < size+start; i++) {
            total = total + arrayList.get(i);
        }
        double avg = total / size;
        double totalAvgSql = 0;
        for (int j = start; j < size+start; j++) {
            double temp = arrayList.get(j) - avg;
            totalAvgSql = totalAvgSql + Math.pow(temp, 2);
        }
        return totalAvgSql / size;
    }

    private static double getDx(ArrayList<Float> arrayList, int[] arr) {
        int size = arr[1];
        int start= arr[0];
        double total = 0;
        int i;
        for (i = start; i < size+start; i++) {
            total = total + arrayList.get(i);
        }
        double avg = total / size;
        double totalAvgSql = 0;
        for (int j = start; j < size+start; j++) {
            double temp = arrayList.get(j) - avg;
            totalAvgSql = totalAvgSql + Math.pow(temp, 2);
        }
        return totalAvgSql / size;

    }

    private static float getAvgOfAList(ArrayList<Float> list) {
        int i = 0;
        float total = 0;
        Iterator<Float> iterator = list.iterator();
        while (iterator.hasNext()) {
            i++;
            total += iterator.next();
        }
        return total / (i);
    }

    //求出中频高频的相对系数
    private static double getDyMidToHifre(ArrayList<Float> arrayList, int[] array) {
        double total = 0;
        int i;
        int start = array[0];
        int size = array[1];
        for (i = start; i < size + start; i++) {
            total = total + arrayList.get(i);
        }
        double avg = total / size;
        double totalAvgSqr = 0;
        for (int j = start; j < size + start; j++) {
            double temp = arrayList.get(j) - avg;
            totalAvgSqr = totalAvgSqr + Math.pow(temp, 2);
        }
        return totalAvgSqr / size;
    }

    //求出600-1000的相对系数
    private static double getForHiFre(ArrayList<Float> arrayList, int[] array) {
        double total = 0;
        int i;

        try {
            int start = array[0];
            int size = array[1];
            for (i = start; i < size + start; i++) {
                total = total + arrayList.get(i);
            }
            double avg = total / size;
            double totalAvgSqr = 0;
            for (int j = start; j < size + start; j++) {
                double temp = arrayList.get(j) - avg;
                totalAvgSqr = totalAvgSqr + Math.pow(temp, 2);
            }
            return totalAvgSqr / size;
        } catch (Exception e) {
            return 0.0;
        }

    }

    private static double getFor1To1000kHz(ArrayList<Float> arrayList) {
        double total = 0;
        int i;
        for (i = 0; i < arrayList.size(); i++) {
            total = total + arrayList.get(i);
        }
        double avg = total / arrayList.size();
        double totalAvgSql = 0;
        for (int j = 0; j < arrayList.size(); j++) {
            double temp = arrayList.get(j) - avg;
            totalAvgSql = totalAvgSql + Math.pow(temp, 2);
        }
        return totalAvgSql / arrayList.size();
    }

    /***
     * 拟合Y值
     * params:
     * listOfX---被拟合的的X值集合
     * listOfY---被拟合的的Y值集合
     * length长度
     * refXValue参照的X集合
     * @return
     *  拟合出的Y值用于计算
     */
    private static ResultObj getYValue(ArrayList<Float> listOfX, ArrayList<Float> listOfY, int length, float refXValue) {
        float x1 = 0;
        float x2 = 0;
        int i = 0;
        if (listOfX == null || listOfY == null) return null;
        if (length <= 0) return null;
        if (listOfX.get(0) > refXValue) return null;
        if (listOfX.get(length - 1) < refXValue) return null;
        x1 = listOfX.get(0);
        Long start= System.currentTimeMillis();
        for (i = 1; i < length; i++) {
            if (x1 == refXValue) {
                return new ResultObj(true, listOfY.get(i - 1));
            } else {
                x2 = listOfX.get(i);
                if (x1 < refXValue && x2 > refXValue) {
                    return new ResultObj(true, fittingFunc(x1, listOfY.get(i - 1), x2, listOfY.get(i), refXValue));
                }
                x1 = x2;
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("Y值寻找耗时"+(end-start));
        return new ResultObj(false, 0.0f);
    }

    /***
     * 拟合函数
     * @return
     */
    private static float fittingFunc(float x1, float y1, float x2, float y2, float x) {
        if (x1 == x2) return 0;
        else return (y2 * (x1 - x) - y1 * (x2 - x)) / (x1 - x2);
    }

    public static FiittingContainer getFittingList(ArrayList<Float> listOfXLine1, ArrayList<Float> listOfXLine2, ArrayList<Float> listOfY1Line, ArrayList<Float> listOfYLine2) {
        if (listOfXLine1 == null || listOfXLine2 == null) return null;
        if (listOfXLine1.size() == 0) return null;
        int count = 0;
        ArrayList<Float> fittedListOfY = new ArrayList<>();
        ArrayList<Float> newListOfXLine1 = new ArrayList<Float>();
        ArrayList<Float> newListOfYLine1 = new ArrayList<>();

        for (int i = 0; i < listOfXLine1.size(); i++) {
            if (listOfXLine1.get(i) < 1.0) continue;
            if (listOfXLine1.get(i) > 1000.0) break;
            //获取集合1内在1到1000范围内的数据
            float refXValue = listOfXLine1.get(i);
//            fittedListOfY.add(getYValue(listOfXLine2, listOfYLine2, listOfXLine2.size(), refXValue));
            ResultObj obj = getYValue(listOfXLine2, listOfYLine2, listOfXLine2.size(), refXValue);
            if (obj != null && obj.isExist()) {
                //若存在
                newListOfXLine1.add(refXValue);
                newListOfYLine1.add(listOfY1Line.get(i));
                fittedListOfY.add(obj.getResult());
            }
        }
        return new FiittingContainer(newListOfXLine1, newListOfXLine1, newListOfYLine1, fittedListOfY);
    }


}

