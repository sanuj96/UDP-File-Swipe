package com.example.android.udpfileswipe;


import android.util.Log;

import java.io.Serializable;

public class MyObjectPacket implements Serializable {


    private static final long serialVersionUID = 0L;
    public String Packetid , fname ,sdata;

    MyObjectPacket(String Pid,String filename,String s)
    {
        Packetid=Pid;
        fname=filename;
        sdata = s;

    }

    public void  setPacketid(String Pid)
    {
        Packetid=Pid;
    }

    public void setSdata(String Sd)
    {
        sdata=Sd;
    }

    public void setfname(String fn)
    {
        fname=fn;
    }



    public void print()
    {
        Log.v("CLog",Packetid+"--"+fname+"--"+sdata);
    }
    public void print2()
    {
        Log.v("SLog",Packetid+"--"+fname+"--"+sdata);
    }


}
