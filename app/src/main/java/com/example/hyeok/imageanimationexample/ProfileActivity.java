package com.example.hyeok.imageanimationexample;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

public class ProfileActivity extends AppCompatActivity implements View.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appBarLayout.setExpanded(true, true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
