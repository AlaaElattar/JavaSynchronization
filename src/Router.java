import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Router {
    ArrayList<Device> devices;
    int numDevices;
    semaphore s;
    public Router(int num, semaphore s){
        devices = new ArrayList<>(num);
        for (int x = 0; x<5; x++){
            devices.add(null);
        }
        numDevices = num;
        this.s =s;
    }

    public void performActivity(Device device, FileWriter f) throws IOException {
        int i = devices.indexOf(device);
        f.append ("connection " + (i+1) +": " + device.getDeviceName() + " performing online activity \n");
    }

    public void addDevice(Device device, FileWriter f) throws IOException{
        s.acquire(device, f);
        int i = 0;
        for (; i<numDevices; i++){
            if(devices.get(i) == null){
                devices.remove(i);
                devices.add(i, device);
                break;
            }
        }
        f.append("connection " + (i+1) +": " + device.getDeviceName() + " occupied \n");
    }

    public void logIn(Device device, FileWriter f) throws IOException{
        int i = devices.indexOf(device);
        f.append ("connection " + (i+1) +": " + device.getDeviceName() + " login \n");

    }

    public void removeDevice(Device device, FileWriter f)throws IOException{
        int i = devices.indexOf(device);
        devices.remove(device);
        devices.add(i, null);
        f.append("connection " + (i+1) +": " + device.getDeviceName() + " logged out \n");
        s.release();

    }
}
