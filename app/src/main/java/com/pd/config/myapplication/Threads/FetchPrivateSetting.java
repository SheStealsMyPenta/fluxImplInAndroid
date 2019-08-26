package com.pd.config.myapplication.Threads;

import android.icu.util.Measure;
import android.icu.util.Output;
import android.os.Bundle;
import android.os.Message;

import com.pd.config.myapplication.async.Net_Handler;
import com.pd.config.myapplication.pojo.PrivateSettings;
import com.pd.config.myapplication.services.Conversions;
import com.pd.config.myapplication.services.CrcCalculate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class FetchPrivateSetting implements Runnable {
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private String ip = "192.168.10.2";
    private int port = 8000;
    private Net_Handler net_handler;

    public void setNet_handler(Net_Handler net_handler) {
        this.net_handler = net_handler;
    }

    @Override
    public void run() {
        //进行连接
        try {
            connect();
        } catch (IOException e) {
            //设备连接失败
            net_handler.sendEmptyMessage(0x31);
            e.printStackTrace();
        }finally {

        }
        //连接成功进行数据访问
        byte[] testBytes = {(byte) 0xA5, 0x20, 0x00, 0x00};
        short valueOfCrc1 = CrcCalculate.calcCrc16(testBytes);
        byte[] bytes = Conversions.short2bits(valueOfCrc1);
        //获取版本号
        byte[] out = {(byte) 0xA5, 0x20, 0x00, 0x00, bytes[1], bytes[0]};
        send(out);
        try {
            receiveResp();
        } catch (IOException e) {
            e.printStackTrace();
        }
        send(fetchPrivateSettings());
        readFromStream();
        releaseResouce();
    }
    private void receiveResp() throws IOException {
        if(inputStream!=null){
            byte[] bytes = new byte[6];
            inputStream.read(bytes, 0, 6);
            inputStream.read(new byte[4], 0, 4);

        }

    }
    private void releaseResouce() {
        try {
            if(inputStream!=null&&outputStream!=null&&socket!=null){
                inputStream.close();
                outputStream.close();
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromStream() {
        try {

            if (inputStream != null) {
                while (true) {
                    int available = inputStream.available();
                    if (available > 0) {
                        byte[] data = new byte[available];
                        if(available==26){
                            byte[] result = new byte[20];
                            inputStream.read(data);
                            System.arraycopy(data,4,result,0,result.length);
                            PrivateSettings settings = new PrivateSettings();
                            settings.setInputAmplitude(getIntFromPack(result, 0));
                            settings.setInputImpedance(getIntFromPack(result, 4));
                            settings.setOutputImpedance(getIntFromPack(result, 8));
                            settings.setInputChnKB(getFloatFromPack(result, 12));
                            settings.setOutputChnKB(getFloatFromPack(result, 16));
                            Message message = Message.obtain();
                            message.what = 0x30;
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("privateSettings", settings);
                            message.setData(bundle);
                            net_handler.sendMessage(message);
                            break;
                        }else {
                            net_handler.sendEmptyMessage(0x31);
                        }

                    }
                }

            } else {
                net_handler.sendEmptyMessage(0x31);
            }


        } catch (IOException e) {
            net_handler.sendEmptyMessage(0x31);
            e.printStackTrace();
        }
    }

    private byte[] fetchPrivateSettings() {
        byte[] command = {(byte) 0xA5, 0x25, 0x00, 0x00};
        byte[] crc = Conversions.short2bits(CrcCalculate.calcCrc16(command));
        byte[] commandSendPack = {(byte) 0xA5, 0x25, 0x00, 0x00, crc[1], crc[0]};
        return commandSendPack;
    }

    private void connect() throws IOException {
        socket = new Socket(ip, port);
        socket.setSoTimeout(4000);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }

    public void send(byte[] bytes) {
        try {
            if (outputStream != null) {
                outputStream.write(bytes);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int getIntFromPack(byte[] pack, int offset) {
        byte[] value = new byte[4];
        System.arraycopy(pack, offset, value, 0, value.length);

        return Conversions.toInt(value);
    }

    float getFloatFromPack(byte[] pack, int offset) {
        byte[] value = new byte[4];
        System.arraycopy(pack, offset, value, 0, value.length);
        return Conversions.toFloat(value);
    }

}
