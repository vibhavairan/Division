package com.sih.division;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";
    public static final String HOSPITAL_TABLE_NAME = "HOSPITAL";
    public static final String COLUMN_HID = "HID";
    public static final String COLUMN_HOSPITAL_NAME = "HNAME";
    public static final String COLUMN_HOSPITAL_PASS = "HPASS";
    public static final String COLUMN_DISTRICT_NAME = "DISTRICT";
    public static final String COLUMN_VACANCIES = "VACANCIESLEFT";
    public static final String BLOG_TABLE_NAME = "BLOG";
    public static final String COLUMN_BID = "BID";
    public static final String COLUMN_BLOG_CONTENT = "CONTENT";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_ABSTRACT = "ABSTRACT";
    public static final String USER_TABLE_NAME = "USER";
    public static final String COLUMN_UID = "USERID";
    public static final String COLUMN_UPASS = "USERPASSWORD";
    public static final String COLUMN_USER_NAME = "USERNAME";
    public static final String COLUMN_GENDER = "GENDER";
    public static final String COLUMN_DOB = "DOB";
    public static final String COLUMN_PERMANENTADD = "PERMANENTADD";
    public static final String COLUMN_USER_DISTRICT_NAME = "DISTRICT";
    public static final String DISEASE_TABLE_NAME = "DISEASE";
    public static final String COLUMN_DISEASE_ID = "DID";
    public static final String COLUMN_DISEASE_NAME = "NAME";
    public static final String COLUMN_PATIENTS = "PAT_COUNT";
    public static final String COLUMN_DATE = "DATE";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + HOSPITAL_TABLE_NAME + " ( " + COLUMN_HID + " VARCHAR(20)," + COLUMN_HOSPITAL_NAME + " VARCHAR(30), " + COLUMN_HOSPITAL_PASS + " VARCHAR(30), " + COLUMN_DISTRICT_NAME + " VARCHAR(30), " + COLUMN_VACANCIES + " INTEGER);");
        db.execSQL("create table " + BLOG_TABLE_NAME + " ( " + COLUMN_BID + " VARCHAR(20),"+ COLUMN_TITLE + " VARCHAR(50),"+ COLUMN_ABSTRACT + " VARCHAR(100)," + COLUMN_BLOG_CONTENT + " VARCHAR(30));");
        db.execSQL("create table " + USER_TABLE_NAME + " ( " + COLUMN_UID + " VARCHAR(20), "+ COLUMN_UPASS + " VARCHAR(30), "+ COLUMN_USER_NAME + " VARCHAR(50)," + COLUMN_DOB + " DATE, " + COLUMN_GENDER + " VARCHAR(100)," + COLUMN_PERMANENTADD + " VARCHAR(100), " + COLUMN_USER_DISTRICT_NAME + " VARCHAR(10)) ;");
        db.execSQL("create table " + DISEASE_TABLE_NAME + " ( " + COLUMN_DISEASE_ID + " VARCHAR(20),"+ COLUMN_HID + " VARCHAR(20),"+ COLUMN_DISEASE_NAME + " VARCHAR(50)," + COLUMN_PATIENTS + " INTEGER, " + COLUMN_DATE + " DATE);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HOSPITAL_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BLOG_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DISEASE_TABLE_NAME);
        onCreate(db);
    }

}
