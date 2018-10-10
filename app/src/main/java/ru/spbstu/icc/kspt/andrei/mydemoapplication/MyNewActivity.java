package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyNewActivity extends Activity {

    public static final String LIFECYCLE = "lifecycle";
    public static final String KEY_COUNTER = "counter";

    private int counter = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNTER, counter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.test);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(KEY_COUNTER);
        }

        Button bn = findViewById(R.id.button);
        if (bn != null) {
            bn.setOnClickListener(this::myBnClicked);
            refreshBnText(bn);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LIFECYCLE, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LIFECYCLE, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LIFECYCLE, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LIFECYCLE, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LIFECYCLE, "onDestroy");
    }

    public void myBnClicked(View view) {
        Button bn = (Button) view;
        counter++;
        refreshBnText(bn);

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123"));
        startActivity(intent);
    }

    private void refreshBnText(Button bn) {
        String title = getResources().getString(R.string.title_click_count, counter);
        bn.setText(title);
    }
}
