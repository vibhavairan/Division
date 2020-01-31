package com.sih.division;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";
    public static final String HOSPITAL_TABLE_NAME = "HOSPITAL";
    public static final String COLUMN_HID = "HID";
    public static final String COLUMN_HOSPITAL_NAME = "HNAME";
    public static final String COLUMN_HOSPITAL_PASS = "HPASS";
    public static final String COLUMN_HOSPITAL_ADDRESS = "HADD";
    public static final String COLUMN_DISTRICT_NAME = "DISTRICT";
    public static final String COLUMN_VACANCIES = "VACANCIESLEFT";
    public static final String COLUMN_HOSPITAL_PHOTO = "PHOTO";
    public static final String COLUMN_HOSPITAL_NUMBER = "NUMBER";
    public static final String BLOG_TABLE_NAME = "BLOG";
    public static final String COLUMN_BID = "BID";
    public static final String COLUMN_BLOG_CONTENT = "CONTENT";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_ABSTRACT = "ABSTRACT";
    public static final String COLUMN_B_USERNAME = "USERNAME";
    public static final String COLUMN_B_PHOTO = "PHOTO";
    public static final String COLUMN_B_DISTRICT = "DISTRICT";
    public static final String USER_TABLE_NAME = "USER";
    public static final String COLUMN_UID = "USERID";
    public static final String COLUMN_UPASS = "USERPASSWORD";
    public static final String COLUMN_USER_NAME = "USERNAME";
    public static final String COLUMN_CONTACT_NUMBER = "CNUMBER";
    public static final String COLUMN_USER_EMAIL = "EMAIL";
    public static final String COLUMN_GENDER = "GENDER";
    public static final String COLUMN_DOB = "DOB";
    public static final String COLUMN_PERMANENTADD = "PERMANENTADD";
    public static final String COLUMN_USER_DISTRICT_NAME = "DISTRICT";
    public static final String COLUMN_PHOTO = "PHOTO";
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
        db.execSQL("create table " + HOSPITAL_TABLE_NAME + " ( " + COLUMN_HID + " VARCHAR(20)," + COLUMN_HOSPITAL_NAME + " VARCHAR(30), " + COLUMN_HOSPITAL_PASS + " VARCHAR(30), " + COLUMN_DISTRICT_NAME + " VARCHAR(30), " + COLUMN_VACANCIES + " INTEGER, " + COLUMN_HOSPITAL_PHOTO + " BLOB, " + COLUMN_HOSPITAL_NUMBER + " INTEGER, " + COLUMN_HOSPITAL_ADDRESS + " VARCHAR(100));");
        db.execSQL("create table " + BLOG_TABLE_NAME + " ( " + COLUMN_BID + " VARCHAR(20),"+ COLUMN_TITLE + " VARCHAR(50),"+ COLUMN_ABSTRACT + " VARCHAR(100)," + COLUMN_BLOG_CONTENT + " VARCHAR(30), " + COLUMN_B_USERNAME + " VARCHAR(50), " + COLUMN_B_PHOTO + " BLOB, " + COLUMN_B_DISTRICT + " VARCHAR(10));");
        db.execSQL("create table " + USER_TABLE_NAME + " ( " + COLUMN_UID + " VARCHAR(20), "+ COLUMN_UPASS + " VARCHAR(30), "+ COLUMN_USER_NAME + " VARCHAR(50)," + COLUMN_CONTACT_NUMBER + " INTEGER, " + COLUMN_USER_EMAIL + " VARCHAR(50), "+ COLUMN_DOB + " DATE, " + COLUMN_GENDER + " VARCHAR(100)," + COLUMN_PERMANENTADD + " VARCHAR(100), " + COLUMN_USER_DISTRICT_NAME + " VARCHAR(10), " + COLUMN_PHOTO + " BLOB) ;");
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

    public void insertRecordHospital(HospitalModel newHospital) {
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_HID, newHospital.getHid());
        cv.put(COLUMN_HOSPITAL_NAME, newHospital.getHname());
        cv.put(COLUMN_HOSPITAL_PASS, newHospital.getHpass());
        cv.put(COLUMN_HOSPITAL_ADDRESS, newHospital.getAddress());
        cv.put(COLUMN_DISTRICT_NAME, newHospital.getDist());
        cv.put(COLUMN_VACANCIES, newHospital.getVaccount());
        cv.put(COLUMN_HOSPITAL_PHOTO, newHospital.getPhoto());
        cv.put(COLUMN_HOSPITAL_NUMBER, newHospital.getNumber());
       database.insert(HOSPITAL_TABLE_NAME, null, cv);
        database.close();

    }

    public void updateRecordHospital(HospitalModel newHospital) {
        database = this.getWritableDatabase();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put(COLUMN_HID, newHospital.getHid());
        dataToInsert.put(COLUMN_HOSPITAL_NAME, newHospital.getHname());
        dataToInsert.put(COLUMN_HOSPITAL_PASS, newHospital.getHpass());
        dataToInsert.put(COLUMN_HOSPITAL_ADDRESS, newHospital.getAddress());
        dataToInsert.put(COLUMN_DISTRICT_NAME, newHospital.getDist());
        dataToInsert.put(COLUMN_VACANCIES, newHospital.getVaccount());
        dataToInsert.put(COLUMN_HOSPITAL_PHOTO, newHospital.getPhoto());
        dataToInsert.put(COLUMN_HOSPITAL_NUMBER, newHospital.getNumber());
        String where = COLUMN_HID + "=" + newHospital.getHid();
        try{
            database.update(HOSPITAL_TABLE_NAME, dataToInsert, where, null);
        }
        catch (Exception e){}
        database.close();
    }

    public void deleteRecordHospital(HospitalModel newHospital) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + HOSPITAL_TABLE_NAME + " where " + COLUMN_HID + " = '" + newHospital.getHid() + "'");
        database.close();
    }

    public void insertRecordUser(UserModel newUser) {
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_UID, newUser.getUid());
        cv.put(COLUMN_UPASS, newUser.getUpass());
        cv.put(COLUMN_USER_NAME, newUser.getUname());
        cv.put(COLUMN_CONTACT_NUMBER, newUser.getCnumber());
        cv.put(COLUMN_USER_EMAIL, newUser.getEmail());
        cv.put(COLUMN_GENDER, newUser.getGender());
        cv.put(COLUMN_DOB, newUser.getDob());
        cv.put(COLUMN_PERMANENTADD, newUser.getPermadd());
        cv.put(COLUMN_USER_DISTRICT_NAME, newUser.getDist());
        cv.put(COLUMN_PHOTO, newUser.getPhoto());
        database.insert( USER_TABLE_NAME, null, cv );
        database.close();
    }

    public void updateRecordUser(UserModel newUser) {
        database = this.getWritableDatabase();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put(COLUMN_UID, newUser.getUid());
        dataToInsert.put(COLUMN_UPASS, newUser.getUpass());
        dataToInsert.put(COLUMN_USER_NAME, newUser.getUname());
        dataToInsert.put(COLUMN_CONTACT_NUMBER, newUser.getCnumber());
        dataToInsert.put(COLUMN_USER_EMAIL, newUser.getEmail());
        dataToInsert.put(COLUMN_GENDER, newUser.getGender());
        dataToInsert.put(COLUMN_DOB, newUser.getDob());
        dataToInsert.put(COLUMN_PERMANENTADD, newUser.getPermadd());
        dataToInsert.put(COLUMN_USER_DISTRICT_NAME, newUser.getDist());
        dataToInsert.put(COLUMN_PHOTO, newUser.getPhoto());
        String where = COLUMN_UID + "=" + newUser.getUid();
        try{
            database.update(USER_TABLE_NAME, dataToInsert, where, null);
        }
        catch (Exception e){}
        database.close();
    }

    public void deleteRecordUser(UserModel newUser) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + USER_TABLE_NAME + " where " + COLUMN_UID + " = '" + newUser.getUid() + "'");
        database.close();
    }

    public void insertRecordBlog(BlogModel newBlog) {
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BID, newBlog.getBid());
        cv.put(COLUMN_BLOG_CONTENT, newBlog.getCont());
        cv.put(COLUMN_TITLE, newBlog.getTitle());
        cv.put(COLUMN_ABSTRACT, newBlog.getAbs());
        cv.put(COLUMN_B_USERNAME, newBlog.getUname());
        cv.put(COLUMN_B_PHOTO, newBlog.getPhoto());
        cv.put(COLUMN_B_DISTRICT, newBlog.getUdist());
        database.insert( BLOG_TABLE_NAME, null, cv );
        database.close();
    }

    public void updateRecordBlog(BlogModel newBlog) {
        database = this.getWritableDatabase();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put(COLUMN_BID, newBlog.getBid());
        dataToInsert.put(COLUMN_BLOG_CONTENT, newBlog.getCont());
        dataToInsert.put(COLUMN_TITLE, newBlog.getTitle());
        dataToInsert.put(COLUMN_ABSTRACT, newBlog.getAbs());
        dataToInsert.put(COLUMN_B_USERNAME, newBlog.getUname());
        dataToInsert.put(COLUMN_B_PHOTO, newBlog.getPhoto());
        dataToInsert.put(COLUMN_B_DISTRICT, newBlog.getUdist());
        String where = COLUMN_BID + "=" + newBlog.getBid();
        try{
            database.update(BLOG_TABLE_NAME, dataToInsert, where, null);
        }
        catch (Exception e){}
        database.close();
    }

    public void deleteRecordBlog(BlogModel newBlog) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + BLOG_TABLE_NAME + " where " + COLUMN_BID + " = '" + newBlog.getBid() + "'");
        database.close();
    }

    public void insertRecordDisease(DiseaseModel newDisease) {
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DISEASE_ID, newDisease.getDid());
        cv.put(COLUMN_HID, newDisease.getHid());
        cv.put(COLUMN_DISEASE_NAME, newDisease.getDname());
        cv.put(COLUMN_PATIENTS, newDisease.getPatcount());
        cv.put(COLUMN_DATE, newDisease.getDate());
        database.insert( DISEASE_TABLE_NAME, null, cv );
        database.close();
    }

    public void updateRecordDisease(DiseaseModel newDisease) {
        database = this.getWritableDatabase();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put(COLUMN_DISEASE_ID, newDisease.getDid());
        dataToInsert.put(COLUMN_HID, newDisease.getHid());
        dataToInsert.put(COLUMN_DISEASE_NAME, newDisease.getDname());
        dataToInsert.put(COLUMN_PATIENTS, newDisease.getPatcount());
        dataToInsert.put(COLUMN_DATE, newDisease.getDate());
        String where = COLUMN_DISEASE_ID + "=" + newDisease.getDid();
        try{
            database.update(DISEASE_TABLE_NAME, dataToInsert, where, null);
        }
        catch (Exception e){}
        database.close();
    }

    public void deleteRecordDisease(DiseaseModel newDisease) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + DISEASE_TABLE_NAME + " where " + COLUMN_DISEASE_ID + " = '" + newDisease.getDid() + "'");
        database.close();
    }
}
