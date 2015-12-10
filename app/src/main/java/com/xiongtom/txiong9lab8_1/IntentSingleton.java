package com.xiongtom.txiong9lab8_1;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.util.ArrayList;

/**
 * Created by Tom on 12/10/15.
 */
public class IntentSingleton  {

    private static IntentSingleton mIntentSingleton;
    private ArrayList<ApplicationInfo> mMyApps;
    private Context mContext;


    private IntentSingleton(Context context) {
        mContext = context;
        mMyApps = new ArrayList<>();
    }

    public static IntentSingleton get(Context context) {
        if (mIntentSingleton == null) {
            mIntentSingleton = new IntentSingleton(context.getApplicationContext());
        }
        return mIntentSingleton;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public ArrayList<ApplicationInfo> getMyApps() {
        return mMyApps;
    }

    public void setMyApps(ArrayList<ApplicationInfo> myApps) {
        mMyApps = myApps;
    }
}
