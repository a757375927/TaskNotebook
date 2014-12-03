package tnb.george.me.tasknotebook.ui;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.adapter.MyTaskListAdapter;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.biz.TaskBiz;
import tnb.george.me.tasknotebook.ui.base.MenuDrawerActivity;

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
    protected TaskBiz taskBiz = new TaskBiz(this);
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

        List<Task> myTask = taskBiz.getTaskList();
        myTaskListAdapter = new MyTaskListAdapter(this,myTask);
        taskListView = ( ListView )findViewById( R.id.task_list_listview );
        taskListView.setAdapter( myTaskListAdapter );
    }

}
