package com.kumar.save_plus.kumar_lazy.canvas02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Animation001Activity extends AppCompatActivity {
    Animation001ActivityLayout activityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLayout= new Animation001ActivityLayout(this);
        setContentView(activityLayout);
    }
}
