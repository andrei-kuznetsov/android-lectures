package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class Activity2 extends Activity {
    private static final int RQ_BN1 = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        findViewById(R.id.create_3).setOnClickListener(this::onCreate3Clicked);
        findViewById(R.id.back_1).setOnClickListener(this::onBack1Clicked);
    }

    private void onBack1Clicked(View view) {
        finish();
    }

    private void onCreate3Clicked(View view) {
        Intent i = new Intent(this, Activity3.class);
        startActivityForResult(i, RQ_BN1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RQ_BN1){
            if (resultCode == Activity3.RESULT_TO_1){
                finish();
            }
        }
    }
}
