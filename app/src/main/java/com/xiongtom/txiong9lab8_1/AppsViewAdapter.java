package com.xiongtom.txiong9lab8_1;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Tom on 12/10/15.
 */
public class AppsViewAdapter extends RecyclerView.Adapter<AppsViewAdapter.AppsViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private MainActivity mMainActivity;
    private IntentSingleton mIntentSingleton;
    private ArrayList<ApplicationInfo> mMyApps;
    private RecyclerView mRecyclerView;

    public AppsViewAdapter(Context context) {
        mContext = context;
        mMainActivity = (MainActivity) context;
        mInflater = LayoutInflater.from(context);
        mIntentSingleton = IntentSingleton.get(context);
        mMyApps = mIntentSingleton.getMyApps();
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.app_item, parent, false);

        AppsViewHolder appsViewHolder = new AppsViewHolder(view);
        mRecyclerView = (RecyclerView) mMainActivity.findViewById(R.id.appsList);

        return appsViewHolder;
    }


    @Override
    public void onBindViewHolder(AppsViewHolder holder, final int position) {

        final String name = mMyApps.get(position).packageName;

        holder.mAppName.setText(name);

        holder.mAppName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PackageManager pm = mMainActivity.getPackageManager();

                Intent mIntent = pm.getLaunchIntentForPackage(name);
                if (mIntent != null) {
                    try {
                        mMainActivity.startActivity(mIntent);

                    } catch (ActivityNotFoundException err) {
                        Toast.makeText(mContext, "App Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return mMyApps.size();
    }


    /**
     * View holder for this adapter
     */
    public class AppsViewHolder extends RecyclerView.ViewHolder {

        TextView mAppName;

        public AppsViewHolder(View itemView) {
            super(itemView);

            mAppName = (TextView) itemView.findViewById(R.id.appName);

        }
    }
}
