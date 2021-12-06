import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

class Network {

    static ArrayList<JTextField> names = new ArrayList<> ();
    static ArrayList<JTextField> types = new ArrayList<> ();

    public static void GUI(){
        JFrame frame = new JFrame ("Network");
        frame.setSize(900, 900);
        frame.setLocation(500, 300);
        frame.setPreferredSize(new Dimension (1100, 900));
        frame.setBackground(Color.white);

        JPanel panel = new JPanel();
        frame.getContentPane();

        final JTextField text1 = new JTextField();
        text1.setBounds(600, 50, 30, 30);
        panel.add(text1);
        Label l1 = new Label("What is the number of WI-FI Connections?");
        l1.setBounds(110, 50, 400, 30);
        l1.setFont(new Font("Verdana", Font.BOLD, 16));
        panel.add(l1);

        final JTextField text2 = new JTextField();
        text2.setBounds(700, 100, 30, 30);
        panel.add(text2);
        Label l2 = new Label("What is the number of devices Clients want to connect?");
        l2.setBounds(110, 100, 600, 30);
        l2.setFont(new Font("Verdana", Font.BOLD, 16));
        panel.add(l2);

        JButton button1 = new JButton("OK");
        Dimension size1 = button1.getPreferredSize();
        button1.setBounds(900, 100, size1.width, size1.height);
        panel.add(button1);


        button1.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll ();
                panel.setLayout (null);
                panel.setBackground (Color.LIGHT_GRAY);

                int q1 = (int)parseInt (text1.getText ());
                int q2 = (int) parseInt (text2.getText ());

                int x1 = 60, y1 = 50, width1 = 60, heigh1 = 30 ;   //Label
                int x2 = 100,y2 = 50,width2=200, heigh2=30;
                for (int i = 0 ; i < q2 ; i++){


                    Label l1 = new Label ("Name");
                    l1.setBounds (x1,y1,width1,heigh1);
                    l1.setFont(new Font("Verdana", Font.BOLD, 16));
                    panel.add (l1);

                    JTextField j1 = new JTextField ();
                    j1.setBounds (x2,y2,width2,heigh2);
                    panel.add(j1);

                    Label l2 = new Label ("type");
                    l2.setBounds (x1+300,y1,width1,heigh1);
                    l2.setFont(new Font("Verdana", Font.BOLD, 16));
                    panel.add (l2);

                    JTextField j2 = new JTextField ();
                    j2.setBounds (x2+300,y2,width2,heigh2);
                    panel.add(j2);

                    names.add (j1);
                    types.add (j2);

                    y1= y1+90;
                    y2 = y2+90;

                }
                panel.setLayout (null);
            }
        });

        JButton button2 = new JButton("OK");
        Dimension size2 = button1.getPreferredSize();
        button1.setBounds(900, 500, size1.width, size1.height);
        panel.add(button1);
        button1.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = " " ,type = " ";
                for (int i = 0 ; i < names.size () ; i++){
                    name = names.get (i).getText ();
                    type = types.get (i).getText ();

                    


                }

            }
        });






        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(null);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        /**System.out.println("What is the number of WI-FI Connections?");
        Scanner in = new Scanner(System.in);
        int numCon = in.nextInt();
        System.out.println("What is the number of devices Clients want to connect?");
        int numDevices = in.nextInt();

        FileWriter file = new FileWriter("log.out");
        semaphore s = new semaphore(numCon);
        Router router = new Router(numCon);

        ArrayList<Thread> devices = new ArrayList<>();
        for (int i = 0; i < numDevices; i++) {
            String name = in.next();
            String type = in.next();
            Thread t = new Thread(new Device(name, type, s, router, file));
            devices.add(t);
        }

        for (int i = 0; i < numDevices; i++) {
            devices.get(i).start();
            devices.get(i).setPriority(numDevices - i);
        }
        for (int i = 0; i < numDevices; i++) {
            devices.get(i).join();
        }
        file.close();**/

        GUI ();

    }
}
