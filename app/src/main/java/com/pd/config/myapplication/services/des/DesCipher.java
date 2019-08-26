/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.config.myapplication.services.des;

/**
 *
 * @author hr018
 */
public class DesCipher {
    /*
     * static method for encrypt and decrypt
     */
    public static boolean doCipher(byte[] Out, byte[] In, int nDataLen, boolean bEncrypt){
        boolean bret;
        byte[] key = DesConstants.getPrivateKey();
        bret = new DesCipher().doDes(Out, In, nDataLen, key, bEncrypt);
        return bret;
    }

    /*
     * variables
     */
    private boolean m_bIs3Des;
    private byte[] m_arDesKey;
    private DesSubKey m_subkey;

    /*
     * constructor
     */
    public DesCipher(){
        m_bIs3Des = false;
        m_arDesKey = new byte[16];
        m_subkey = new DesSubKey();
    }

    /*
     * public method
     */
    public boolean doDes(byte[] Out, byte[] In, int nDataLen, byte[] key, boolean bEncrypt){
        int i, j;
        int off_out, off_in;
        int templen = (nDataLen + 7) & 0xFFFFFFF8;
        if ((Out.length <= 0) || (In.length <= 0) || (key.length <= 0) || (Out.length < In.length) ||
            (Out.length < (nDataLen + 4)) || (templen == 0)){
            return false;
        }

        setKey(key);
        off_out = 0;
        off_in = 0;
        j = templen >> 3;
        if (m_bIs3Des){
            for (i = 0; i < j; i++){
                goDesKey(Out, off_out, In, off_in, 0, bEncrypt);
                goDesKey(Out, off_out, Out, off_out, 1, !bEncrypt);
                goDesKey(Out, off_out, Out, off_out, 0, bEncrypt);
                off_out += 8;
                off_in += 8;
            }
        }else{
            for (i = 0; i < j; i++){
                goDesKey(Out, off_out, In, off_in, 0, bEncrypt);
                off_out += 8;
                off_in += 8;
            }
        }

        return true;
    }

    /*
     * private methods for assistance
     */
    /* set key for encrypt and decrypt */
    private void setKey(byte[] key){
        int i, keyLen;
        keyLen = key.length;
        for (i = 0; i < 16; i++){
            if (i > keyLen){
                m_arDesKey[i] = 0;
            }else{
                m_arDesKey[i] = key[i];
            }
        }

        m_subkey.setSubKey(0, m_arDesKey, 0);
        if (keyLen > 8){
            m_bIs3Des = true;
            m_subkey.setSubKey(1, m_arDesKey, 8);
        }else{
            m_bIs3Des = false;
        }
    }

    /* encrypt or decrypt */
    private void goDesKey(byte []OutAddr, int outOffset, byte[] InAddr, int inOffset, int SubKeyIndex, boolean bEncrypt){
        int i, off_ri, off_li;
        byte []M, tmp, tmpM, tmpOut, tmpIn;
        tmp = new byte[32];
        tmpIn = new byte[8];
        if (InAddr.length > (inOffset + 8)){
            System.arraycopy(InAddr, inOffset, tmpIn, 0, 8);
        }else{
            System.arraycopy(InAddr, inOffset, tmpIn, 0, InAddr.length - inOffset);
            i = InAddr.length - inOffset;
            while (i < 8){
                tmpIn[i++] = 0;
            }
        }

        M = DesConstants.byteToBits(tmpIn, 0, 8, 64);
        DesConstants.transform(M, M, DesConstants.IP_TABLE);

        off_ri = 32;
        off_li = 0;
        if (bEncrypt){
            for (i = 0; i < 16; i++){
                System.arraycopy(M, off_ri, tmp, 0, 32);
                fFunc(M, off_ri, SubKeyIndex, i);
                xor(M, off_ri, off_li, 32);
                System.arraycopy(tmp, 0, M, off_li, 32);
            }
        }else{
            for (i = 15; i >= 0; i--){
                System.arraycopy(M, off_li, tmp, 0, 32);
                fFunc(M, off_li, SubKeyIndex, i);
                xor(M, off_li, off_ri, 32);
                System.arraycopy(tmp, 0, M, off_ri, 32);
            }
        }

        DesConstants.transform(M, M, DesConstants.IPR_TABLE);
        tmpOut = DesConstants.bitsToByte(M, 0, 8, 64);
        System.arraycopy(tmpOut, 0, OutAddr, outOffset, tmpOut.length);
    }

    /* f function */
    private void fFunc(byte[] buf, int offset, int SubKeyIndex, int keyIdx){
        byte []subkey, MR, tmpBuf;
        if (buf.length != 64){
            return ;
        }

        subkey = m_subkey.getSubKey(SubKeyIndex, keyIdx);
        if (subkey == null){
            return ;
        }

        tmpBuf = new byte[32];
        MR = new byte[48];
        System.arraycopy(buf, offset, tmpBuf, 0, 32);
        DesConstants.transform(MR, tmpBuf, DesConstants.E_TABLE);
        xor(MR, subkey, 48);
        sFunc(tmpBuf, MR);
        DesConstants.transform(tmpBuf, tmpBuf, DesConstants.P_TABLE);
        System.arraycopy(tmpBuf, 0, buf, offset, 32);
    }

    /* XOR function */
    private void xor(byte[] buf, int off_out, int off_in, int len){
        int i;
        if (buf.length < len){
            return ;
        }

        for (i = 0; i < len; i++){
            buf[off_out + i] ^= buf[off_in + i];
        }
    }

    private void xor(byte[] outbuf, byte[] inbuf, int len){
        int i;
        if ((outbuf.length < len) || (inbuf.length < len)){
            return ;
        }

        for (i = 0; i < len; i++){
            outbuf[i] ^= inbuf[i];
        }
    }

    /* s function */
    private void sFunc(byte[] outbuf, byte[] inbuf){
        int i, j, k;
        int off_out, off_in;
        byte[] tmpBuf;

        off_out = 0;
        off_in = 0;
        for (i = 0; i < 8; i++){
            j = (inbuf[off_in] << 1) + inbuf[off_in + 5];
            k = (inbuf[off_in + 1] << 3) + (inbuf[off_in + 2] << 2) + (inbuf[off_in + 3] << 1) + inbuf[off_in + 4];
            tmpBuf = DesConstants.byteToBits(DesConstants.S_BOX, 64 * i + 16 * j + k, 1, 4);
            System.arraycopy(tmpBuf, 0, outbuf, off_out, 4);

            off_out += 4;
            off_in += 6;
        }
    }
}
