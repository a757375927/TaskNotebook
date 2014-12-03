package tnb.george.me.tasknotebook.biz;

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
public class TaskBiz {

    DBUtils utils;
    public final static String TABLE_NAME = "TaskNote";

    public TaskBiz(Context c){
        utils = new DBUtils(c);
    }

    public void save(Task task){
        ContentValues values = new ContentValues();
        values.put(task.NAME,task.getName());
        values.put(task.LOCATION,task.getLocation());
        values.put(task.DESCRIPTION,task.getDescription());
        values.put(task.BEGINDATE,task.getBeginDate().getTime());
        values.put(task.ENDDATE,task.getEndDate().getTime());
        values.put(task.CREATEDATE,task.getCreateDate().getTime());
        utils.insert(values,TABLE_NAME);
    }

    public List<Task> getTaskList(){
        List<Task> res = new ArrayList<Task>();
        Cursor c = utils.query("TaskNote");
        while( c.moveToNext() ){
            int id = c.getColumnIndex( Task.ID );
            String name = c.getString(1);
            String location = c.getString(2);
            String description = c.getString(3);
            Date beginDate = new Date(c.getLong(4));
            Date endDate = new Date(c.getLong(5));
            Date createDate = new Date(c.getLong(6));
            res.add( new Task(id,name,location,description,beginDate,endDate,createDate));
        }

        return res;
    }

    public void delete(int id){
        utils.del(id,TABLE_NAME);
    }


}
