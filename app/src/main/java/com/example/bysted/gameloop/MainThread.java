package com.example.bysted.gameloop;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Bysted on 16-04-2018.
 */
// the main thread (the meat of the game)
public class MainThread extends Thread
{
    public static final int MAX_FPS = 30;
    private double averageFPS;
    //interface to someone holding a display surface. Allows you to control the surface size and format, edit the pixels in the surface, and monitor changes to the surface.
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;

    private boolean running;
    //used to draw with
    public static Canvas canvas;

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run()
    {
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while (running)
        {
            //the build in clock
            startTime = System.nanoTime();
            canvas = null;

            // try locking the canvas for exclusive pixel editing on the surface
            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    //the meat of the game. calls update and draw
                    this.gamePanel.Update();
                    this.gamePanel.draw(canvas);
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }finally
            {
                if (canvas != null)
                {
                    try
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            //gets time in nano. divide by million to get time in milliseconds (converting from NanoSec. to MiliSec.)
            timeMillis = (System.nanoTime() - startTime)/ 1000000;
            waitTime = targetTime - timeMillis;
            try
            {
                //capping the framerate
                if (waitTime > 0)
                {
                    this.sleep(waitTime);
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS)
            {
                averageFPS = 1000/((totalTime/frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                //prints out the fps to the system (logcat)
                System.out.println(averageFPS);
            }
        }
    }
}
