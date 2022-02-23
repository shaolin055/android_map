package com.example.shaolin.map;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class Direction {
    int trac_linelength;
    double sin;
    double cos;
    int orig_x,orig_y;
    /*\\ArrayList<Integer> y_cor;
    ArrayList<Integer> x_cor;*/
    public Direction()

    {
        //x_cor=new ArrayList<Integer>();
        //y_cor=new ArrayList<Integer>();
        trac_linelength=0;
    }
    public int show_direction(ArrayList<Integer> x__cor, ArrayList<Integer> y__cor, int position, int min_deciding_dis, int predic_distance, int buffer_dis)
    {

        /*x_cor.clear();
        y_cor.clear();
        x_cor = x__cor;
        y_cor=y__cor;*/
        ArrayList<Integer> x_cor=new ArrayList<Integer>(x__cor);
        ArrayList<Integer> y_cor=new ArrayList<Integer>(y__cor);
        x__cor.clear();
        y__cor.clear();
        //check if user in starting/ending position in path
        int new_x1,new_x2,new_y1,new_y2;
        int x,y,x1,y1,x2,y2;
        orig_x=x_cor.get(position);
        orig_y=y_cor.get(position);
        for (int i = position; i < x_cor.size()-2; i++)
        {
            if(i==0||(i==x__cor.size()-1))
            {
                continue;
            }

            x=x_cor.get(i);
            y=y_cor.get(i);
            x1=x_cor.get(i-1);
            y1=y_cor.get(i-1);
            x2=x_cor.get(i+1);
            y2=y_cor.get(i+1);
            if(Math.sqrt((orig_y - y) * (orig_y - y) + (orig_x- x) * (orig_x - x))>predic_distance)
            {
                System.out.println("Go straight"+i);
                return 0;
            }
            //transforming axis to current line
           /* if(x1==x)
            {
                sin=1;
                cos=0;
            }
            else if(y1==y)
            {
                sin=0;
                cos=1;
            }
            else {*/
            sin = (y1 - y) / Math.sqrt((y1 - y) * (y1 - y) + (x1 - x) * (x1 - x));
            cos = (x1 - x) / Math.sqrt((y1 - y) * (y1 - y) + (x1 - x) * (x1 - x));
            //}
            //new point
            new_x1=(int) ((x-x)*cos-(y-y)*sin);
            new_y1=(int) ((x-x)*sin-(y-y)*cos);
            new_x2=(int) ((x2-x)*cos-(y2-y)*sin);
            new_y2=(int) ((x2-x)*sin-(y2-y)*cos);
            //check if 90degree
            if(Math.abs(new_y2)>min_deciding_dis)
            {
                if(new_y2<0)
                {
                    System.out.println("Left in" + i);
                    return 1;
                }
                else
                {
                    System.out.println("Right in"+i);
                    return 2;
                }

            }
        }
        System.out.println("If this point is reached, Chapal will suck Saifuls dick");
        return 0;
    }
    public int trace_position(ArrayList<Integer> x__cor, ArrayList<Integer> y__cor, int x,int y,int buffer_dis)
    {
        ArrayList<Integer> x_cor=new ArrayList<Integer>(x__cor);
        ArrayList<Integer> y_cor=new ArrayList<Integer>(y__cor);
        /*x_cor.clear();
        y_cor.clear();*/
        x_cor = x__cor;
        y_cor=y__cor;
        int x1,y1,new_y2;
        double dist,lowest_dist=10000;
        int lowest_position=0;
        for(int i=0;i<x_cor.size();i++)
        {
            x1=x_cor.get(i);
            y1=y_cor.get(i);
            dist=Math.sqrt((y1 - y) * (y1 - y) + (x1 - x) * (x1 - x));
            if(dist<lowest_dist)
            {
                lowest_dist=dist;
                lowest_position=i;
            }
            if(dist<buffer_dis)
            {
                return i;
            }
            /*//instruct to go left right cant write code as we need user direction for deciding the angle between user and path
            sin = (y1 - y) / Math.sqrt((y1 - y) * (y1 - y) + (x1 - x) * (x1 - x));
            cos = (x1 - x) / Math.sqrt((y1 - y) * (y1 - y) + (x1 - x) * (x1 - x));
            //}
            //new point
            //new_x1=(int) ((x-x)*cos-(y-y)*sin);
            //new_y1=(int) ((x-x)*sin-(y-y)*cos);
            //new_x2=(int) ((x2-x)*cos-(y2-y)*sin);
            new_y2=(int) ((x2-x)*sin-(y2-y)*cos);
            //check if 90degree
            if(Math.abs(new_y2)>min_deciding_dis)
            {
                if(new_y2<0)
                {System.out.println("Left in" + i);}
                else {System.out.println("Right in"+i);}
            }*/

        }
        return -1;
    }
}

