package com.pd.config.myapplication.Threads;

import com.pd.config.myapplication.async.Net_Handler;
import com.pd.config.myapplication.pojo.PrivateSettings;
import com.pd.config.myapplication.services.Conversions;
import com.pd.config.myapplication.services.CrcCalculate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SetPrivateSetting implements Runnable {
    private Net_Handler net_handler;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String ip = "192.168.10.2";
    private int port = 8000;
    private PrivateSettings privateSettings;

    public void setPrivateSettings(PrivateSettings privateSettings) {
        this.privateSettings = privateSettings;
    }

    public void setNet_handler(Net_Handler net_handler) {
        this.net_handler = net_handler;
    }

    @Override
    public void run() {
        try {
            connect();
        } catch (IOException e) {
            net_handler.sendEmptyMessage(0x31);
            return;
        }
        send(mkPackToSetPrivateSetting());
        try {
            receiveFromStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            releaseResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveFromStream() throws IOException {
      while(true){
          if(inputStream.available()>0){
              byte[] result = new byte[inputStream.available()];
              inputStream.read(result);
              System.out.println(result.length);
              net_handler.sendEmptyMessage(0x32);
          }
      }
    }

    private void releaseResource() throws IOException {
    if(inputStream!=null&&outputStream!=null&&socket!=null){
        inputStream.close();
        outputStream.close();
        socket.close();
    }
    }

    private byte[] mkPackToSetPrivateSetting() {
        byte[] data = new byte[20];
        int inputAmplitude = privateSettings.getInputAmplitude();
        byte[] bytes = Conversions.int2BytesArray(inputAmplitude);
        System.arraycopy(bytes, 0, data, 0, bytes.length);
        byte[] inputImpe = Conversions.int2BytesArray(privateSettings.getInputImpedance());
        System.arraycopy(inputImpe, 0, data, 4, inputImpe.length);
        byte[] outputImpe = Conversions.int2BytesArray(privateSettings.getOutputImpedance());
        System.arraycopy(outputImpe,0,data,8,outputImpe.length);
        byte[] inputKB = Conversions.float2byte(privateSettings.getInputChnKB());
        System.arraycopy(inputKB,0,data,12,inputKB.length);
        byte[] outputKB= Conversions.float2byte(privateSettings.getOutputChnKB());
        System.arraycopy(outputKB,0,data,16,outputKB.length);
        return getArr_set_params(data);
    }

    private byte[] getArr_set_params(byte[] data) {
        short h = 20;
        byte[] lengthOfPack = Conversions.short2bits(h);
        byte[] command = new byte[24];
        command[0] = (byte) 0xA5;
        command[1] = 0x26;
        command[2] = lengthOfPack[0];
        command[3] = lengthOfPack[1];
        System.arraycopy(data, 0, command, 4, data.length);
        byte[] crc = Conversions.short2bits(CrcCalculate.calcCrc16(command));
        byte[] sendCommandPack = new byte[26];
        System.arraycopy(command, 0, sendCommandPack, 0, command.length);
        sendCommandPack[24] = crc[1];
        sendCommandPack[25] = crc[0];
//        command[24] = crc[1];
//        command[25] = crc[0];
        return sendCommandPack;
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
}
