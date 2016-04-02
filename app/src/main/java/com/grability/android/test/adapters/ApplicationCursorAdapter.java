package com.grability.android.test.adapters;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.grability.android.test.R;
import com.grability.android.test.database.AppDatabaseHelper;
import com.grability.android.test.utils.VolleySingleton;

public class ApplicationCursorAdapter extends CursorAdapter {
    private Context context;

    public ApplicationCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.context=context;
    }
    public ApplicationCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_application, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView textViewAppName = (TextView) view.findViewById(R.id.textViewAppName);
        NetworkImageView imageViewAppIcon = (NetworkImageView) view.findViewById(R.id.imageViewAppIcon);
        // Extract properties from cursor
        String appName = cursor.getString(cursor.getColumnIndexOrThrow(AppDatabaseHelper.COL_APP_NAME));
        String appImageUrl = cursor.getString(cursor.getColumnIndexOrThrow(AppDatabaseHelper.COL_APP_IMAGE_A));
        textViewAppName.setText(appName);
        ImageLoader mImageLoader= VolleySingleton.getInstance().getImageLoader();
            /*ImageLoader.ImageContainer aa = mImageLoader.get(appImage, new ImageLoader.ImageListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                }
            });*/
        imageViewAppIcon.setImageUrl(appImageUrl, mImageLoader);

        Animation anim = AnimationUtils.loadAnimation(context, R.anim.list_item_anim);
        anim.reset();
        LinearLayout l = (LinearLayout) view;
        l.clearAnimation();
        l.startAnimation(anim);
    }
}
