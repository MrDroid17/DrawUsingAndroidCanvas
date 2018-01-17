package com.kumar.save_plus.kumar_lazy.canvas03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    AnimationActivityLayout animationActivityLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationActivityLayout= new AnimationActivityLayout(this);
        setContentView(animationActivityLayout);


    }

    @Override
    protected void onPause() {
        super.onPause();
        animationActivityLayout.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        animationActivityLayout.resume();
    }
}
