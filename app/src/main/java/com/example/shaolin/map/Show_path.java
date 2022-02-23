package com.example.shaolin.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import java.util.ArrayList;


public class Show_path {
    Surface_creator surface;
    //String[][] init_map;
    String[] path;

    private Paint line,passed_line,circle,wide_line,option_rect,Text,ok_button;
    ArrayList x_hold=new ArrayList();
    ArrayList y_hold=new ArrayList();
    Interpolation interpolation;
    int circl_locator;
    int line_anim;
    Direction direction;
    Context context;
    MediaPlayer media_left;
    MediaPlayer media_right;
    MediaPlayer media_straight;
    int player_memory=-1;
    Show_button button;
    float H,W;

    public Show_path(Surface_creator surface, Context context)
    {
        this.surface=surface;
        //this.init_map=surface.init_map;
        this.path=surface.path;
        line=new Paint();
        line.setAntiAlias(true);
        line.setColor(Color.BLUE);
        line.setStrokeWidth(35);

        passed_line=new Paint();
        passed_line.setAntiAlias(true);
        passed_line.setColor(Color.RED);
        passed_line.setStrokeWidth(35);

        //paint.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));

        circle=new Paint();
        circle.setAntiAlias(true);
        circle.setColor(Color.WHITE);
        circle.setStyle(Paint.Style.FILL);

        //paint.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));

        wide_line=new Paint();
        wide_line.setAntiAlias(true);
        wide_line.setColor(Color.RED);
        wide_line.setStrokeWidth(50);

        ok_button=new Paint();
        ok_button.setAntiAlias(true);
        ok_button.setColor(Color.RED);
        ok_button.setStrokeWidth(50);

        Text=new Paint();
        Text.setColor(Color.WHITE);
        Text.setTextSize(180);

        option_rect=new Paint();
        option_rect.setAntiAlias(true);
        option_rect.setColor(Color.GRAY);
        option_rect.setStrokeWidth(50);

        //paint.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));
        interpolation=new Interpolation();
        direction=new Direction();
        circl_locator=0;
        line_anim=3;
        media_left=MediaPlayer.create(context,R.raw.left);
        media_right=MediaPlayer.create(context,R.raw.right);
        media_straight=MediaPlayer.create(context,R.raw.straight);

        button=new Show_button(surface);

        W=surface.sized_map.getWidth();
        H=surface.sized_map.getHeight();
    }
    public void path(Canvas canvas) {
        int das = line_anim;
        x_hold.clear();
        y_hold.clear();

        final int savestate = canvas.save();
        //need to optimize the scaling elsewhere
        canvas.scale(surface.scal_x,surface.scal_y);
        //canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(surface.sized_map, 0, 0, null);
        //canvas.drawCircle(500,500,200,null);

        surface.new_map = re_map(surface.init_map, surface.orig_width, surface.orig_hight, surface.sized_map.getWidth(), surface.sized_map.getHeight());
        canvas.drawColor(Color.TRANSPARENT);

        //region Starting drwaing

        if(surface.first_menu_button)
        {
            canvas.drawRect((int) (W * 0f), H * 0.9f, (int) (W * 1f), (int) H * 1f, ok_button);
            canvas.drawText("Menu",W*0.3f,H*0.97f,Text);
        }
        if(!surface.taking_input)
        {
            //can.restoreToCount(savestate);
            x_hold.add(chkx(surface.new_map, path[0]));
            y_hold.add(chky(surface.new_map, path[0]));
            for (int i = 0; i < path.length - 1; i++) {
                int x1 = chkx(surface.new_map, path[i]);
                int y1 = chky(surface.new_map, path[i]);
                int x2 = chkx(surface.new_map, path[i + 1]);
                int y2 = chky(surface.new_map, path[i + 1]);
                x_hold.add(x2);
                y_hold.add(y2);
                //canvas.drawLine(x1, y1, x2, y2, line);
            }
            interpolation.interpol(x_hold, y_hold, 16); // x coordinate of path, y coordinate of path, distance between point will be created


        // drawing dashed line code
        for(int i=0;i<interpolation.point_x.size()-1;i++)
        {
            int x1=interpolation.point_x.get(i).hashCode();
            int y1=interpolation.point_y.get(i).hashCode();
            int x2=interpolation.point_x.get(i+1).hashCode();
            int y2=interpolation.point_y.get(i+1).hashCode();
            /*if(das==3)
            {
                canvas.drawLine(x1,y1,x2,y2,line);
                das=0;
            }*/
            if(i<circl_locator)
            {
                canvas.drawLine(x1,y1,x2,y2,passed_line);
            }
            else
            {
                canvas.drawLine(x1,y1,x2,y2,line);
            }



            das++;
        }

            if (circl_locator >= interpolation.point_x.size())
            {
                circl_locator = 0;
            }
            canvas.drawCircle(interpolation.point_x.get(circl_locator).hashCode(), interpolation.point_y.get(circl_locator).hashCode(), 25, circle);
            int user_position = direction.trace_position(interpolation.point_x, interpolation.point_y, interpolation.point_x.get(circl_locator).hashCode(), interpolation.point_y.get(circl_locator).hashCode(), 10);
            int direc = direction.show_direction(interpolation.point_x, interpolation.point_y, user_position, 2, 100, 30);
            System.out.println(direc);
            if (player_memory != direc) {
                player_memory = direc;
                switch (direc) {
                    case 0:
                        media_straight.start();
                        break;
                    case 1:
                        media_left.start();
                        break;
                    case 2:
                        media_right.start();
                        break;
                    default:
                }
            }
            circl_locator = circl_locator + 3;
            line_anim--;
            if (line_anim == 0) {
                line_anim = 3;
            }

        }
        //endregion

        if(surface.taking_input)
        {
            if(surface.path_update)
            {
                this.path=null;
                this.path=surface.path;
                surface.path_update=false;
                surface.taking_input=false;
            }
            if(surface.sec_introduction)
            {
                button.introbox(canvas,"Select the source point");
            }
            else if(surface.sec_secintro)
            {
                button.introbox(canvas,"Select the Destination point");
            }
            if(surface.sec_show_firstchoice_confirm_box||surface.sec_show_secondchoice_confirm_box)
            {
                button.confirmationbox(canvas,surface.init_map[surface.touched_node][3]);
            }
            if(surface.menu_bar)
            {
                button.bar(canvas);
            }
            //canvas.drawRect(0,0,200,200,line);
            if(surface.show_first_option)
            {
                canvas.drawRect((int) (W*0.15f),H*0.05f,(int) ( W*0.85f),(int) H*0.18f,option_rect);
                canvas.drawRect((int) (W*0.15f),H*0.9f,(int) ( W*0.85f),(int) H*1f,ok_button);
                canvas.drawText("Select Source",W*0.2f,H*0.08f,Text);
                canvas.drawText("You choose: "+surface.init_map[surface.choice[0]][5],W*0.2f,H*0.15f,Text);
                canvas.drawText("Done",W*0.3f,H*0.97f,Text);
                button.firstoption(canvas);
            }
            if(surface.show_second_option)
            {
                canvas.drawRect((int) (W*0.15f),H*0.05f,(int) ( W*0.85f),(int) H*0.18f,option_rect);
                canvas.drawRect((int) (W*0.15f),H*0.9f,(int) ( W*0.85f),(int) H*1f,ok_button);
                canvas.drawText("Select Destination",W*0.2f,H*0.08f,Text);
                canvas.drawText("You choose: "+surface.init_map[surface.choice[1]][5],W*0.2f,H*0.15f,Text);
                canvas.drawText("Done",W*0.3f,H*0.97f,Text);
                button.firstoption(canvas);
            }
        }
        canvas.restoreToCount(savestate);
    }
    private int chkx(String[][] x,String n)
    {
        int y=0;
        for(int i=0;i<x.length;i++)
        {
            if(n.matches(x[i][0]))
            {
                y= Integer.parseInt(x[i][1]);
                break;
            }
        }
        return y;
    }
    private int chky(String[][] x,String n)
    {
        int y=0;
        for(int i=0;i<x.length;i++)
        {
            if(n.matches(x[i][0]))
            {
                y= Integer.parseInt(x[i][2]);
                break;
            }
        }
        return y;
    }
    public String[][] re_map(String[][] x,int a1,int b1,int a2,int b2)
    {
        String[][] z=new String[x.length][4];
        int y=0;
        for(int i=0;i<x.length;i++)
        {
            int b=Integer.parseInt(x[i][1]);
            int a=(a2*b)/a1;
            z[i][1] = Integer.toString(a);
            b=Integer.parseInt(x[i][2]);
            a=(b2*b)/b1;
            z[i][2] = Integer.toString(a);
            z[i][0]=x[i][0];
            // z[i][4]=x[i][4];
        }
        return z;
    }
}