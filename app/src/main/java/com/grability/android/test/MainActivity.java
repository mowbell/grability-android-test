package com.grability.android.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grability.android.test.database.AppDatabaseHelper;
import com.grability.android.test.utils.ScreenUtils;
import com.grability.android.test.vo.CategoryVO;

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
        final Cursor cursor=appDatabaseHelper.getAllCategories();
        final AbsListView list= (AbsListView) findViewById(R.id.listView);
        // The desired columns to be bound
        String[] columns = new String[] {
                AppDatabaseHelper.COL_CATEGORY_LABEL
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                android.R.id.text1
        };
        final SimpleCursorAdapter cursorAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor, columns, to);
        list.setAdapter(cursorAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) list.getItemAtPosition(position);
                CategoryVO cat = CategoryVO.extract(cursor);
                Intent intentApps = new Intent(MainActivity.this, ApplicationsListActivity.class);
                intentApps.putExtra(ApplicationsListActivity.CATEGORY_ID, String.valueOf(cat.getID()));
                intentApps.putExtra(ApplicationsListActivity.CATEGORY_NAME, cat.getLabel());
                startActivity(intentApps);

                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.splash_in_anim,R.anim.splash_out_anim);
    }
}
