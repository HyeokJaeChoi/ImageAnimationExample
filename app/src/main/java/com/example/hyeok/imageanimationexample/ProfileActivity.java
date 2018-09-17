package com.example.hyeok.imageanimationexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity{

    ImageView profileBackgroundImage;
    CircleImageView profileUserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileBackgroundImage = (ImageView)findViewById(R.id.profile_background);
        profileUserImage = (CircleImageView)findViewById(R.id.profile_user_image);

        initBackgroundImage();
        initProfileImage();
    }

    public void initBackgroundImage(){
        Picasso.get().load(R.drawable.profile_background).into(profileBackgroundImage);
    }

    public void initProfileImage(){
        Picasso.get().load(R.drawable.profile_image).into(profileUserImage);
    }

    public void expandImageAllScrean(View view){
        Intent intent = new Intent(ProfileActivity.this, FillUserImageActivity.class);
        startActivity(intent);
    }
}
