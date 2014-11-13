package tnb.george.me.tasknotebook.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.utils.DBUtils;
import tnb.george.me.tasknotebook.utils.UIUtils;

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
        values.put("taskTime",task.getTaskTime().getTime());
        values.put("createTime",task.getCreateTime().getTime());
        utils.insert(values);
    }

    public List<Task> getTaskList(){
        List<Task> res = new ArrayList<Task>();
        Cursor c = utils.query();

        res.add(new Task(new Date(),"aaa"));
        while( c.moveToNext() ){
            int id = c.getColumnIndex( Task.ID );
            // c.getString(Task.CREATEDATE);
            String taskInfo = c.getString( 2 );
            Date taskTime = new Date(c.getLong(4));
            res.add( new Task(taskTime,taskInfo));
        }

         /*
        String[] from = { Task.ID,Task.TASKDATE,Task.TASKINFO };
        int[] to = { R.id.task_list_item_id,R.id.task_list_item_taskTime,R.id.task_list_item_taskInfo };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter( this,R.layout.task_list_row_item,c,from,to );
        taskListView = ( ListView )findViewById( R.id.task_list_listview );
        taskListView.setAdapter( adapter );
        */
        return res;
    }

    public void delete(int id){

    }


}
