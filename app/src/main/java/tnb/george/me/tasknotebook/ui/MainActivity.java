package tnb.george.me.tasknotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.utils.StringUtils;
import tnb.george.me.tasknotebook.utils.UIUtils;


public class MainActivity extends BaseActivity {

    Button commitBtn;
    Button toWNLBtn;

    EditText datetimeTxt;
    EditText taskInfoTxt;
    EditText dateinTxt;
    EditText timeinTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                UIUtils.showToask0(getApplicationContext(),getString(R.string.infoNotComplete));
            }
            Date dateNew = null;
            try {
                dateNew = StringUtils.stringToDate(datetime);
            }catch(ParseException ex){
                UIUtils.showToask0(getApplicationContext(),getString(R.string.dataTypeNotAvailable));
            }
            Task task = new Task("1",taskInfo,new Date(),dateNew);

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
