package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ArticleListActivity extends Activity implements ArticleListFragment.ArticleListener {
    public static final String DETAILS_TX = "initial";
    public static final String LIST_FRAGMENT = "listFragment";
    public static final String DETAILS_FRAGMENT = "detailsFragment";
    public static final String NONE = "None";
    public static final int REQUEST_CODE = 1;
    private boolean twoPanes = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        twoPanes =
                (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);

        FragmentManager mgr = getFragmentManager();
        mgr.popBackStack(DETAILS_TX, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction tx = mgr.beginTransaction();

        if (mgr.findFragmentByTag(LIST_FRAGMENT) == null) {
            tx.add(R.id.fragment_holder, new ArticleListFragment(), LIST_FRAGMENT);
        }

        if (twoPanes) {
            tx.replace(R.id.fragment_holder_right, DetailsFragment.newInstance(NONE), DETAILS_FRAGMENT);
        }

        tx.commit();

        registerBroadcast();
    }

    private void registerBroadcast() {
        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_POWER_CONNECTED);
            filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
            filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
            registerReceiver(new MyBroadcastReceiver(), filter);
        } else {
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    registerBroadcast();
                }
            }
        }
    }

    @Override
    public void onArticle(String text) {
        FragmentManager mgr = getFragmentManager();

        if (twoPanes) {
            DetailsFragment detailsFragment = (DetailsFragment) mgr.findFragmentById(R.id.fragment_holder_right);
            detailsFragment.setText(text);
        } else {
            FragmentTransaction tx = mgr.beginTransaction();
            tx.replace(R.id.fragment_holder, DetailsFragment.newInstance(text), DETAILS_FRAGMENT);
            tx.addToBackStack(DETAILS_TX);
            tx.commit();
        }
    }
}
