package com.pd.config.myapplication.services;

import android.os.Bundle;
import android.os.Message;

import com.pd.config.myapplication.async.Net_Handler;
import com.pd.config.myapplication.services.des.DesCipher;
import com.pd.config.myapplication.statics.Flag;
import com.pd.config.myapplication.statics.StateOfTheIntenet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class AndroidShortConnnectionClient implements Runnable {
    private Net_Handler handler;
    private String ip;
    private int port;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private List<Float> listOfPoint;
    private float currentFrequency;
    private boolean finish = false;
    private boolean isConnected = false;
    private long timeOutBefore;
    private long timeOutAfter;
    private boolean isTimeOut = false;
    private boolean interupt = false;
    private float finalPoint;
    private String currentDevice;

    public String getCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(String currentDevice) {
        this.currentDevice = currentDevice;
    }

    public AndroidShortConnnectionClient(String ip, int port, ArrayList<Float> listOfPoints) {
        this.ip = ip;
        this.port = port;
        this.listOfPoint = listOfPoints;
        this.finalPoint = listOfPoint.get(listOfPoints.size() - 1);
    }

    public void setHandler(Net_Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        connect();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isConnected) {
            //开电
            invokeDevice();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            int i = 0;
//            while (i < 3) {
//                short h = 8;
//                byte[] bytesForValue = Conversions.short2bits(h);
//                byte[] command = commandMaking(1000.0f, bytesForValue, 0);
//                send(command);
//                try {
//                    receiveData();
//                    if (finish) {
//                        break;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                i++;
//            }
            try {
                if (inputStream.available() == 22)
                    inputStream.read(new byte[22]);

            } catch (IOException e) {
                e.printStackTrace();
            }


            while (!finish) {
                for (Float xValue : listOfPoint) {
                    currentFrequency = xValue;
                    short h = 8;
                    byte[] bytesForValue = Conversions.short2bits(h);
                    byte[] command = commandMaking(xValue, bytesForValue);
                    send(command);
                    receiveFromStream();
                    if (StateOfTheIntenet.ToCloseTheThread) {
                        disconnectForUser();
                        finish = true;
                        break;
                    }
                    if (interupt) {
                        sendDisconnectMsg();
                        finish = true;
                        break;
                    }
                }
            }
        } else {
            sendFalseMessage();
        }
        //线程关闭后释放所有资源
        try {
            releaseResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveData() throws IOException {
        timeOutBefore = System.currentTimeMillis();
        while (true) {
            if (inputStream != null) {
                if (inputStream.available() > 0) {
                    timeOutBefore = System.currentTimeMillis();
                    if (StateOfTheIntenet.ToCloseTheThread) {
                        disconnectForUser();
                        finish = true;
                        break;
                    }
                    inputStream.read(new byte[22]);
                    break;
                } else {
                    timeOutAfter = System.currentTimeMillis();
                    if (timeOutAfter - timeOutBefore > 2000) {
                        sendDisconnectMsg();
                        finish = true;
                        break;
                    } else {
//                      System.out.println(timeOutAfter-timeOutBefore);
                    }
                    if (StateOfTheIntenet.ToCloseTheThread) {
                        disconnectForUser();
                        finish = true;
                        break;
                    }
                }
            }

        }

    }

    private void releaseResource() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }

    private void invokeDevice() {
        short i = 1;
        byte[] lengthOf1 = Conversions.short2bits(i);
        byte[] testBytes = {(byte) 0xA5, 0x20, 0x00, 0x00};
        byte[] bytesToStartTest = {(byte) 0xA5, 0x21, 0x01, 0x00, 0x01};
        short valueOfCrc1 = CrcCalculate.calcCrc16(testBytes);
        short valueOfCrc2 = CrcCalculate.calcCrc16(bytesToStartTest);
        /*valueOfCrc1=  Math.abs(valueOfCrc1);*/
        /*   short valueOfCrc1 = CrcCalculate.calculateCrc(testBytes,testBytes.length);*/
        byte[] bytes = Conversions.short2bits(valueOfCrc1);
        byte[] bytes1 = Conversions.short2bits(valueOfCrc2);
        //获取版本号
        byte[] out = {(byte) 0xA5, 0x20, 0x00, 0x00, bytes[1], bytes[0]};
        //打开采集开关
        byte[] out1 = {(byte) 0xA5, 0x21, 0x01, 0x00, 0x01, bytes1[1], bytes1[0]};
        send(out);
        try {
            Thread.sleep(30);
            receiveResp();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        send(out1);
        try {
            Thread.sleep(30);
            receiveResp();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取输出阻抗
        send(getArr_fetch_params());
        fetchImpæ();
    }

    private void fetchImpæ() {
        try {
            int available = inputStream.available();
            while (true) {
                if (available > 0) {
                    byte[] params = new byte[available];
                    inputStream.read(params);
                    byte[] data = new byte[20];
                    System.arraycopy(params, 4, data, 0, data.length);
                    byte[] output = new byte[4];
                    System.arraycopy(data, 8, output, 0, output.length);
                    int out = Conversions.toInt(output);
                    if (currentDevice.equals("变压器设备")) {
                        if (out == 0) {
                            return;
                        } else {
                            byte[] reset = Conversions.int2BytesArray(0);
                            System.arraycopy(reset, 0, data, 4, reset.length);
                            send(getArr_set_params(data));
                            return;
                        }
                    } else if (currentDevice.equals("互感器设备") || currentDevice.equals("其他设备")) {
                        if (out == 1) {
                            return;
                        } else {
                            byte[] reset = Conversions.int2BytesArray(1);
                            System.arraycopy(reset, 0, data, 8, reset.length);
                            send(getArr_set_params(data));
                            return;
                        }
                    }
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
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
        System.arraycopy(command,0,sendCommandPack,0,command.length);
        sendCommandPack[24] = crc[1];
        sendCommandPack[25] =crc[0];
//        command[24] = crc[1];
//        command[25] = crc[0];
        return sendCommandPack;
    }

    private byte[] getArr_fetch_params() {

        byte[] command = {(byte) 0xA5, 0x25, 0x00, 0x00};
        byte[] crc = Conversions.short2bits(CrcCalculate.calcCrc16(command));
        byte[] commandSendPack = {(byte) 0xA5, 0x25, 0x00, 0x00, crc[1], crc[0]};
        return commandSendPack;

    }

    private void receiveResp() throws IOException {
        byte[] bytes = new byte[6];
        inputStream.read(bytes, 0, 6);
        inputStream.read(new byte[4], 0, 4);
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

    private void connect() {
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(4000);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            isConnected = socket != null && outputStream != null && inputStream != null;
        } catch (IOException e) {
            e.printStackTrace();
            //连接不上
            sendFalseMessage();
        }

    }

    //利用传来的float数据与长度为8,算出来的byte[]数组,标志，返回一个以byte[]为命令形式的数组
    private byte[] commandMaking(float frequency, byte[] bytesForValue) {
        byte[] bytes2 = Conversions.floatToByteArray(frequency);
        byte[] bytes3 = Conversions.int2BytesArray(0);
        byte[] getPoints = {(byte) 0xA5, 0x23, bytesForValue[0], bytesForValue[1], bytes2[0], bytes2[1], bytes2[2], bytes2[3], bytes3[3], bytes3[2], bytes3[1], bytes3[0]};
        short crcValueOfPoint = CrcCalculate.calcCrc16(getPoints);
        byte[] bytesForCrc = Conversions.short2bits(crcValueOfPoint);
        return new byte[]{(byte) 0xA5, 0x23, bytesForValue[0], bytesForValue[1], bytes2[0], bytes2[1], bytes2[2], bytes2[3], bytes3[3], bytes3[2], bytes3[1], bytes3[0], bytesForCrc[1], bytesForCrc[0]};
    }

    private void sendFalseMessage() {
        Message msg = Message.obtain();
        msg.what = 0x19;
        Bundle bundle = new Bundle();
        bundle.putBoolean("fail", true);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    private void receiveThatPoints(boolean send) {
        byte[] valuesForPoint = new byte[22];
        try {
            inputStream.read(valuesForPoint, 0, 22);
            byte[] myData = new byte[16];
            System.arraycopy(valuesForPoint, 4, myData, 0, 16);
            byte[] depDate = new byte[16];
            /*DES.DES_DO(depDate, myData, 16, false);*/
            DesCipher.doCipher(depDate, myData, 12, false);
            byte[] result = new byte[4];
            System.arraycopy(depDate, 0, result, 0, 4);
            float frequency = Conversions.toFloat(depDate);
            System.out.println("当前频率" + currentFrequency + "收到频率" + frequency);
            if (currentFrequency == frequency) {
                byte[] dbValueBytes = new byte[4];
                System.arraycopy(depDate, 4, dbValueBytes, 0, 4);
                float dbValue = Conversions.toFloat(dbValueBytes);
                float[] values = new float[2];
                values[0] = frequency;
                values[1] = dbValue;
                if (send) {
                    sendTheMessage(values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendTheMessage(float[] values) {
        /* Message msg = handler.obtainMessage();*/
        Message msg = Message.obtain();
        msg.what = 0x02;

        Bundle bundle = new Bundle();
//        bundle.putFloatArray("results", values);
        bundle.putFloat("pointY", values[1]);
        bundle.putFloat("pointX", values[0] / 1000);
        if (Flag.isFirstPoint) {
            bundle.putBoolean("start", true);
            Flag.isFirstPoint = false;
        } else {
            bundle.putBoolean("start", false);
        }
        bundle.putBoolean("end", false);
        msg.setData(bundle);
        handler.sendMessage(msg);
        System.out.println("finalPoint :" + finalPoint / 1000);
        System.out.println("currentPoint: " + values[0] / 1000);
        if (finalPoint == values[0]) {
            try {
                finish = true;
                notifyEnd();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void notifyEnd() throws IOException {
        Message msg = Message.obtain();
        msg.what = 0x04;
        Bundle bundle = new Bundle();
        bundle.putBoolean("end", true);
        Flag.isFirstPoint = true;
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    private void receiveFromStream() {
        try {
            if (inputStream != null) {
                timeOutBefore = System.currentTimeMillis();
                while (true) {

                    timeOutAfter = System.currentTimeMillis();
                    int receive_bytes = inputStream.available();
                    if (receive_bytes == 6) {
                        inputStream.read(new byte[6], 0, 6);
                        timeOutBefore = timeOutAfter;
                    }
                    if (StateOfTheIntenet.ToCloseTheThread) {
                        break;
                    }
                    if (receive_bytes % 22 == 0 && receive_bytes != 0) {
                        receiveThatPoints(true);
                        Thread.sleep(10);
                        timeOutBefore = timeOutAfter;
                        break;
                    }
                    if (receive_bytes == 28) {
                        inputStream.read(new byte[6], 0, 6);
                        continue;
                    }

                    if (receive_bytes == 26) {
                        inputStream.read(new byte[4], 0, 4);
                        continue;
                    }

                    if (receive_bytes == 0) {
                        if (timeOutAfter - timeOutBefore > 8000) {
                            isTimeOut = true;
                        }
                        if (isTimeOut) {
                            //设备连接超时准备关闭线程
                            interupt = true;
                            break;
                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //连接超时
            StateOfTheIntenet.ToCloseTheThread = true;
            sendFalseMessage();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendDisconnectMsg() {
        Message msg = Message.obtain();
        msg.what = 0x03;
        Bundle bundle = new Bundle();
        bundle.putString("afk", "afk");
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    private void disconnectForUser() {
        Message msg = Message.obtain();
        msg.what = 0x20;
        handler.sendMessage(msg);
        StateOfTheIntenet.ToCloseTheThread = false;
    }
}
