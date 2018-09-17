package com.example.hyeok.imageanimationexample;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullScreenImageActivity extends AppCompatActivity implements View.OnTouchListener{

    private static final String TAG = "FullScreenImageActivity";

    ConstraintLayout container;
    ImageView fullScreenImage;

    private int previousFingerPosition = 0;
    private int defaultContainerHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        container = (ConstraintLayout)findViewById(R.id.profile_user_image_full_screen_container);
        fullScreenImage = (ImageView)findViewById(R.id.profile_user_image_full_screen);

        initFullScreenImage();
        container.setOnTouchListener(this);
    }

    public void initFullScreenImage(){
        Picasso.get()
                .load(R.drawable.profile_image)
                .noFade()
                .fit()
                .centerInside()
                .into(fullScreenImage);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int Y = (int)event.getRawY();

        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN :
                defaultContainerHeight = container.getHeight();
                previousFingerPosition = Y;
                break;
            case MotionEvent.ACTION_UP :
                if(container.getY() > 0)
                    finish();
                break;
            case MotionEvent.ACTION_MOVE :
                container.setY(Math.max(0f, Math.min(defaultContainerHeight, container.getY() + (Y - previousFingerPosition))));
                container.requestLayout();
                previousFingerPosition = Y;
                break;
        }
        return true;
    }
}
