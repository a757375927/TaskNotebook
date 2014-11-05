package tnb.george.me.tasknotebook.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.bean.MenuCategory;
import tnb.george.me.tasknotebook.bean.MenuItem;
import tnb.george.me.tasknotebook.utils.UIUtils;

/**
 * Created by GeorgeZou on 2014/11/4.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/4<br/>
 */
public class MenuAdapter extends BaseAdapter{

    String LOG_TAG = "BASEADAPTER";

    public interface MenuListener{
        void onActiveViewChange(View v);
    }

    public MenuAdapter(Context context,List<Object> mItem){
        this.mContext = context;
        this.mItem = mItem;
    }

    private Context mContext;
    private List<Object> mItem;
    private MenuListener menuListener;
    private int mActivePosition = -1;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<Object> getmItem() {
        return mItem;
    }

    public void setmItem(List<Object> mItem) {
        this.mItem = mItem;
    }

    public MenuListener getMenuListener() {
        return menuListener;
    }

    public void setMenuListener(MenuListener menuListener) {
        this.menuListener = menuListener;
    }

    public int getmActivePosition() {
        return mActivePosition;
    }

    public void setmActivePosition(int mActivePosition) {
        this.mActivePosition = mActivePosition;
    }



    @Override
    public int getCount() {
        return mItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position){ return getItem(position) instanceof MenuItem ? 0:1;}

    @Override
    public int getViewTypeCount(){return 2;}
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        Object item = getItem(i);

        if(item instanceof MenuCategory){
            if(v == null){
               v = LayoutInflater.from(mContext).inflate(R.layout.menu_row_category,viewGroup,false);

            }
            ((TextView)v).setText(((MenuCategory) item).mTitle);

        }else{
            if(v == null) {
                v = LayoutInflater.from(mContext).inflate(R.layout.menu_row_item, viewGroup, false);
            }
            TextView tv = (TextView)v;
            tv.setText(((MenuItem)item).mTitle);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(((MenuItem)item).mIconRes,0,0,0);
            }else{
                tv.setCompoundDrawablesWithIntrinsicBounds(((MenuItem)item).mIconRes,0,0,0);
            }

        }
        v.setTag(R.id.mdActiveViewPosition,i);

        if(i == mActivePosition){
            menuListener.onActiveViewChange(v);
        }
        return v;
    }
}
