package tnb.george.me.tasknotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import me.drakeet.materialdialog.MaterialDialog;
import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.biz.TaskBiz;
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
public class MainActivity extends MenuDrawerActivity implements View.OnTouchListener {

    private final String LOG_TAG = "MAINACTIVITY";

    protected Button commitBtn;
    protected Button toWNLBtn;
    protected Button toTaskListBtn;

    EditText datetimeTxt;
    EditText taskInfoTxt;
    EditText nameTxt;
    EditText locationTxt;
    EditText descriptionTxt;

    EditText beginDateTxt;
    EditText endDateTxt;

    TaskBiz taskBiz = new TaskBiz(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //菜单
        initView();
    }

    /**
     * 绑定控件
     */
    private void initView(){
        menuDrawer.setContentView(R.layout.activity_main);
        commitBtn = (Button)findViewById(R.id.commitBtn);
        toWNLBtn = (Button)findViewById(R.id.toWNLBtn);
        toTaskListBtn = (Button)findViewById(R.id.toTaskListBtn);

        nameTxt = (EditText)findViewById(R.id.nameTxt);
        descriptionTxt = (EditText)findViewById(R.id.descriptionTxt);
        locationTxt = (EditText)findViewById(R.id.locationTxt);

        beginDateTxt = (EditText)this.findViewById(R.id.beginDateTimeTxt);
        endDateTxt = (EditText)this.findViewById(R.id.endDateTimeTxt);


        beginDateTxt.setOnTouchListener(this);
        endDateTxt.setOnTouchListener(this);

        //绑定事件
        commitBtn.setOnClickListener(commitListener);
        toWNLBtn.setOnClickListener(toWNLListener);
        toTaskListBtn.setOnClickListener(toTaskListListener);

    }
    /**
     * 提交按钮点击事件
     */
    View.OnClickListener commitListener =  new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            String beginDateStr = beginDateTxt.getText().toString();
            String endDateStr = endDateTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            String location = locationTxt.getText().toString();
            String name =nameTxt.getText().toString();

            if(StringUtils.isEmpty(beginDateStr) || StringUtils.isEmpty(endDateStr)){
                UIUtils.showLong(MainActivity.this,getString(R.string.infoNotComplete));
                return;
            }

            Date beginDate = StringUtils.stringToDate(beginDateStr,StringUtils.DATE_TIME_FORMATE);
            Date endDate = StringUtils.stringToDate(endDateStr,StringUtils.DATE_TIME_FORMATE);

            Task task = new Task(0,name,location,description,beginDate,endDate,new Date());

            taskBiz.save(task);

            UIUtils.showLong(MainActivity.this,getString(R.string.add_success));
            toTaskListActivity();
        }
    };


    protected void toTaskListActivity(){
        Intent intent = new Intent(MainActivity.this,TaskListActivity.class);
        startActivity(intent);
    }
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

    /**
     * 到万年历activity的点击事件
     */
    View.OnClickListener toTaskListListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            toTaskListActivity();
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

    /**
     * 日期时间EditView 选择框点击事件
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        //是否重复点击
        if(UIUtils.isFastDoubleClick())
            return false;
        //ACTION_DOWN和ACTION_UP单点触摸屏幕，按下去和放开的操作；
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){

            View viewDateTimeDelog = View.inflate(this,R.layout.date_time_delog,null);
            final DatePicker datePicker = (DatePicker)viewDateTimeDelog.findViewById(R.id.date_picker);
            final TimePicker timePicker = (TimePicker)viewDateTimeDelog.findViewById(R.id.time_picker);

            final MaterialDialog mMaterialDialog = new MaterialDialog(this).setContentView(viewDateTimeDelog);

            mMaterialDialog  .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();

                        }
                    });

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),null);

            timePicker.setIs24HourView(true);
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(Calendar.MINUTE);

            if(view.getId() == R.id.beginDateTimeTxt){
                final int inType = beginDateTxt.getInputType();
                beginDateTxt.setInputType(InputType.TYPE_NULL);
                beginDateTxt.onTouchEvent(motionEvent);
                beginDateTxt.setInputType(inType);
                beginDateTxt.setSelection(beginDateTxt.getText().length());

                mMaterialDialog.setPositiveButton("确认开始时间", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StringBuffer sb = new StringBuffer();
                                sb.append(String.format("%d-%02d-%02d",
                                        datePicker.getYear(),
                                        datePicker.getMonth() + 1,
                                        datePicker.getDayOfMonth()));
                                sb.append("  ");
                                sb.append(timePicker.getCurrentHour()).append(":")
                                        .append(timePicker.getCurrentMinute());
                                beginDateTxt.setText(sb);
                                endDateTxt.requestFocus();
                                mMaterialDialog.dismiss();
                            }
                        });
            }else if(view.getId() == R.id.endDateTimeTxt){
                final Date beginDate = StringUtils.stringToDate(beginDateTxt.getText().toString(), StringUtils.DATE_TIME_FORMATE);

                int inType = endDateTxt.getInputType();
                endDateTxt.setInputType(InputType.TYPE_NULL);
                endDateTxt.onTouchEvent(motionEvent);
                endDateTxt.setInputType(inType);
                endDateTxt.setSelection(endDateTxt.getText().length());

                mMaterialDialog.setPositiveButton("确认开始时间", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        sb.append(timePicker.getCurrentHour())
                                .append(":").append(timePicker.getCurrentMinute());

                        Date endDate = StringUtils.stringToDate(sb.toString(), StringUtils.DATE_TIME_FORMATE);
                        if(endDate.getTime() < beginDate.getTime()){
                            UIUtils.showLong(MainActivity.this,"结束时间不能早于开始时间");
                        }else{
                            endDateTxt.setText(sb);
                            mMaterialDialog.dismiss();
                        }
                    }
                });
            }
            mMaterialDialog.show();

            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View viewDateTimeDelog = View.inflate(this,R.layout.date_time_delog,null);
            final DatePicker datePicker = (DatePicker)viewDateTimeDelog.findViewById(R.id.date_picker);
            final TimePicker timePicker = (TimePicker)viewDateTimeDelog.findViewById(R.id.time_picker);
            builder.setView(viewDateTimeDelog);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),null);

            timePicker.setIs24HourView(true);
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(Calendar.MINUTE);

            if(view.getId() == R.id.beginDateTimeTxt){
                final int inType = beginDateTxt.getInputType();
                beginDateTxt.setInputType(InputType.TYPE_NULL);
                beginDateTxt.onTouchEvent(motionEvent);
                beginDateTxt.setInputType(inType);
                beginDateTxt.setSelection(beginDateTxt.getText().length());

                builder.setTitle("选择起始时间");
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        sb.append(timePicker.getCurrentHour()).append(":")
                                .append(timePicker.getCurrentMinute());
                        beginDateTxt.setText(sb);
                        endDateTxt.requestFocus();
                    }
                });
            }else if(view.getId() == R.id.endDateTimeTxt){
                final Date beginDate = StringUtils.stringToDate(beginDateTxt.getText().toString(), StringUtils.DATE_TIME_FORMATE);

                int inType = endDateTxt.getInputType();
                endDateTxt.setInputType(InputType.TYPE_NULL);
                endDateTxt.onTouchEvent(motionEvent);
                endDateTxt.setInputType(inType);
                endDateTxt.setSelection(endDateTxt.getText().length());
                builder.setTitle("选取结束时间");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        sb.append(timePicker.getCurrentHour())
                                .append(":").append(timePicker.getCurrentMinute());

                        Date endDate = StringUtils.stringToDate(sb.toString(), StringUtils.DATE_TIME_FORMATE);
                        if(endDate.getTime() < beginDate.getTime()){
                            UIUtils.showLong(MainActivity.this,"结束时间不能早于开始时间");
                        }else{
                            endDateTxt.setText(sb);
                            dialog.cancel();
                        }
                    }
                });

            }
            Dialog dialog = builder.create();
            dialog.show();*/

        }

        return true;
    }

}
