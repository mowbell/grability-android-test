package com.grability.android.test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grability.android.test.R;
import com.grability.android.test.vo.ApplicationVO;

public class ApplicationDetailsFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ApplicationVO application;

    public ApplicationDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_application_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState!=null)
            return;

        if(application!=null){
            updateView();

        }

    }



    public void setApplication(ApplicationVO application) {
        if(this.application==null || !this.application.equals(application)) {
            this.application = application;
            this.updateView();
        }
    }

    private void updateView() {
        if(getActivity()!=null){
            View view=this.getView();
            TextView appTitle= (TextView) view.findViewById(R.id.textViewTitle);
            appTitle.setText(application.getTitle());

            TextView appReleaseDate= (TextView) view.findViewById(R.id.textViewReleaseDate);
            appReleaseDate.setText(application.getReleaseDate());

            TextView appArtist= (TextView) view.findViewById(R.id.textViewArtist);
            appArtist.setText(application.getArtist());

            TextView appSummary= (TextView) view.findViewById(R.id.textViewSummary);
            appSummary.setText(application.getSummary());
            if(this.getDialog()!=null){
                this.getDialog().setTitle(application.getName());
                appTitle.setVisibility(View.GONE);
            }
        }
    }
}
