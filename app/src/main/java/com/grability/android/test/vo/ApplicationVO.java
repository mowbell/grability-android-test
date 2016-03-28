package com.grability.android.test.vo;

import android.database.Cursor;

import com.grability.android.test.database.AppDatabaseHelper;

public class ApplicationVO {
    private int ID;
    private int catID;
    private String name;
    private String imageAURL;
    private String imageBURL;
    private String imageCURL;
    private String summary;
    private String title;
    private String artist;
    private String releaseDate;

    public ApplicationVO(int ID, int catID, String name, String imageAURL, String imageBURL, String imageCURL, String summary, String title, String artist, String releaseDate) {
        this.ID = ID;
        this.catID = catID;
        this.name = name;
        this.imageAURL = imageAURL;
        this.imageBURL = imageBURL;
        this.imageCURL = imageCURL;
        this.summary = summary;
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
    }


    public int getID() {
        return ID;
    }

    public int getCatID() {
        return catID;
    }

    public String getName() {
        return name;
    }

    public String getImageAURL() {
        return imageAURL;
    }

    public String getImageBURL() {
        return imageBURL;
    }

    public String getImageCURL() {
        return imageCURL;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public static ApplicationVO extract(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_ID));
        int catID = cursor.getInt(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_CATEGORY_ID));
        String name = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_NAME));
        String imageAURL = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_IMAGE_A));
        String imageBURL = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_IMAGE_B));
        String imageCURL = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_IMAGE_C));
        String summary = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_SUMMARY));
        String title = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_TITLE));
        String artist = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_ARTIST));
        String releaseDate = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_APP_RELEASE_DATE));

        return new ApplicationVO(id,catID,name,imageAURL,imageBURL,imageCURL,summary,title,artist,releaseDate);
    }
}
