package com.example.samplearchitecture.network;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;

import javax.inject.Inject;

import static java.lang.annotation.RetentionPolicy.*;
import static android.net.ConnectivityManager.*;

/**
 * Created by Sean on 12/29/2015.
 */
public class NetworkManager {

    @Retention(SOURCE)
    @IntDef({OFFLINE, CELLULAR, WIFI})
    public @interface Status {}
    public static final int OFFLINE = 0;
    public static final int CELLULAR = 1;
    public static final int WIFI = 2;

    @NonNull
    private Context context;
    @Nullable
    private NetworkInfo info;
    @NonNull
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateNetwork();
        }
    };

    @Inject
    public NetworkManager(@NonNull Application app) {
        this.context = app.getApplicationContext();
        updateNetwork();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        app.registerReceiver(receiver, filter);
    }

    public void updateNetwork() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.info = cm.getActiveNetworkInfo();
    }

    public @Status int getNetworkStatus() {
        if (info == null) return OFFLINE;
        int type = info.getType();
        if (type == TYPE_WIFI) return WIFI;
        if (type == TYPE_MOBILE) return CELLULAR;
        return OFFLINE;
    }

    public boolean online() {
        return info != null && info.isConnected();
    }

    public boolean cellular() {
        return info != null && info.getType() == TYPE_MOBILE;
    }

}
