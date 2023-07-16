package com.sxt;
import javax.security.auth.callback.TextInputCallback;
import javax.swing.*;
import  java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.lang.Thread.sleep;



public class TTS {

    public static String program_file_path = "C:\\Users\\86158\\Desktop\\Java&Python_web_crawler_12306";    //revise it to THIS PROGRAM'S PATH OF YOUR COMPUTER.
    public static String python_interpreter_path = "C:\\Users\\86158\\miniconda3\\python.exe";               //revise it to YOUR PYTHON'S INTEPRETER PATH.
    String imfor=new String("");

    private static String getTemplateContent() throws Exception{
        File file = new File(program_file_path+"\\data.txt");
        if(!file.exists()){
            return null;
        }
        FileInputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        String str =new String(bytes);
//        System.out.print(str);
        return str ;
    }

    int s_change=0;
    int e_change=0;
    int summit_state=0;
    int TicketNum=0;
    String []Ticket=new String[1000];
    String Year=new String("2023");
    String Month=new String("1");
    String Day=new String("1");
    String start=new String("无");
    String end=new String("无");
    String text=new String("无");
    Frame frame=new Frame("ticket searching system");
    Dialog d1=new Dialog(frame,"Please select start place",true);
    Dialog d2=new Dialog(frame,"Please select destination",true);
    Dialog d3=new Dialog(frame,"tickets searched",true);

    Button b1=new Button("Start Place");
    Button b2=new Button("Destination");
    Button b3=new Button("Search");
    Button b4=new Button(("Submit"));//未用
    Label b_d1_input=new Label("Just press X on upper right after chosen.");Label b_d2_input=new Label("Just press X on upper right after chosen.");
    Button []city_d1=new Button[33];
    Button []city_d2=new Button[33];

    Panel p1=new Panel();
    Panel p2=new Panel();
    Panel p3=new Panel();Panel p4=new Panel();Panel p5=new Panel();Panel p6=new Panel();//空白区域
    Panel d1_p3=new Panel(); Panel d2_p3=new Panel();//空白区域
    Panel p7=new Panel();Panel p8=new Panel();
    Panel p9=new Panel();
    Panel p10= new Panel();
    Panel p11= new Panel();
    Panel p_d1 =new Panel();Panel p_d2 =new Panel();
    //!!!
    Panel p_d3_0=new Panel();Panel p_d3_1=new Panel();Panel p_d3_2=new Panel();Panel p_d3_3=new Panel();
    Panel p_d3_4=new Panel();Panel p_d3_5=new Panel();Panel p_d3_6=new Panel();Panel p_d3_7=new Panel();
    Panel p_d3_8=new Panel();Panel p_d3_9=new Panel();Panel p_d3_10=new Panel();Panel p_d3_11=new Panel();
    Panel p_d3_12=new Panel();Panel p_d3_13=new Panel();Panel p_d3_14=new Panel();Panel p_d3_15=new Panel();
    Panel p_d3_16=new Panel();Panel p_d3_17=new Panel();
    Label[]TicketImf0=new Label[100];Label[]TicketImf1=new Label[100];Label[]TicketImf2=new Label[100];
    Label[]TicketImf3=new Label[100];Label[]TicketImf4=new Label[100];Label[]TicketImf5=new Label[100];
    Label[]TicketImf6=new Label[100];Label[]TicketImf7=new Label[100];Label[]TicketImf8=new Label[100];
    Label[]TicketImf9=new Label[100];Label[]TicketImf10=new Label[100];Label[]TicketImf11=new Label[100];
    Label[]TicketImf12=new Label[100];Label[]TicketImf13=new Label[100];Label[]TicketImf14=new Label[100];
    Label[]TicketImf15=new Label[100];Label[]TicketImf16=new Label[100];Label[]TicketImf17=new Label[100];
    ///!!!!

    Panel d1_topic=new Panel();Panel d2_topic=new Panel();Panel d3_topic=new Panel();
    Panel d1_input=new Panel();Panel d2_input=new Panel();
    Panel fake_s3=new Panel();
    ScrollPane s1=new ScrollPane();
    ScrollPane s2=new ScrollPane();
    ScrollPane s3=new ScrollPane();


    Label t1=new Label("Welcome to Ticket Searching System");Label t2=new Label("note: please press 'submit' button first if you finished your selection, then wait 10 seconds and press 'search'.");
    Label t3=new Label("hot-cities");Label t4=new Label("hot-cities");
    Label d3_s=new Label();Label d3_e=new Label();Label d3_t=new Label();
    Label year=new Label(".");Label month=new Label(".");Label day=new Label(".");



    Choice yearchooser=new Choice();
    Choice monthchooser=new Choice();
    Choice daychooser=new Choice();

    TextField td1=new TextField("other cities");
    TextField td2=new TextField("other cities");
    String[] hot_city=new String[]{"北京","上海","重庆","长沙","长春","成都","福州","广州","天津","贵阳","呼和浩特","哈尔滨"
            ,"合肥","杭州","海口","济南","昆明","拉萨","兰州","南宁","南京","南昌","沈阳","石家庄","太原","乌鲁木齐","武汉","西宁","西安"
            ,"银川","郑州","深圳","厦门"};
    int hot_city_len=33;
    TICKET []real_ticket;

    public void Wlistener()
    {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        d1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                d1.setVisible(false);
            }
        });
        d2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                d2.setVisible(false);
            }
        });
        d3.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                d3.setVisible(false);
                d3_topic.remove(d3_s);
                d3_topic.remove(d3_e);
                d3_topic.remove(d3_t);
                d3_topic.repaint();

                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_0.remove(TicketImf0[i]);
                    p_d3_0.repaint();
                }
                fake_s3.remove(p_d3_0);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_1.remove(TicketImf1[i]);
                    p_d3_1.repaint();
                }
                fake_s3.remove(p_d3_1);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_2.remove(TicketImf2[i]);
                    p_d3_2.repaint();
                }
                fake_s3.remove(p_d3_2);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_3.remove(TicketImf3[i]);
                    p_d3_3.repaint();
                }
                fake_s3.remove(p_d3_3);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_4.remove(TicketImf4[i]);
                    p_d3_4.repaint();
                }
                fake_s3.remove(p_d3_4);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_5.remove(TicketImf5[i]);
                    p_d3_5.repaint();
                }
                fake_s3.remove(p_d3_5);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_6.remove(TicketImf6[i]);
                    p_d3_6.repaint();
                }
                fake_s3.remove(p_d3_6);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_7.remove(TicketImf7[i]);
                    p_d3_7.repaint();
                }
                fake_s3.remove(p_d3_7);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_8.remove(TicketImf8[i]);
                    p_d3_8.repaint();
                }
                fake_s3.remove(p_d3_8);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_9.remove(TicketImf9[i]);
                    p_d3_9.repaint();
                }
                fake_s3.remove(p_d3_9);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_10.remove(TicketImf10[i]);
                    p_d3_10.repaint();
                }
                fake_s3.remove(p_d3_10);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_11.remove(TicketImf11[i]);
                    p_d3_11.repaint();
                }
                fake_s3.remove(p_d3_11);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_12.remove(TicketImf12[i]);
                    p_d3_12.repaint();
                }
                fake_s3.remove(p_d3_12);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_13.remove(TicketImf13[i]);
                    p_d3_13.repaint();
                }
                fake_s3.remove(p_d3_13);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_14.remove(TicketImf14[i]);
                    p_d3_14.repaint();
                }
                fake_s3.remove(p_d3_14);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_15.remove(TicketImf15[i]);
                    p_d3_15.repaint();
                }
                fake_s3.remove(p_d3_15);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_16.remove(TicketImf16[i]);
                    p_d3_16.repaint();
                }
                fake_s3.remove(p_d3_16);
                for(int i=0;i<TicketNum+1;i++)
                {
                    p_d3_17.remove(TicketImf17[i]);
                    p_d3_17.repaint();
                }
                fake_s3.remove(p_d3_17);



                fake_s3.repaint();
                s3.remove(fake_s3);
                s3.repaint();
                d3.remove(s3);
                d3.repaint();
                d3.remove(d3_topic);
                d3.repaint();
                s_change=0;
                e_change=0;
                summit_state=0;
                TicketNum=0;
            }
        });

    }
    public void Tlistener()
    {
        td1.addTextListener(new TextListener(){
            @Override
            public void textValueChanged(TextEvent e) {
                text=td1.getText();
            }
        });
        td2.addTextListener(new TextListener(){
            @Override
            public void textValueChanged(TextEvent e) {
                text=td2.getText();
            }
        });
    }
    void Blistener()
    {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d1.setVisible(true);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d2.setVisible(true);
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(s_change!=0&&e_change!=0&&summit_state==1)
                {d3.setVisible(true);  }
            }
        });

        for(int i=0;i<hot_city_len;i++)
        {
            int j=i;
            city_d1[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(start!=hot_city[j]){
                        start=hot_city[j];
                        s_change=1;
                    }
                }
            });
        }
        for(int i=0;i<hot_city_len;i++)
        {
            int j=i;
            city_d2[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(end!=hot_city[j]){
                        end=hot_city[j];
                        e_change=1;
                    }
                }
            });
        }
        /*
        b_d1_input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start!=text)
                {
                    start=text;
                    s_change=1;

                }
            }
        });
        b_d2_input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(end!=text)
                {
                    end=text;
                    e_change=1;
                }
            }
        });
        */
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                summit_state=1;
                if(s_change+e_change==2&&summit_state==1)
                {

                    BufferedWriter out = null;
                    try {
                        out = new BufferedWriter(new FileWriter("config.txt"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        out.write(start+"\n"); //This is to input 'start/end' to config.txt, for python to use.
                        out.write(end+"\n");
                        out.write(Year+"\n");
                        out.write(Integer.parseInt(Month)<10? '0'+Month+"\n": Month+"\n");
                        out.write(Integer.parseInt(Day)<10? '0'+Day+"\n": Day+"\n");
  //                      out.write(Month+"\n");
  //                      out.write(Day);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        out.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    Process proc;
                    try {
                        String s;
                        proc = Runtime.getRuntime().exec(python_interpreter_path +' '+ program_file_path + "\\web_crawler.py");
                        System.out.print("Searching, please wait......\n");
    //                    Dialog d5=new Dialog(frame,"Searching, please wait......",true);
                        sleep(20000);
                        System.out.print("Searching completed, press 'search' to have a look.\n");


                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }



                    try {
                        imfor=getTemplateContent();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    TicketCount_E();
                    d3Interface();
                }
            }
        });
    }
    void CListener()
    {
        yearchooser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Year=yearchooser.getSelectedItem();
            }
        });
        monthchooser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Month=monthchooser.getSelectedItem();
            }
        });
        daychooser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Day=daychooser.getSelectedItem();

            }
        });
    }
    void mainInterface()
    {
        yearchooser.add("2023");
        yearchooser.add("2024");
        yearchooser.add("2025");
        monthchooser.add("1"); monthchooser.add("2"); monthchooser.add("3"); monthchooser.add("4");
        monthchooser.add("5"); monthchooser.add("6"); monthchooser.add("7"); monthchooser.add("8");
        monthchooser.add("9"); monthchooser.add("10"); monthchooser.add("11"); monthchooser.add("12");
        daychooser.add("1"); daychooser.add("2"); daychooser.add("3"); daychooser.add("4");
        daychooser.add("5"); daychooser.add("6"); daychooser.add("7"); daychooser.add("8");
        daychooser.add("9"); daychooser.add("10"); daychooser.add("11"); daychooser.add("12");
        daychooser.add("13"); daychooser.add("14"); daychooser.add("15"); daychooser.add("16");
        daychooser.add("17"); daychooser.add("18"); daychooser.add("19"); daychooser.add("20");
        daychooser.add("21"); daychooser.add("22"); daychooser.add("23"); daychooser.add("24");
        daychooser.add("25"); daychooser.add("26"); daychooser.add("27"); daychooser.add("28");
        daychooser.add("29"); daychooser.add("30");
        p11.add(yearchooser);p11.add(year);p11.add(monthchooser);p11.add(month);p11.add(daychooser);p11.add(day);
 //       p9.add(t5);p10.add(t6);
        p7.add(t1);p8.add(t2);
        t1.setSize(400,400);

        frame.setLocation(500,100);
        frame.setSize(900,600);
        frame.setVisible(true);

        d1.setBounds(200,200,600,400);
        d2.setBounds(200,200,600,400);
        d3.setBounds(200,200,1800,1200);


        frame.add(p1,BorderLayout.NORTH);
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        p1.add(p7);
        p1.add(p8);
        p1.add(p9);
        p1.add(p10);
        p1.add(p11);

        p2.setLayout(new GridLayout(4,1,10,20));
        p2.add(b1);
        p2.add(b2);
        p2.add(b4);
        p2.add(b3);

        frame.add(p2);
        frame.add(p3,BorderLayout.WEST);
        frame.add(p4,BorderLayout.EAST);
        frame.add(p5,BorderLayout.SOUTH);
        frame.repaint();
        frame.setVisible(true);


    }
    void d1Interface()
    {
        d1_topic.add(t3);
        d1.add(d1_topic,BorderLayout.NORTH);
        p_d1.setLayout(new BoxLayout(p_d1,BoxLayout.Y_AXIS));
        for(int i=0;i<hot_city_len;i++)
        {
            city_d1[i]=new Button(hot_city[i]);
            p_d1.add(city_d1[i],BorderLayout.WEST);
        }
        d1_input.add(td1);
        d1_input.add(b_d1_input);
        s1.add(p_d1,BorderLayout.CENTER);
        d1.add(d1_p3,BorderLayout.WEST);
        d1.add(s1);
        d1.add(d1_input,BorderLayout.SOUTH);
    }
    void d2Interface()
    {
        d2_topic.add(t4);
        d2.add(d2_topic,BorderLayout.NORTH);
        p_d2.setLayout(new BoxLayout(p_d2,BoxLayout.Y_AXIS));
        for(int i=0;i<hot_city_len;i++)
        {
            city_d2[i]=new Button(hot_city[i]);
            p_d2.add(city_d2[i],BorderLayout.WEST);
        }

        s2.add(p_d2,BorderLayout.CENTER);
        d2.add(d2_p3,BorderLayout.WEST);
        d2.add(s2);
        d2_input.add(td2);
        d2_input.add(b_d2_input);
        d2.add(d2_input,BorderLayout.SOUTH);
    }
    void d3Interface()
    {
        //System.out.println(s_change+e_change);
        if(s_change==1&&e_change==1) {
            d3_s = new Label("start from:" + start);
            d3_e = new Label("destination is:" + end);
            d3_t =new Label("train start time:"+Year+"."+Month+"."+Day+".");
            d3_topic.add(d3_s);
            d3_topic.add(d3_e);
            d3_topic.add(d3_t);
            d3.add(d3_topic, BorderLayout.NORTH);

            p_d3_0.setLayout(new BoxLayout(p_d3_0,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf0[i]=new Label("num");
                else TicketImf0[i]=new Label(real_ticket[i-1].number);
                //System.out.println(real_ticket[i].number+i);
                p_d3_0.add(TicketImf0[i]);
            }

            p_d3_1.setLayout(new BoxLayout(p_d3_1,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf1[i]=new Label("Start");
                else TicketImf1[i]=new Label(real_ticket[i-1].start+"站");
                //System.out.println(real_ticket[i].number+i);
                p_d3_1.add(TicketImf1[i]);
            }

            p_d3_2.setLayout(new BoxLayout(p_d3_2,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf2[i]=new Label("Destination");
                else TicketImf2[i]=new Label(real_ticket[i-1].end+"站");
                //System.out.println(real_ticket[i].number+i);
                p_d3_2.add(TicketImf2[i]);
            }

            p_d3_3.setLayout(new BoxLayout(p_d3_3,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf3[i]=new Label("start-time");
                else TicketImf3[i]=new Label(real_ticket[i-1].start_time);
                //System.out.println(real_ticket[i].number+i);
                p_d3_3.add(TicketImf3[i]);
            }
            p_d3_4.setLayout(new BoxLayout(p_d3_4,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf4[i]=new Label("arrive-time");
                else TicketImf4[i]=new Label(real_ticket[i-1].end_time);
                //System.out.println(real_ticket[i].number+i);
                p_d3_4.add(TicketImf4[i]);
            }
            p_d3_5.setLayout(new BoxLayout(p_d3_5,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf5[i]=new Label("duration");
                else TicketImf5[i]=new Label(real_ticket[i-1].spend_time);
                //System.out.println(real_ticket[i].number+i);
                p_d3_5.add(TicketImf5[i]);
            }
            p_d3_6.setLayout(new BoxLayout(p_d3_6,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf6[i]=new Label("situation");
                else TicketImf6[i]=new Label(real_ticket[i-1].state);
                //System.out.println(real_ticket[i].number+i);
                p_d3_6.add(TicketImf6[i]);
            }
            p_d3_7.setLayout(new BoxLayout(p_d3_7,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf7[i]=new Label("business-class");
                else TicketImf7[i]=new Label(real_ticket[i-1].seat[0]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_7.add(TicketImf7[i]);
            }
            p_d3_8.setLayout(new BoxLayout(p_d3_8,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf8[i]=new Label("first-class");
                else TicketImf8[i]=new Label(real_ticket[i-1].seat[1]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_8.add(TicketImf8[i]);
            }
            p_d3_9.setLayout(new BoxLayout(p_d3_9,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf9[i]=new Label("second-class");
                else TicketImf9[i]=new Label(real_ticket[i-1].seat[2]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_9.add(TicketImf9[i]);
            }
            p_d3_10.setLayout(new BoxLayout(p_d3_10,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf10[i]=new Label("advanced-soft-sleeper");
                else TicketImf10[i]=new Label(real_ticket[i-1].seat[3]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_10.add(TicketImf10[i]);
            }
            p_d3_11.setLayout(new BoxLayout(p_d3_11,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf11[i]=new Label("soft-sleeper");
                else TicketImf11[i]=new Label(real_ticket[i-1].seat[4]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_11.add(TicketImf11[i]);
            }
            p_d3_12.setLayout(new BoxLayout(p_d3_12,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf12[i]=new Label("sleeper");
                else TicketImf12[i]=new Label(real_ticket[i-1].seat[5]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_12.add(TicketImf12[i]);
            }
            p_d3_13.setLayout(new BoxLayout(p_d3_13,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf13[i]=new Label("hard-sleeper\n" +
                        "second-sleeper");
                else TicketImf13[i]=new Label(real_ticket[i-1].seat[6]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_13.add(TicketImf13[i]);
            }
            p_d3_14.setLayout(new BoxLayout(p_d3_14,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf14[i]=new Label("soft-seat");
                else TicketImf14[i]=new Label(real_ticket[i-1].seat[7]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_14.add(TicketImf14[i]);
            }
            p_d3_15.setLayout(new BoxLayout(p_d3_15,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf15[i]=new Label("hard-seat");
                else TicketImf15[i]=new Label(real_ticket[i-1].seat[8]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_15.add(TicketImf15[i]);
            }
            p_d3_16.setLayout(new BoxLayout(p_d3_16,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf16[i]=new Label("no-seat");
                else TicketImf16[i]=new Label(real_ticket[i-1].seat[9]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_16.add(TicketImf16[i]);
            }
            p_d3_17.setLayout(new BoxLayout(p_d3_17,BoxLayout.Y_AXIS));
            for(int i=0;i<TicketNum+1;i++)
            {
                if(i==0) TicketImf17[i]=new Label("others");
                else TicketImf17[i]=new Label(real_ticket[i-1].seat[10]);
                //System.out.println(real_ticket[i].number+i);
                p_d3_17.add(TicketImf17[i]);
            }

            fake_s3.add(p_d3_0,BorderLayout.CENTER);
            fake_s3.add(p_d3_1,BorderLayout.CENTER);
            fake_s3.add(p_d3_2,BorderLayout.CENTER);
            fake_s3.add(p_d3_3,BorderLayout.CENTER);
            fake_s3.add(p_d3_4,BorderLayout.CENTER);
            fake_s3.add(p_d3_5,BorderLayout.CENTER);
            fake_s3.add(p_d3_6,BorderLayout.CENTER);
            fake_s3.add(p_d3_7,BorderLayout.CENTER);
            fake_s3.add(p_d3_8,BorderLayout.CENTER);
            fake_s3.add(p_d3_9,BorderLayout.CENTER);
            fake_s3.add(p_d3_10,BorderLayout.CENTER);
            fake_s3.add(p_d3_11,BorderLayout.CENTER);
            fake_s3.add(p_d3_12,BorderLayout.CENTER);
            fake_s3.add(p_d3_13,BorderLayout.CENTER);
            fake_s3.add(p_d3_14,BorderLayout.CENTER);
            fake_s3.add(p_d3_15,BorderLayout.CENTER);
            fake_s3.add(p_d3_16,BorderLayout.CENTER);
            fake_s3.add(p_d3_17,BorderLayout.CENTER);
            s3.add(fake_s3,BorderLayout.CENTER);

            d3.add(s3);

        }
    }
    void TicketCount_E()
    {
        int flag=0;int kinds=11;int i=0,j=0,k=0;//i piao j piaoduiying k zuowei duiying
        TICKET[]ttt;
        ttt=new TICKET[100];
        for(int ii=0;ii<100;ii++)
        {

            ttt[ii]=new TICKET();
        }


//        String imf=imfor;
        String imf= new String(imfor);
//        System.out.print(imf);
        String []the_spilt=imf.split("预订");
        for(String s:the_spilt)
        {
            String []sp=s.split("[\n,\"复\n\",\"智复静\n\",\"复静\n\",\"智复\n\",\"铺\n\"]");
            for(String sb:sp)
            {
                if(flag==0 && sb.compareTo("")!=0 && sb.compareTo("\r")!=0)
                {
                    if(j==0) ttt[i].number=sb;
                    if(j==1) ttt[i].start=sb;
                    if(j==2) ttt[i].end=sb;
                    if(j==3) ttt[i].start_time=sb;
                    if(j==4) ttt[i].end_time=sb;
                    if(j==5) ttt[i].spend_time=sb;
                    if(j==6) ttt[i].state=sb;

                    // System.out.println(sb);
                    j++;
                }
                if(flag==1)
                {
                    if(sb.length()<5&&sb.compareTo("")!=0)
                    {
                        // System.out.println(sb);
                        ttt[i].seat[k]=sb;
                        kinds--;k++;
                    }
                    else
                    {
                        String[]seat=sb.split(" ");
                        for(String seat_sb:seat)
                        {
                            ttt[i].seat[k]=seat_sb;kinds--;k++;
                            // System.out.println(seat_sb);
                        }
                    }

                }
                if(kinds==0)
                {
                    k=0;j=0;flag=0;kinds=11;i++;
                }
                if(sb.compareTo("当日到达\r")==0||sb.compareTo("次日到达\r")==0||sb.compareTo("两日到达\r")==0)
                {
                    flag=1;
                }

            }
            TicketNum++;
        }

        real_ticket=ttt;
    }
    public void init()
    {
        mainInterface();
        d1Interface();
        d2Interface();
        Wlistener();
        Blistener();
        Tlistener();
        CListener();
    }



    public static void main(String args[])
    {
        new TTS().init();

    }

}
//month year day end start 全局变量
//String imfor=new String("G103\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "06:20\n" +
//            "11:58\n" +
//            "05:38\n" +
//            "当日到达\n" +
//            "6\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G1\n" +
//            "复静\n" +
//            "北京南\n" +
//            "上海\n" +
//            "07:00\n" +
//            "11:29\n" +
//            "04:29\n" +
//            "当日到达\n" +
//            "候补 候补 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G105\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "07:17\n" +
//            "13:03\n" +
//            "05:46\n" +
//            "当日到达\n" +
//            "4\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G107\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "07:25\n" +
//            "13:12\n" +
//            "05:47\n" +
//            "当日到达\n" +
//            "12\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G109\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "07:45\n" +
//            "13:48\n" +
//            "06:03\n" +
//            "当日到达\n" +
//            "6\n" +
//            "有 候补 -- -- -- -- -- -- -- -- 预订\n" +
//            "G3\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海\n" +
//            "08:00\n" +
//            "12:32\n" +
//            "04:32\n" +
//            "当日到达\n" +
//            "候补 候补 候补 -- -- -- -- -- -- -- -- 预订\n" +
//            "G111\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "08:16\n" +
//            "14:11\n" +
//            "05:55\n" +
//            "当日到达\n" +
//            "3\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G113\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "08:39\n" +
//            "14:59\n" +
//            "06:20\n" +
//            "当日到达\n" +
//            "候补 有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G5\n" +
//            "智复静\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "09:00\n" +
//            "13:37\n" +
//            "04:37\n" +
//            "当日到达\n" +
//            "候补 候补 候补 -- -- -- -- -- -- -- -- 预订\n" +
//            "G115\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "09:10\n" +
//            "14:48\n" +
//            "05:38\n" +
//            "当日到达\n" +
//            "候补 候补 候补 -- -- -- -- -- -- -- -- 预订\n" +
//            "G117\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "09:20\n" +
//            "14:55\n" +
//            "05:35\n" +
//            "当日到达\n" +
//            "候补 候补 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G119\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "09:24\n" +
//            "15:32\n" +
//            "06:08\n" +
//            "当日到达\n" +
//            "3\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G7\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "10:00\n" +
//            "14:35\n" +
//            "04:35\n" +
//            "当日到达\n" +
//            "候补 候补 候补 -- -- -- -- -- -- -- -- 预订\n" +
//            "G121\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "10:05\n" +
//            "15:42\n" +
//            "05:37\n" +
//            "当日到达\n" +
//            "候补 候补\n" +
//            "12\n" +
//            "-- -- -- -- -- -- -- -- 预订\n" +
//            "G123\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "10:20\n" +
//            "16:26\n" +
//            "06:06\n" +
//            "当日到达\n" +
//            "7\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G125\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "10:46\n" +
//            "16:50\n" +
//            "06:04\n" +
//            "当日到达\n" +
//            "候补 有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G9\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "11:00\n" +
//            "15:37\n" +
//            "04:37\n" +
//            "当日到达\n" +
//            "候补 候补 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G127\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "11:05\n" +
//            "17:08\n" +
//            "06:03\n" +
//            "当日到达\n" +
//            "11\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G129\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "11:18\n" +
//            "17:38\n" +
//            "06:20\n" +
//            "当日到达\n" +
//            "候补 候补 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G133\n" +
//            "北京南\n" +
//            "上海\n" +
//            "11:49\n" +
//            "18:02\n" +
//            "06:13\n" +
//            "当日到达\n" +
//            "候补 候补\n" +
//            "16\n" +
//            "-- -- -- -- -- -- -- -- 预订\n" +
//            "1461\n" +
//            "北京\n" +
//            "上海\n" +
//            "11:55\n" +
//            "06:47\n" +
//            "18:52\n" +
//            "次日到达\n" +
//            "-- -- -- -- 候补 -- 候补 -- 有 有 -- 预订\n" +
//            "G11\n" +
//            "智复静\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "12:00\n" +
//            "16:38\n" +
//            "04:38\n" +
//            "当日到达\n" +
//            "候补 候补 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G135\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "12:12\n" +
//            "18:21\n" +
//            "06:09\n" +
//            "当日到达\n" +
//            "候补 有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G137\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "12:47\n" +
//            "18:56\n" +
//            "06:09\n" +
//            "当日到达\n" +
//            "候补\n" +
//            "14\n" +
//            "有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G13\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海\n" +
//            "13:00\n" +
//            "17:35\n" +
//            "04:35\n" +
//            "当日到达\n" +
//            "7\n" +
//            "候补 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G139\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "13:04\n" +
//            "19:06\n" +
//            "06:02\n" +
//            "当日到达\n" +
//            "1\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G141\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "13:34\n" +
//            "19:24\n" +
//            "05:50\n" +
//            "当日到达\n" +
//            "5\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G15\n" +
//            "智复静\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "14:00\n" +
//            "18:33\n" +
//            "04:33\n" +
//            "当日到达\n" +
//            "候补 候补 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G143\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "14:08\n" +
//            "20:07\n" +
//            "05:59\n" +
//            "当日到达\n" +
//            "11\n" +
//            "有 候补 -- -- -- -- -- -- -- -- 预订\n" +
//            "G145\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "14:14\n" +
//            "20:12\n" +
//            "05:58\n" +
//            "当日到达\n" +
//            "14\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G147\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "14:27\n" +
//            "20:43\n" +
//            "06:16\n" +
//            "当日到达\n" +
//            "10\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G17\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "15:00\n" +
//            "19:34\n" +
//            "04:34\n" +
//            "当日到达\n" +
//            "候补\n" +
//            "1\n" +
//            "有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G149\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "15:08\n" +
//            "21:10\n" +
//            "06:02\n" +
//            "当日到达\n" +
//            "候补 候补 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G151\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "15:49\n" +
//            "22:12\n" +
//            "06:23\n" +
//            "当日到达\n" +
//            "11\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G19\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "16:00\n" +
//            "20:28\n" +
//            "04:28\n" +
//            "当日到达\n" +
//            "4\n" +
//            "20\n" +
//            "有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G153\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "16:30\n" +
//            "22:27\n" +
//            "05:57\n" +
//            "当日到达\n" +
//            "1\n" +
//            "16\n" +
//            "有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G157\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "16:53\n" +
//            "23:13\n" +
//            "06:20\n" +
//            "当日到达\n" +
//            "3\n" +
//            "10\n" +
//            "有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G21\n" +
//            "复静\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "17:00\n" +
//            "21:18\n" +
//            "04:18\n" +
//            "当日到达\n" +
//            "7\n" +
//            "18\n" +
//            "有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G159\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "17:19\n" +
//            "23:18\n" +
//            "05:59\n" +
//            "当日到达\n" +
//            "11\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G161\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "17:33\n" +
//            "23:32\n" +
//            "05:59\n" +
//            "当日到达\n" +
//            "11\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G23\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海\n" +
//            "18:00\n" +
//            "22:43\n" +
//            "04:43\n" +
//            "当日到达\n" +
//            "10\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G25\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海虹桥\n" +
//            "18:04\n" +
//            "22:58\n" +
//            "04:54\n" +
//            "当日到达\n" +
//            "13\n" +
//            "有 有 -- -- -- -- -- -- -- -- 预订\n" +
//            "G27\n" +
//            "智复静\n" +
//            "北京南\n" +
//            "上海\n" +
//            "19:00\n" +
//            "23:29\n" +
//            "04:29\n" +
//            "当日到达\n" +
//            "6\n" +
//            "14\n" +
//            "有 -- -- -- -- -- -- -- -- 预订\n" +
//            "Z281\n" +
//            "北京丰台\n" +
//            "上海南\n" +
//            "19:17\n" +
//            "09:46\n" +
//            "14:29\n" +
//            "次日到达\n" +
//            "-- -- -- -- 候补 -- 候补 -- 有 有 -- 预订\n" +
//            "D701\n" +
//            "复\n" +
//            "北京\n" +
//            "上海\n" +
//            "19:22\n" +
//            "07:25\n" +
//            "12:03\n" +
//            "次日到达\n" +
//            "-- -- 有 -- 有 -- 有 -- -- 有 -- 预订\n" +
//            "D709\n" +
//            "复\n" +
//            "北京南\n" +
//            "上海\n" +
//            "19:36\n" +
//            "07:54\n" +
//            "12:18\n" +
//            "次日到达\n" +
//            "-- -- 有 -- 有 -- 有 -- -- -- -- 预订\n" +
//            "T109\n" +
//            "北京\n" +
//            "上海\n" +
//            "20:05\n" +
//            "11:00\n" +
//            "14:55\n" +
//            "次日到达\n" +
//            "-- -- -- -- 候补 -- 有 -- 有 有 -- 预订\n" +
//            "D705\n" +
//            "复\n" +
//            "北京\n" +
//            "上海\n" +
//            "21:21\n" +
//            "09:27\n" +
//            "12:06\n" +
//            "次日到达\n" +
//            "-- -- 有 -- 有 -- 有 -- -- 有 -- 预订");
