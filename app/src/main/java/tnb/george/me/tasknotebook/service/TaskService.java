package tnb.george.me.tasknotebook.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.utils.DBUtils;

/**
 * Created by GeorgeZou on 2014/10/29.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/10/29<br/>
 */
public class TaskService {

    DBUtils utils;
    public TaskService(Context c){
        utils = new DBUtils(c);
    }

    public void save(Task task){
        ContentValues values = new ContentValues();
        values.put("name",task.getName());
        values.put("taskInfo",task.getTaskInfo());
        values.put("taskDate",task.getTaskDate().getTime());
        values.put("createDate",task.getCreateDate().getTime());
        utils.insert(values);
    }

    public List<Task> getTaskList(){
        List<Task> res = new ArrayList<Task>();

        return res;
    }

    public void delete(int id){

    }


}
