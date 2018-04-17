package com.example.bysted.gameloop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Bysted on 16-04-2018.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private MainThread thread;

    private Bitmap bitmap;

    private Player player;
    private Point playerPoint;

    public GamePanel (Context context )
    {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);

        //instantiates a new player
        player = new Player( bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icontrundle), new Rect(100,100,100,100));
        //where the player should spawn, on the screen
        playerPoint = new Point(100,100);

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height)
    {

    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder)
    {
        thread = new MainThread(getHolder(), this);
        //make gameloop start running
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
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int) event.getX(),(int) event.getY());
        }


        //always detect touch
        return true;
        //return super.onTouchEvent(event);
    }

    public void Update ()
    {
        player.Update(playerPoint);

    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        //fills the whole canvas(screen) with the color you specify
        canvas.drawColor(Color.WHITE);
        player.Draw(canvas);
    }
}
