package com.example.shaolin.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Show_button {

    Surface_creator sur;
    //Canvas canvas;
    private Paint line;
    private Paint Rect;
    private Paint Text;
    private Paint Text1;
    private Paint first_option,ok_button;
    float H,W;

    public Show_button(Surface_creator surface)
    {
        this.sur=surface;
        //this.canvas=canvas;
        line=new Paint();
        line.setColor(Color.rgb(38,103,135));
        line.setStrokeWidth(15);

        ok_button=new Paint();
        ok_button.setAntiAlias(true);
        ok_button.setColor(Color.RED);
        ok_button.setStrokeWidth(50);

        Rect=new Paint();
        Rect.setColor(Color.GRAY);
        Rect.setStrokeWidth(15);

        Text=new Paint();
        Text.setColor(Color.WHITE);
        Text.setTextSize(170);

        first_option=new Paint();
        first_option.setColor(Color.BLACK);
        first_option.setStrokeWidth(15);

        Text1=new Paint();
        Text1.setColor(Color.RED);
        Text1.setTextSize(200);

        W=sur.sized_map.getWidth();
        H=sur.sized_map.getHeight();
    }

    public void bar(Canvas canvas)
    {
        canvas.drawRect(0,0,W*0.75f,H,line);
        canvas.drawRect(0,0,W*0.75f,H*0.1f,ok_button);
        //canvas.drawRoundRect( W*0.75f*0.25/2,H*0.7/4,0.625*W,0.275*H,10,10,line);
        canvas.drawRect( W*0.075f,H*0.175f,0.65f*W,0.275f*H,Rect);
        canvas.drawRect( W*0.075f,H*0.375f,0.65f*W,0.475f*H,Rect);
        canvas.drawText("Select selection type",W*0.075f,0.075f*H,Text);
        canvas.drawText("Select from List",W*0.75f*0.31f/2f,0.23f*H,Text);
        canvas.drawText("Select from Map",W*0.75f*0.31f/2f,0.45f*H,Text);
        //draw image to show button
    }

    public  void confirmationbox(Canvas canvas, String x)
    {
        System.out.println("Showing the confirmation box");
        canvas.drawRect(W*0.2f,H*0.35f ,W*0.9f,H*0.5f,Rect);
        canvas.drawRect(W*0.2f,H*0.5f ,W*0.55f,H*0.6f,Rect);
        canvas.drawRect(W*0.55f,H*0.5f ,W*0.9f,H*0.6f,Rect);
        canvas.drawText("You Choose:", W*0.25f,H*0.45f,Text);
        canvas.drawText(x,W*0.35f,H*0.5f,Text);
        canvas.drawText("Done",W*0.25f,H*0.55f,Text);
        canvas.drawText("Cancel",W*0.6f,H*0.55f,Text);
    }

    public void introbox(Canvas canvas, String x)
    {
        canvas.drawRect(W*0f,H*0.1f,W*1f,H*0.2f,ok_button);
        //canvas.drawRoundRect( W*0.75f*0.25/2,H*0.7/4,0.625*W,0.275*H,10,10,line);
        canvas.drawText(x,W*0.1f,0.13f*H,Text);
    }
/*
    public void firstoption(Canvas canvas)
    {
        android.graphics.Rect rect_src=new Rect();
        android.graphics.Rect rect_dest=new Rect();
        float w = W*0.7f, h = H*0.7f;
        float dh=h/10;
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap((int) w, (int) (dh*sur.node_list.length), conf); // this creates a MUTABLE bitmap
        Canvas can = new Canvas(bmp);
        can.drawColor(Color.WHITE);

        for (int i=0;i<sur.node_list.length-1;i++)
        {
            can.drawLine(0,dh*i,w,dh*i,line);
            can.drawText(sur.node_list[i],200,dh*(i+1)-100,Text1);
        }
        rect_src.set(0,(int) (sur.track_x_source_choice*dh),(int) w,(int) (sur.track_x_source_choice*dh+h));
        rect_dest.set((int) (w*0.214f),(int) (H*0.2f),(int) (w*1.214f),(int) (H*0.9));
        canvas.drawBitmap(bmp,rect_src,rect_dest,null);
        bmp.recycle();
    }*/
    public void firstoption(Canvas canvas)
    {
        android.graphics.Rect rect_src=new Rect();
        android.graphics.Rect rect_dest=new Rect();
        float w = W*0.7f, h = H*0.7f;
        float dh=h/10;
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap((int) w, (int) h, conf); // this creates a MUTABLE bitmap
        Canvas can = new Canvas(bmp);
        can.drawColor(Color.WHITE);
        for (int i=0;i<10-1;i++)
        {
            can.drawLine(0,dh*i,w,dh*i,line);
            can.drawText(sur.node_list[i+sur.track_x_source_choice][0],200,dh*(i+1)-100,Text1);
        }
        rect_src.set(0,0,(int) w,(int) h);
        rect_dest.set((int) (w*0.214f),(int) (H*0.2f),(int) (w*1.214f),(int) (H*0.9));
        canvas.drawBitmap(bmp,rect_src,rect_dest,null);
        bmp.recycle();
    }
}