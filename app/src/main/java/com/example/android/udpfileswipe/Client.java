package com.example.android.udpfileswipe;


import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Client extends Thread {
    public DatagramSocket sock;
    public DatagramPacket packet,sp;
    public InetSocketAddress ip;
    public InetAddress Toip;
    public MyObjectPacket obj;
    public String fname;

    Client(String fn) {
        try {
            sock = new DatagramSocket(null);
            ip = new InetSocketAddress("0.0.0.0", 7776);
            sock.bind(ip);
            Log.v("ClientLog", "Client Socket Created....");

        } catch (IOException e) {
            Log.v("ClientLog", "Client Socket failed....");
        }
        
        fname=fn;
    }


    public void run()
    {
        Log.v("ClientLog", "Client thread");
        MyObjectPacket c1;

        try
        {
            File myFile = new File("/sdcard/"+fname);
            myFile.createNewFile();
            FileOutputStream f = new FileOutputStream(myFile);
            while(true)
            {
            byte[] buffer = new byte[10000];


            packet = new DatagramPacket(buffer, buffer.length);
            Log.v("ClientLog", "Receive step1");
            sock.receive(packet);
            buffer = packet.getData();
                //changed to implement stop and wait

                /*String str=new String("yes");
                Log.v("Acc","step1");
                sp=new DatagramPacket(str.getBytes(),str.getBytes().length,InetAddress.getByName("0.0.0.0"),7777);
                Log.v("Acc","step2");
                sock.send(sp);
                Log.v("Acc", "step3");*/
                //changed to implement stop and wait

            ByteArrayInputStream baos = new ByteArrayInputStream(buffer);
            //Log.v("ClientLog", "Receive step2");
            ObjectInputStream oos = new ObjectInputStream(baos);
            //Log.v("ClientLog", "Receive step3");
            c1 = (MyObjectPacket) oos.readObject();
                if(c1.Packetid.equals("end"))
                    break;
                f.write(c1.sdata.getBytes());
                f.flush();


                //Log.v("Print",c1.sdata);
            //c1.print();
                //Log.v("Finish","Running!!!");


            }

            f.close();
            Log.v("Finish","Done!!!");




            }
            catch (Exception e)
            {
            Log.v("ClientLog","Receive failed....Error="+e.toString());
            }

    }



}
