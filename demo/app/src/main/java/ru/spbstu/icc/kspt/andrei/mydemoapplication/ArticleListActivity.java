package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class ArticleListActivity extends Activity implements ArticleListFragment.ArticleListener {
    public static final String DETAILS_TX = "initial";
    public static final String LIST_FRAGMENT = "listFragment";
    public static final String DETAILS_FRAGMENT = "detailsFragment";
    public static final String NONE = "None";
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
