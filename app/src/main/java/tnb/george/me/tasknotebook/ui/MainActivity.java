package tnb.george.me.tasknotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.bean.MenuItem;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.service.TaskService;
import tnb.george.me.tasknotebook.ui.base.MenuDrawerActivity;
import tnb.george.me.tasknotebook.utils.StringUtils;
import tnb.george.me.tasknotebook.utils.UIUtils;

/**
 * Created by GeorgeZou on 2014/10/29.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/10/29<br/>
 */
public class MainActivity extends MenuDrawerActivity {

    private final String LOG_TAG = "MAINACTIVITY";
    Button commitBtn;
    Button toWNLBtn;
    EditText datetimeTxt;
    EditText taskInfoTxt;
    EditText dateinTxt;
    EditText timeinTxt;

    TaskService taskService = new TaskService(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(LOG_TAG,"COME INTO onCreate function");

    }

    @Override
    protected void onStart() {
        super.onStart();
        //菜单
        initView();
    }

    @Override
    protected void onMenuItemClicked(int position, MenuItem item) {
        UIUtils.showLong(this,"on click menu item:"+item.mTitle);
    }

    /**
     * 绑定控件
     */
    private void initView(){
        menuDrawer.setContentView(R.layout.activity_main);
        commitBtn = (Button)findViewById(R.id.commitBtn);
        toWNLBtn = (Button)findViewById(R.id.toWNLBtn);
        datetimeTxt = (EditText)findViewById(R.id.dateTimeTxt);
        taskInfoTxt = (EditText)findViewById(R.id.taskInfoTxt);
        dateinTxt = (EditText)findViewById(R.id.dateinTxt);
        timeinTxt = (EditText)findViewById(R.id.timeinTxt);
        commitBtn.setOnClickListener(commitListener);
        toWNLBtn.setOnClickListener(toWNLListener);

    }
    /**
     * 提交按钮点击事件
     */
    View.OnClickListener commitListener =  new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            String datetime = datetimeTxt.getText().toString();
            String taskInfo = taskInfoTxt.getText().toString();

            if(StringUtils.isEmpty(datetime) || StringUtils.isEmpty(taskInfo)){
                UIUtils.showLong(MainActivity.this,getString(R.string.infoNotComplete));
            }

            Toast.makeText(MainActivity.this, "datetime:"+datetime+" taskInfo:"+taskInfo, Toast.LENGTH_LONG).show();

            Date dateNew = null;
            try {
                dateNew = StringUtils.stringToDate(datetime);
            }catch(ParseException ex){
                UIUtils.showLong(MainActivity.this,getString(R.string.dataTypeNotAvailable));
            }
            Toast.makeText(MainActivity.this, "commit Listener ", Toast.LENGTH_LONG).show();
            Task task = new Task("1",taskInfo,new Date(),dateNew);
            UIUtils.showLong(MainActivity.this,"准备添加内容:"+dateNew.toString()+" "+taskInfo);

            try {
                taskService.save(task);
            }catch(Exception ex) {
                UIUtils.showLong(MainActivity.this,"ERROR:" + ex.getMessage());
                Log.i("TaskNotebook-->", "exception MainAct(88):" + ex.getMessage());
            }
        }
    };

    /**
     * 到万年历activity的点击事件
     */
    View.OnClickListener toWNLListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,WNLActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == WNLActivity.TO_ADD_TASK){
            Bundle bundle = data.getExtras();
            String date = bundle.getString("DATE");
            UIUtils.showLong(this,date);
        }
    }

}
