package tnb.george.me.tasknotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.ui.base.MenuDrawerActivity;
import tnb.george.me.tasknotebook.utils.UIUtils;

/**
 * 万年历页面
 */
public class WNLActivity extends MenuDrawerActivity {


    public static final int TO_TASKINFO_PAGE = 1000;    //到任务详情页面的标识
    public static final int TO_ADD_TASK = 2000;         //到添加任务页面的标识

    private ImageButton wnlAddBtn;
    private  CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();

    }

    private void initView(){
        menuDrawer.setContentView(R.layout.activity_wnl);

        wnlAddBtn = (ImageButton)findViewById(R.id.wnl_add_btn);
        wnlAddBtn.setOnClickListener(toAddPageBtnListener);

        calendarView = (CalendarView)findViewById(R.id.wnl_mainCalendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = year + "-" + month +"-" + dayOfMonth;

                UIUtils.showLong(WNLActivity.this,date);
            }
        });


    }

    private View.OnClickListener toAddPageBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("date",calendarView.getDate());
            setResult(TO_ADD_TASK,intent);
            finish();
        }
    };

    private View.OnClickListener toTaskInfoPageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("date",calendarView.getDate());
            setResult(TO_TASKINFO_PAGE ,intent);
            finish();
        }
    };


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

    @Override
    protected void onMenuItemClicked(int position, tnb.george.me.tasknotebook.bean.MenuItem item) {

    }

}
