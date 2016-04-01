package com.grability.android.test;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.grability.android.test.fragments.ApplicationDetailsFragment;
import com.grability.android.test.fragments.ApplicationsListFragment;
import com.grability.android.test.utils.ScreenUtils;
import com.grability.android.test.vo.ApplicationVO;

public class ApplicationsListActivity extends AppCompatActivity implements ApplicationsListFragment.OnApplicationsListListener {

    public static final String CATEGORY_ID = "CATEGORY_ID";
    public static final String CATEGORY_NAME = "CATEGORY_NAME";

    private String categoryID;
    private String categoryName;
    private ApplicationDetailsFragment detailsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ScreenUtils.isTablet(this))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_applications_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryName=getIntent().getStringExtra(CATEGORY_NAME);
        categoryID=getIntent().getStringExtra(CATEGORY_ID);

        getSupportActionBar().setTitle(categoryName);





        ApplicationsListFragment detailsFragment = (ApplicationsListFragment) getSupportFragmentManager().findFragmentById(R.id.aplications_list_fragment);
        detailsFragment.setActivityListener(this);
    }


    @Override
    public void onApplicationSelected(ApplicationVO applicationVO) {
        if(findViewById(R.id.application_detail_container)!=null){
            detailsFragment = ApplicationDetailsFragment.newInstance(applicationVO);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.application_detail_container, detailsFragment);
            transaction.commit();
        }
        else{
            detailsFragment = ApplicationDetailsFragment.newInstance(applicationVO);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            detailsFragment.show(transaction, "dialog");
        }
    }
}
