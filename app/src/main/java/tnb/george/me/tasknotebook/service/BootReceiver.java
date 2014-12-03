package tnb.george.me.tasknotebook.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by GeorgeZou on 2014/12/3.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/12/3<br/>
 */
public class BootReceiver extends BroadcastReceiver {

    private final String TAG = "BootReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        //后面XXX.class就是要启动的服务
        Intent serviceIntent = new Intent(context,TaskService.class);
        context.startService(serviceIntent);
        Log.v(TAG,"开机服务启动...");
        //启动应用，参数为需要自动启动的应用的包名
        //Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
        //context.startActivity(intent );
    }
}
