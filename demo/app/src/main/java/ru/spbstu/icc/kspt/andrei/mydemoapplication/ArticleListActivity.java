package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class ArticleListActivity extends Activity implements ArticleListFragment.ArticleListener {
    public static final String DETAILS_TX = "initial";
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
        if (twoPanes){
            tx.show(mgr.findFragmentById(R.id.list_fragment));
            tx.show(mgr.findFragmentById(R.id.details_fragment));
        } else {
            tx.show(mgr.findFragmentById(R.id.list_fragment));
            tx.hide(mgr.findFragmentById(R.id.details_fragment));
        }
        tx.commit();
    }

    @Override
    public void onArticle(String text) {
        FragmentManager mgr = getFragmentManager();
        if (twoPanes){
            DetailsFragment detailsFragment =
                    (DetailsFragment) mgr.findFragmentById(R.id.details_fragment);
            detailsFragment.setText(text);
        } else {
            FragmentTransaction tx = mgr.beginTransaction();
            tx.hide(mgr.findFragmentById(R.id.list_fragment));
            tx.show(mgr.findFragmentById(R.id.details_fragment));
            tx.addToBackStack(DETAILS_TX);
            tx.commit();
        }
    }
}
