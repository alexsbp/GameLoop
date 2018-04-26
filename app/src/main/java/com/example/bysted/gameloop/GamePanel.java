package com.example.bysted.gameloop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Bysted on 16-04-2018.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private MainThread thread;
    public Bitmap bitmap;

    private Player player;
    private Meteor meteor;
    private ArrayList<Meteor> meteorList = new ArrayList<>();

    public GamePanel (Context context )
    {
        super(context);
        //sets the current class (GamePanel) as the handler for the events happening on the actual surface.
        getHolder().addCallback(this);

        //Create the game loop thread
        thread = new MainThread(getHolder(), this);

        //makes our Game Panel focusable, which means it can receive focus so it can handle events.
        setFocusable(true);

        //instantiates a new player
        player = new Player( bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship));
        meteorList.add(new Meteor(bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meteor), 300, -20));
        meteorList.add(new Meteor(bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meteor), 400, -20));
        meteorList.add(new Meteor(bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meteor), 500, -20));
       // meteorList.add(new Meteor(bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meteor), 300, 200));

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height)
    {

    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder)
    {
        //the surface is created and we can safely start the game loop
        thread.setRunning(true);
        thread.start();


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder)
    {
        boolean retry = true;
        while (true)
        {
            try
            {
                thread.setRunning(false);
                thread.join();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            retry = false;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            //setting the player to the position where you are touching
            player.handleActionDown((int)event.getX(), (int)event.getY());

        }
        if (event.getAction() == MotionEvent.ACTION_MOVE)
        {
            // the gestures
            if (player.isTouched())
            {
                // the droid was picked up and is being dragged
                player.setX((int)event.getX());
                player.setY((int)event.getY());
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            // touch was released
            if (player.isTouched())
            {
                player.setTouched(false);
            }
        }
        return true;
    }


    public void Update ()
    {
        player.Update();

        for (Meteor item : meteorList)
        {
            item.Update();
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        //fills the whole canvas(screen) with the color you specify
        canvas.drawColor(Color.WHITE);
        player.Draw(canvas);

        for (Meteor item : meteorList)
        {
            item.Draw(canvas);
        }
    }
}
