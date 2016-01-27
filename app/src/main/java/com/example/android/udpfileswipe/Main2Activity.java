package com.example.android.udpfileswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    Button OnlySend,OnlyRec,SendRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        OnlySend=(Button)findViewById(R.id.send);
        OnlyRec=(Button)findViewById(R.id.rec);
        SendRec=(Button)findViewById(R.id.sendrec);
    }

    public void onSendTap(View view)
    {
        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }

    public  void onRecTap(View view)
    {
        Intent intent=new Intent(Main2Activity.this,ReceiveActivity.class);
        startActivity(intent);
    }

    public void onBothTap(View view)
    {
        Intent intent=new Intent(Main2Activity.this,BothActivity.class);
        startActivity(intent);
    }
}
