package com.grability.android.test.database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.grability.android.test.vo.ApplicationVO;
import com.grability.android.test.vo.CategoryVO;

public class AppDatabaseHelper extends SQLiteOpenHelper {
    /** Database name. */
    private static final String DB_NAME = "appsdatastore";
    /** Database version. */
    private static final int DB_VERSION = 1;

    private static final String TABLE_CATEGORY = "CATEGORY";
    private static final String TABLE_APPLICATION = "APPLICATION";

    public static final String COL_CATEGORY_ID = "ID";
    public static final String COL_CATEGORY_LABEL = "LABEL";

    public static final String COL_APP_CATEGORY_ID = "CAT_ID";
    public static final String COL_APP_ID = "ID";
    public static final String COL_APP_NAME = "NAME";
    public static final String COL_APP_IMAGE_A = "IMAGE_A";
    public static final String COL_APP_IMAGE_B = "IMAGE_B";
    public static final String COL_APP_IMAGE_C = "IMAGE_C";
    public static final String COL_APP_SUMMARY = "SUMMARY";
    public static final String COL_APP_TITLE = "TITLE";
    public static final String COL_APP_ARTIST = "ARTIST";
    public static final String COL_APP_RELEASE_DATE = "RELEASE_DATE";

    public static final String CREATE_TABLE_CATEGORY_QUERY =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY +
                    "(" +
                    COL_CATEGORY_ID  + " PRIMARY KEY, " +
                    COL_CATEGORY_LABEL +
                    ")";

    public static final String CREATE_TABLE_APPLICATION_QUERY =
            "CREATE TABLE IF NOT EXISTS " + TABLE_APPLICATION+
                    "(" +
                    COL_APP_ID  + " PRIMARY KEY, " +
                    COL_APP_CATEGORY_ID  + ", " +
                    COL_APP_NAME  + ", " +
                    COL_APP_IMAGE_A  + ", " +
                    COL_APP_IMAGE_B  + ", " +
                    COL_APP_IMAGE_C  + ", " +
                    COL_APP_SUMMARY  + ", " +
                    COL_APP_TITLE  + ", " +
                    COL_APP_ARTIST  + ", " +
                    COL_APP_RELEASE_DATE +
                    //"PRIMARY KEY ("+COL_APP_ID+")" +
                    ")";
    private static final String TAG = "AppDatabaseHelper";


    public AppDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableApplicationCategory(db);
    }

    private void createTableApplicationCategory(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATEGORY_QUERY);
        db.execSQL(CREATE_TABLE_APPLICATION_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPLICATION);
        onCreate(db);
    }

    public long addCategory(CategoryVO category) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_CATEGORY_ID, category.getID());
        initialValues.put(COL_CATEGORY_LABEL, category.getLabel());
        Log.w(TAG, "Insertando Categoria: " + initialValues.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        return db.replace(TABLE_CATEGORY, null, initialValues);
    }

    public long addApplication(ApplicationVO category) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_APP_ID, category.getID());
        initialValues.put(COL_APP_CATEGORY_ID, category.getCatID());
        initialValues.put(COL_APP_IMAGE_A, category.getImageAURL());
        initialValues.put(COL_APP_IMAGE_B, category.getImageBURL());
        initialValues.put(COL_APP_IMAGE_C, category.getImageCURL());
        initialValues.put(COL_APP_NAME, category.getName());
        initialValues.put(COL_APP_TITLE, category.getTitle());
        initialValues.put(COL_APP_SUMMARY, category.getSummary());
        initialValues.put(COL_APP_ARTIST, category.getArtist());
        initialValues.put(COL_APP_RELEASE_DATE, category.getReleaseDate());
        Log.w(TAG, "Insertando Applicaión: " + initialValues.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        return db.replace(TABLE_APPLICATION, null, initialValues);
    }

    public void clearCategories(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CATEGORY);
    }
    public void clearApplications(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_APPLICATION);
    }
}
