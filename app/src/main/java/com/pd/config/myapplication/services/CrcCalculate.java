package com.pd.config.myapplication.services;

/**
 * Created by stormingshadow on 2017/12/5.
 */

public class CrcCalculate {
    static byte[] crc_16_h = new byte[]{
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x00, (byte) 0xC1, (byte) 0x81, 0x40, 0x01, (byte) 0xC0, (byte) 0x80, 0x41,
            0x01, (byte) 0xC0, (byte) 0x80, 0x41, 0x00, (byte) 0xC1, (byte) 0x81, 0x40
    };
    static byte[] crc_16_l= new byte[]{
            0x00, (byte) 0xC0, (byte) 0xC1, 0x01, (byte) 0xC3, 0x03, 0x02, (byte) 0xC2,
            (byte) 0xC6, 0x06, 0x07, (byte) 0xC7, 0x05, (byte) 0xC5, (byte) 0xC4, 0x04,
            (byte) 0xCC, 0x0C, 0x0D, (byte) 0xCD, 0x0F, (byte) 0xCF, (byte) 0xCE, 0x0E,
            0x0A, (byte) 0xCA, (byte) 0xCB, 0x0B, (byte) 0xC9, 0x09, 0x08, (byte) 0xC8,
            (byte) 0xD8, 0x18, 0x19, (byte) 0xD9, 0x1B, (byte) 0xDB, (byte) 0xDA, 0x1A,
            0x1E, (byte) 0xDE, (byte) 0xDF, 0x1F, (byte) 0xDD, 0x1D, 0x1C, (byte) 0xDC,
            0x14, (byte) 0xD4, (byte) 0xD5, 0x15, (byte) 0xD7, 0x17, 0x16, (byte) 0xD6,
            (byte) 0xD2, 0x12, 0x13, (byte) 0xD3, 0x11, (byte) 0xD1, (byte) 0xD0, 0x10,
            (byte) 0xF0, 0x30, 0x31, (byte) 0xF1, 0x33, (byte) 0xF3, (byte) 0xF2, 0x32,
            0x36, (byte) 0xF6, (byte) 0xF7, 0x37, (byte) 0xF5, 0x35, 0x34, (byte) 0xF4,
            0x3C, (byte) 0xFC, (byte) 0xFD, 0x3D, (byte) 0xFF, 0x3F, 0x3E, (byte) 0xFE,
            (byte) 0xFA, 0x3A, 0x3B, (byte) 0xFB, 0x39, (byte) 0xF9, (byte) 0xF8, 0x38,
            0x28, (byte) 0xE8, (byte) 0xE9, 0x29, (byte) 0xEB, 0x2B, 0x2A, (byte) 0xEA,
            (byte) 0xEE, 0x2E, 0x2F, (byte) 0xEF, 0x2D, (byte) 0xED, (byte) 0xEC, 0x2C,
            (byte) 0xE4, 0x24, 0x25, (byte) 0xE5, 0x27, (byte) 0xE7, (byte) 0xE6, 0x26,
            0x22, (byte) 0xE2, (byte) 0xE3, 0x23, (byte) 0xE1, 0x21, 0x20, (byte) 0xE0,
            (byte) 0xA0, 0x60, 0x61, (byte) 0xA1, 0x63, (byte) 0xA3, (byte) 0xA2, 0x62,
            0x66, (byte) 0xA6, (byte) 0xA7, 0x67, (byte) 0xA5, 0x65, 0x64, (byte) 0xA4,
            0x6C, (byte) 0xAC, (byte) 0xAD, 0x6D, (byte) 0xAF, 0x6F, 0x6E, (byte) 0xAE,
            (byte) 0xAA, 0x6A, 0x6B, (byte) 0xAB, 0x69, (byte) 0xA9, (byte) 0xA8, 0x68,
            0x78, (byte) 0xB8, (byte) 0xB9, 0x79, (byte) 0xBB, 0x7B, 0x7A, (byte) 0xBA,
            (byte) 0xBE, 0x7E, 0x7F, (byte) 0xBF, 0x7D, (byte) 0xBD, (byte) 0xBC, 0x7C,
            (byte) 0xB4, 0x74, 0x75, (byte) 0xB5, 0x77, (byte) 0xB7, (byte) 0xB6, 0x76,
            0x72, (byte) 0xB2, (byte) 0xB3, 0x73, (byte) 0xB1, 0x71, 0x70, (byte) 0xB0,
            0x50, (byte) 0x90, (byte) 0x91, 0x51, (byte) 0x93, 0x53, 0x52, (byte) 0x92,
            (byte) 0x96, 0x56, 0x57, (byte) 0x97, 0x55, (byte) 0x95, (byte) 0x94, 0x54,
            (byte) 0x9C, 0x5C, 0x5D, (byte) 0x9D, 0x5F, (byte) 0x9F, (byte) 0x9E, 0x5E,
            0x5A, (byte) 0x9A, (byte) 0x9B, 0x5B, (byte) 0x99, 0x59, 0x58, (byte) 0x98,
            (byte) 0x88, 0x48, 0x49, (byte) 0x89, 0x4B, (byte) 0x8B, (byte) 0x8A, 0x4A,
            0x4E, (byte) 0x8E, (byte) 0x8F, 0x4F, (byte) 0x8D, 0x4D, 0x4C, (byte) 0x8C,
            0x44, (byte) 0x84, (byte) 0x85, 0x45, (byte) 0x87, 0x47, 0x46, (byte) 0x86,
            (byte) 0x82, 0x42, 0x43, (byte) 0x83, 0x41, (byte) 0x81, (byte) 0x80, 0x40
    };
    public static short calcCrc16(byte[] data) {
        return calcCrc16(data, 0, data.length);
    }

    /**
     * 计算CRC16校验
     *
     * @param data
     *            需要计算的数组
     * @param offset
     *            起始位置
     * @param len
     *            长度
     * @return CRC16校验值
     */
    public static short calcCrc16(byte[] data, int offset, int len) {
        return calcCrc16(data, offset, len, 0xffff);
    }

    /**
     * 计算CRC16校验
     *
     * @param data
     *            需要计算的数组
     * @param offset
     *            起始位置
     * @param len
     *            长度
     * @param preval
     *            之前的校验值
     * @return CRC16校验值
     */
    public static short calcCrc16(byte[] data, int offset, int len, int preval) {
        int ucCRCHi = (preval & 0xff00) >> 8;
        int ucCRCLo = preval & 0x00ff;
        int iIndex;
        for (int i = 0; i < len; ++i) {
            iIndex = (ucCRCLo ^ data[offset + i]) & 0x00ff;
            ucCRCLo = ucCRCHi ^ crc_16_h[iIndex];
            ucCRCHi = crc_16_l[iIndex];
        }
        return (short) (((ucCRCHi & 0x00ff) << 8)&0xffff | (ucCRCLo & 0x00ff) & 0xffff);
    }
    public static short calculateCrc(byte[] buf,int len){

    short CRC;
    int i,j;
    CRC = (short) 0xffff;
    for (i=0;i<len;i++){
        CRC = (short) (CRC^(buf[i]&0xff));
        for(j=0;j<8;j++){
            if((CRC&0x01) ==1) {
                CRC = (short) ((CRC >> 1) ^ 0xA001);
            } else{
               CRC = (short) (CRC>>1);
                }
            }
                                            /*A5 20 00 00 22 23*/
        }

  return CRC;
    }




    }

