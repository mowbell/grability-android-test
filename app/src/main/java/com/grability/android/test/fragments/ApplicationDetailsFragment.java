package com.grability.android.test.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.grability.android.test.R;
import com.grability.android.test.utils.VolleySingleton;
import com.grability.android.test.vo.ApplicationVO;

public class ApplicationDetailsFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_APP_ID = "APP_ID";
    private static final String ARG_APP_NAME = "APP_NAME";
    private static final String ARG_APP_TITLE = "APP_TITLE";
    private static final String ARG_APP_SUMMARY = "APP_SUMMARY";
    private static final String ARG_APP_RELEASE_DATE = "APP_RELEASE_DATE";
    private static final String ARG_APP_ARTIST = "APP_ARTIST";
    private static final String ARG_APP_IMAGE = "APP_IMAGE";

    // TODO: Rename and change types of parameters
    private int appID;
    private String appName;
    private String appTitle;
    private String appSummary;
    private String appReleaseDate;
    private String appArtist;
    private String appImage;


    public ApplicationDetailsFragment() {
        // Required empty public constructor
    }

    public static ApplicationDetailsFragment newInstance(ApplicationVO app) {
        Bundle args = new Bundle();
        args.putInt(ARG_APP_ID, app.getID());
        args.putString(ARG_APP_NAME, app.getName());
        args.putString(ARG_APP_TITLE, app.getTitle());
        args.putString(ARG_APP_SUMMARY, app.getSummary());
        args.putString(ARG_APP_RELEASE_DATE, app.getReleaseDate());
        args.putString(ARG_APP_ARTIST, app.getArtist());
        args.putString(ARG_APP_IMAGE, app.getImageCURL());

        ApplicationDetailsFragment fragment = new ApplicationDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appID = getArguments().getInt(ARG_APP_ID);
            appName = getArguments().getString(ARG_APP_NAME);
            appTitle = getArguments().getString(ARG_APP_TITLE);
            appSummary = getArguments().getString(ARG_APP_SUMMARY);
            appReleaseDate = getArguments().getString(ARG_APP_RELEASE_DATE);
            appArtist = getArguments().getString(ARG_APP_ARTIST);
            appImage = getArguments().getString(ARG_APP_IMAGE);
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
        updateView();
    }




    @Override
    public void onStart() {
        super.onStart();

    }

    private void updateView() {
        if(getActivity()!=null){
            View view=this.getView();
            TextView appTitleTextView= (TextView) view.findViewById(R.id.textViewTitle);
            appTitleTextView.setText(appTitle);

            TextView appReleaseDateTextView= (TextView) view.findViewById(R.id.textViewReleaseDate);
            appReleaseDateTextView.setText(appReleaseDate);

            TextView appArtistTextView= (TextView) view.findViewById(R.id.textViewArtist);
            appArtistTextView.setText(appArtist);

            TextView appSummaryTextView= (TextView) view.findViewById(R.id.textViewSummary);
            appSummaryTextView.setText(appSummary);
            if(this.getDialog()!=null){
                this.getDialog().setTitle(appName);
                appTitleTextView.setVisibility(View.GONE);
            }

            NetworkImageView avatar = (NetworkImageView)view.findViewById(R.id.imageViewApplication);
            ImageLoader mImageLoader= VolleySingleton.getInstance().getImageLoader();
            /*ImageLoader.ImageContainer aa = mImageLoader.get(appImage, new ImageLoader.ImageListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                }
            });*/
            avatar.setImageUrl(appImage, mImageLoader);

            Animation animLayout = AnimationUtils.loadAnimation(getActivity(), R.anim.app_details_anim);
            animLayout.reset();
            RelativeLayout ly = (RelativeLayout) view;
            ly.clearAnimation();
            ly.startAnimation(animLayout);

            Animation animSummary = AnimationUtils.loadAnimation(getActivity(), R.anim.app_details_summary_anim);
            animSummary.reset();
            ScrollView sv = (ScrollView) view.findViewById(R.id.scrollViewSummary);
            sv.clearAnimation();
            sv.startAnimation(animSummary);
        }
    }
}
