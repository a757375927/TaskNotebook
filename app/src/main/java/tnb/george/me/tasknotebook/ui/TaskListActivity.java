package tnb.george.me.tasknotebook.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.service.TaskService;
import tnb.george.me.tasknotebook.utils.DBUtils;

public class TaskListActivity extends ListActivity {

    TaskService taskService = new TaskService(this);

    DBUtils utils  = new DBUtils(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(getString(R.string.tasklistactivityTitle));
        taskService.getTaskList();
        refreshListDate();
        ListView listView = getListView();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final long temp = l;
                builder.setMessage(getString(R.string.reallyWantToDelete)).setPositiveButton("是",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                utils.del((int)temp);
                                refreshListDate();
                            }
                        });
            }
        });
        setContentView(R.layout.activity_task_list);
    }

    public void refreshListDate(){
        Cursor c = utils.query();
        String[] from = {"id","name","taskInfo","taskDate"};
        int[] to =  { R.id.text0, R.id.text1, R.id.text2, R.id.text3 };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.row,c,from,to);
        ListView listView = getListView();
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
