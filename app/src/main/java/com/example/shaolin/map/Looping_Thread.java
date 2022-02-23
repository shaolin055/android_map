package com.example.shaolin.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by Shaolin on 6/23/2016.
 */
public class Looping_Thread extends Thread{
    Surface_creator surface_creator;
    SurfaceHolder surfaceHolder;
    Show_path show_path;
    public boolean running=false;
    public Canvas canvas;
//    public Paint paint;

    Context context;

    public Looping_Thread(SurfaceHolder surfaceHolder, Surface_creator surface_creator, Context context)
    {
        super();
        this.surface_creator=surface_creator;
        this.surfaceHolder=surfaceHolder;
        show_path=new Show_path(surface_creator,context);
        //paint=new Paint();
        //paint.setColor(Color.BLACK);
        //paint.setStyle(Paint.Style.FILL);
        this.context=context;
    }

    @Override
    public void run() {
        while (running)
        {
            try {

                canvas=this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    //canvas.drawColor(Color.RED);
                    //canvas.drawCircle(500,500,200,paint);
                    show_path.path(canvas);

                }
            }
            catch (Exception e)
            {

            }
            finally {
                if (canvas!=null)
                {
                    try {

                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            try {
                this.sleep(500);
                //surface_creator.sized_map.recycle();
            }
            catch (Exception e){}
            //System.out.println(System.currentTimeMillis()+" Click");
        }
    }
}
