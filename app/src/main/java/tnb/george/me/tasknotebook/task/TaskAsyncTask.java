package tnb.george.me.tasknotebook.task;

import android.os.AsyncTask;

import tnb.george.me.tasknotebook.bean.Task;

/**
 * Created by GeorgeZou on 2014/11/27.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/27<br/>
 */
public class TaskAsyncTask extends AsyncTask{
    Task task;
    public TaskAsyncTask(Task task){
        this.task = task;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
}
