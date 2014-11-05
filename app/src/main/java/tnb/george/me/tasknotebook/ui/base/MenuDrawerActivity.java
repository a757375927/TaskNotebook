package tnb.george.me.tasknotebook.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import java.util.ArrayList;
import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.adapter.MenuAdapter;
import tnb.george.me.tasknotebook.bean.MenuCategory;
import tnb.george.me.tasknotebook.bean.MenuItem;

/**
 * Created by GeorgeZou on 2014/11/5.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/5<br/>
 */
public class MenuDrawerActivity extends Activity implements MenuAdapter.MenuListener{


    protected MenuAdapter menuAdapter;
    protected ListView listView;
    private int mActivePosition = 0;
    private int width;
    private MenuDrawer menuDrawer;

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
        menuDrawer.setContentView(R.layout.activity_main);
        List<Object> items = new ArrayList<Object>();
        items.add(new MenuItem("TestMenu1",R.drawable.icon_search_pressed));
        items.add(new MenuItem("TestMenu1",R.drawable.ic_action_refresh_dark));
        items.add(new MenuCategory("Cat 2"));
        items.add(new MenuItem("设置",R.drawable.menu_icon_setting));
        items.add(new MenuItem("关于",R.drawable.menu_about));
        items.add(new MenuCategory(" "));
        items.add(new MenuItem("退出",R.drawable.menu_exit));

        listView = new ListView(this);
        menuAdapter = new MenuAdapter(this,items);
        menuAdapter.setMenuListener(this);
        menuAdapter.setmActivePosition(mActivePosition);
        listView.setAdapter(menuAdapter);
        menuDrawer.setupUpIndicator(this);
        menuDrawer.setDrawerIndicatorEnabled(true);
        menuDrawer.setMenuView(listView);
        menuDrawer.setMenuSize(width);

    }


    @Override
    public void onActiveViewChange(View v) {

    }
}
