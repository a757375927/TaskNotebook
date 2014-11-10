package tnb.george.me.tasknotebook.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.bean.MenuItem;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.ui.base.MenuDrawerActivity;
import tnb.george.me.tasknotebook.utils.DBUtils;

/**
 * Created by GeorgeZou on 2014/11/10.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/10<br/>
 */
public class TaskListActivity extends MenuDrawerActivity{

    private String LOG_TAG = "";

    protected ListView taskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("任务列表");
        final DBUtils utils = new DBUtils(this);
        Cursor c = utils.query();
        while(c.moveToNext()){
            int id = c.getColumnIndex(Task.ID);
           // c.getString(Task.CREATEDATE);
            c.getColumnIndex(Task.TASKDATE);
            c.getColumnIndex(Task.TASKINFO);

        }
        String[] from = {Task.ID,Task.CREATEDATE,Task.TASKDATE,Task.TASKINFO};
        int[] to = {R.id.task_list_item_id,R.id.task_list_item_createTime,R.id.task_list_item_taskTime,R.id.task_list_item_taskInfo};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.task_list_row_item,c,from,to);
        taskListView = (ListView)findViewById(R.id.task_list_listview);
        taskListView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //菜单
        menuDrawer.setContentView(R.layout.activity_task_list);
    }


    @Override
    protected void onMenuItemClicked(int position, MenuItem item) {

    }


    public class TaskListAdapter extends BaseAdapter {

        View[] itemsViews;

        public TaskListAdapter(List<Task> items){
            itemsViews = new View[items.size()];
            for(int i = 0;i<items.size();i++){
                itemsViews[i] = makeItemView(items.get(i));
            }
        }

        public View makeItemView(Task task) {
            LayoutInflater inflater = (LayoutInflater) TaskListActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.task_list_row_item,null);

            TextView title = (TextView) itemView.findViewById(R.id.task_list_item_taskInfo);
            title.setText(task.getTaskInfo());
            TextView text = (TextView) itemView.findViewById(R.id.task_list_item_taskTime);
            text.setText(task.getTaskTime().toString());
            ImageView image = (ImageView) itemView.findViewById(R.id.task_list_item_taskIcon);
            image.setImageResource(R.drawable.actionbar_add_icon);

            return itemView;
        }


        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                return itemsViews[position];
            return convertView;
        }
    }

}
