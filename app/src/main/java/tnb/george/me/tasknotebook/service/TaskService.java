package tnb.george.me.tasknotebook.service;

import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by GeorgeZou on 2014/12/3.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/12/3<br/>
 */
public class TaskService extends Service {

    private static final String TAG = "TaskService";
    private IBinder binder = new TaskService.TaskBinder();

    protected MediaPlayer mediaPlayer = null;
    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate");
        // if(mediaPlayer==null)
        //     mediaPlayer=MediaPlayer.create(this, uri);
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    public class TaskBinder extends Binder{

        TaskService getService(){
            return TaskService.this;

        }
    }

}
