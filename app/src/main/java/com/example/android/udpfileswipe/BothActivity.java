package com.example.android.udpfileswipe;

import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BothActivity extends AppCompatActivity {
    TextView t1,t2,t3,iptv;
    EditText e1,e2,e3;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_both);
        t1=(TextView)findViewById(R.id.textView4);
        t1=(TextView)findViewById(R.id.textView5);
        t1=(TextView)findViewById(R.id.textView6);
        iptv=(TextView)findViewById(R.id.Myip);
        e1=(EditText)findViewById(R.id.editText4);
        e1.setText("0.0.0.0");
        e2=(EditText)findViewById(R.id.editText5);
        e2.setText("abc.txt");
        e3=(EditText)findViewById(R.id.editText6);
        e3.setText("xyz.txt");
        b=(Button)findViewById(R.id.button2);

        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        iptv.setText("My IP :"+ip);
    }

    public void onTap(View view)
    {
        Toast.makeText(this, "DestIP :" + e1.getText() + "\n" + "Filename :" + e2.getText()+"\nSave As :"+e3.getText(), Toast.LENGTH_SHORT).show();
        Server S=new Server(e1.getText().toString(),e2.getText().toString());
        Client C=new Client(e3.getText().toString());
        C.start();
        S.start();
    }
}
