package com.example.jianfeng.l003usingservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.example.jianfeng.l003usingservice.EchoService.*;


public class MainActivity extends Activity implements View.OnClickListener, ServiceConnection {

    private Button btnStartService,btnStopService,btnBindService,btnUnbindService,btnGetCurrentNumber;
    private Intent serviceIntent;
    private EchoService echoService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent = new Intent(this,EchoService.class);

        btnStartService = (Button)findViewById(R.id.btnStartService);
        btnStopService = (Button)findViewById(R.id.btnStopServcie);
        btnBindService = (Button)findViewById(R.id.btnBindService);
        btnUnbindService = (Button)findViewById(R.id.btnUnbindService);
        btnGetCurrentNumber = (Button) findViewById(R.id.btnGetCurrentNumber);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnGetCurrentNumber.setOnClickListener(this);
        System.out.println("onCreate");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnStartService:
                startService(serviceIntent);
                break;
            case R.id.btnStopServcie:
                stopService(serviceIntent);
                break;
            case R.id.btnBindService:
                bindService(serviceIntent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                echoService = null;
                break;
            case R.id.btnGetCurrentNumber:
                if(echoService!=null)
                {
                    System.out.println("当前服务中的数字是："+echoService.getCurrentNum());
                }
                break;
        }

    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        System.out.println("onServiceConnected");
        echoService = ((EchoService.EchoServiceBinder)iBinder).getService();

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        System.out.println("onServiceDisconnected");
    }
}
