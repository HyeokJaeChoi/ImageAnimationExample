package com.example.hyeok.imageanimationexample;

import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements View.OnTouchListener{

    private static final String TAG = "ProfileActivity";

    ImageView profileBackgroundImage;
    CircleImageView profileUserImage;
    ConstraintLayout profileContainer;
    ConstraintLayout profileContent;

    private int defaultBackgroundHeight;
    private int defaultContentHeight;
    private int defaultUserImageHeight;
    private int defaultUserImageY;
    private int previousFingerPosition = 0;

    Display display;
    Point displaySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileBackgroundImage = (ImageView)findViewById(R.id.profile_background);
        profileUserImage = (CircleImageView)findViewById(R.id.profile_user_image);
        profileContainer = (ConstraintLayout)findViewById(R.id.profile_container);
        profileContent = (ConstraintLayout)findViewById(R.id.profile_content);

        display = getWindowManager().getDefaultDisplay();
        displaySize = new Point();
        display.getSize(displaySize);

        initBackgroundImage();
        initProfileImage();

        profileContainer.setOnTouchListener(this);
    }

    public void initBackgroundImage(){
        Picasso.get().load(R.drawable.profile_background).into(profileBackgroundImage);
    }

    public void initProfileImage(){
        Picasso.get().load(R.drawable.profile_image).into(profileUserImage);
    }

    public void expandImageAllScreen(View view){
        Intent intent = new Intent(ProfileActivity.this, FullScreenImageActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int Y = (int)event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN :
                defaultBackgroundHeight = profileBackgroundImage.getHeight();
                defaultContentHeight = profileContent.getHeight();
                defaultUserImageHeight = profileUserImage.getHeight();
                defaultUserImageY = (int)profileUserImage.getY();
                previousFingerPosition = Y;
                Log.d(TAG, "ActionDown" + defaultBackgroundHeight + " " + defaultContentHeight + " " + Y);
                break;
            case MotionEvent.ACTION_UP :
                Log.d(TAG, "ActionUp");
                if((profileBackgroundImage.getHeight() > defaultBackgroundHeight) && (profileContent.getHeight() < defaultContentHeight)){
                    finish();
                    break;
                }
            case MotionEvent.ACTION_MOVE :
                Log.d(TAG, "ActionMove Y" + " " + (Y - previousFingerPosition));
                profileBackgroundImage.getLayoutParams().height = Math.max(defaultBackgroundHeight, Math.min(displaySize.y, profileBackgroundImage.getHeight() + (Y - previousFingerPosition)));
                profileContent.getLayoutParams().height = (int)Math.min(defaultContentHeight, Math.max(0f, profileContent.getHeight() - (Y - previousFingerPosition)));
                profileUserImage.setY(Math.max(defaultUserImageY, Math.min(displaySize.y, profileUserImage.getY() + (Y - previousFingerPosition))));
                profileBackgroundImage.requestLayout();
                profileContent.requestLayout();
                profileUserImage.requestLayout();
                previousFingerPosition = Y;
                Log.d(TAG, "ActionMove" + profileBackgroundImage.getHeight() + " " + profileContent.getHeight());
                break;
        }
        return true;
    }
}
