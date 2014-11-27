package tnb.george.me.tasknotebook.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GeorgeZou on 2014/10/29.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/10/29<br/>
 */
public class DBUtils extends SQLiteOpenHelper{
    private static final String DB_NAME = "TASKNOTEBOOK.db";

   // public Task(int id, String name,String location,String description,Date beginDate,Date endDate,Date createDate){
    private static final String CREATE_TBL = "create table TaskNote("+
            " _id integer primary key autoincrement," +
            " name text," +
            " location text," +
            " description text," +
            " beginDate integer," +
            " endDate integer," +
            " createDate integer)";

    private SQLiteDatabase db;
    public DBUtils(Context c){
        super(c,DB_NAME,null,2);
    }

    public void onCreate(SQLiteDatabase db){
        this.db = db;
        db.execSQL(CREATE_TBL);

    }

    public void insert(ContentValues values,String tableName){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableName,null,values);
        db.close();
    }

    public Cursor query(String tableName){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(tableName,null,null,null,null,null,null);
        return c;
    }

    public void del(int id,String tableName){
        if(db == null)
            db = getWritableDatabase();
        db.delete(tableName," _id=? ",new String[]{String.valueOf(id)});
    }

    public void close(){
        if(db != null)
            db.close();

    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }


}
