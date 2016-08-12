package com.example.serialtest;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private SerialPortUtil mSerialPortUtil = null;
    private SerialPortUtil.OnDataReceiveListener lister = new SerialPortUtil.OnDataReceiveListener() {
        @Override
        public void onDataReceive(byte[] buffer, int size) {
        }
    };
    private SendBuffThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSerialPortUtil = SerialPortUtil.getInstance();
        mSerialPortUtil.setOnDataReceiveListener(lister);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        thread = new SendBuffThread();
        thread.start();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private class SendBuffThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true) {
            try {
                    Thread.sleep(1000);
                    byte[] buff = {'w', 'g', 't','\n'};
                    mSerialPortUtil.sendBuffer(buff);
                    Log.d("wuguitong", "-----------------------------send---------------------------");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
