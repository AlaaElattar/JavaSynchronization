import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

enum Status{
    connected, waiting, disconnected
}

class Device extends Thread {
    private String type;
    private String deviceName;
    private semaphore s;
    public static Router router;
    public static FileWriter file;

    public Device(String n, String t, semaphore s, Router r, FileWriter file){
        deviceName = n;
        type = t;
        this.s = s;
        router = r;
        this.file = file;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getType() {
        return type;
    }

    private void connect(Router router){
        s.acquire(this);
        router.addDevice(this);
    }

    private void disconnect(Router router){
        router.removeDevice(this);
        s.release(this);
    }

    @Override
    public void run(){
        this.connect(router);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            router.logIn (this,file);
        } catch (IOException e) {
            e.printStackTrace ();
        }

        /**try {
            //Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }**/

        try {
            router.performActivity(this,file);
        } catch (IOException e) {
            e.printStackTrace ();
        }


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.disconnect(router);
    }
}
