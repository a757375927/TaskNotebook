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
    private static final String TBL_NAME = "TaskNote";
    private static final String CREATE_TBL = "create table TaskNote("+
            " _id integer primary key autoincrement," +
            " taskInfo text," +
            " createTime integer," +
            " taskTime integer)";

    private SQLiteDatabase db;
    public DBUtils(Context c){
        super(c,DB_NAME,null,2);
    }

    public void onCreate(SQLiteDatabase db){
        this.db = db;
        db.execSQL(CREATE_TBL);

    }

    public void insert(ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TBL_NAME,null,values);
        db.close();
    }

    public Cursor query(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TBL_NAME,null,null,null,null,null,null);
        return c;
    }

    public void del(int id){
        if(db == null)
            db = getWritableDatabase();
        db.delete(TBL_NAME," _id=? ",new String[]{String.valueOf(id)});
    }

    public void close(){
        if(db != null)
            db.close();

    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }


}
