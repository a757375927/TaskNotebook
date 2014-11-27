package tnb.george.me.tasknotebook.bean;

import java.util.Date;

/**
 * Created by GeorgeZou on 2014/10/29.
 */
public class Task {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LOCATION = "location";
    public static final String TASKINFO = "taskInfo";
    public static final String BEGINDATE = "beginDate";
    public static final String ENDDATE = "endDate";
    public static final String CREATEDATE = "createDate";

    public Task(){}

    public Task(int id, String name,String location,String description,Date beginDate,Date endDate,Date createDate){
        this.setName(name);
        this.setBeginDate(beginDate);
        this.setDescription(description);
        this.setEndDate(endDate);
        this.setId(id);
        this.setLocation(location);
        this.setCreateDate(createDate);
    }

    private int id;
    private String name;
    private String location;
    private String description;
    private Date beginDate;
    private Date endDate;
    private Date createDate;
    private Contact contact;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
