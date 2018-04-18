package com.example.bysted.gameloop;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable rocketAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void StartAnimation(View v)
    {
        ImageView rocketImage = (ImageView) findViewById(R.id.MyImgView);
        rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
        rocketAnimation.start();
    }
    public void StopAnimation(View v)
    {
        rocketAnimation.stop();
    }

    public void StartGame(View v)
    {
            //fullscreen
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
            //new activity
            setContentView(new GamePanel(this));
            Intent intent = new Intent(this, GamePanel.class);
    }
}
