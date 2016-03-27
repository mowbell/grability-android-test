package com.grability.android.test;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grability.android.test.database.AppDatabaseHelper;
import com.grability.android.test.utils.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ScreenUtils.isTablet(this))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        setTitle("Aplicaciones");
        fillList();

    }



    private void fillList() {
        final AppDatabaseHelper appDatabaseHelper = new AppDatabaseHelper(this);
        Cursor cursor=appDatabaseHelper.getAllCategories();
        AbsListView list= (AbsListView) findViewById(R.id.listView);
        // The desired columns to be bound
        String[] columns = new String[] {
                AppDatabaseHelper.COL_CATEGORY_LABEL
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                android.R.id.text1
        };
        SimpleCursorAdapter cursorAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor, columns, to);
        list.setAdapter(cursorAdapter);
    }
}
