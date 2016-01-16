package com.android.shengbx.insist.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shengbx on 12/31/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    final static String DB_NAME = "insist.db";
    public static final String TABLE_NAME = "INSIST";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String DAY_LONGEST = "day_longest";
    public static final String DAY_NOW = "day_now";
    public static final String _ID = "_id";

    public DataBaseHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder dbcreate = new StringBuilder();
        dbcreate.append("CREATE TABLE" +
                " " + TABLE_NAME);
        dbcreate.append(" (_id integer PRIMARY KEY ,title text(20) NOT NULL,type integer, day_longest integer,day_now integer);");
        db.execSQL(dbcreate.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
