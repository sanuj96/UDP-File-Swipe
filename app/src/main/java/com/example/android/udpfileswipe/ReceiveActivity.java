package com.example.android.udpfileswipe;

import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReceiveActivity extends AppCompatActivity {
    TextView t3,t7;
    EditText e3;
    Button b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        t3=(TextView)findViewById(R.id.textView3);
        t7=(TextView)findViewById(R.id.t7);
        e3=(EditText)findViewById(R.id.editText3);
        e3.setText("xyz.txt");
        b3=(Button)findViewById(R.id.recbutton);

        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        t7.setText("My IP :"+ip);

    }


    public void onReceiveTap(View view)
    {
        Client C=new Client(e3.getText().toString());
        C.start();
    }
}
