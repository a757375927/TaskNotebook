package tnb.george.me.tasknotebook.task;

import android.os.AsyncTask;

import tnb.george.me.tasknotebook.bean.Task;

/**
 * Created by GeorgeZou on 2014/11/27.\
 * 练习用AysncTask
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/27<br/>
 */
public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

    private Task task;

    public MyAsyncTask(Task task){
        this.task = task;
    }

    /**
     *   这里的Integer参数对应AsyncTask中的第一个参数
     *   这里的String返回值对应AsyncTask的第三个参数
     *   该方法并不运行在UI线程中，主要用于异步操作，所有改方法中不能对UI
     * 当中的控件进行设置和修改
     *   但是可以调用 publiskProgress方法触发onProgressUpdate对UI进行操作
     * @param integers
     * @return
     */
    @Override
    protected String doInBackground(Integer... integers) {
        return null;
    }

    /**
     * 该方法运行在UI线程当中，可以对UI控件进行设置
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 这里的String参数对应AsyncTask中的第三个参数（也就是doInBackground的返回值）
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI控件进行设置
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    /**
     *   这里的Integer参数对应AsyncTask中的第二个参数
     *   在doInBackground方法当中，每次调用publishProgress方法都会
     * 触发onProgressUpdate执行
     *   onProgressUpdate 是在UI线程中执行，可以对所有UI控件进行操作
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
