package com.example.shaolin.map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Surface_creator extends SurfaceView implements SurfaceHolder.Callback{

    Looping_Thread loop;
    Bitmap initial_map;
    Bitmap sized_map;
    public String[][] init_map;//={{"58","603","1294","0"},{"59","493","1294","0"},{"60","360","1294","0"},{"61","178","1294","3.240-"},{"62","360","1155","3.240A"},{"63","491","1155","3.240B"},{"64","603","1131","0"},{"65","730","1131","3.242"},{"66","959","1131","0"},{"67","849","1131","3.368"},{"68","1102","1131","3.37"},{"69","959","1071","0"},{"70","959","1058","0"},{"71","1017","1057","0"},{"72","852","1067","3.372"},{"73","1017","998","0"},{"74","1123","998","3.374"},{"75","603","1067","0"},{"76","730","1067","3.244"},{"77","603","913","0"},{"78","759","913","3.246"},{"79","1017","928","3.300.6"},{"80","1129","928","3.376"},{"81","1017","727","0"},{"82","955","727","3.300.7"},{"83","1129","727","3.378"},{"84","1017","664","0"},{"85","1129","664","3.380-"},{"86","1017","615","0"},{"87","842","615","3.382"},{"88","603","767","0"},{"89","361","767","3.248"},{"90","361","1056","3.248A"},{"91","122","767","3.248B"},{"92","603","639","0"},{"93","603","630","0"},{"94","603","537","0"},{"95","603","494","0"},{"96","701","537","3.62"},{"97","711","639","3.-E3"},{"98","415","630","3.250-"},{"99","140","630","3.250A"},{"100","457","494","0"},{"101","210","494","3.252"},{"102","210","219","0"},{"103","83","219","0"},{"104","83","330","3.254A"},{"105","83","532","3.254A.1"},{"106","83","80","3.254A.2"},{"107","299","219","0"},{"108","299","182","0"},{"109","299","99","3.254"},{"110","457","182","0"},{"111","457","291","0"},{"112","514","291","3.252A"},{"113","603","182","0"},{"114","603","69","3.200.2"},{"115","603","260","0"},{"116","686","260","0"},{"117","686","371","0"},{"118","737","371","3.398"},{"119","815","260","3.300D"},{"120","815","198","0"},{"121","703","198","0"},{"122","703","95","3.396"},{"123","932","198","0"},{"124","932","89","3.392"},{"125","896","260","0"},{"126","896","376","0"},{"127","847","376","3.394"},{"128","1017","260","0"},{"129","1017","218","0"},{"130","1017","87","0"},{"131","1128","87","3.390-"},{"132","1128","218","3.388"},{"133","1017","394","0"},{"134","1116","394","3.386"},{"135","963","420","3.300.8"},{"136","1017","455","0"},{"137","1114","455","3.384"},{"138","1017","420","0"},{"139","339","3736","202"},{"140","413","3320","206"},{"141","799","2903","210"},{"142","431","2909","212"},{"143","729","2750","214"},{"144","431","2515","216"},{"145","731","2415","218"},{"146","421","2379","220"},{"147","832","2270","222"},{"148","851","2079","224"},{"149","773","1892","226"},{"150","416","1841","228"},{"151","738","1721","230"},{"152","468","1698","232"},{"153","733","1541","236"},{"154","479","1581","238"},{"155","699","3584","302"},{"156","760","3833","304"},{"157","828","3837","306"},{"158","897","3589","308"},{"159","981","3770","310"},{"160","984","3938","311"},{"161","1201","4013","312"},{"162","1224","3886","314"},{"163","1212","3698","316"},{"164","1137","3425","318"},{"165","850","3296","320"},{"166","1124","3325","322"},{"167","1126","3182","324"},{"168","845","3144","326"},{"169","1090","2890","328"},{"170","843","2821","330"},{"171","1131","2784","332"},{"172","1086","2675","334"},{"173","892","2653","336"},{"174","1097","2532","338"},{"175","693","2650","340"},{"176","870","2415","344"},{"177","1089","2357","346"},{"178","1119","2280","348"},{"179","1133","2074","350"},{"180","1136","2003","352"},{"181","1078","1843","354"},{"182","1084","1710","356"},{"183","961","1706","358"},{"184","1082","1605","360"},{"185","1114","1331","362"},{"186","852","1447","363"},{"187","722","1445","364"},{"188","970","3532","3001"},{"189","1022","2784","3003"},{"190","1354","4083","200_1"},{"191","303","3193","206_1"},{"192","364","3125","206_2"},{"193","296","3327","206_3"},{"194","168","3328","206a"},{"195","176","3192","206b"},{"196","365","3046","206c"},{"197","313","2785","212_1"},{"198","162","2791","212a"},{"199","188","2520","216a"},{"200","302","2246","220_1"},{"201","165","2245","220a"},{"202","762","2271","222_1"},{"203","751","2078","224_1"},{"204","692","1816","226_1"},{"205","362","2037","228_1"},{"206","363","2113","228a"},{"207","189","1842","228b"},{"208","671","1753","230_1"},{"209","279","1699","232a"},{"210","1105","4014","312_1"},{"211","674","1677","av"},{"212","700","3323","e1"},{"213","694","3219","e2"},{"214","701","3505","s1"},{"215","602","3725","m1"},{"216","601","3697","m2"},{"217","702","3698","m3"},{"218","762","3698","m4"},{"219","823","3698","m5"},{"220","900","3698","m6"},{"221","1022","3698","m7"},{"222","1101","3698","m8"},{"223","1101","3763","m9"},{"224","1101","3888","m10"},{"225","1101","3936","m11"},{"226","1101","3955","m12"},{"227","1022","3532","m13"},{"228","1022","3421","m14"},{"229","1022","3326","m15"},{"230","1022","3292","m16"},{"231","1022","3185","m17"},{"232","1022","3149","m18"},{"233","962","3149","m19"},{"234","962","3134","m20"},{"235","962","2884","m21"},{"236","962","2823","m22"},{"237","962","2786","m23"},{"238","962","2682","m24"},{"239","962","2533","m25"},{"240","891","2533","m26"},{"241","686","2533","m27"},{"242","603","2533","m28"},{"243","603","2509","m29"},{"244","603","2751","m30"},{"245","603","2898","m31"},{"246","603","3328","m32"},{"247","603","3505","m33"},{"248","963","2418","m34"},{"249","963","2348","m35"},{"250","963","2280","m36"},{"251","963","2074","m37"},{"252","963","2006","m38"},{"253","963","1847","m39"},{"254","603","2417","m40"},{"255","603","2380","m41"},{"256","603","2176","m42"},{"257","603","1839","m43"},{"258","603","1814","m44"},{"259","603","3327","m45"},{"260","754","2176","m46"},{"261","603","1755","m47"},{"262","603","1700","m48"},{"263","603","1671","m49"},{"264","603","1582","m50"},{"265","603","1537","m51"},{"266","603","1324","m52"},{"267","717","1324","m53"},{"268","857","1324","m54"},{"269","961","1324","m55"},{"270","961","1605","m56"},{"271","961","1710","m57"}};
    public String[]  path=null;//={"106","103","102","107","108","110","113","115","116","119","125","128","133","138","136","86","84","82","79","71","70","69","66"};
    public String[][]  node_list=null;//{"106","103","102","107","108","110","113","115","116","119","125","128","133","138","136","86","84","82","79","71","70","69","66"};
    String[][] new_map;
    public int track_x_source_choice=0;
    //public String[]  path={"125","128","133","138","136","86","84","82","79","71","70","69","66"};
    public int orig_width=1354;
    public int orig_hight=4083;
    public int new_width=600;
    public int new_hight=700;
    float surf_width;
    float surf_length;

    float scal_x;
    float scal_y;
    
    //touch flags
    boolean menu_bar=false; // controls the visibility of menu bar
    boolean taking_input=false; // true when source destication input is being collected
    boolean show_first_option=false; // controls the fist input page of select from list method.
    boolean show_second_option=false; // control the second input page of select from list method.
    boolean first_menu_button=false; //controls the button to show menu
    
    boolean touch_on=false; // flag to indicate user already touched the screen
    
    boolean sec_introduction=false;
    boolean sec_firstchoice=false;
    boolean sec_show_firstchoice_confirm_box=false;
    boolean sec_secondchoice=false;
    boolean sec_show_secondchoice_confirm_box=false;
    boolean sec_secintro=false;

    boolean path_update=false;
    
    float py=0;
    long source_input_time;
    long button_click_time;

    public int[] choice={0,0};
    public int touched_node;

    public Surface_creator(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        sized_map=decodeSampledBitmapFromResource(getResources(), R.drawable.map, new_width, new_hight);
        loop=new Looping_Thread(getHolder(),this,context);
        create_init();
    }
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        System.out.println("Surface changed");
        surf_width=getWidth();
        surf_length=getHeight();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry=true;

        while (retry)
        {
            try
            {
                loop.running=false;
                loop.join();
                System.out.println("surface destroyed");
            }
            catch (InterruptedException e)
            {e.printStackTrace();}
            retry=false;
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("Surface starting");
        loop.running=true;
        loop.start();
        surf_width=getWidth();
        surf_length=getHeight();
        scal_x = surf_width / sized_map.getWidth();
        scal_y = surf_length / sized_map.getHeight();
        System.out.println("Surface strt function ends");
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            System.out.println("Touch action starts");
            touch_on=true;
            py=event.getY();
            /*if(event.getY()>1000&&first_menu_button)
            {
                first_menu_button=false;
                System.out.println("Touch action detected: VERY down");
                menu_bar=true;
                taking_input=true;
                try
                {
                    loop.sleep(200);
                }
                catch (Exception e){}
            }*/
        }
        else if(event.getAction()==MotionEvent.ACTION_UP)
        {
            System.out.println("Touch action ends");


            //showing and hiding first menu button

            if(!taking_input&&!first_menu_button)
            {
                first_menu_button=true;
                new Timer().schedule(new firstTask(this), 3000);
                button_click_time= System.currentTimeMillis();
            }
            // touch on fist menu button
            else if(first_menu_button &&( (System.currentTimeMillis()-button_click_time)>1000)&& event.getX(0) > (surf_width * 0.15f) && event.getX(0) < (0.85f * surf_width) && event.getY(0) > (surf_length * 0.9f) && event.getY(0) < (1f * surf_length))
            {
                first_menu_button=false;
                taking_input=true;
                menu_bar=true;
            }

            if(menu_bar&&taking_input)
            {
                if(event.getX(0)>( surf_width*0.09375f) && event.getX(0)<(0.625f*surf_width )&&event.getY(0)>(surf_length*0.175f)&&event.getY(0)<(0.275f*surf_length ))
                {
                    menu_bar=false;
                    show_first_option=true;
                    choice[0]=0;
                    choice[1]=0;
                }
            }

            if(menu_bar&&taking_input&& event.getX(0)>surf_width*0.075f && event.getX(0)< 0.65f*surf_width  && event.getY(0)> surf_length*0.375f && event.getY(0)< surf_length*.475f)
            {
                menu_bar=false;
                sec_introduction=true;
                choice[0]=0;
                choice[1]=0;
                new Timer().schedule(new intro_control(this), 3000);
            }

            // region Flag controll for select from map option
            if(sec_firstchoice)
            {
                sec_firstchoice=false;
                sec_show_firstchoice_confirm_box=true;
                touched_node= which_node(event.getX(),event.getY());
            }
            else if(sec_show_firstchoice_confirm_box&&event.getX()>surf_width *0.3f&&event.getX()<surf_width*0.5f&&event.getY()>surf_length*0.5f &&event.getY()<surf_length*0.6f)
            {
                sec_show_firstchoice_confirm_box=false;
                sec_secintro=true;
                choice[0]=touched_node;
                new Timer().schedule(new intro_control(this), 3000);
            }
            else if(sec_show_firstchoice_confirm_box&&event.getX()>surf_width *0.5f&&event.getX()<surf_width*0.7f&&event.getY()>surf_length*0.5f &&event.getY()<surf_length*0.6f)
            {
                sec_show_firstchoice_confirm_box=false;
                sec_firstchoice=true;
            }

            else if(sec_secondchoice)
            {
                sec_secondchoice=false;
                touched_node= which_node(event.getX(),event.getY());
                sec_show_secondchoice_confirm_box=true;
            }
            else if(sec_show_secondchoice_confirm_box&&event.getX()>surf_width *0.3f&&event.getX()<surf_width*0.5f&&event.getY()>surf_length*0.5f &&event.getY()<surf_length*0.6f)
            {
                sec_show_secondchoice_confirm_box=false;
                choice[1]=touched_node;
                path=calc_route(choice[0],choice[1]);
                path_update=true;
                System.out.println(path);
                //taking_input=false; // this is triggering after path is updated in show_path

            }
            else if(sec_show_secondchoice_confirm_box&&event.getX()>surf_width *0.5f&&event.getX()<surf_width*0.7f&&event.getY()>surf_length*0.5f &&event.getY()<surf_length*0.6f)
            {
                sec_show_secondchoice_confirm_box=false;
                sec_secondchoice=true;
            }
            //endregion


            if(show_first_option&&touch_on)
            {
                if (touch_on && Math.abs(py - event.getY()) < (surf_length / 10)) // selecting source
                {
                    if (event.getX(0) > (surf_width * 0.15f) && event.getX(0) < (0.85f * surf_width) && event.getY(0) > (surf_length * 0.2f) && event.getY(0) < (0.9f * surf_length))
                    {
                        System.out.println("In the region");
                        choice[0] = Integer.parseInt( node_list [(int) ((event.getY()-surf_length*0.2) / ((surf_length*0.7) / 10)) + track_x_source_choice][1]);
                        System.out.println("Selected node: " + node_list[choice[0]]);
                        //show_first_option=false;
                        //show_second_option=true;
                    }
                    if (event.getX(0) > (surf_width * 0.15f) && event.getX(0) < (0.85f * surf_width) && event.getY(0) > (surf_length * 0.9f) && event.getY(0) < (1f * surf_length))
                    {
                        /*try
                        {
                            loop.sleep(2000);
                        }
                        catch (Exception e){}*/
                        show_first_option=false;
                        show_second_option=true;
                        source_input_time= System.currentTimeMillis();
                        track_x_source_choice=0;
                        System.out.println("Taking 1st input");
                    }
                }
            }
            if(show_second_option&&touch_on==true)
            {
                if (touch_on && Math.abs(py - event.getY()) < (surf_length / 10)) // selecting source
                {
                    if (event.getX(0) > (surf_width * 0.15f) && event.getX(0) < (0.85f * surf_width) && event.getY(0) > (surf_length * 0.2f) && event.getY(0) < (0.9f * surf_length))
                    {
                        System.out.println("In the region");
                        choice[1] = Integer.parseInt( node_list [(int) ((event.getY()-surf_length*0.2) / ((surf_length*0.7) / 10)) + track_x_source_choice][1]);
                        System.out.println("Selected node: " + node_list[choice[0]]);
                        //show_first_option=false;
                        //show_second_option=true;
                    }
                    if ((System.currentTimeMillis()-source_input_time)>1000&&event.getX(0) > (surf_width * 0.15f) && event.getX(0) < (0.85f * surf_width) && event.getY(0) > (surf_length * 0.9f) && event.getY(0) < (1f * surf_length)) //Checks if used waits atleast 1s before pressing the second done button
                    {
                        show_second_option=false;
                        path=calc_route(choice[0],choice[1]);
                        path_update=true;
                        System.out.println(path);
                        //taking_input=false;
                        System.out.println("Taking 2nd input");
                    }
                }
            }
            if(touch_on)
            {
                touch_on=false;
            }
        }
        else if(event.getAction()==MotionEvent.ACTION_POINTER_UP)
        {
            System.out.println("Touch action detected: 2nd up");

        }
        else if(event.getAction()==MotionEvent.ACTION_POINTER_DOWN)
        {
            System.out.println("Touch action detected: 2nd down");

        }
        else if(event.getAction()==MotionEvent.ACTION_MOVE)
        {
            System.out.println("Touch action continue");
            if(show_first_option||show_second_option)
            {

                if(touch_on&& py<event.getY())
                {
                    if(track_x_source_choice==0)
                    {

                    }
                    else {track_x_source_choice--;}
                }
                else if (touch_on&& py>event.getY())
                {
                    if(track_x_source_choice==node_list.length-10)
                    {}
                    else
                    {
                        track_x_source_choice++;
                    }
                }
            }
        }
        return true;
    }
    public void update()
    {
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    private int which_node(float a, float b)
    {
        int x,y;
        double dist,lowest_dist=10000;
        int lowest_position=0;
        int x1,y1;

        x=(int) (a/scal_x);
        y=(int) (b/scal_y);
        for(int i=0;i<new_map.length;i++)
        {
            x1=Integer.parseInt(new_map[i][1]);
            y1=Integer.parseInt(new_map[i][2]);
            dist=Math.sqrt((y1 - y) * (y1 - y) + (x1 - x) * (x1 - x));
            if(dist<lowest_dist)
            {
                lowest_dist=dist;
                lowest_position=i;
            }
        }
        System.out.println("selected node"+x+","+y+";"+ new_map[lowest_position][0]+","+new_map[lowest_position][1]+","+new_map[lowest_position][2]+","+init_map[lowest_position][3]);
        return  lowest_position;
    }


    public void create_init()
    {
        String[] parts;
        int size=0;
        InputStream inputStream1 = this.getResources().openRawResource(R.raw.map);

        InputStreamReader inputreader1 = new InputStreamReader(inputStream1);
        BufferedReader buffreader1 = new BufferedReader(inputreader1);

        String line;
        StringBuilder text = new StringBuilder();

        try {

            while (( line = buffreader1.readLine()) != null) {
                size++;
            }
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
        try {
            buffreader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("initializing init_map");
        init_map= new String[size][6];
        node_list=new String[size][2];

        InputStream inputStream = this.getResources().openRawResource(R.raw.map);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        try {
            int i=0,k=0;
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
                parts=line.split("\\|");
                init_map[i][0]=parts[0]; // node number
                init_map[i][1]=parts[1]; // x
                init_map[i][2]=parts[2]; // y
                init_map[i][3]=parts[5]; // node name
                init_map[i][4]=parts[3]; // connected node
                init_map[i][5]=parts[4]; // node name only room
                if(!parts[4].equals("0")) {
                    node_list[k][0] = parts[4];
                    node_list[k][1] = parts[0];
                    k++;
                }
                i++;
            }
        } catch (IOException e) {
        }
    }

    public String[] calc_route(int source, int dest) {
        int src=source;  // mentioning the destination (which is 1 for the given problem)
        int dst=dest;
        int crnt_src=src;
        int crnt_dst=dst;

        //String[][] init_map={{"0","0","0","1,4"},{"1","0","0","0,2"},{"2","0","0","1,5,6,8"},{"3","0","0","4,7,8"},{"4","0","0","0,3,6"},{"5","0","0","2,9"},{"6","0","0","2,4,7"},{"7","0","0","3,6"},{"8","0","0","2,3"},{"9","0","0","3,5"}};
        int nodes=init_map.length;  // number of nodes
        //int[][] con={{0,1,0,0,1,0,0,0,0,0},{1,0,1,0,0,0,0,0,0,0},{0,1,0,0,0,1,1,0,1,0},{0,0,0,0,1,0,0,1,1,1},{1,0,0,1,0,0,1,0,0,0},{0,0,1,0,0,0,0,0,0,1},{0,0,1,0,1,0,0,1,0,0},{0,0,0,1,0,0,1,0,0,0},{0,0,1,1,0,0,0,0,0,0},{0,0,0,1,0,1,0,0,0,0}};  // connection matrix 10*10(showing the connection between nodes)
        //double[][] dist={{0,1,100,100,1,100,100,100,100,100},{1,0,1,100,100,100,100,100,100,100},{100,1,0,100,100,3,1,100,4,100},{100,100,100,0,5,100,100,1,2,1},{1,100,100,5,0,100,1,100,100,100},{100,100,3,100,100,0,100,100,100,1},{100,100,1,100,1,100,0,1,100,100},{100,100,100,1,100,100,1,0,100,100},{100,100,4,2,100,100,100,100,0,100},{100,100,100,1,100,1,100,100,100,0}}; //distance matrix 10*10(showing the distance between nodes)

        int[] visit_src= new int[init_map.length];
        Arrays.fill(visit_src,1); // %%% matrix that keeps record of visited nodes (1 means unvisited)
        int[] visit_dst= new int[init_map.length];
        Arrays.fill(visit_dst,1); // matrix that keeps record of visited nodes (1 means unvisited)

        int[] next_hop_src=new int[init_map.length]; // %%% next hop matrix from source
        Arrays.fill(next_hop_src,-1);
        int[] next_hop_dst=new int[init_map.length];  //%%% next hop matrix from dest
        Arrays.fill(next_hop_dst,-1);

        double[] dist2_src=new double[init_map.length];  //%% distance matrix to destination node 1
        Arrays.fill(dist2_src,100000);
        dist2_src[src] = 0.01;  // %% distance between destination to destination is zero
        double[] dist2_dst =new double[init_map.length];
        Arrays.fill(dist2_dst,100000);
        dist2_dst[dst] = 0.01;

        for(int p = 1;p<=nodes - 1;p++)// %%%%%for loop which will be executed until all nodes are calculated
        {
            String[] parts;
            double min=10000000;
            int min_pos=0;
            double dummy;
            //[k, crnt_src]=min(dist2_src. * visit_src);
            for(int k=0;k<dist2_src.length;k++)
            {
                if(dist2_src[k] * visit_src[k]<min)
                {
                    min=dist2_src[k] * visit_src[k];
                    min_pos=k;
                }
            }
            crnt_src=min_pos;

            if (next_hop_dst[crnt_src]!=-1)
            {
                System.out.println("Source reached a visited destination point");
                break;
            }
            if(crnt_src == crnt_dst)
            {
                System.out.println("Source = Dest");
                break;
            }

            //       [k, crnt_dst]=min(dist2_dst. * visit_dst);
            min=100000000;
            min_pos=0;
            for(int k=0;k<dist2_dst.length;k++)
            {
                if(dist2_dst[k] * visit_dst[k]<min)
                {
                    min=dist2_dst[k] * visit_dst[k];
                    min_pos=k;
                }
            }
            crnt_dst=min_pos;
            if (next_hop_src[crnt_dst]!=-1)
            {
                System.out.println("Destination reached a visited source point");
                crnt_src=crnt_dst;
                break;
            }
            if(crnt_src == crnt_dst)
            {
                System.out.println("Source = destination");
                break;
            }

            parts=init_map[crnt_src][4].split("\\,");
            for (int z = 0;z<=parts.length -1;z++)//%%%%src
            {
                //if (con[crnt_src][z] == 1) //%%%checking if there is a connection between current node and node z
                //{
                dummy=node_dist(crnt_src,Integer.parseInt(parts[z]));
                //dummy=dist[crnt_src][Integer.parseInt(parts[z])];
                if ( dummy + dist2_src[crnt_src] < dist2_src[Integer.parseInt(parts[z])])
                {
                    dist2_src[Integer.parseInt(parts[z])] =  dummy + dist2_src[crnt_src];//%%%updating distance from node z to node 1
                    next_hop_src[Integer.parseInt(parts[z])] = crnt_src;//%%%%updating next hop from node z to node 1
                }
                //}
            }

            parts=init_map[crnt_dst][4].split("\\,");
            for (int z = 0;z<=parts.length-1;z++)// %%%%%dst
            {
                //if (con[crnt_dst][z] == 1) //%%%checking if there is a connection between current node and node z
                //{
                dummy=node_dist(crnt_dst,Integer.parseInt(parts[z]));
                //dummy=dist[crnt_dst][Integer.parseInt(parts[z])];
                if(  dummy + dist2_dst[crnt_dst] < dist2_dst[Integer.parseInt(parts[z])]) {
                    dist2_dst[Integer.parseInt(parts[z])] = dummy + dist2_dst[crnt_dst]; //%%%updating distance from node z to node 1
                    next_hop_dst[Integer.parseInt(parts[z])] = crnt_dst; //%%%%updating next hop from node z to node 1
                }
                //}
            }
            visit_src[crnt_src] = 10000000;  //%%%updating the visited node matrix
            visit_dst[crnt_dst] = 10000000;
        }
        List<String> a=new ArrayList<>();
        List<String> b=new ArrayList<>();
        a.add(0, Integer.toString(crnt_src));
        for (int i=1;i<1000;i++)
        {
            //System.out.println( a.get(i-1));
            a.add(i, Integer.toString(next_hop_src[Integer.parseInt(a.get(i - 1))]));

            if ( Integer.parseInt(a.get(i)) == src)
            {
                break;
            }
        }
        for(int i=0;i<a.size();i++)
        {
            b.add(i,a.get(a.size()-i-1));
            //System.out.println( b.get(i));
        }
        System.out.println("inter");
        for ( int i=b.size();i<1000;i++)
        {
            b.add (i,Integer.toString(next_hop_dst[Integer.parseInt(b.get(i - 1))]));
            //System.out.println( b.get(i));
            if(Integer.parseInt( b.get(i)) == dst) {
                break;
            }
        }

        String[] pum=b.toArray(new String[b.size()]);
        return pum;
    }

    public double node_dist(int a,int b)
    {
        int a1,b1;
        int x,y,x1,y1;
        a1=a;
        b1=b;
        x= Integer.parseInt(init_map[a1][1]);
        y= Integer.parseInt(init_map[a1][2]);
        x1=Integer.parseInt(init_map[b1][1]);
        y1= Integer.parseInt(init_map[b1][2]);
        return Math.sqrt((y1 - y) * (y1 - y) + (x1 - x) * (x1 - x));
    }
}
class firstTask extends TimerTask {

    Surface_creator sur;
    firstTask(Surface_creator sur)
    {
        this.sur=sur;

    }
    @Override

    public void run() {
        sur.first_menu_button=false;
    }
};



class intro_control extends TimerTask {

    Surface_creator sur;
    intro_control(Surface_creator sur)
    {
        this.sur=sur;

    }
    @Override

    public void run() {
        if(sur.sec_introduction)
        {
            sur.sec_introduction = false;
            sur.sec_firstchoice = true;
        }
        else if(sur.sec_secintro)
        {
            sur.sec_secintro=false;
            sur.sec_secondchoice=true;
        }
    }
};