package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class DetailsActivity extends Activity {
    public static final String DETAILS_KEY = "ru.spbstu.icc.kspt.andrei.mydemoapplication.articletext";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        FragmentManager mgr = getFragmentManager();
        DetailsFragment detailsFragment =
                (DetailsFragment) mgr.findFragmentById(R.id.details_fragment);
        String text = getIntent().getStringExtra(DETAILS_KEY);
        detailsFragment.setText(text);
    }
}
