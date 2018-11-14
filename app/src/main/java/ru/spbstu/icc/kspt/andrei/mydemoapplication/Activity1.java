package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class Activity1 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        findViewById(R.id.create_2).setOnClickListener(this::onCreate2Clicked);
        findViewById(R.id.create_3).setOnClickListener(this::onCreate3Clicked);
    }

    private void onCreate2Clicked(View view) {
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
    }

    private void onCreate3Clicked(View view) {
        Intent i = new Intent(this, Activity3.class);
        startActivity(i);
    }
}
