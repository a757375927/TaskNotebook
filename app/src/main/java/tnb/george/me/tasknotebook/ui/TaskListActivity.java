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


    @Override
    protected void onMenuItemClicked(int position, MenuItem item) {

    }

    public class MyTaskListAdapter extends BaseAdapter{

        Context mContext;
        List<Task> mTask;

        public MyTaskListAdapter(Context context,List<Task> task) {
            super();
            mContext = context;
            mTask = task;
        }

        private int[] colors = new int[]{ 0xff626569, 0xff4f5257 };

        public View getView(final int position,View convertView,ViewGroup parent) {
            ImageView image = null;
            TextView title = null;
            TextView text = null;
            Button button = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.task_list_item, null
                );
                image = (ImageView) convertView.findViewById(R.id.array_image);
                title = (TextView) convertView.findViewById(R.id.array_title);
                text = (TextView) convertView.findViewById(R.id.array_text);
                button = (Button) convertView.findViewById(R.id.array_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        UIUtils.showLong(TaskListActivity.this, "您点击的第" + position + "个按钮");

                    }
                });

            }
            int colorPos = position % colors.length;
            convertView.setBackgroundColor(colors[colorPos]);
            title.setText(mTask.get(position).getTaskTime().toString());
            text.setText(mTask.get(position).getTaskInfo());
            if(colorPos == 0)
                image.setImageResource(R.drawable.actionbar_add_icon);
            else
                image.setImageResource(R.drawable.actionbar_more_icon);
            return convertView;

        }

        public int getCount() {
            return mTask.size();
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }
    }

}
