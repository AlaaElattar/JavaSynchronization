import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;



public class Device extends Thread {
    private String type;
    private String deviceName;
    //private semaphore s;
    public Router router;
    public static FileWriter file;
    private JFrame frame;
    private JPanel panel;
    private boolean isDisconnected;
    private int color;

    public Device(String n, String t, Router r, FileWriter file){
        deviceName = n;
        type = t;
        //this.s = s;
        router = r;
        this.file = file;
        deviceFrame(n, t);


        isDisconnected = false;
        color = 0;
    }



    public String getDeviceName() {
        return deviceName;
    }

    public String getType() {
        return type;
    }

    private void connect(Router router){
        try {
            router.addDevice(this,file);
        }catch (IOException e){
            e.printStackTrace ();
        }

    }

    private void disconnect(Router router){
        try{
            router.removeDevice(this,file);
            isDisconnected = true;
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }

    @Override
    public void run(){
        this.connect(router);
        connectedDeviceGui(this);



        while (true){
            System.out.print("");
            if (isDisconnected)
                break;
        }
    }

    private void deviceFrame(String n, String t){
        frame = new JFrame(n + " (" + t +")");
        frame.setPreferredSize(new Dimension(500, 400));
        frame.setLocation(500, 300);
        frame.setBackground(Color.LIGHT_GRAY);

        panel = new JPanel();
        frame.getContentPane();

        JLabel waiting = new JLabel("Waiting for Connection...");
        waiting.setFont(new Font("Verdana", Font.BOLD, 16));
        panel.add(waiting);
        waiting.setBounds(130, 100, 300, 100);

        panel.setLayout(null);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }

    public void connectedDeviceGui(Device device){
        panel.removeAll();

        JButton login = new JButton("Log in");
        login.setBounds(190, 130, 100, 70);
        login.setFont(new Font("Verdana", Font.BOLD, 18));
        device.panel.add(login);
        panel.setBackground(Color.white);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    device.router.logIn (device, device.file);
                    device.logInDeviceGui(device);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.setLayout(null);
    }

    private void logInDeviceGui(Device device){
        panel.removeAll();

        JButton perform = new JButton("Perform Online Activity");
        perform.setFont(new Font("Verdana", Font.BOLD, 16));
        perform.setBounds(130, 100, 250, 70);
        panel.add(perform);
        panel.setBackground(Color.LIGHT_GRAY);
        JButton logOut = new JButton("Log out");
        logOut.setFont(new Font("Verdana", Font.BOLD, 16));
        logOut.setBounds(180, 180, 130, 70);
        panel.add(logOut);

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                device.disconnect(router);
            }
        });

        perform.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    router.performActivity (device, device.file);

                    Thread.sleep(300);
                    if(device.color == 1){
                        device.panel.setBackground(Color.LIGHT_GRAY);
                        device.color = 0;
                    }else{
                        device.panel.setBackground(Color.WHITE);
                        device.color = 1;
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });


        panel.setLayout(null);
    }
}
