package com.example.hyeok.imageanimationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FillUserImageActivity extends AppCompatActivity {

    ImageView fillUserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_user_image);

        fillUserImage = (ImageView)findViewById(R.id.fill_user_image);

        initFullUserImage();
    }

    public void initFullUserImage(){
        Picasso.get().load(R.drawable.profile_image).into(fillUserImage);
    }
}
