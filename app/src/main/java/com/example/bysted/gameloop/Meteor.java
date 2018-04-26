package com.example.bysted.gameloop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alex on 26-04-2018.
 */

public class Meteor extends AppCompatActivity implements IDrawUpdate
{


    private int x, y;
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    private Bitmap bitmap;
    private Rect sourceRect;
    private int spriteWidth;
    private int spriteHeight;

    public Meteor (Bitmap bitmapMeteor, int x, int y)
    {
        this.bitmap = bitmapMeteor;
        this.x = x;
        this.y = y;
        this.spriteWidth = bitmap.getWidth();
        this.spriteHeight = bitmap.getHeight();
        sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);

    }

    @Override
    public void Draw(Canvas canvas)
    {
        Rect rect = new Rect(getX()- (bitmap.getWidth()/2) , getY()- (bitmap.getHeight()/2), getX() + spriteWidth/2, getY() + spriteHeight/2);
        canvas.drawBitmap(bitmap, sourceRect , rect, null);
    }

    @Override
    public void Update()
    {
        if (y >= 700)
        {
            
        }
        y += 20;
    }
}
