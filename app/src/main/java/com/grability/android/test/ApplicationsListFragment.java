package com.grability.android.test;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

import com.grability.android.test.database.AppDatabaseHelper;
import com.grability.android.test.vo.ApplicationVO;

/**
 * A placeholder fragment containing a simple view.
 */
public class ApplicationsListFragment extends Fragment {

    OnApplicationsListListener listener;

    public void setActivityListener(OnApplicationsListListener listener) {
        this.listener=listener;
    }

    public interface OnApplicationsListListener {
        void onApplicationSelected(ApplicationVO applicationVO);
    }
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void fillList(View view, Bundle savedInstanceState) {
        final AppDatabaseHelper appDatabaseHelper = new AppDatabaseHelper(this.getActivity());
        String catID= getActivity().getIntent().getStringExtra(ApplicationsListActivity.CATEGORY_ID);
        Cursor cursor=appDatabaseHelper.getCategoryApplications(catID);
        final AbsListView list= (AbsListView) view.findViewById(R.id.listViewApplications);
        // The desired columns to be bound
        String[] columns = new String[] {
                AppDatabaseHelper.COL_APP_NAME
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
                Cursor cursor = (Cursor) list.getItemAtPosition(position);
                ApplicationVO app=ApplicationVO.extract(cursor);
                if(listener!=null)
                    listener.onApplicationSelected(app);
            }
        });






    }
}
