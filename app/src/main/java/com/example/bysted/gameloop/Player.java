package com.example.bysted.gameloop;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements GameObject  {
    private Rect rectangle;
    private int color;
    private Bitmap bitmap;
    private float left, right;

    int width, height;

    public Player (Bitmap bitmapplayer)
    {
        //this.rectangle = player;
        this.bitmap = bitmapplayer;
        this.color = color;
    }

    @Override
    public void Draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        //canvas.drawRect(rectangle, paint );


        canvas.drawBitmap(bitmap, canvas.getWidth()/2, 0, paint);

        /*bitmap.getHeight();
        bitmap.getWidth();*/

    }

    @Override
    public void Update()
    {

    }

    public void Update(Point point)
    {
        rectangle.set(point.x - rectangle.width()/2,point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2 );
        //bitmap.setPixel(point.x , point.y, Color.WHITE);

    }
}
