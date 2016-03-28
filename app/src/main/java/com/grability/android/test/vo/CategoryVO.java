package com.grability.android.test.vo;

import android.database.Cursor;

import com.grability.android.test.database.AppDatabaseHelper;

public class CategoryVO {
    private int ID;
    private String label;

    public int getID() {
        return ID;
    }

    public CategoryVO(int ID, String label) {
        this.ID = ID;
        this.label = label;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLabel() {
        return label;
    }

    public static CategoryVO extract(Cursor cursor){
        int categoryID = cursor.getInt(cursor.getColumnIndex(AppDatabaseHelper.COL_CATEGORY_ID));
        String categoryName = cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.COL_CATEGORY_LABEL));
        return new CategoryVO(categoryID,categoryName);
    }

}
