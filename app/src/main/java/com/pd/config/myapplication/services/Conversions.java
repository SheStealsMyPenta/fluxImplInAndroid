package com.pd.config.myapplication.services;

/**
 * Created by stormingshadow on 2017/12/6.
 */

public class Conversions {
    public static byte[] short2bits(short n){
        byte[] temp = new byte[2];
        temp[0] = (byte)n;
        temp[1] = (byte)(n>>8);
        return temp;
    }
    public static byte[] floatToByteArray(float data){
        int temp = Float.floatToIntBits(data);
        byte[] temp_buff = new byte[4];
        for(int i = 0;i<4;i++){
            temp_buff[i] = (byte)(temp>>(8*i)& 0xff);
        }
        return temp_buff;
    }
    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;

    }
    public static byte[] int2BytesArray(int n ){

        byte[] b = new byte[4];
        for(int i =0;i<4;i++){
            b[i] = (byte)(n>>(i*8));
        }
        return b;
    }
    public static float toFloat(byte[] data){
        return Float.intBitsToFloat(toInt(data));
    }

   public  static int toInt(byte[] data) {
        int temp = 0;
        if(data.length>=4){
            temp = temp| (data[0] & 0xff);
            temp = temp|(data[1]&0xff)<<8;
            temp = temp|(data[2]&0xff)<<16;
            temp = temp|(data[3]&0xff)<<24;
        }
        return  temp;
    }

}
