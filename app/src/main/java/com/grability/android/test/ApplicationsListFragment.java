package com.grability.android.test;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

import com.grability.android.test.R;
import com.grability.android.test.database.AppDatabaseHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class ApplicationsListFragment extends Fragment {

    public ApplicationsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_applications_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillList(view, savedInstanceState);

    }


    private void fillList(View view, Bundle savedInstanceState) {
        final AppDatabaseHelper appDatabaseHelper = new AppDatabaseHelper(this.getActivity());
        Cursor cursor=appDatabaseHelper.getAllCategories();
        AbsListView list= (AbsListView) view.findViewById(R.id.listViewApplications);
        // The desired columns to be bound
        String[] columns = new String[] {
                AppDatabaseHelper.COL_CATEGORY_LABEL
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                android.R.id.text1
        };
        SimpleCursorAdapter cursorAdapter=new SimpleCursorAdapter(this.getActivity(),android.R.layout.simple_list_item_1,cursor, columns, to);
        list.setAdapter(cursorAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //startActivity(new Intent(getActivity(),ApplicationListActivity.class));
            }
        });
    }
}
