package com.example.android.udpfileswipe;


import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class Server extends Thread {
        public DatagramSocket sock;
        public DatagramPacket sp,rp;
        public InetSocketAddress ip;
        public InetAddress Toip;
        public MyObjectPacket obj;
        public String filename, toipstr;

        public boolean accrec;   //changed to implement stop and wait

        Server(String toips, String f) {
            filename = f;
            toipstr = toips;
            try {
                sock = new DatagramSocket(null);
                Log.v("ServerLog", "Server Socket step1");
                ip = new InetSocketAddress("0.0.0.0", 7777);
                Log.v("ServerLog", "Server Socket step2");
                sock.bind(ip);
                Log.v("ServerLog", "Server Socket Created....");

            } catch (IOException e) {
                Log.v("ServerLog", "Server Socket failed....");
            }


        }


        public void run() {


            Log.v("ServerLog", "Server thread");


            try {
                Toip = InetAddress.getByName(toipstr);
                Log.v("ServerLog", "Toip created!!!!");
            } catch (IOException e) {
                Log.v("ServerLog", "ip creation failed!!!!");
            }

            byte[] b;
            byte[] buffer=new byte[1];
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard,filename);
            int n,count=1;
            String str="";

            accrec=false;//changed to implement stop and wait



            try {

                FileInputStream fin=new FileInputStream(file);
                while((n=fin.read(buffer) )!= -1)
                {   String str1=new String(buffer);
                    //Log.v("str1", str1);

                    str=str+str1;


                    if (count%10==0 )
                    {
                        //Log.v("string", str);
                        obj=new MyObjectPacket(count+"",filename,str);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        //Log.v("ServerLog", "baos");
                        ObjectOutputStream oos = new ObjectOutputStream(baos);
                        //Log.v("ServerLog", "oos");

                        oos.writeObject(obj);
                        oos.flush();

                        b= baos.toByteArray();
                        sp = new DatagramPacket(b,b.length,Toip,7776);
                        rp = new DatagramPacket(b,b.length);
                        sock.send(sp);

                        //changed to implement stop and wait
                        /*
                        Runnable r =new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Log.v("Runnable","Before Receive");
                                    sock.receive(rp);
                                    Log.v("Runnable", "After Receive");
                                    if(new String(rp.getData()).equals("yes"))
                                    {
                                        accrec=true;
                                        Log.v("Runnable","Confirmed!!!");
                                    }

                                }
                                catch (Exception e)
                                {
                                    Log.v("Runnable","Error="+e.toString());
                                }

                            }
                        };

                        Thread waitforacknowledgement =new Thread(r);
                        Log.v("Runnable","New Thread....");
                        waitforacknowledgement.start();
                        Log.v("Runnable", "New Thread Started...");
                        while(true)
                        {
                            TimeUnit.MICROSECONDS.sleep(10);
                            Log.v("Runnable", "While{}");
                            if(accrec)
                            {
                                Log.v("Runnable", "if{}");
                                accrec=false;
                                break;
                            }
                            else
                            {
                                sock.send(sp);
                                Log.v("Runnable","Sending packet again...");
                            }

                        }*/

                        //changed to implement stop and wait
                        str="";


                    }


                    buffer=new byte[1];
                    count++;
                }

                //Log.v("string", str);
                obj=new MyObjectPacket(count+"",filename,str);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //Log.v("ServerLog", "baos");
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                //Log.v("ServerLog", "oos");

                oos.writeObject(obj);
                oos.flush();

                b= baos.toByteArray();
                sp = new DatagramPacket(b,b.length,Toip,7776);
                sock.send(sp);

                obj=new MyObjectPacket("end","end","end");
                baos = new ByteArrayOutputStream();
                //Log.v("ServerLog", "baos");
                oos = new ObjectOutputStream(baos);
                //Log.v("ServerLog", "oos");

                oos.writeObject(obj);
                oos.flush();

                b= baos.toByteArray();
                sp = new DatagramPacket(b,b.length,Toip,7776);
                sock.send(sp);


            }
            catch (Exception e)
            {
                Log.v("ServerLog", "Sending failed....");
            }


        }
    }
