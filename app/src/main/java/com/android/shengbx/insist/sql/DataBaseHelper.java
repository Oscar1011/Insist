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
    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String NOT_NULL_CONSTARINT = " NOT NULL";
    private static final String COMMA_SEP = ",";

    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String DAY_LONGEST = "day_longest";
    public static final String DAY_NOW = "day_now";

    public static final String LAST_SIGN_DATE = "last_sign_date";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "("
                                                    + _ID +INTEGER_TYPE + " PRIMARY KEY" +NOT_NULL_CONSTARINT+ COMMA_SEP
                                                    + TITLE +TEXT_TYPE + NOT_NULL_CONSTARINT + COMMA_SEP
                                                    + TYPE + INTEGER_TYPE + COMMA_SEP
                                                    + DAY_LONGEST +INTEGER_TYPE + COMMA_SEP
                                                    + DAY_NOW +INTEGER_TYPE +COMMA_SEP
                                                    + LAST_SIGN_DATE + TEXT_TYPE + ");";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public DataBaseHelper(Context context){
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        StringBuilder dbcreate = new StringBuilder();
//        dbcreate.append("CREATE TABLE" +
//                " " + TABLE_NAME);
//        dbcreate.append(" (_id integer PRIMARY KEY ,title text(20) NOT NULL,type integer, day_longest integer,day_now integer);");
//        db.execSQL(dbcreate.toString());
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
