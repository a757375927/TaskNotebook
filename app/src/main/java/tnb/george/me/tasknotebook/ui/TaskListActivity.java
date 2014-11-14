package tnb.george.me.tasknotebook.ui;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.adapter.MyTaskListAdapter;
import tnb.george.me.tasknotebook.bean.MenuItem;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.service.TaskService;
import tnb.george.me.tasknotebook.ui.base.MenuDrawerActivity;
import tnb.george.me.tasknotebook.utils.DBUtils;
import tnb.george.me.tasknotebook.utils.UIUtils;

/**
 * Created by GeorgeZou on 2014/11/10.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/10<br/>
 */
public class TaskListActivity extends MenuDrawerActivity{

    private String LOG_TAG = "TASK_LIST_ACTIVITY";

    protected ListView taskListView;
    protected TaskService taskService = new TaskService(this);
    protected MyTaskListAdapter myTaskListAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
    }

    @Override
    protected void onStart() {
        super.onStart();
        //菜单
        menuDrawer.setContentView(R.layout.activity_task_list);
        this.setTitle("任务列表");

        List<Task> myTask = taskService.getTaskList();
        myTaskListAdapter = new MyTaskListAdapter(this,myTask);
        taskListView = ( ListView )findViewById( R.id.task_list_listview );
        taskListView.setAdapter( myTaskListAdapter );
    }

}
