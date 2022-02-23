package com.example.shaolin.map;

import java.util.ArrayList;

public class Interpolation {
    int total_point=0;
    ArrayList x_main;
    ArrayList y_main;
    ArrayList<Integer> point_x=new ArrayList<Integer>();//
    ArrayList<Integer> point_y=new ArrayList<Integer>();
    public Interpolation()
    {
    }
    public void interpol(ArrayList x_held, ArrayList y_held, int small_fraction)
    {
        int x1,x2;
        int y1,y2;
        point_x.clear();
        point_y.clear();
        for (int i=0; i<x_held.size()-1;i++)
        {
            x1=x_held.get(i).hashCode();
            x2=x_held.get(i+1).hashCode();
            y1=y_held.get(i).hashCode();
            y2=y_held.get(i+1).hashCode();
           double length=Math.sqrt((double)(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))));
            int count= (int) length/small_fraction;
            point(x1,y1,x2,y2,small_fraction,count,length);
        }
        point_x.add(x_held.get(x_held.size()-1).hashCode());
        point_y.add(y_held.get(y_held.size()-1).hashCode());

    }

   public void point(int x1,int y1, int x2, int y2, int small_fraction, int count, double length)
   {
       point_x.add(x1);
       point_y.add(y1);
       int a;
       int x,y;
       for(int i=0;i<count-1;i++)
       {
           a=(i+1)*small_fraction;
           x=(int) ((x1*(length-a)+(x2*a))/length);
           y=(int) ((y1*(length-a)+(y2*a))/length);
           point_x.add(x);
           point_y.add(y);
       }
   }
}
