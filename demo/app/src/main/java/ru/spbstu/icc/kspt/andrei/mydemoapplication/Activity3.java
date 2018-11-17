package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class Activity3 extends Activity {
    public static final int RESULT_TO_1 = RESULT_FIRST_USER + 0;
    private static final int RESULT_TO_2 = RESULT_FIRST_USER + 1;
    private static final int RESULT_TO_3 = RESULT_FIRST_USER + 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        findViewById(R.id.back_1).setOnClickListener(this::onBack1Clicked);
        findViewById(R.id.back_2).setOnClickListener(this::onBack2Clicked);
    }

    private void onBack2Clicked(View view) {
        finish();
    }

    private void onBack1Clicked(View view) {
        setResult(RESULT_TO_1);
        finish();
    }
}
