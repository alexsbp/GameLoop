package com.example.bysted.gameloop;

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
import android.widget.Toast;

public class Player extends AppCompatActivity implements IDrawUpdate {
    private Rect rectangle;
    private int color;
    private Bitmap bitmap, bit;
    public Bitmap getBitmap() {return bitmap; }
    public void setBitmap (Bitmap b) {bitmap = b; }

    int width, height;

    //Gyroscope sensors
    SensorEventListener gyroscopeSensorListener;
    Sensor gyroscopeSensor;
    SensorManager sensorManager;


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

    public void SensorMethodGyroscope()
    {

        //creates sensormanager to get gyroscope
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        // Create a listener
        gyroscopeSensorListener = new SensorEventListener()


        {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                //asks about the z-axe
                if(sensorEvent.values[2] > 1f) { // anticlockwise

                    /*Matrix matrix = new Matrix();
                    matrix.preScale(-1.0f, 1.0f);
                    bit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);*/

                    bitmap.getPixel(300,300);

                    Toast.makeText(getApplicationContext(), "Left" + bit, Toast.LENGTH_SHORT).show();

                } else if(sensorEvent.values[2] < -1f) { // clockwise

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
