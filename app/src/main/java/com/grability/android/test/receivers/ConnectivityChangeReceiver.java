package com.grability.android.test.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Mauricio on 28/03/2016.
 */
public class ConnectivityChangeReceiver extends BroadcastReceiver {
    private OnConnectivityChangeListener changeListener;

    public interface OnConnectivityChangeListener{
        public void onChange();
    }

    public void setOnConnectivityChangeListener(OnConnectivityChangeListener listener){
        this.changeListener=listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(changeListener!=null)
            changeListener.onChange();
    }
}
