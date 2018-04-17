package com.example.bysted.gameloop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements IDrawUpdate {
    private Rect rectangle;
    private int color;
    private Bitmap bitmap;

    int width, height;

    public Player (Bitmap bitmapplayer, Rect player)
    {
        this.rectangle = player;
        this.bitmap = bitmapplayer;
        this.color = color;
    }

    @Override
    public void Draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        //canvas.drawRect(rectangle, paint );


        canvas.drawBitmap(bitmap, 10, 10, paint);


    }

    @Override
    public void Update()
    {

    }

    public void Update(Point point)
    {
        //rectangle.set(point.x - rectangle.width()/2,point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2 );

    }
}
