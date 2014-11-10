package tnb.george.me.tasknotebook.bean;

import java.util.Date;

/**
 * Created by GeorgeZou on 2014/10/29.
 */
public class Task {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TASKINFO = "taskInfo";
    public static final String CREATEDATE = "createDate";
    public static final String TASKDATE = "taskDate";

    public Task(String name,String taskInfo,Date createTime,Date taskDate){
        this.setName(name);
        this.setTaskInfo(taskInfo);
        this.setCreateTime(createTime);
        this.setTaskTime(taskTime);
    }

    private int id;
    private String name;
    private Date createTime;
    private Date taskTime;
    private String taskInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }
}
