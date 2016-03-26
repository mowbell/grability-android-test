package com.grability.android.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.ly_splash);
        l.clearAnimation();
        l.startAnimation(anim);
    }

}
