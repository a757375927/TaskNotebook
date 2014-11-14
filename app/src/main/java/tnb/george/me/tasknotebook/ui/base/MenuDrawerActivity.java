package tnb.george.me.tasknotebook.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import java.util.ArrayList;
import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.adapter.MenuAdapter;
import tnb.george.me.tasknotebook.bean.MenuCategory;
import tnb.george.me.tasknotebook.bean.MenuItem;
import tnb.george.me.tasknotebook.ui.MainActivity;
import tnb.george.me.tasknotebook.ui.TaskListActivity;
import tnb.george.me.tasknotebook.ui.WNLActivity;
import tnb.george.me.tasknotebook.ui.notboringactionbar.NoBoringActionBarActivity;
import tnb.george.me.tasknotebook.utils.UIUtils;

/**
 * Created by GeorgeZou on 2014/11/5.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/5<br/>
 */
public abstract class MenuDrawerActivity extends Activity implements MenuAdapter.MenuListener{

    protected MenuAdapter menuAdapter;
    protected ListView listView;
    protected MenuDrawer menuDrawer;
    private int mActivePosition = 0;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = (int) (metric.widthPixels / 10 * 4.62); // 获取屏幕宽度（像素），并且侧滑菜单占6/10比例，接近黄金比例~

    }

    @Override
    protected void onStart() {
        super.onStart();
        initMenuDrawer();
    }

    private void initMenuDrawer(){
        menuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.LEFT);
        List<Object> items = new ArrayList<Object>();
        items.add(new MenuItem("我的首页",R.drawable.mm_title_btn_keyboard_normal));
        items.add(new MenuItem("任务列表",R.drawable.menu_about));
        items.add(new MenuItem("万年历",R.drawable.com_toolbar_keyboard_pressed));
        items.add(new MenuCategory("Cat 2"));
        items.add(new MenuItem("历史任务",R.drawable.com_toolbar_keyboard_pressed));
        items.add(new MenuItem("设置",R.drawable.menu_icon_setting));
        items.add(new MenuItem("关于",R.drawable.menu_about));
        items.add(new MenuCategory(" "));
        items.add(new MenuItem("退出",R.drawable.menu_exit));

        listView = new ListView(this);
        menuAdapter = new MenuAdapter(this,items);
        menuAdapter.setMenuListener(this);
        menuAdapter.setmActivePosition(mActivePosition);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(mItemClickListener);
        menuDrawer.setupUpIndicator(this);
        menuDrawer.setDrawerIndicatorEnabled(true);
        menuDrawer.setMenuView(listView);
        menuDrawer.setMenuSize(width);

    }


    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener(){

        public void onItemClick(AdapterView<?> parent,View view ,int position,long id){
            if(menuAdapter.getItem(position) == null || menuAdapter.getItem(position) instanceof MenuCategory)
                return;

            mActivePosition = position;
            menuDrawer.setActiveView(view,position);
            menuAdapter.setmActivePosition(position);
            onMenuItemClicked(position,(MenuItem)menuAdapter.getItem(position));

        }
    };

    protected void onMenuItemClicked(int position,MenuItem item){
        UIUtils.showLong(this,"title："+item.mTitle+ " position:"+position);

        Intent intent;
        switch(position){
            case 0:
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this,TaskListActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this,WNLActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this,NoBoringActionBarActivity.class);
                startActivity(intent);
                break;


        }
    }

    @Override
    public void onActiveViewChange(View v) {

    }
}
