package com.example.bysted.gameloop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Toast;

public class Player extends AppCompatActivity implements IDrawUpdate {

    // the actual bitmap
    private Bitmap bitmap;
    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    //the x and y coordinate
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

    // if droid is touched/picked up
    private boolean touched;
    public boolean isTouched() {
        return touched;
    }
    public void setTouched(boolean touched) {
        this.touched = touched;
    }


    //Gyroscope sensors
    SensorEventListener gyroscopeSensorListener;
    Sensor gyroscopeSensor;
    SensorManager sensorManager;

    private Rect sourceRect;    // the rectangle to be drawn from the animation bitmap
    private int spriteWidth;
    private int spriteHeight;


    public Player (Bitmap bitmapplayer)
    {
        //this.rectangle = player;
        this.bitmap = bitmapplayer;
        this.x = 500;
        this.y = 500;
        spriteWidth = bitmap.getWidth();
        spriteHeight = bitmap.getHeight();
        sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);

    }

    @Override
    public void Draw(Canvas canvas)
    {
        Rect rect = new Rect(getX()- (bitmap.getWidth()/2) , getY()- (bitmap.getHeight()/2), getX() + spriteWidth/2, getY() + spriteHeight/2);
        canvas.drawBitmap(bitmap, sourceRect , rect, null);
    }

    public void handleActionDown(int eventX, int eventY)
    {
        //check if we are touching inside of the player bitmap
        if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2)))
        {
            if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2)))
            {
                //player touched
                setTouched(true);
            } else
                {
                setTouched(false);
                }
        } else
            {
                setTouched(false);
            }
    }


    @Override
    public void Update()
    {

    }

    public void SensorMethodGyroscope()
    {

        //creates sensormanager to get gyroscope
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);



        // Create a listener
        gyroscopeSensorListener = new SensorEventListener()
        {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent)
            {
                //asks about the z-axe
                if(sensorEvent.values[2] > 1f)
                { // anticlockwise
                    Toast.makeText(getApplicationContext(), "Left", Toast.LENGTH_SHORT).show();

                } else if(sensorEvent.values[2] < -1f)
                { // clockwise
                    Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i)
            {
            }
        };
        // Register the listener
        sensorManager.registerListener(gyroscopeSensorListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
}
