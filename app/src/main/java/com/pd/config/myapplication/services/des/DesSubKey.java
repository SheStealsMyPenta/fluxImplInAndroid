/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.config.myapplication.services.des;

/**
 *
 * @author hr018
 */
public class DesSubKey {
    private byte[] keyBuf;
    public DesSubKey(){
        keyBuf = new byte[2 * 16 * 48];
    }

    /*
     * public methods
     * setSubKey
     */
    public void setSubKey(int offset, byte[] keyval, int keyoffset){
        int i, pos;
        byte[] k;
        byte[] tempK;
        if (offset >= 2){
            return ;
        }

        if (keyval.length < (keyoffset + 8)){
            return ;
        }

        k = DesConstants.byteToBits(keyval, keyoffset, 8, 64);
        tempK = new byte[64];

        DesConstants.transform(k, k, DesConstants.PC1_TABLE);

        pos = offset * 16 * 48;
        for (i = 0; i < 16; i++){
            DesConstants.rotateL(k, 0, 28, DesConstants.LOOP_TABLE[i]);
            DesConstants.rotateL(k, 28, 28, DesConstants.LOOP_TABLE[i]);
            DesConstants.transform(tempK, k, DesConstants.PC2_TABLE);
            System.arraycopy(tempK, 0, keyBuf, pos, 48);
            pos += 48;
        }
    }

    /*
     * getSubKey
     */
    public byte[] getSubKey(int offset, int indx){
        int pos;
        byte[] selKey;
        if ((offset < 0) || (offset > 1) || (indx < 0) || (indx >= 16)){
            return null;
        }

        selKey = new byte[48];
        pos = offset * 16 * 48;
        pos = pos + indx * 48;
        System.arraycopy(keyBuf, pos, selKey, 0, 48);
        return selKey;
    }
}
