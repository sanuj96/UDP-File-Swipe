package com.example.android.udpfileswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    public EditText e1,e2;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        e1=(EditText)findViewById(R.id.editText);
        e1.setText("0.0.0.0");
        e2=(EditText)findViewById(R.id.editText2);
        e2.setText("abc.txt");
        send=(Button)findViewById(R.id.button);
    }

    public void onTap(View view)
    {
        Toast.makeText(this, "DestIP=" + e1.getText() + "\n" + "Filename=" + e2.getText(), Toast.LENGTH_SHORT).show();
        Server S=new Server(e1.getText().toString(),e2.getText().toString());
        S.start();

    }
}
