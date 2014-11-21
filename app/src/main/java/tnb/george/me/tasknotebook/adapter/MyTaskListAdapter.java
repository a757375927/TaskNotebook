package tnb.george.me.tasknotebook.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tnb.george.me.tasknotebook.R;
import tnb.george.me.tasknotebook.bean.Task;
import tnb.george.me.tasknotebook.ui.TaskListActivity;
import tnb.george.me.tasknotebook.utils.UIUtils;

/**
 * Created by GeorgeZou on 2014/11/14.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/14<br/>
 */
public class MyTaskListAdapter extends BaseAdapter {

    Context mContext;
    List<Task> mTask;

    public MyTaskListAdapter(Context context,List<Task> task) {
        super();
        mContext = context;
        mTask = task;
    }

    private int[] colors = new int[]{ 0xff626569, 0xff4f5257 };
    //private int[] colors = new int[]{ #e9e9e9, 0xff4f5257 };

    public View getView(final int position,View convertView,ViewGroup parent) {
        ImageView image = null;
        TextView title = null;
        TextView text = null;
        //Button button = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.task_list_item, null
            );
            image = (ImageView) convertView.findViewById(R.id.array_image);
            title = (TextView) convertView.findViewById(R.id.array_title);
            text = (TextView) convertView.findViewById(R.id.array_text);
            int colorPos = position % colors.length;

            convertView.setBackgroundColor(Color.parseColor("#E9E9E9"));
            title.setText(mTask.get(position).getTaskTime().toString());
            text.setText(mTask.get(position).getTaskInfo());

            if(colorPos == 0)
                image.setImageResource(R.drawable.image01);
            else
                image.setImageResource(R.drawable.image02);

        }
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
